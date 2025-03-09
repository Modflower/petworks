package gay.ampflower.mod.pet.registry;

import gay.ampflower.mod.pet.block.CollarBlock;
import gay.ampflower.mod.pet.block.WallCollarBlock;
import gay.ampflower.mod.pet.item.CollarMaterials;
import gay.ampflower.mod.pet.util.Pivot;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class PetworksBlocks {
	public static final Block
		MAID_COLLAR = register("maid_collar", new CollarBlock(CollarMaterials.CLOTH)),
		MAID_COLLAR_WALL = register("maid_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	COLLAR = register("collar", new CollarBlock(CollarMaterials.CLOTH)),
		COLLAR_WALL = register("collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	MISSING_COLLAR = register("missing_collar", new CollarBlock(CollarMaterials.CLOTH)),
		MISSING_COLLAR_WALL = register("missing_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	PRIDE_COLLAR = register("pride_collar", new CollarBlock(CollarMaterials.CLOTH)),
		PRIDE_COLLAR_WALL = register("pride_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	TRANS_COLLAR = register("trans_collar", new CollarBlock(CollarMaterials.CLOTH)),
		TRANS_COLLAR_WALL = register("trans_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	LESBIAN_COLLAR = register("lesbian_collar", new CollarBlock(CollarMaterials.CLOTH)),
		LESBIAN_COLLAR_WALL = register("lesbian_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	GAY_COLLAR = register("gay_collar", new CollarBlock(CollarMaterials.CLOTH)),
		GAY_COLLAR_WALL = register("gay_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	GENDERQUEER_COLLAR = register("genderqueer_collar", new CollarBlock(CollarMaterials.CLOTH)),
		GENDERQUEER_COLLAR_WALL = register("genderqueer_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	GENDERFLUID_COLLAR = register("genderfluid_collar", new CollarBlock(CollarMaterials.CLOTH)),
		GENDERFLUID_COLLAR_WALL = register("genderfluid_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	AGENDER_COLLAR = register("agender_collar", new CollarBlock(CollarMaterials.CLOTH)),
		AGENDER_COLLAR_WALL = register("agender_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	BIGENDER_COLLAR = register("bigender_collar", new CollarBlock(CollarMaterials.CLOTH)),
		BIGENDER_COLLAR_WALL = register("bigender_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	DEMIBOY_1_COLLAR = register("demiboy_1_collar", new CollarBlock(CollarMaterials.CLOTH)),
		DEMIBOY_1_COLLAR_WALL = register("demiboy_1_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	DEMIBOY_2_COLLAR = register("demiboy_2_collar", new CollarBlock(CollarMaterials.CLOTH)),
		DEMIBOY_2_COLLAR_WALL = register("demiboy_2_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	DEMIGENDER_COLLAR = register("demigender_collar", new CollarBlock(CollarMaterials.CLOTH)),
		DEMIGENDER_COLLAR_WALL = register("demigender_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	DEMIGIRL_1_COLLAR = register("demigirl_1_collar", new CollarBlock(CollarMaterials.CLOTH)),
		DEMIGIRL_1_COLLAR_WALL = register("demigirl_1_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	DEMIGIRL_2_COLLAR = register("demigirl_2_collar", new CollarBlock(CollarMaterials.CLOTH)),
		DEMIGIRL_2_COLLAR_WALL = register("demigirl_2_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	INTERSEX_1_COLLAR = register("intersex_1_collar", new CollarBlock(CollarMaterials.CLOTH)),
		INTERSEX_1_COLLAR_WALL = register("intersex_1_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	INTERSEX_2_COLLAR = register("intersex_2_collar", new CollarBlock(CollarMaterials.CLOTH)),
		INTERSEX_2_COLLAR_WALL = register("intersex_2_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	ARO_COLLAR = register("aro_collar", new CollarBlock(CollarMaterials.CLOTH)),
		ARO_COLLAR_WALL = register("aro_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	ACE_COLLAR = register("ace_collar", new CollarBlock(CollarMaterials.CLOTH)),
		ACE_COLLAR_WALL = register("ace_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	AROACE_COLLAR = register("aroace_collar", new CollarBlock(CollarMaterials.CLOTH)),
		AROACE_COLLAR_WALL = register("aroace_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	APLATONIC_COLLAR = register("aplatonic_collar", new CollarBlock(CollarMaterials.CLOTH)),
		APLATONIC_COLLAR_WALL = register("aplatonic_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	PAN_COLLAR = register("pan_collar", new CollarBlock(CollarMaterials.CLOTH)),
		PAN_COLLAR_WALL = register("pan_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	CLOTH_COLLAR = register("cloth_collar", new CollarBlock(CollarMaterials.CLOTH)),
		CLOTH_COLLAR_WALL = register("cloth_collar_wall", new WallCollarBlock(CollarMaterials.CLOTH)),

	HIDE_COLLAR = register("hide_collar", new CollarBlock(CollarMaterials.HIDE)),
		HIDE_COLLAR_WALL = register("hide_collar_wall", new WallCollarBlock(CollarMaterials.HIDE)),

	LEATHER_COLLAR = register("leather_collar", new CollarBlock(CollarMaterials.LEATHER)),
		LEATHER_COLLAR_WALL = register("leather_collar_wall", new WallCollarBlock(CollarMaterials.LEATHER)),

	CHAIN_COLLAR = register("chain_collar", new CollarBlock(CollarMaterials.CHAIN)),
		CHAIN_COLLAR_WALL = register("chain_collar_wall", new WallCollarBlock(CollarMaterials.CHAIN)),

	IRON_COLLAR = register("iron_collar", new CollarBlock(CollarMaterials.IRON)),
		IRON_COLLAR_WALL = register("iron_collar_wall", new WallCollarBlock(CollarMaterials.IRON)),

	GOLD_COLLAR = register("gold_collar", new CollarBlock(CollarMaterials.GOLD)),
		GOLD_COLLAR_WALL = register("gold_collar_wall", new WallCollarBlock(CollarMaterials.GOLD)),

	COPPER_COLLAR = register("copper_collar", new CollarBlock(CollarMaterials.COPPER)),
		COPPER_COLLAR_WALL = register("copper_collar_wall", new WallCollarBlock(CollarMaterials.COPPER)),

	QUARTZ_COLLAR = register("quartz_collar", new CollarBlock(CollarMaterials.QUARTZ)),
		QUARTZ_COLLAR_WALL = register("quartz_collar_wall", new WallCollarBlock(CollarMaterials.QUARTZ)),

	AMETHYST_COLLAR = register("amethyst_collar", new CollarBlock(CollarMaterials.AMETHYST)),
		AMETHYST_COLLAR_WALL = register("amethyst_collar_wall", new WallCollarBlock(CollarMaterials.AMETHYST)),

	EMERALD_COLLAR = register("emerald_collar", new CollarBlock(CollarMaterials.EMERALD)),
		EMERALD_COLLAR_WALL = register("emerald_collar_wall", new WallCollarBlock(CollarMaterials.EMERALD)),

	DIAMOND_COLLAR = register("diamond_collar", new CollarBlock(CollarMaterials.DIAMOND)),
		DIAMOND_COLLAR_WALL = register("diamond_collar_wall", new WallCollarBlock(CollarMaterials.DIAMOND)),

	NETHERITE_COLLAR = register("netherite_collar", new CollarBlock(CollarMaterials.NETHERITE)),
		NETHERITE_COLLAR_WALL = register("netherite_collar_wall", new WallCollarBlock(CollarMaterials.NETHERITE)),

	OBSIDIAN_COLLAR = register("obsidian_collar", new CollarBlock(CollarMaterials.OBSIDIAN, Settings.create().hardness(16F).resistance(32768F))),
		OBSIDIAN_COLLAR_WALL = register("obsidian_collar_wall", new WallCollarBlock(CollarMaterials.OBSIDIAN, Settings.create().hardness(16F).resistance(32768F))),

	BEDROCK_COLLAR = register("bedrock_collar", new CollarBlock(CollarMaterials.BEDROCK, Settings.create().hardness(16F).resistance(32768F))),
		BEDROCK_COLLAR_WALL = register("bedrock_collar_wall", new WallCollarBlock(CollarMaterials.BEDROCK, Settings.create().hardness(16F).resistance(32768F)));


	private static Block register(String id, Block block) {
		return Pivot.INSTANCE.register(RegistryKeys.BLOCK, id, block);
	}

	public static void init() {
	}
}
