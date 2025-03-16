package gay.ampflower.mod.pet.registry.tag;

import gay.ampflower.mod.pet.util.Util;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class PetworksItemTags {
	public static final TagKey<Item> COLLARS = TagKey.of(RegistryKeys.ITEM, Util.id("collars"));
	public static final TagKey<Item> SHEARABLE_COLLARS = TagKey.of(RegistryKeys.ITEM, Util.id("shearable_collars"));
	public static final TagKey<Item> DYEABLE_COLLARS = TagKey.of(RegistryKeys.ITEM, Util.id("dyeable_collars"));
	public static final TagKey<Item> ARMOR_COLLARS = TagKey.of(RegistryKeys.ITEM, Util.id("armor_collars"));
	public static final TagKey<Item> GUISES = TagKey.of(RegistryKeys.ITEM, Util.id("guises"));
	public static final TagKey<Item> BRACELETS = TagKey.of(RegistryKeys.ITEM, Util.id("bracelets"));

	public static final TagKey<Item> TRINKETS_COLLAR = TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "chest/collar"));
	public static final TagKey<Item> TRINKETS_GUISE = TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "chest/guise"));
	public static final TagKey<Item> TRINKETS_HAND_BRACELET = TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "hand/bracelet"));
	public static final TagKey<Item> TRINKETS_OFFHAND_BRACELET = TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "offhand/bracelet"));
	public static final TagKey<Item> TRINKETS_AGLET = TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "feet/aglet"));

	public static final TagKey<Item> CURIOS_COLLAR = TagKey.of(RegistryKeys.ITEM, Identifier.of("curios", "collar"));
	public static final TagKey<Item> CURIOS_GUISE = TagKey.of(RegistryKeys.ITEM, Identifier.of("curios", "guise"));
	public static final TagKey<Item> CURIOS_BRACELET = TagKey.of(RegistryKeys.ITEM, Identifier.of("curios", "bracelet"));


	public static final TagKey<Item> COLLAR_ENCHANTABLE = TagKey.of(RegistryKeys.ITEM, Util.id("enchantable/collar"));
	public static final TagKey<Item> WEARABLE_ENCHANTABLE = TagKey.of(RegistryKeys.ITEM, Util.id("enchantable/wearable"));

	public static final TagKey<Item> EQUIPPABLE = ItemTags.EQUIPPABLE_ENCHANTABLE;
	public static final TagKey<Item> HEAD_ARMOR = ItemTags.HEAD_ARMOR_ENCHANTABLE;
}
