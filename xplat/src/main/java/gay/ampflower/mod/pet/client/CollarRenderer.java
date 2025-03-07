package gay.ampflower.mod.pet.client;

import gay.ampflower.mod.pet.component.type.GlowingComponent;
import gay.ampflower.mod.pet.mixin.client.AccessorAnimalModel;
import gay.ampflower.mod.pet.registry.PetworksDataComponentTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class CollarRenderer {
	private static final float D = 0.075f;

	public void render(final Part part,
							 final ItemStack stack,
							 final EntityModel<? extends LivingEntity> contextModel,
							 final MatrixStack matrices,
							 final VertexConsumerProvider vertexConsumers,
							 final int light,
							 final LivingEntity entity,
							 float headYaw,
							 float headPitch
	) {
		if (part.isHand() && contextModel instanceof ModelWithArms armed) {
			renderItem(entity, armed, contextModel.child, stack, part, matrices, vertexConsumers, light);
			return;
		}
		if (contextModel instanceof BipedEntityModel<? extends LivingEntity> bipedModel) {
			renderBiped(stack, bipedModel, matrices, vertexConsumers, light, entity);
		}
	}

	private void renderBiped(
		final ItemStack stack,
		final BipedEntityModel<? extends LivingEntity> contextModel,
		final MatrixStack matrices,
		final VertexConsumerProvider vertexConsumers,
		int light,
		final LivingEntity entity
	) {
		final var animal = (AccessorAnimalModel) contextModel;
		final boolean firstPerson = !contextModel.head.visible;

		// Set up ground truth:
		final float headYaw = RenderUtil.wrapRadians(contextModel.head.yaw);
		final float headPitch;

		if (firstPerson) {
			headPitch = RenderUtil.deg(contextModel.body.pitch);
		} else {
			headPitch = RenderUtil.deg(contextModel.head.pitch);
		}

		final float bodyYaw = contextModel.body.yaw;
		final boolean isInSneakingPose = contextModel.sneaking;

		matrices.push();

		if (!contextModel.sneaking) {
			matrices.translate(0, Math.max(contextModel.head.pivotY, contextModel.hat.pivotY) / 16F, 0);
		}

		if (contextModel.child && animal.isHeadScaled()) {
			final var sc = 1.5F / animal.getInvertedChildHeadScale();
			matrices.scale(sc, sc, sc);
			matrices.translate(
				0,
				animal.getChildHeadYOffset() / 16F - 0.015F,
				animal.getChildHeadZOffset() / 16F
			);
		}

		final var quat = new Quaternionf();

		if (!firstPerson) {
			quat.rotateY(MathHelper.lerp(
				RenderUtil.pow2a(headPitch / 142.3F, 0.5F),
				bodyYaw,
				headYaw)
			);

			// Sneaking requires more roll for it to look right.
			// Also a lil' roll while moving your head looks cute :3
			float hr = isInSneakingPose ? -85F : -115F;
			quat.rotateZ(RenderUtil.ipow2(headPitch / 90F) *
				(headYaw - bodyYaw) * headPitch / hr);
		}

		// A harsher angle is required with sneaking.
		float hpmd = 0.60F;
		if (isInSneakingPose && headPitch > 0) {
			hpmd = 1.50F;
		}

		float hpm = RenderUtil.bell(headPitch / 180F) / hpmd + 0.5F;
		float bpm = RenderUtil.bell(headPitch / 45.F) / 1.2F + 0.5F;

		// + PI fixes rendering upside down.
		quat.rotateX(RenderUtil.rad(headPitch) * hpm
			+ MathHelper.PI
			+ contextModel.body.pitch * bpm);

		// Fix rendering backwards
		quat.rotateY(MathHelper.PI);

		if (firstPerson) {
			quat.rotateY(-MathHelper.lerp(0.5F, bodyYaw, headYaw));
			quat.rotateZ((headYaw - bodyYaw) * 0.035F);
		}

		float ty = 0.F;
		float tzd = 450F;

		if (headPitch < 0) {
			tzd = Float.POSITIVE_INFINITY;
		} else {
			ty = RenderUtil.pow2(headPitch / tzd);
		}


		if (isInSneakingPose && !contextModel.riding) {
			ty = RenderUtil.pow2(headPitch / 720) * 1.5F + 0.225F;
		}

		matrices.translate(0, ty, RenderUtil.pow2(headPitch / tzd));

		if (contextModel.child && animal.isHeadScaled()) {
			quat.scale(1.5F / animal.getInvertedChildHeadScale());
		}

		matrices.multiply(quat, 0, D, 0);

		final var itemRenderer = MinecraftClient.getInstance().getItemRenderer();

		// I'll be honest, I tried.
		float yShift = isInSneakingPose
			? RenderUtil.pow3((headPitch) / 270F)
			: RenderUtil.pow2(headPitch / 450F) * (1.f / (headPitch / 28.125f - 4.f) + 1.25f);

		float zShift = 0F;

		if (firstPerson) {
			final float mul = contextModel.head.pitch / MathHelper.HALF_PI;
			zShift = 0.001F;
			yShift -= 0.0225F * mul;
			if (isInSneakingPose) {
				yShift -= 0.03F * mul;
				zShift = 0.055F;
			}
		}

		matrices.translate(0,
			.585F - yShift,
			// Intentional; minimises clipping while also snugging up the collar.
			!isInSneakingPose && headPitch > 0
				? RenderUtil.pow3((headPitch) / 220F) * (1.f / (headPitch / 28.125f - 4.f) + 1.25f)
				: RenderUtil.bell(headPitch / 45.F) / (headPitch > 0 ? 75F : 22.5F) + zShift);

		itemRenderer.renderItem(
			stack,
			ModelTransformationMode.NONE,
			light(entity, stack, light),
			OverlayTexture.DEFAULT_UV,
			matrices,
			vertexConsumers,
			null,
			0
		);
		matrices.pop();
	}

	private void renderItem(LivingEntity entity, ModelWithArms context, boolean child, ItemStack stack, Part part, MatrixStack matrices, VertexConsumerProvider provider, int light) {
		final var itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		final boolean rightHanded = entity.getMainArm() == Arm.RIGHT;
		final boolean right = rightHanded ? part.mainHand : part.offHand;
		final boolean left = rightHanded ? part.offHand : part.mainHand;
		light = light(entity, stack, light);

		matrices.push();
		if (child) {
			matrices.translate(0, .75F, 0);
			matrices.scale(.5F, .5F, .5F);
		}

		if (right) {
			renderItem(entity, context, stack, ModelTransformationMode.THIRD_PERSON_RIGHT_HAND, Arm.RIGHT, matrices, provider, light, itemRenderer);
		}
		if (left) {
			renderItem(entity, context, stack, ModelTransformationMode.THIRD_PERSON_LEFT_HAND, Arm.LEFT, matrices, provider, light, itemRenderer);
		}

		matrices.pop();
	}

	private void renderItem(LivingEntity entity, ModelWithArms context, ItemStack stack, ModelTransformationMode transform, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ItemRenderer itemRenderer) {
		matrices.push();
		context.setArmAngle(arm, matrices);
		matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90F));
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
		boolean leftHanded = arm == Arm.LEFT;
		matrices.translate((leftHanded ? -1F : 1F) / 16F, 0.125F, -0.625F);

		itemRenderer.renderItem(
			entity,
			stack,
			transform,
			leftHanded,
			matrices,
			vertexConsumers,
			entity.getWorld(),
			light,
			OverlayTexture.DEFAULT_UV,
			entity.getId() + transform.ordinal()
		);

		matrices.pop();
	}

	private int light(final LivingEntity entity, final ItemStack stack, final int light) {
		// TODO: Apply this to items as well; for now this will do.
		final var glowing = stack.getOrDefault(PetworksDataComponentTypes.GLOWING, GlowingComponent.DEFAULT);

		final int block = light & 0xFFFF;
		final int sky = (light >>> 16) & 0xFFFF;

		return MathHelper.lerp(glowing.block() / 15F, block, 0xFF) |
			(MathHelper.lerp(glowing.sky() / 15F, sky, 0xFF) << 16);
	}
}
