package gay.ampflower.mod.pet.registry;

import gay.ampflower.mod.pet.util.Pivot;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

/**
 * @author Ampflower
 * @since 0.2.0
 **/
public final class PetworksItemGroups {
	public static final ItemGroup COLLAR_GROUP = register("collars", Pivot.INSTANCE.createItemGroupBuilder()
		.icon(PetworksItems.PRIDE_COLLAR::getDefaultStack)
		.displayName(Text.translatable("itemGroup.petworks.collars"))
		.entries((context, entries) -> Pivot.registerCollars(entries::add))
		.build());

	public static final ItemGroup GUISE_GROUP = register("guises", Pivot.INSTANCE.createItemGroupBuilder()
		.icon(PetworksItems.TAMED_CAT_GUISE::getDefaultStack)
		.displayName(Text.translatable("itemGroup.petworks.guises"))
		.entries((context, entries) -> Pivot.registerGuises(entries::add))
		.build());

	private static ItemGroup register(final String id, final ItemGroup itemGroup) {
		return Pivot.INSTANCE.register(RegistryKeys.ITEM_GROUP, id, itemGroup);
	}

	public static void init() {
	}
}
