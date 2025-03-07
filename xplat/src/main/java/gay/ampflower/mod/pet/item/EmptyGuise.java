package gay.ampflower.mod.pet.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public enum EmptyGuise implements Guise {
	INSTANCE;

	@Override
	public @Nullable SoundEvent getAmbientSound(final @NotNull LivingEntity wearer) {
		return null;
	}

	@Override
	public @Nullable SoundEvent getHurtSound(final @NotNull LivingEntity wearer, final DamageSource source) {
		return null;
	}

	@Override
	public @Nullable SoundEvent getDeathSound(final @NotNull LivingEntity wearer) {
		return null;
	}
}
