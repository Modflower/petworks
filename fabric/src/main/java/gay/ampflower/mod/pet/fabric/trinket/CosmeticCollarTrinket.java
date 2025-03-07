package gay.ampflower.mod.pet.fabric.trinket;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

/**
 * @author Ampflower
 * @since 0.1.0
 */
public class CosmeticCollarTrinket extends CollarTrinket {
	@Override
	public TrinketEnums.DropRule getDropRule(final ItemStack stack, final SlotReference slot, final LivingEntity entity) {
		if (stack.hasEnchantments() || entity.getType() != EntityType.PLAYER) {
			return super.getDropRule(stack, slot, entity);
		}
		return TrinketEnums.DropRule.KEEP;
	}
}
