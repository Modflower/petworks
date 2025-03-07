package gay.ampflower.mod.pet.registry;

import gay.ampflower.mod.pet.block.entity.CollarBlockEntity;
import gay.ampflower.mod.pet.util.Pivot;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.RegistryKeys;

import static gay.ampflower.mod.pet.registry.PetworksBlocks.AMETHYST_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.AMETHYST_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.BEDROCK_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.BEDROCK_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.CHAIN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.CHAIN_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.CLOTH_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.CLOTH_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.COPPER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.COPPER_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DIAMOND_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DIAMOND_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.EMERALD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.EMERALD_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GOLD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GOLD_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.HIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.HIDE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.IRON_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.IRON_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.LEATHER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.LEATHER_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.MAID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.MAID_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.NETHERITE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.NETHERITE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.OBSIDIAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.OBSIDIAN_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.QUARTZ_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.QUARTZ_COLLAR_WALL;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class PetworksBlockEntities {
	public static final BlockEntityType<CollarBlockEntity>
		COLLAR = register("collar", CollarBlockEntity::new,
		MAID_COLLAR, MAID_COLLAR_WALL,
		CLOTH_COLLAR, CLOTH_COLLAR_WALL,
		HIDE_COLLAR, HIDE_COLLAR_WALL,
		LEATHER_COLLAR, LEATHER_COLLAR_WALL,
		CHAIN_COLLAR, CHAIN_COLLAR_WALL,
		IRON_COLLAR, IRON_COLLAR_WALL,
		GOLD_COLLAR, GOLD_COLLAR_WALL,
		COPPER_COLLAR, COPPER_COLLAR_WALL,
		QUARTZ_COLLAR, QUARTZ_COLLAR_WALL,
		AMETHYST_COLLAR, AMETHYST_COLLAR_WALL,
		EMERALD_COLLAR, EMERALD_COLLAR_WALL,
		DIAMOND_COLLAR, DIAMOND_COLLAR_WALL,
		NETHERITE_COLLAR, NETHERITE_COLLAR_WALL,
		OBSIDIAN_COLLAR, OBSIDIAN_COLLAR_WALL,
		BEDROCK_COLLAR, BEDROCK_COLLAR_WALL
	);

	private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType.BlockEntityFactory<T> factory, Block... blocks) {
		return Pivot.INSTANCE.register(RegistryKeys.BLOCK_ENTITY_TYPE, id,
			BlockEntityType.Builder.create(factory, blocks).build(null));
	}

	public static void init() {
	}
}
