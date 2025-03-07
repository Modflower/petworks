package gay.ampflower.mod.pet.fabric.trinket;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class GimickCollarTrinket extends CollarTrinket {
	@Override
	public boolean canUnequip(final ItemStack stack, final SlotReference slot, final LivingEntity entity) {
		if (entity instanceof PlayerEntity player && player.isCreative()) {
			return true;
		}
		return stack.getDamage() > stack.getMaxDamage() / 2 && super.canUnequip(stack, slot, entity);
	}
}
