package gay.ampflower.mod.pet.block;

import gay.ampflower.mod.pet.block.entity.CollarBlockEntity;
import gay.ampflower.mod.pet.component.type.GlowingComponent;
import gay.ampflower.mod.pet.item.CollarMaterial;
import gay.ampflower.mod.pet.util.ItemUtils;
import gay.ampflower.mod.pet.util.VoxelEmitter;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public abstract class AbstractCollarBlock extends BlockWithEntity {
	protected static final int
		HALF_SIZE = 3,
		MIN = 8 - HALF_SIZE,
		MAX = 8 + HALF_SIZE,
		HEIGHT = 1;

	protected static final VoxelEmitter COLLAR_EMITTER = VoxelEmitter.ofBlock(MIN, 0, MIN - 1, MAX, HEIGHT, MAX);

	public AbstractCollarBlock(final CollarMaterial material, final Settings settings) {
		super(settings
			.nonOpaque()
			.pistonBehavior(PistonBehavior.DESTROY)
			.sounds(material.getSoundGroup())
		);
	}

	protected AbstractCollarBlock(final Settings settings) {
		super(settings);
	}

	@Override
	public BlockRenderType getRenderType(final BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public BlockState onBreak(final World world, final BlockPos pos, final BlockState state, final PlayerEntity player) {
		final var ret = super.onBreak(world, pos, state, player);
		if (world.isClient) {
			// Client is irrelevant to us; this can only cause graphical glitches.
			return ret;
		}

		final var blockEntity = world.getBlockEntity(pos);
		if (!(blockEntity instanceof CollarBlockEntity collar)) {
			return ret;
		}

		if (player.isCreative()) {
			collar.clear();
			return ret;
		}

		final var stack = collar.getStack();
		if (!player.isSneaking()) {
			player.getInventory().offerOrDrop(stack.copyAndEmpty());
		}
		return ret;
	}

	@Override
	public void onStateReplaced(final BlockState state, final World world, final BlockPos pos, final BlockState newState, final boolean moved) {
		if (!state.isOf(newState.getBlock())) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof CollarBlockEntity collar) {
				ItemScatterer.spawn(world, pos, collar);
				world.updateComparators(pos, this);
			}

			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	@Override
	public void onPlaced(final World world, final BlockPos pos, final BlockState state, @Nullable final LivingEntity placer, final ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);

		if (!(world.getBlockEntity(pos) instanceof CollarBlockEntity collar)) {
			return;
		}

		if (BlockItem.writeNbtToBlockEntity(world, placer instanceof PlayerEntity player ? player : null, pos, itemStack)) {
			return;
		}

		collar.setStack(itemStack.copyWithCount(1));
	}

	@Override
	protected ItemActionResult onUseWithItem(final ItemStack stack, final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockHitResult hit) {
		if (player.canModifyBlocks() && world.getBlockEntity(pos) instanceof CollarBlockEntity collar) {
			final var collarStack = collar.getStack();

			if (stack.isOf(Items.GLOW_INK_SAC) && GlowingComponent.addGlowing(collarStack, null)) {
				collar.markDirty();
				world.playSound(null, collar.getPos(), SoundEvents.ITEM_GLOW_INK_SAC_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
				stack.decrementUnlessCreative(1, player);
				return ItemActionResult.SUCCESS;
			}

			if (stack.isOf(Items.INK_SAC) && GlowingComponent.subtractGlowing(collarStack, null)) {
				collar.markDirty();
				world.playSound(null, collar.getPos(), SoundEvents.ITEM_INK_SAC_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
				stack.decrementUnlessCreative(1, player);
				return ItemActionResult.SUCCESS;
			}

			if (collarStack.isIn(ItemTags.DYEABLE)) {
				if (stack.getItem() instanceof DyeItem dye) {
					var newStack = DyedColorComponent.setColor(collarStack, List.of(dye));
					if (!ItemStack.areEqual(collarStack, newStack)) {
						collar.setStack(newStack);
						collar.markDirty();
						world.playSound(null, collar.getPos(), SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.F, 1.F);
						stack.decrementUnlessCreative(1, player);
						return ItemActionResult.SUCCESS;
					}
				}

				if (stack.isOf(Items.WATER_BUCKET)) {
					if (!collarStack.contains(DataComponentTypes.DYED_COLOR)) {
						// To prevent watering the land when you didn't intend to.
						return ItemActionResult.CONSUME;
					}
					collarStack.remove(DataComponentTypes.DYED_COLOR);
					collar.markDirty();
					world.playSound(null, collar.getPos(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.F, 1.F);
					player.setStackInHand(hand,
						ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
					return ItemActionResult.SUCCESS;
				}
				if (stack.isOf(Items.POTION) && ItemUtils.isPotionOf(stack, Potions.WATER)
					&& collarStack.contains(DataComponentTypes.DYED_COLOR)) {
					collarStack.remove(DataComponentTypes.DYED_COLOR);
					world.playSound(null, collar.getPos(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.F, 1.F);
					collar.markDirty();
					player.setStackInHand(hand,
						ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
					return ItemActionResult.SUCCESS;
				}
			}
		}

		return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
	}

	@Override
	public ItemStack getPickStack(final WorldView world, final BlockPos pos, final BlockState state) {
		if (world.getBlockEntity(pos) instanceof CollarBlockEntity collar) {
			return collar.getStack(0).copy();
		}
		return ItemStack.EMPTY;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(final BlockPos pos, final BlockState state) {
		return new CollarBlockEntity(pos, state);
	}
}
