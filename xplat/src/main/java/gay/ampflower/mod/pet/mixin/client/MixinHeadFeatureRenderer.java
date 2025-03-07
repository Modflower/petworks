package gay.ampflower.mod.pet.mixin.client;

import gay.ampflower.mod.pet.client.HeadRendererSuppressor;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
@Mixin(HeadFeatureRenderer.class)
@Debug(export = true)
public class MixinHeadFeatureRenderer {
	@Inject(
		method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", shift = At.Shift.AFTER),
		cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	private void checkSuppressed(final MatrixStack matrixStack, final VertexConsumerProvider vertexConsumerProvider, final int i, final LivingEntity livingEntity, final float f, final float g, final float h, final float j, final float k, final float l, final CallbackInfo ci, ItemStack stack) {
		if (HeadRendererSuppressor.suppressed.contains(stack.getItem())) {
			ci.cancel();
		}
	}
}
