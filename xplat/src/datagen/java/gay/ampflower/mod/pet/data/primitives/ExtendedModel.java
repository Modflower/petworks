package gay.ampflower.mod.pet.data.primitives;

import com.google.gson.JsonObject;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;

/**
 * @author Ampflower
 * @since ${version}
 **/
public abstract class ExtendedModel extends Model {
	public ExtendedModel(final Optional<Identifier> parent, final Optional<String> variant, final TextureKey... requiredTextureKeys) {
		super(parent, variant, requiredTextureKeys);
	}

	@Override
	public final JsonObject createJson(final Identifier id, final Map<TextureKey, Identifier> textures) {
		final var json = super.createJson(id, textures);
		injectJson(id, textures, json);
		return json;
	}

	public abstract void injectJson(Identifier id, Map<TextureKey, Identifier> textures, JsonObject json);
}
