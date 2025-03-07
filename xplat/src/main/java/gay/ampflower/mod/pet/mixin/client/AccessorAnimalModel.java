package gay.ampflower.mod.pet.mixin.client;

import net.minecraft.client.render.entity.model.AnimalModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
@Mixin(AnimalModel.class)
public interface AccessorAnimalModel {
	@Accessor
	boolean isHeadScaled();

	@Accessor
	float getChildHeadYOffset();

	@Accessor
	float getChildHeadZOffset();

	@Accessor
	float getInvertedChildHeadScale();

	@Accessor
	float getInvertedChildBodyScale();

	@Accessor
	float getChildBodyYOffset();
}
