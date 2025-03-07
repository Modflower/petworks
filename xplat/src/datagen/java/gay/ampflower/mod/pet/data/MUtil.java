package gay.ampflower.mod.pet.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Ampflower
 * @noinspection OptionalUsedAsFieldOrParameterType
 * @since ${version}
 */
public final class MUtil {
	public static <T> void put(JsonObject json, String key, Optional<T> optional, Function<T, JsonElement> function) {
		put(json, key, optional.map(function));
	}

	public static void put(JsonObject json, String key, Optional<JsonElement> optional) {
		optional.ifPresent(value -> json.add(key, value));
	}

	public static <T> void putString(JsonObject json, String key, Optional<T> optional, Function<T, String> function) {
		putString(json, key, optional.map(function));
	}

	public static void putString(JsonObject json, String key, Optional<String> optional) {
		optional.ifPresent(value -> json.addProperty(key, value));
	}

	public static JsonElement toString(Object object) {
		return new JsonPrimitive(object.toString());
	}

	public static JsonElement toBoolean(Boolean object) {
		return new JsonPrimitive(object);
	}

	public static JsonElement toNumber(Number object) {
		return new JsonPrimitive(object);
	}
}
