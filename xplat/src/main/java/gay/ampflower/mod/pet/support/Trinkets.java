package gay.ampflower.mod.pet.support;

import gay.ampflower.mod.pet.item.EmptyGuise;
import gay.ampflower.mod.pet.item.Guise;
import gay.ampflower.mod.pet.registry.tag.PetworksItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Predicate;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface Trinkets {
	Trinkets INSTANCE = ServiceLoader.load(Trinkets.class).findFirst().orElseThrow();

	List<ItemStack> getStacks(final LivingEntity entity, final Predicate<ItemStack> predicate);

	boolean equip(final LivingEntity entity, final ItemStack trinket);

	boolean equip(final LivingEntity entity, final ItemStack trinket, final SlotPriority... priority);

	static Guise getGuise(final LivingEntity entity) {
		final var stacks = INSTANCE.getStacks(entity, stack -> stack.getItem() instanceof Guise);
		if (stacks.isEmpty()) {
			return EmptyGuise.INSTANCE;
		}
		return (Guise) stacks.get(0).getItem();
	}

	static List<ItemStack> getCollars(final Object entity) {
		if (entity instanceof LivingEntity livingEntity) {
			return getCollars(livingEntity);
		}
		return List.of();
	}

	static List<ItemStack> getCollars(final LivingEntity entity) {
		return INSTANCE.getStacks(entity, stack -> stack.isIn(PetworksItemTags.COLLARS));
	}
}
