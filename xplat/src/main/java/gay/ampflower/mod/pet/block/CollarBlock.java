package gay.ampflower.mod.pet.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.ampflower.mod.pet.item.CollarMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class CollarBlock extends AbstractCollarBlock {
	public static final MapCodec<CollarBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			createSettingsCodec()
		).apply(instance, CollarBlock::new)
	);

	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

	private static final Map<Direction, VoxelShape> FACING_TO_SHAPE =
		COLLAR_EMITTER.toFloorMap(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

	public CollarBlock(final CollarMaterial material, final Settings settings) {
		super(material, settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
	}

	private CollarBlock(final Settings settings) {
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return CODEC;
	}

	public CollarBlock(final CollarMaterial material) {
		this(material, Settings.create());
	}

	@Override
	public boolean canPlaceAt(final BlockState state, final WorldView world, final BlockPos pos) {
		return world.getBlockState(pos.down()).isSolid();
	}

	@Nullable
	@Override
	public BlockState getPlacementState(final ItemPlacementContext ctx) {
		return this.getDefaultState()
			.with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	public BlockState getStateForNeighborUpdate(final BlockState state, final Direction direction, final BlockState neighborState, final WorldAccess world, final BlockPos pos, final BlockPos neighborPos) {
		if (direction == Direction.DOWN && !this.canPlaceAt(state, world, pos)) {
			return Blocks.AIR.getDefaultState();
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
		return FACING_TO_SHAPE.get(state.get(FACING));
	}

	@Override
	public VoxelShape getCollisionShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
		return FACING_TO_SHAPE.get(state.get(FACING));
	}

	@Override
	protected void appendProperties(final StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
