package gay.ampflower.mod.pet.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface Guise {
	int animalSoundDelay = 120;

	default int getMinAmbientSoundDelay() {
		return animalSoundDelay;
	}

	default float getSoundVolume(final @NotNull LivingEntity wearer) {
		return 1.0F;
	}

	default float getSoundPitch(final @NotNull LivingEntity wearer) {
		return getSoundPitch(wearer.getRandom(), wearer.isBaby() ? 1.5F : 1.0F);
	}

	static float getSoundPitch(final Random random, final float base) {
		return (random.nextFloat() - random.nextFloat()) * 0.2F + base;
	}

	default boolean playStepSound(final @NotNull LivingEntity wearer, final BlockPos pos, final BlockState state) {
		return false;
	}

	@Nullable
	default SoundEvent getJumpSound(final @NotNull LivingEntity wearer) {
		return null;
	}

	default boolean playJumpSound(final @NotNull LivingEntity wearer) {
		final var sound = getJumpSound(wearer);
		if (sound == null) {
			return false;
		}
		final var rng = wearer.getRandom();
		wearer.playSound(sound, getSoundVolume(wearer), getSoundPitch(wearer) * 0.8F);
		return true;
	}

	@Nullable
	default SoundEvent getAttackSound(final @NotNull LivingEntity wearer) {
		return null;
	}

	default boolean playAttackSound(final @NotNull LivingEntity wearer) {
		final var sound = getAttackSound(wearer);
		System.out.printf("%s: %s -> %s\n", this, wearer, sound);
		if (sound == null) {
			return false;
		}
		wearer.getWorld().playSound(null, wearer.getX(), wearer.getY(), wearer.getZ(), sound, wearer.getSoundCategory(), 1.F, getSoundPitch(wearer));
		return true;
	}

	@Nullable
	SoundEvent getAmbientSound(final @NotNull LivingEntity wearer);

	default boolean playAmbientSound(final @NotNull LivingEntity wearer) {
		final var sound = getAmbientSound(wearer);
		if (sound == null) {
			return false;
		}
		wearer.playSound(sound, getSoundVolume(wearer), getSoundPitch(wearer));
		return true;
	}

	@Nullable
	default SoundEvent getResponseSound(final @NotNull LivingEntity wearer) {
		return getAmbientSound(wearer);
	}

	default boolean playResponseSound(final @NotNull LivingEntity wearer, final float volume) {
		final var response = getResponseSound(wearer);
		if (response == null) {
			return false;
		}
		wearer.playSound(response, volume, 1.F);
		return true;
	}

	@Nullable
	default SoundEvent getEatSound(final @NotNull LivingEntity wearer) {
		return null;
	}

	@Nullable
	default SoundEvent getSleepSound(final @NotNull LivingEntity wearer) {
		return null;
	}

	@Nullable
	SoundEvent getHurtSound(final @NotNull LivingEntity wearer, final DamageSource source);

	@Nullable
	SoundEvent getDeathSound(final @NotNull LivingEntity wearer);

}
