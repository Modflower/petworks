package gay.ampflower.mod.pet.util;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

import java.util.Collection;
import java.util.function.IntFunction;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
final class Unchecked {
	public static <T> T[] array(int length) {
		return array(new Object[length]);
	}

	@SuppressWarnings({"unchecked", "SuspiciousArrayCast"})
	public static <T, V extends T> V[] array(T[] array) {
		return (V[]) array;
	}

	public static <T, V extends T> V[] toArray(Collection<V> collection, IntFunction<T[]> supplier) {
		return collection.toArray(array(supplier.apply(collection.size())));
	}

	public static <T> T fetch(Registry<?> root, RegistryKey<T> key) {
		return ((Registry<T>) root).get(key);
	}
}
