package gay.ampflower.mod.pet.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class TamedCatGuiseItem extends GuiseItem {
	public TamedCatGuiseItem() {
		super(Items.SALMON, 15714446, 9794134);
	}

	@Override
	public @Nullable SoundEvent getAmbientSound(final @NotNull LivingEntity wearer) {
		if (wearer.isSneaking()) {
			return SoundEvents.ENTITY_CAT_PURR;
		}
		return wearer.getRandom().nextInt(4) == 0 ?
			SoundEvents.ENTITY_CAT_PURREOW : SoundEvents.ENTITY_CAT_AMBIENT;
	}

	@Override
	public @Nullable SoundEvent getHurtSound(final @NotNull LivingEntity wearer, final DamageSource source) {
		return wearer.getRandom().nextInt(8) == 0 ?
			SoundEvents.ENTITY_CAT_HISS : SoundEvents.ENTITY_CAT_HURT;
	}

	@Override
	public @Nullable SoundEvent getDeathSound(final @NotNull LivingEntity wearer) {
		return SoundEvents.ENTITY_CAT_DEATH;
	}
}
