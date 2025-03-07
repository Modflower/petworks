package gay.ampflower.mod.pet.data.primitives;

import com.google.gson.JsonObject;
import gay.ampflower.mod.pet.data.MUtil;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;

/**
 * @author Ampflower
 * @since ${version}
 **/
public class GuiModel extends ExtendedModel {
	public final Optional<GuiLight> guiLight;

	public GuiModel(final Optional<Identifier> parent, final Optional<String> variant,
						 final Optional<GuiLight> guiLight,
						 final TextureKey... requiredTextureKeys) {
		super(parent, variant, requiredTextureKeys);
		this.guiLight = guiLight;
	}

	@Override
	public void injectJson(final Identifier id, final Map<TextureKey, Identifier> textures, final JsonObject json) {
		MUtil.putString(json, "gui_light", guiLight, GuiLight::getType);
	}
}
