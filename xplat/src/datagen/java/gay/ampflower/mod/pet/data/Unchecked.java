package gay.ampflower.mod.pet.data;

import com.mojang.logging.LogUtils;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Ampflower
 * @since ${version}
 **/
public final class Unchecked {
	private static final Logger logger = LogUtils.getLogger();

	/**
	 * @noinspection unchecked
	 */
	public static <T> List<RegistryKey<T>> getRegistryKeys(Class<?> basic, RegistryKey<Registry<T>> sniffer) {
		return Unchecked.getStaticFieldValues(basic, (Class<RegistryKey<T>>) (Object) RegistryKey.class, key -> key.isOf(sniffer));
	}

	public static <T> List<T> getStaticFieldValues(Class<?> basic, Class<T> sniffer, Predicate<T> validater) {
		final var list = new ArrayList<T>();
		for (final var field : basic.getFields()) {
			if (!field.canAccess(null) || field.getType() != sniffer) {
				continue;
			}
			try {
				//noinspection unchecked
				final T t = (T) field.get(field);
				if (validater.test(t)) {
					list.add(t);
				}
			} catch (ReflectiveOperationException rogue) {
				logger.warn("Failed to fetch {}", field, rogue);
			}
		}

		return List.copyOf(list);
	}
}
