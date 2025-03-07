package gay.ampflower.mod.pet.fabric.trinket;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import gay.ampflower.mod.pet.item.CollarItem;
import gay.ampflower.mod.pet.util.Util;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class CollarTrinket implements Trinket {

	@Override
	public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(final ItemStack stack, final SlotReference slot, final LivingEntity entity, final Identifier slotIdentifier) {
		final var map = Trinket.super.getModifiers(stack, slot, entity, slotIdentifier);

		if (stack.getItem() instanceof CollarItem collar) {
			final var material = collar.material;
			if (material.getArmour() != 0) {
				map.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(
					Util.mux(slotIdentifier, EntityAttributes.GENERIC_ARMOR),
					material.getArmour(),
					EntityAttributeModifier.Operation.ADD_VALUE
				));
			}
			if (material.getToughness() != 0) {
				map.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(
					Util.mux(slotIdentifier, EntityAttributes.GENERIC_ARMOR_TOUGHNESS),
					material.getToughness(),
					EntityAttributeModifier.Operation.ADD_VALUE
				));
			}
			if (material.getKnockbackResistance() != 0.f) {
				map.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(
					Util.mux(slotIdentifier, EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE),
					material.getKnockbackResistance(),
					EntityAttributeModifier.Operation.ADD_VALUE
				));
			}
		}

		EnchantmentHelper.applyAttributeModifiers(stack, EquipmentSlot.HEAD, (attribute, modifier) -> {
			map.put(attribute,
				new EntityAttributeModifier(
					Util.mux(slotIdentifier, modifier.id()),
					modifier.value(),
					modifier.operation()
				));
		});

		return map;
	}
}
