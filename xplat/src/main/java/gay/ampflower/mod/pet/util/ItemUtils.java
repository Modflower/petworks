package gay.ampflower.mod.pet.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * @author Ampflower
 * @since 0.1.1
 **/
public final class ItemUtils {
	public static boolean isPotionOf(ItemStack stack, RegistryEntry<Potion> potion) {
		final var potionComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
		if (potionComponent == null) {
			return false;
		}
		return potionComponent.potion().orElse(null) == potion;
	}
}
