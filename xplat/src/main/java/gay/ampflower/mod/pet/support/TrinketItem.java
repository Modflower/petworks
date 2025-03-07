package gay.ampflower.mod.pet.support;

import net.minecraft.item.ItemStack;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface TrinketItem {
	default boolean isAlike(ItemStack stack) {
		return stack.getItem() == this;
	}

	default int maxWearCount() {
		return -1;
	}
}
