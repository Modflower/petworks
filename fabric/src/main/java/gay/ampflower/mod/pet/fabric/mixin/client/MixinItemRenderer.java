package gay.ampflower.mod.pet.fabric.mixin.client;

import gay.ampflower.mod.pet.fabric.client.ItemModelSwapper;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
	@Shadow
	@Final
	private ItemModels models;

	@ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0, shift = At.Shift.AFTER), argsOnly = true)
	private BakedModel onRender$supplyRenderer(final BakedModel original, final ItemStack itemStack, final ModelTransformationMode mode) {
		final var id = ItemModelSwapper.getOrElse(itemStack.getItem(), mode, null);
		if (id != null) {
			return this.models.getModelManager().getModel(id);
		}
		return original;
	}

	@ModifyVariable(method = "getModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemModels;getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;", shift = At.Shift.BY, by = 2))
	private BakedModel onGetModel$supplyRenderer(final BakedModel original, final ItemStack itemStack) {
		final var id = ItemModelSwapper.getDefault(itemStack.getItem());
		if (id != null) {
			return this.models.getModelManager().getModel(id);
		}
		return original;
	}
}
