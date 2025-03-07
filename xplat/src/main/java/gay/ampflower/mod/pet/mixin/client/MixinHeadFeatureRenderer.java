package gay.ampflower.mod.pet.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import gay.ampflower.mod.pet.client.HeadRendererSuppressor;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
@Mixin(HeadFeatureRenderer.class)
@Debug(export = true)
public class MixinHeadFeatureRenderer {
	@Inject(
		method = "render",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", shift = At.Shift.AFTER),
		cancellable = true)
	private void checkSuppressed(final CallbackInfo ci, @Local final ItemStack stack) {
		if (HeadRendererSuppressor.suppressed.contains(stack.getItem())) {
			ci.cancel();
		}
	}
}
