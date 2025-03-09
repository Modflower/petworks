package gay.ampflower.mod.pet.registry;

import gay.ampflower.mod.pet.item.CollarDyeableItem;
import gay.ampflower.mod.pet.item.CollarItem;
import gay.ampflower.mod.pet.item.CollarMaterial;
import gay.ampflower.mod.pet.item.CollarMaterials;
import gay.ampflower.mod.pet.item.FoxGuiseItem;
import gay.ampflower.mod.pet.item.RigidGuiseItem;
import gay.ampflower.mod.pet.item.StrayCatGuiseItem;
import gay.ampflower.mod.pet.item.TamedCatGuiseItem;
import gay.ampflower.mod.pet.item.WolfGuiseItem;
import gay.ampflower.mod.pet.util.Pivot;
import net.minecraft.block.Block;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;

import static net.minecraft.sound.SoundEvents.ENTITY_OCELOT_AMBIENT;
import static net.minecraft.sound.SoundEvents.ENTITY_OCELOT_DEATH;
import static net.minecraft.sound.SoundEvents.ENTITY_OCELOT_HURT;
import static net.minecraft.sound.SoundEvents.ENTITY_RABBIT_AMBIENT;
import static net.minecraft.sound.SoundEvents.ENTITY_RABBIT_ATTACK;
import static net.minecraft.sound.SoundEvents.ENTITY_RABBIT_DEATH;
import static net.minecraft.sound.SoundEvents.ENTITY_RABBIT_HURT;
import static net.minecraft.sound.SoundEvents.ENTITY_RABBIT_JUMP;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class PetworksItems {
	public static final Item
		MAID_COLLAR = registerDyeableCollar("maid_collar", CollarMaterials.MAID,
		PetworksBlocks.MAID_COLLAR, PetworksBlocks.MAID_COLLAR_WALL),
		COLLAR = registerDyeableCollar("collar", CollarMaterials.CLOTH,
			PetworksBlocks.COLLAR, PetworksBlocks.COLLAR_WALL),
		WORLDWIDEPIXEL_COLLAR = registerCollar("worldwidepixel_collar", CollarMaterials.CLOTH,
			PetworksBlocks.WORLDWIDEPIXEL_COLLAR, PetworksBlocks.WORLDWIDEPIXEL_COLLAR_WALL),
		MISSING_COLLAR = registerCollar("missing_collar", CollarMaterials.CLOTH,
			PetworksBlocks.MISSING_COLLAR, PetworksBlocks.MISSING_COLLAR_WALL),
		PRIDE_COLLAR = registerCollar("pride_collar", CollarMaterials.CLOTH,
			PetworksBlocks.PRIDE_COLLAR, PetworksBlocks.PRIDE_COLLAR_WALL),
		TRANS_COLLAR = registerCollar("trans_collar", CollarMaterials.CLOTH,
			PetworksBlocks.TRANS_COLLAR, PetworksBlocks.TRANS_COLLAR_WALL),
		LESBIAN_COLLAR = registerCollar("lesbian_collar", CollarMaterials.CLOTH,
			PetworksBlocks.LESBIAN_COLLAR, PetworksBlocks.LESBIAN_COLLAR_WALL),
		GAY_COLLAR = registerCollar("gay_collar", CollarMaterials.CLOTH,
			PetworksBlocks.GAY_COLLAR, PetworksBlocks.GAY_COLLAR_WALL),
		GENDERQUEER_COLLAR = registerCollar("genderqueer_collar", CollarMaterials.CLOTH,
			PetworksBlocks.GENDERQUEER_COLLAR, PetworksBlocks.GENDERQUEER_COLLAR_WALL),
		GENDERFLUID_COLLAR = registerCollar("genderfluid_collar", CollarMaterials.CLOTH,
			PetworksBlocks.GENDERFLUID_COLLAR, PetworksBlocks.GENDERFLUID_COLLAR_WALL),
		AGENDER_COLLAR = registerCollar("agender_collar", CollarMaterials.CLOTH,
			PetworksBlocks.AGENDER_COLLAR, PetworksBlocks.AGENDER_COLLAR_WALL),
		BIGENDER_COLLAR = registerCollar("bigender_collar", CollarMaterials.CLOTH,
			PetworksBlocks.BIGENDER_COLLAR, PetworksBlocks.BIGENDER_COLLAR_WALL),
		DEMIBOY_1_COLLAR = registerCollar("demiboy_1_collar", CollarMaterials.CLOTH,
			PetworksBlocks.DEMIBOY_1_COLLAR, PetworksBlocks.DEMIBOY_1_COLLAR_WALL),
		DEMIBOY_2_COLLAR = registerCollar("demiboy_2_collar", CollarMaterials.CLOTH,
			PetworksBlocks.DEMIBOY_2_COLLAR, PetworksBlocks.DEMIBOY_2_COLLAR_WALL),
		DEMIGENDER_COLLAR = registerCollar("demigender_collar", CollarMaterials.CLOTH,
			PetworksBlocks.DEMIGENDER_COLLAR, PetworksBlocks.DEMIGENDER_COLLAR_WALL),
		DEMIGIRL_1_COLLAR = registerCollar("demigirl_1_collar", CollarMaterials.CLOTH,
			PetworksBlocks.DEMIGIRL_1_COLLAR, PetworksBlocks.DEMIGIRL_1_COLLAR_WALL),
		DEMIGIRL_2_COLLAR = registerCollar("demigirl_2_collar", CollarMaterials.CLOTH,
			PetworksBlocks.DEMIGIRL_2_COLLAR, PetworksBlocks.DEMIGIRL_2_COLLAR_WALL),
		INTERSEX_1_COLLAR = registerCollar("intersex_1_collar", CollarMaterials.CLOTH,
			PetworksBlocks.INTERSEX_1_COLLAR, PetworksBlocks.INTERSEX_1_COLLAR_WALL),
		INTERSEX_2_COLLAR = registerCollar("intersex_2_collar", CollarMaterials.CLOTH,
			PetworksBlocks.INTERSEX_2_COLLAR, PetworksBlocks.INTERSEX_2_COLLAR_WALL),
		ARO_COLLAR = registerCollar("aro_collar", CollarMaterials.CLOTH,
			PetworksBlocks.ARO_COLLAR, PetworksBlocks.ARO_COLLAR_WALL),
		ACE_COLLAR = registerCollar("ace_collar", CollarMaterials.CLOTH,
			PetworksBlocks.ACE_COLLAR, PetworksBlocks.ACE_COLLAR_WALL),
		AROACE_COLLAR = registerCollar("aroace_collar", CollarMaterials.CLOTH,
			PetworksBlocks.AROACE_COLLAR, PetworksBlocks.AROACE_COLLAR_WALL),
		APLATONIC_COLLAR = registerCollar("aplatonic_collar", CollarMaterials.CLOTH,
			PetworksBlocks.APLATONIC_COLLAR, PetworksBlocks.APLATONIC_COLLAR_WALL),
		PAN_COLLAR = registerCollar("pan_collar", CollarMaterials.CLOTH,
			PetworksBlocks.PAN_COLLAR, PetworksBlocks.PAN_COLLAR_WALL),
		CLOTH_COLLAR = registerDyeableCollar("cloth_collar", CollarMaterials.CLOTH,
			PetworksBlocks.CLOTH_COLLAR, PetworksBlocks.CLOTH_COLLAR_WALL),
		HIDE_COLLAR = registerDyeableCollar("hide_collar", CollarMaterials.HIDE,
			PetworksBlocks.HIDE_COLLAR, PetworksBlocks.HIDE_COLLAR_WALL),
		LEATHER_COLLAR = registerDyeableCollar("leather_collar", CollarMaterials.LEATHER,
			PetworksBlocks.LEATHER_COLLAR, PetworksBlocks.LEATHER_COLLAR_WALL),
		CHAIN_COLLAR = registerDyeableCollar("chain_collar", CollarMaterials.CHAIN,
			PetworksBlocks.CHAIN_COLLAR, PetworksBlocks.CHAIN_COLLAR_WALL),
		IRON_COLLAR = registerCollar("iron_collar", CollarMaterials.IRON,
			PetworksBlocks.IRON_COLLAR, PetworksBlocks.IRON_COLLAR_WALL),
		GOLD_COLLAR = registerCollar("gold_collar", CollarMaterials.GOLD,
			PetworksBlocks.GOLD_COLLAR, PetworksBlocks.GOLD_COLLAR_WALL),
		COPPER_COLLAR = registerCollar("copper_collar", CollarMaterials.COPPER,
			PetworksBlocks.COPPER_COLLAR, PetworksBlocks.COPPER_COLLAR_WALL),
		QUARTZ_COLLAR = registerCollar("quartz_collar", CollarMaterials.QUARTZ,
			PetworksBlocks.QUARTZ_COLLAR, PetworksBlocks.QUARTZ_COLLAR_WALL),
		AMETHYST_COLLAR = registerCollar("amethyst_collar", CollarMaterials.AMETHYST,
			PetworksBlocks.AMETHYST_COLLAR, PetworksBlocks.AMETHYST_COLLAR_WALL),
		EMERALD_COLLAR = registerCollar("emerald_collar", CollarMaterials.EMERALD,
			PetworksBlocks.EMERALD_COLLAR, PetworksBlocks.EMERALD_COLLAR_WALL),
		DIAMOND_COLLAR = registerCollar("diamond_collar", CollarMaterials.DIAMOND,
			PetworksBlocks.DIAMOND_COLLAR, PetworksBlocks.DIAMOND_COLLAR_WALL),
		NETHERITE_COLLAR = registerCollar("netherite_collar", CollarMaterials.NETHERITE,
			PetworksBlocks.NETHERITE_COLLAR, PetworksBlocks.NETHERITE_COLLAR_WALL),
		OBSIDIAN_COLLAR = registerCollar("obsidian_collar", CollarMaterials.OBSIDIAN,
			PetworksBlocks.OBSIDIAN_COLLAR, PetworksBlocks.OBSIDIAN_COLLAR_WALL),
		BEDROCK_COLLAR = registerDyeableCollar("bedrock_collar", CollarMaterials.BEDROCK,
			PetworksBlocks.BEDROCK_COLLAR, PetworksBlocks.BEDROCK_COLLAR_WALL);

	public static final Item
		WOLF_GUISE = register("wolf_guise", new WolfGuiseItem()),
		TAMED_CAT_GUISE = register("tamed_cat_guise", new TamedCatGuiseItem()),
		STRAY_CAT_GUISE = register("stray_cat_guise", new StrayCatGuiseItem()),
		OCELOT_GUISE = register("ocelot_guise", RigidGuiseItem.ofOcelot(Items.COOKED_COD, 15720061, 5653556, ENTITY_OCELOT_AMBIENT, ENTITY_OCELOT_HURT, ENTITY_OCELOT_DEATH, 900)),
		FOX_GUISE = register("fox_guise", new FoxGuiseItem()),
		RABBIT_GUISE = register("rabbit_guise", RigidGuiseItem.ofRabbit(Items.GOLDEN_CARROT, 10051392, 755512, ENTITY_RABBIT_JUMP, ENTITY_RABBIT_ATTACK, ENTITY_RABBIT_AMBIENT, ENTITY_RABBIT_HURT, ENTITY_RABBIT_DEATH
		));

	private static Item register(String id, Item item) {
		return Pivot.INSTANCE.register(RegistryKeys.ITEM, id, item);
	}

	private static Item.Settings makeSettings(CollarMaterial material) {
		final var settings = new Item.Settings();
		if (material == CollarMaterials.NETHERITE) {
			settings.fireproof();
		}
		if (material.getDurability() != 0) {
			settings.maxDamage(material.getDurability());
		}
		settings.maxCount(1);
		return settings;
	}

	private static Item registerCollar(String id, CollarMaterial material, Block standing, Block wall) {
		final var item = register(id, new CollarItem(material, standing, wall, makeSettings(material)));
		CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put(item, CauldronBehavior.CLEAN_DYEABLE_ITEM);
		return item;
	}

	private static Item registerDyeableCollar(String id, CollarMaterial material, Block standing, Block wall) {
		final var item = register(id, new CollarDyeableItem(material, standing, wall, makeSettings(material)));
		CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put(item, CauldronBehavior.CLEAN_DYEABLE_ITEM);
		return item;
	}

	public static void init() {
	}
}
