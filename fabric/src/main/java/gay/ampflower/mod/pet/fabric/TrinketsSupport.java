package gay.ampflower.mod.pet.fabric;

import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import gay.ampflower.mod.pet.support.Trinkets;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

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
}
