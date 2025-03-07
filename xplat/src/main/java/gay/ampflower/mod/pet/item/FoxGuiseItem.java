package gay.ampflower.mod.pet.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class FoxGuiseItem extends GuiseItem {
	public FoxGuiseItem() {
		super(Items.SWEET_BERRIES, 14005919, 13396256);
	}

	@Override
	public @Nullable SoundEvent getAmbientSound(final @NotNull LivingEntity wearer) {
		if (wearer.isSneaking() || wearer.getLastAttackTime() + 100 > wearer.age) {
			return SoundEvents.ENTITY_FOX_AGGRO;
		}
		final var world = wearer.getWorld();
		if (!world.isDay() && wearer.getRandom().nextFloat() < 0.05F) {
			List<PlayerEntity> list = world.getEntitiesByClass(PlayerEntity.class, wearer.getBoundingBox().expand(16.d), EntityPredicates.EXCEPT_SPECTATOR);
			list.remove(wearer);
			if (list.isEmpty()) {
				return SoundEvents.ENTITY_FOX_SCREECH;
			}
		}
		return SoundEvents.ENTITY_FOX_AMBIENT;
	}

	@Override
	public @Nullable SoundEvent getResponseSound(final @NotNull LivingEntity wearer) {
		//if(wearer.isSneaking()) {
		//    return SoundEvents.ENTITY_FOX_;
		//}
		return SoundEvents.ENTITY_FOX_AMBIENT;
	}

	@Override
	public @Nullable SoundEvent getSleepSound(final @NotNull LivingEntity wearer) {
		return SoundEvents.ENTITY_FOX_SLEEP;
	}

	@Override
	public @Nullable SoundEvent getEatSound(final @NotNull LivingEntity wearer) {
		return SoundEvents.ENTITY_FOX_EAT;
	}

	@Override
	public @Nullable SoundEvent getHurtSound(final @NotNull LivingEntity wearer, final DamageSource source) {
		return wearer.getRandom().nextInt(8) == 0 ?
			SoundEvents.ENTITY_FOX_AGGRO : SoundEvents.ENTITY_FOX_HURT;
	}

	@Override
	public @Nullable SoundEvent getDeathSound(final @NotNull LivingEntity wearer) {
		return SoundEvents.ENTITY_FOX_DEATH;
	}
}
