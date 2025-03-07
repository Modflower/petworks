package gay.ampflower.mod.pet.util;

import com.mojang.datafixers.util.Either;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class Util {
	public static final Random rng = new Random();
	public static final String MODID = "petworks";

	public static Identifier id(String path) {
		return Identifier.of(MODID, path);
	}

	public static Identifier synthetic(String type, String path) {
		return Identifier.of(MODID, '/' + type + '/' + path);
	}

	public static Identifier itemId(String path) {
		return Identifier.of(MODID, "item/" + path);
	}

	public static Identifier blockId(String path) {
		return Identifier.of(MODID, "block/" + path);
	}

	public static String slashify(Identifier id) {
		return id.getNamespace() + '/' + id.getPath();
	}

	public static String slashify(RegistryEntry<?> entry) {
		return entry.getKey().map(RegistryKey::getValue).map(Util::slashify).orElseThrow();
	}

	public static String slashify(TagKey<?> tagKey) {
		return Util.slashify(tagKey.id());
	}

	public static Identifier mux(Identifier base, String other) {
		return id(slashify(base) + '/' + other);
	}

	public static Identifier mux(Identifier base, Identifier other) {
		return mux(base, slashify(other));
	}

	public static Identifier mux(Identifier base, RegistryEntry<?> entry) {
		return mux(base, entry.getKey().orElseThrow().getValue());
	}


	public static String idString(String path) {
		return MODID + ':' + path;
	}

	public static int dyeColor(DyeColor colour) {
		return colour.getEntityColor();
	}

	public static <T> Stream<RegistryEntry<T>> iterate(TagKey<T> tag) {
		final var registry = Unchecked.fetch(Registries.REGISTRIES, tag.registry());
		if (registry == null) {
			return Stream.empty();
		}
		return registry.getEntryList(tag).map(RegistryEntryList.Named::stream).orElse(Stream.empty());
	}

	public static <T> T unbox(Either<T, T> either) {
		return either.map(Function.identity(), Function.identity());
	}

	public static <K, V, T, A, C> C map(Map<K, V> map, BiFunction<K, V, T> function, Collector<? super T, A, C> collector) {
		final A a = collector.supplier().get();
		final var accumulator = collector.accumulator();

		for (final var entry : map.entrySet()) {
			accumulator.accept(a, function.apply(entry.getKey(), entry.getValue()));
		}

		return collector.finisher().apply(a);
	}

	@NotNull
	public static NbtCompound safeCopy(final @Nullable NbtCompound original) {
		if (original == null) {
			return new NbtCompound();
		}
		return original.copy();
	}

	public static <T, A, C extends Iterable<T>> C appendToCopy(Iterable<T> ledger, Collector<? super T, A, C> collector, T... amendments) {
		final var a = collector.supplier().get();
		final var accumulator = collector.accumulator();

		for (final var entry : ledger) {
			accumulator.accept(a, entry);
		}

		for (final var entry : amendments) {
			accumulator.accept(a, entry);
		}

		return collector.finisher().apply(a);
	}

	public static <T> Set<T> appendToCopy(Set<T> ledger, Iterable<T> amendments) {
		final var set = new HashSet<>(ledger);
		for (final var entry : amendments) {
			set.add(entry);
		}
		return Set.copyOf(set);
	}

	public static <T> Set<T> appendToCopy(Set<T> ledger, T... amendments) {
		final var set = new HashSet<>(ledger);
		set.addAll(Arrays.asList(amendments));
		return Set.copyOf(set);
	}

	public static <T> List<T> appendToCopy(Collection<T> ledger, Collection<T> amendments) {
		final T[] array = ledger.toArray(Unchecked.array(ledger.size() + amendments.size()));

		System.arraycopy(Unchecked.toArray(amendments, Unchecked::array), 0, array, ledger.size(), amendments.size());
		return List.of(array);
	}

	public static <T> List<T> appendToCopy(List<T> ledger, T... amendments) {
		final T[] array = ledger.toArray(Unchecked.array(ledger.size() + amendments.length));
		System.arraycopy(amendments, 0, array, ledger.size(), amendments.length);
		return List.of(array);
	}

	//

	public static <T> Set<T> removeInCopy(Set<T> ledger, Iterable<T> amendments) {
		final var set = new HashSet<>(ledger);
		boolean m = false;
		for (final var entry : amendments) {
			m |= set.remove(entry);
		}
		if (!m) {
			return ledger;
		}
		return Set.copyOf(set);
	}

	public static <T> Set<T> removeInCopy(Set<T> ledger, T... amendments) {
		return removeInCopy(ledger, (Iterable<T>) Arrays.asList(amendments));
	}

	public static <T> List<T> removeInCopy(Collection<T> ledger, Collection<T> amendments) {
		final var list = new ArrayList<>(ledger);
		if (!list.removeAll(amendments)) {
			return List.copyOf(ledger);
		}
		return List.copyOf(list);
	}

	public static <T> List<T> removeInCopy(List<T> ledger, T... amendments) {
		return removeInCopy(ledger, Arrays.asList(amendments));
	}

	public static <T> T getRandom(List<T> list, Random random) {
		return list.get(random.nextInt(list.size()));
	}

	public static <T> boolean has(Iterable<T> iterable, Predicate<T> predicate) {
		for (final T t : iterable) {
			if (predicate.test(t)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAdventure(final PlayerEntity player, final Position pos) {
		if (player instanceof ServerPlayerEntity serverPlayer) {
			return serverPlayer.isBlockBreakingRestricted(serverPlayer.getServerWorld(), BlockPos.ofFloored(pos), serverPlayer.interactionManager.getGameMode());
		}
		return true;
	}
}
