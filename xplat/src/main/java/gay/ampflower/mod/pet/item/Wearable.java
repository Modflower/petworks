package gay.ampflower.mod.pet.item;

import net.minecraft.entity.EquipmentSlot;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface Wearable {
	default @Nullable EquipmentSlot getEquivalentSlotType() {
		return null;
	}

	default boolean isArmor() {
		return false;
	}
}
