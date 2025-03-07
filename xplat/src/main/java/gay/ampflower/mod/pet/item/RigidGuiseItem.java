package gay.ampflower.mod.pet.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class RigidGuiseItem extends GuiseItem {
	private final SoundEvent
		JUMP_SOUND,
		ATTACK_SOUND,
		STEP_SOUND,
		AMBIENT_SOUND,
		HURT_SOUND,
		DEATH_SOUND;

	private final int AMBIENT_DELAY;

	private final float STEP_VOLUME, STEP_PITCH;

	public RigidGuiseItem(final Item component, final int primaryColor, final int secondaryColor,
								 final SoundEvent jumpSound,
								 final SoundEvent attackSound,
								 final SoundEvent stepSound, final float stepVolume, final float stepPitch,
								 final SoundEvent ambientSound,
								 final SoundEvent hurtSound,
								 final SoundEvent deathSound,
								 final int minAmbientSoundDelay
	) {
		super(component, primaryColor, secondaryColor);

		JUMP_SOUND = jumpSound;
		ATTACK_SOUND = attackSound;

		STEP_SOUND = stepSound;
		STEP_VOLUME = stepVolume;
		STEP_PITCH = stepPitch;

		AMBIENT_SOUND = ambientSound;

		HURT_SOUND = hurtSound;

		DEATH_SOUND = deathSound;

		AMBIENT_DELAY = minAmbientSoundDelay;
	}

	public RigidGuiseItem(final Item component, final int primaryColor, final int secondaryColor,
								 final SoundEvent stepSound, final float stepVolume, final float stepPitch,
								 final SoundEvent ambientSound,
								 final SoundEvent hurtSound,
								 final SoundEvent deathSound
	) {
		this(component, primaryColor, secondaryColor, null, null, stepSound, stepVolume, stepPitch, ambientSound, hurtSound, deathSound, animalSoundDelay);
	}

	public static RigidGuiseItem ofOcelot(final Item component,
													  final int primaryColor, final int secondaryColor,
													  final SoundEvent ambientSound,
													  final SoundEvent hurtSound,
													  final SoundEvent deathSound,
													  final int soundDelay) {
		return new RigidGuiseItem(component, primaryColor, secondaryColor, null, null, null, 0.F, 0.F, ambientSound, hurtSound, deathSound, soundDelay);
	}

	public static RigidGuiseItem ofRabbit(final Item component,
													  final int primaryColor, final int secondaryColor,
													  final SoundEvent jumpSound,
													  final SoundEvent attackSound,
													  final SoundEvent ambientSound,
													  final SoundEvent hurtSound,
													  final SoundEvent deathSound
	) {
		return new RigidGuiseItem(component, primaryColor, secondaryColor, jumpSound, attackSound, null, 0, 0, ambientSound, hurtSound, deathSound, animalSoundDelay);
	}

	@Override
	public int getMinAmbientSoundDelay() {
		return AMBIENT_DELAY;
	}

	@Override
	public boolean playStepSound(final @NotNull LivingEntity wearer, final BlockPos pos, final BlockState state) {
		if (STEP_VOLUME == 0) {
			return false;
		}
		wearer.playSound(STEP_SOUND, STEP_VOLUME, STEP_PITCH);
		return true;
	}

	@Override
	public @Nullable SoundEvent getJumpSound(final @NotNull LivingEntity wearer) {
		return JUMP_SOUND;
	}

	@Override
	public @Nullable SoundEvent getAttackSound(final @NotNull LivingEntity wearer) {
		return ATTACK_SOUND;
	}

	@Override
	public SoundEvent getAmbientSound(final @NotNull LivingEntity wearer) {
		return AMBIENT_SOUND;
	}

	@Override
	public SoundEvent getHurtSound(final @NotNull LivingEntity wearer, final DamageSource source) {
		return HURT_SOUND;
	}

	@Override
	public SoundEvent getDeathSound(final @NotNull LivingEntity wearer) {
		return DEATH_SOUND;
	}
}
