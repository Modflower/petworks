package gay.ampflower.mod.pet.fabric.mixin.client;

import gay.ampflower.mod.pet.fabric.ClientMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Ampflower
 * @since 0.1.1
 **/
@Mixin(ModelLoader.class)
@Environment(EnvType.CLIENT)
public class MixinModelLoader {
	@Shadow
	private native void loadItemModel(ModelIdentifier $);

	// Fabric API method is insufficient.
	@Inject(method = "<init>", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/render/model/ModelLoader;modelsToBake:Ljava/util/Map;"))
	private void onInit(CallbackInfo ci) {
		for (final var collar : ClientMain.manualLoadModels) {
			this.loadItemModel(collar);
		}
	}
}
