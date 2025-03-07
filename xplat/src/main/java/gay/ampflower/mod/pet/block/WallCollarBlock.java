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
public class WallCollarBlock extends AbstractCollarBlock {
	public static final MapCodec<WallCollarBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			createSettingsCodec()
		).apply(instance, WallCollarBlock::new)
	);
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

	private static final Map<Direction, VoxelShape> FACING_TO_SHAPE =
		COLLAR_EMITTER.toWallMap(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

	public WallCollarBlock(final CollarMaterial material, final Settings settings) {
		super(material, settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
	}

	public WallCollarBlock(final Settings settings) {
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return CODEC;
	}

	public WallCollarBlock(final CollarMaterial material) {
		this(material, Settings.create());
	}

	@Override
	public boolean canPlaceAt(final BlockState state, final WorldView world, final BlockPos pos) {
		return world.getBlockState(pos.offset((state.get(FACING)).getOpposite())).isSolid();
	}

	@Nullable
	@Override
	// Taken from WallSignBlock
	public BlockState getPlacementState(final ItemPlacementContext ctx) {
		BlockState blockState = this.getDefaultState();
		WorldView worldView = ctx.getWorld();
		BlockPos blockPos = ctx.getBlockPos();
		Direction[] directions = ctx.getPlacementDirections();

		for (Direction direction : directions) {
			if (direction.getAxis().isHorizontal()) {
				Direction direction2 = direction.getOpposite();
				blockState = blockState.with(FACING, direction2);
				if (blockState.canPlaceAt(worldView, blockPos)) {
					return blockState;
				}
			}
		}

		return null;
	}

	@Override
	public BlockState getStateForNeighborUpdate(final BlockState state, final Direction direction, final BlockState neighborState, final WorldAccess world, final BlockPos pos, final BlockPos neighborPos) {
		if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
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
