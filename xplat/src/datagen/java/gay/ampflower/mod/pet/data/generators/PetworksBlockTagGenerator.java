package gay.ampflower.mod.pet.data.generators;

import gay.ampflower.mod.pet.registry.tag.PetworksBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

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
 * @since ${version}
 **/
public class PetworksBlockTagGenerator extends FabricTagProvider.BlockTagProvider {
	public PetworksBlockTagGenerator(final FabricDataOutput output, final CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(final RegistryWrapper.WrapperLookup arg) {
		getOrCreateTagBuilder(PetworksBlockTags.COLLARS).add(
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

		getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
			OBSIDIAN_COLLAR, OBSIDIAN_COLLAR_WALL,
			BEDROCK_COLLAR, BEDROCK_COLLAR_WALL
		);
	}
}
