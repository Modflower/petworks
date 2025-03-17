package gay.ampflower.mod.pet.support;

import net.minecraft.entity.EquipmentSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ampflower
 * @since 0.2.0
 **/
public interface SlotPriority {
	@NotNull
	TrinketSlot trinketSlot();

	@NotNull
	String curiosSlot();

	@Nullable
	default EquipmentSlot vanillaSlot() {
		return null;
	}
}
