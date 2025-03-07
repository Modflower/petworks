package gay.ampflower.mod.pet.client;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class HeadRendererSuppressor {
	public static final Set<Item> suppressed = new HashSet<>();

	public static void register(ItemConvertible... items) {
		for (final var item : items) {
			suppressed.add(item.asItem());
		}
	}
}
