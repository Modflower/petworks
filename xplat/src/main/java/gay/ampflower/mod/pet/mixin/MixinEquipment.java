package gay.ampflower.mod.pet.mixin;

import gay.ampflower.mod.pet.item.Wearable;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
@Mixin(Equipment.class)
public interface MixinEquipment extends Wearable {
	@Shadow
	EquipmentSlot getSlotType();

	@Override
	default @Nullable EquipmentSlot getEquivalentSlotType() {
		return this.getSlotType();
	}
}
