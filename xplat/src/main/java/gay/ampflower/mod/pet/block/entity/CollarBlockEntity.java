package gay.ampflower.mod.pet.block.entity;

import gay.ampflower.mod.pet.block.AbstractCollarBlock;
import gay.ampflower.mod.pet.item.CollarDyeableItem;
import gay.ampflower.mod.pet.item.CollarItem;
import gay.ampflower.mod.pet.registry.PetworksBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SingleStackInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class CollarBlockEntity extends BlockEntity implements Clearable, SingleStackInventory {
	private ItemStack item;

	public CollarBlockEntity(final BlockPos pos, final BlockState state) {
		super(PetworksBlockEntities.COLLAR, pos, state);
	}

	public int getColor(int index) {
		final var stack = this.getStack();
		if (stack.getItem() instanceof CollarDyeableItem collar) {
			return collar.getColor(stack, index);
		}
		final var dyeable = stack.get(DataComponentTypes.DYED_COLOR);
		if (dyeable != null) {
			return dyeable.rgb();
		}
		return -1;
	}

	@Override
	protected void writeNbt(final NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
		super.writeNbt(nbt, wrapperLookup);
		if (!this.getStack().isEmpty()) {
			nbt.put("item", this.getStack().encode(wrapperLookup));
		}
	}

	@Override
	public void readNbt(final NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
		super.readNbt(nbt, wrapperLookup);
		if (nbt.contains("item", NbtElement.COMPOUND_TYPE)) {
			this.setStack(ItemStack.fromNbtOrEmpty(wrapperLookup, nbt.getCompound("item")));
		}

		// Very hacky, I know.
		// This is the only way to get /data and such to behave correctly,
		// and update the rendered block in the world.
		this.redraw();
	}

	@Override
	public ItemStack getStack() {
		return Objects.requireNonNullElse(this.item, ItemStack.EMPTY);
	}

	@Override
	public void setStack(final ItemStack stack) {
		if (stack.getItem() instanceof CollarItem) {
			this.item = stack;
			this.updateState();
		}
	}

	private void updateState() {
		if (this.item == null) {
			this.item = ItemStack.EMPTY;
			return;
		}
		if (this.world == null) {
			return;
		}
		if (getStack().isEmpty()) {
			if (this.world.getBlockState(getPos()).getBlock() instanceof AbstractCollarBlock) {
				this.world.setBlockState(getPos(), Blocks.AIR.getDefaultState());
			}
		} else {
			redraw();
		}
	}

	private void redraw() {
		if (this.world != null && this.world.isClient) {
			world.updateListeners(getPos(), getCachedState(), getCachedState(), Block.REDRAW_ON_MAIN_THREAD);
		}
	}

	@Override
	public int getMaxCountPerStack() {
		return 1;
	}

	@Override
	public boolean canPlayerUse(final PlayerEntity player) {
		return Inventory.canPlayerUse(this, player);
	}

	@Override
	public boolean isValid(final int slot, final ItemStack stack) {
		return getStack(slot).isEmpty() && stack.getItem() instanceof CollarItem;
	}

	@Override
	public boolean canTransferTo(final Inventory hopperInventory, final int slot, final ItemStack stack) {
		return hopperInventory.containsAny(ItemStack::isEmpty);
	}

	@Override
	public void markDirty() {
		super.markDirty();
		updateState();
	}

	@Override
	public BlockEntityUpdateS2CPacket toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup wrapperLookup) {
		final var compound = super.toInitialChunkDataNbt(wrapperLookup);
		writeNbt(compound, wrapperLookup);
		return compound;
	}
}
