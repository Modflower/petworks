package gay.ampflower.mod.pet.fabric;

import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import gay.ampflower.mod.pet.support.SlotPriority;
import gay.ampflower.mod.pet.support.Trinkets;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class TrinketsSupport implements Trinkets {
	@Override
	public List<ItemStack> getStacks(final LivingEntity entity, final Predicate<ItemStack> predicate) {
		return TrinketsApi.getTrinketComponent(entity).orElseThrow()
			.getEquipped(predicate)
			.stream().map(Pair::getRight).toList();
	}

	@Override
	public boolean equip(final LivingEntity entity, final ItemStack trinket) {
		return TrinketItem.equipItem(entity, trinket);
	}

	// Frankly, this ended up being more of a copy from Trinkets
	@Override
	public boolean equip(final LivingEntity entity, final ItemStack trinket, final SlotPriority... priorities) {
		final var optional = TrinketsApi.getTrinketComponent(entity);
		if (optional.isEmpty()) {
			return false;
		}

		final var component = optional.get();
		final var groups = component.getInventory();

		for (final var priority : priorities) {
			final var trinketSlot = priority.trinketSlot();
			final var group = groups.get(trinketSlot.group());

			if (group == null) {
				continue;
			}

			final var inventory = group.get(trinketSlot.path());

			if (inventory == null) {
				continue;
			}

			for (int i = 0; i < inventory.size(); i++) {
				if (!inventory.getStack(i).isEmpty()) {
					continue;
				}

				final var slotRef = new SlotReference(inventory, i);
				if (TrinketSlot.canInsert(trinket, slotRef, entity)) {
					ItemStack newStack = trinket.copy();
					inventory.setStack(i, newStack);

					var raw = TrinketsApi.getTrinket(trinket.getItem());
					var sound = raw.getEquipSound(trinket, slotRef, entity);

					if (!newStack.isEmpty() && sound != null) {
						entity.emitGameEvent(GameEvent.EQUIP);
						entity.playSound(sound.value(), 1, 1);
					}

					if (!entity.isInCreativeMode()) {
						trinket.setCount(0);
					}
					return true;
				}
			}
		}

		return TrinketItem.equipItem(entity, trinket);
	}
}
