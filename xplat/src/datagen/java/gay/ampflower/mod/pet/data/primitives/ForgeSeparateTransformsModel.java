package gay.ampflower.mod.pet.data.primitives;

import com.google.gson.JsonObject;
import gay.ampflower.mod.pet.data.MUtil;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;

/**
 * @author Ampflower
 * @since ${version}
 **/
public class ForgeSeparateTransformsModel extends GuiModel {
	public final Optional<String> loader;
	public final Optional<Model> base;
	public final Map<String, Model> perspectives;

	public ForgeSeparateTransformsModel(final Optional<Identifier> parent,
													final Optional<String> variant,
													final Optional<String> loader,
													final Optional<GuiLight> light,
													final Optional<Model> base,
													final Map<String, Model> map,
													final TextureKey... requiredTextureKeys) {
		super(parent, variant, light, requiredTextureKeys);
		this.loader = loader;
		this.base = base;
		this.perspectives = Map.copyOf(map);
	}

	@Override
	public void injectJson(final Identifier id, final Map<TextureKey, Identifier> textures, final JsonObject json) {
		super.injectJson(id, textures, json);

		MUtil.putString(json, "loader", loader);
		MUtil.put(json, "base", base, mdl -> mdl.createJson(id, textures));
		if (!perspectives.isEmpty()) {
			final var subJson = new JsonObject();
			perspectives.forEach((k, v) -> subJson.add(k, v.createJson(id, textures)));
			json.add("perspectives", subJson);
		}
	}
}
