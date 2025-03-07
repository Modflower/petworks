package gay.ampflower.mod.pet.mixin;

import gay.ampflower.mod.pet.item.Droppable;
import gay.ampflower.mod.pet.support.Trinkets;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {
	@Unique
	private int ambientSoundChance;

	@Shadow
	public abstract void playSound(final SoundEvent sound, final float volume, final float pitch);

	protected MixinPlayerEntity(final EntityType<? extends LivingEntity> entityType, final World world) {
		super(entityType, world);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void addAmbience(CallbackInfo callback) {
		if (this.isAlive() && this.random.nextInt(1000) < this.ambientSoundChance++) {
			this.resetSoundDelay();
			Trinkets.getGuise(this).playAmbientSound(this);
		}
	}

	@Inject(method = "playStepSound", at = @At("HEAD"), cancellable = true)
	protected void overridePlayStepSound(final BlockPos pos, final BlockState state, CallbackInfo callback) {
		if (isTouchingWater()) return;

		if (Trinkets.getGuise(this).playStepSound(this, pos, state)) {
			callback.cancel();
		}
	}

	@Override
	public SoundEvent getEatSound(final ItemStack stack) {
		final var sound = Trinkets.getGuise(this).getEatSound(this);

		return sound != null ? sound : super.getEatSound(stack);
	}

	@Inject(method = "jump", at = @At("HEAD"))
	private void playJumpSound(final CallbackInfo callback) {
		Trinkets.getGuise(this).playJumpSound(this);
	}

	@Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
	protected void overrideDeathSound(final CallbackInfoReturnable<SoundEvent> callback) {
		final var sound = Trinkets.getGuise(this).getDeathSound(this);

		if (sound != null) {
			callback.setReturnValue(sound);
		}
	}

	@Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
	private void overrideHurtSound(final DamageSource source, CallbackInfoReturnable<SoundEvent> callback) {
		this.resetSoundDelay();
		final var sound = Trinkets.getGuise(this).getHurtSound(this, source);
		if (sound != null) {
			callback.setReturnValue(sound);
		}
	}

	@Override
	public void onAttacking(final Entity target) {
		super.onAttacking(target);
		Trinkets.getGuise(this).playAttackSound(this);
	}

	@ModifyVariable(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("HEAD"), argsOnly = true)
	private ItemStack onDropItem(ItemStack stack, ItemStack $literallyTheSameThing, boolean randomDirection, boolean retainOwnership) {
		if (stack.getItem() instanceof Droppable droppable) {
			return droppable.onDrop((PlayerEntity) (Object) this, stack, retainOwnership);
		}
		return stack;
	}

	// Sound Support

	@Unique
	private void resetSoundDelay() {
		this.ambientSoundChance = -Trinkets.getGuise(this).getMinAmbientSoundDelay();
	}
}
