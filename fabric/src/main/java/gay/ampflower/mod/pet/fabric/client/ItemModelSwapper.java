package gay.ampflower.mod.pet.fabric.client;

import com.mojang.logging.LogUtils;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class ItemModelSwapper {
	private static final Logger logger = LogUtils.getLogger();

	private static final Map<Item, DefaultingEnumMap<ModelTransformationMode, ModelIdentifier>> backing = new HashMap<>();

	private ItemModelSwapper() {
		throw new AssertionError("Sorry, what? No I can't allow that, have pets. :3");
	}

	public static void register(Item item, ModelIdentifier def, Map<ModelTransformationMode, ModelIdentifier> map) {
		final var none = map.get(ModelTransformationMode.NONE);
		if (none != null) {
			logger.warn("{} has NONE set to {}; use default for NONE.", item, none);
		}
		final var existing = backing.get(item);
		if (existing != null) {
			logger.warn("{} is overriding {} with {} => {}", item, existing, def, map, new Throwable());
		}
		backing.put(item, new DefaultingEnumMap<>(ModelTransformationMode.class, def, map));
	}

	public static Map<ModelTransformationMode, ModelIdentifier> append(Map<ModelTransformationMode, ModelIdentifier> entries, ModelIdentifier id, ModelTransformationMode... modes) {
		if (entries == null) {
			entries = new EnumMap<>(ModelTransformationMode.class);
		}
		for (final var mode : modes) {
			entries.put(mode, id);
		}
		return entries;
	}

	public static ModelIdentifier get(Item item, ModelTransformationMode mode) {
		final var models = backing.get(item);
		if (models == null) {
			return null;
		}
		return models.get(mode);
	}

	public static ModelIdentifier getDefault(Item item) {
		final var models = backing.get(item);
		if (models == null) {
			return null;
		}
		return models.def;
	}

	public static @Nullable ModelIdentifier getOrElse(Item item, ModelTransformationMode mode, @Nullable ModelIdentifier fallback) {
		final var models = backing.get(item);
		if (models == null) {
			return fallback;
		}
		final var ret = models.getRaw(mode);
		if (ret == null) {
			return fallback;
		}
		return ret;
	}

	private static final class DefaultingEnumMap<K extends Enum<K>, V> {
		private final Object[] values;
		private final V def;

		DefaultingEnumMap(Class<K> ref, V def, Map<K, V> map) {
			if (!ref.isEnum()) {
				throw new AssertionError(ref);
			}
			final var refSet = EnumSet.allOf(ref);
			final var values = new Object[refSet.size()];
			for (final var entry : map.entrySet()) {
				final var key = entry.getKey();
				if (!refSet.contains(key)) {
					throw new IllegalArgumentException(String.format("%s incompat with %s", entry, ref));
				}
				final var oldValue = values[key.ordinal()];
				if (oldValue != null) {
					throw new IllegalArgumentException("Already seen " + key + ": " + entry + " overrides " + oldValue);
				}
				values[entry.getKey().ordinal()] = Objects.requireNonNull(entry.getValue(),
					() -> (entry + " contains null"));
			}

			this.values = values;
			this.def = def;
		}

		public V get(K k) {
			return Objects.requireNonNullElse(getRaw(k), def);
		}

		public V getRaw(K k) {
			return (V) values[k.ordinal()];
		}

		public V getOrDefault(K k, V def) {
			return Objects.requireNonNullElse(getRaw(k), def);
		}

		@Override
		public String toString() {
			return "DefaultingEnumMap{" +
				"values=" + Arrays.toString(values) +
				", def=" + def +
				'}';
		}
	}
}
