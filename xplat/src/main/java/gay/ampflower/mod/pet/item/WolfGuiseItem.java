package gay.ampflower.mod.pet.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class WolfGuiseItem extends GuiseItem {
	public WolfGuiseItem() {
		super(Items.BONE, 14144467, 13545366);
	}

	@Override
	public boolean playStepSound(final @NotNull LivingEntity wearer, final BlockPos pos, final BlockState state) {
		wearer.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1F);
		return true;
	}

	@Override
	public @Nullable SoundEvent getAmbientSound(final @NotNull LivingEntity wearer) {
		if (wearer.isSneaking() || wearer.getLastAttackTime() + 100 > wearer.age) {
			return SoundEvents.ENTITY_WOLF_GROWL;
		}
		if (wearer.getRandom().nextInt(3) == 0) {
			if (wearer.getHealth() < wearer.getMaxHealth() / 2) {
				return SoundEvents.ENTITY_WOLF_WHINE;
			}
			return SoundEvents.ENTITY_WOLF_PANT;
		}
		return SoundEvents.ENTITY_WOLF_AMBIENT;
	}

	@Override
	public @Nullable SoundEvent getResponseSound(final @NotNull LivingEntity wearer) {
		if (wearer.isSneaking()) {
			return SoundEvents.ENTITY_WOLF_GROWL;
		}
		return SoundEvents.ENTITY_WOLF_HOWL;
	}

	@Override
	public @Nullable SoundEvent getHurtSound(final @NotNull LivingEntity wearer, final DamageSource source) {
		return wearer.getRandom().nextInt(8) == 0 ?
			SoundEvents.ENTITY_WOLF_GROWL : SoundEvents.ENTITY_WOLF_HURT;
	}

	@Override
	public @Nullable SoundEvent getDeathSound(final @NotNull LivingEntity wearer) {
		return SoundEvents.ENTITY_WOLF_DEATH;
	}
}
