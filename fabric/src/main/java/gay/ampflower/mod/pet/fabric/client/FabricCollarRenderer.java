package gay.ampflower.mod.pet.fabric.client;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import gay.ampflower.mod.pet.client.CollarRenderer;
import gay.ampflower.mod.pet.client.Part;
import gay.ampflower.mod.pet.client.RenderUtil;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class FabricCollarRenderer extends CollarRenderer implements TrinketRenderer, ArmorRenderer {

	@Override
	public void render(
		final ItemStack stack,
		final SlotReference slotReference,
		final EntityModel<? extends LivingEntity> contextModel,
		final MatrixStack matrices,
		final VertexConsumerProvider vertexConsumers,
		final int light,
		final LivingEntity entity,
		final float limbAngle, final float limbDistance,
		final float tickDelta, final float animationProgress,
		final float headYaw, final float headPitch) {
		super.render(toPart(slotReference), slotReference.index(), stack, contextModel, matrices, vertexConsumers, light, entity, headYaw, headPitch);
	}

	private static Part toPart(SlotReference reference) {
		return switch (reference.inventory().getSlotType().getGroup()) {
			case "head" -> Part.HEAD;
			case "chest" -> Part.CHEST;
			case "legs" -> Part.LEGS;
			case "feet" -> Part.FEET;
			case "hand" -> Part.HAND;
			case "offhand" -> Part.OFFHAND;
			default -> Part.UNKNOWN;
		};
	}

	@Override
	public void render(
		final MatrixStack matrices,
		final VertexConsumerProvider vertexConsumers,
		final ItemStack stack,
		final LivingEntity entity,
		final EquipmentSlot slot,
		final int light,
		final BipedEntityModel<LivingEntity> contextModel) {
		if (slot != EquipmentSlot.HEAD) {
			return;
		}
		super.render(Part.HEAD, 0, stack, contextModel, matrices, vertexConsumers, light, entity,
			RenderUtil.deg(contextModel.head.yaw),
			RenderUtil.deg(contextModel.head.pitch));
	}
}
