package gay.ampflower.mod.pet.data.generators;

import gay.ampflower.mod.pet.registry.tag.PetworksBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

import static gay.ampflower.mod.pet.registry.PetworksBlocks.ACE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.ACE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.AGENDER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.AGENDER_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.AMETHYST_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.AMETHYST_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.APLATONIC_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.APLATONIC_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.AROACE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.AROACE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.ARO_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.ARO_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.BEDROCK_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.BEDROCK_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.BIGENDER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.BIGENDER_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.BISEXUAL_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.BISEXUAL_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.CHAIN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.CHAIN_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.CLOTH_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.CLOTH_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.COPPER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.COPPER_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIBOY_1_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIBOY_1_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIBOY_2_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIBOY_2_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIGENDER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIGENDER_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIGIRL_1_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIGIRL_1_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIGIRL_2_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIGIRL_2_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIROMANTIC_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMIROMANTIC_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMISEXUAL_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DEMISEXUAL_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DIAMOND_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.DIAMOND_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.EMERALD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.EMERALD_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GAY_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GAY_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GENDERFLUID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GENDERFLUID_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GENDERQUEER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GENDERQUEER_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GOLD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GOLD_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GREYACE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GREYACE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GREYARO_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GREYARO_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GREYROSE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.GREYROSE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.HIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.HIDE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.INTERSEX_1_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.INTERSEX_1_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.INTERSEX_2_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.INTERSEX_2_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.IRON_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.IRON_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.LEATHER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.LEATHER_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.LESBIAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.LESBIAN_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.MAID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.MAID_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.MISSING_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.MISSING_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.NETHERITE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.NETHERITE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.NONBINARY_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.NONBINARY_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.OBSIDIAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.OBSIDIAN_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.PAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.PAN_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.POLYAMORY_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.POLYAMORY_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.PRIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.PRIDE_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.PROGRESS_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.PROGRESS_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.QUARTZ_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.QUARTZ_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.TRANS_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.TRANS_COLLAR_WALL;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.WORLDWIDEPIXEL_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksBlocks.WORLDWIDEPIXEL_COLLAR_WALL;

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
			COLLAR, COLLAR_WALL,
			WORLDWIDEPIXEL_COLLAR, WORLDWIDEPIXEL_COLLAR_WALL,
			MISSING_COLLAR, MISSING_COLLAR_WALL,
			PRIDE_COLLAR, PRIDE_COLLAR_WALL,
			PROGRESS_COLLAR, PROGRESS_COLLAR_WALL,
			TRANS_COLLAR, TRANS_COLLAR_WALL,
			GENDERQUEER_COLLAR, GENDERQUEER_COLLAR_WALL,
			GENDERFLUID_COLLAR, GENDERFLUID_COLLAR_WALL,
			AGENDER_COLLAR, AGENDER_COLLAR_WALL,
			BIGENDER_COLLAR, BIGENDER_COLLAR_WALL,
			DEMIBOY_1_COLLAR, DEMIBOY_1_COLLAR_WALL,
			DEMIBOY_2_COLLAR, DEMIBOY_2_COLLAR_WALL,
			DEMIGENDER_COLLAR, DEMIGENDER_COLLAR_WALL,
			DEMIGIRL_1_COLLAR, DEMIGIRL_1_COLLAR_WALL,
			DEMIGIRL_2_COLLAR, DEMIGIRL_2_COLLAR_WALL,
			INTERSEX_1_COLLAR, INTERSEX_1_COLLAR_WALL,
			INTERSEX_2_COLLAR, INTERSEX_2_COLLAR_WALL,
			NONBINARY_COLLAR, NONBINARY_COLLAR_WALL,
			ARO_COLLAR, ARO_COLLAR_WALL,
			DEMIROMANTIC_COLLAR, DEMIROMANTIC_COLLAR_WALL,
			ACE_COLLAR, ACE_COLLAR_WALL,
			DEMISEXUAL_COLLAR, DEMISEXUAL_COLLAR_WALL,
			AROACE_COLLAR, AROACE_COLLAR_WALL,
			APLATONIC_COLLAR, APLATONIC_COLLAR_WALL,
			GREYROSE_COLLAR, GREYROSE_COLLAR_WALL,
			GREYACE_COLLAR, GREYACE_COLLAR_WALL,
			GREYARO_COLLAR, GREYARO_COLLAR_WALL,
			LESBIAN_COLLAR, LESBIAN_COLLAR_WALL,
			GAY_COLLAR, GAY_COLLAR_WALL,
			BISEXUAL_COLLAR, BISEXUAL_COLLAR_WALL,
			PAN_COLLAR, PAN_COLLAR_WALL,
			POLYAMORY_COLLAR, POLYAMORY_COLLAR_WALL,
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
