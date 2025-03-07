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
