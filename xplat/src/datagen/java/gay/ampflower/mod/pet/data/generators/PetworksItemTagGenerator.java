package gay.ampflower.mod.pet.data.generators;

import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

import static gay.ampflower.mod.pet.registry.PetworksItems.ACE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.AGENDER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.AMETHYST_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.APLATONIC_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.AROACE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.ARO_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.BEDROCK_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.BIGENDER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.CHAIN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.CLOTH_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.COPPER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.DEMIBOY_1_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.DEMIBOY_2_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.DEMIGENDER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.DEMIGIRL_1_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.DEMIGIRL_2_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.DIAMOND_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.EMERALD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.FOX_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.GAY_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.GENDERFLUID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.GENDERQUEER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.GOLD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.HIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.INTERSEX_1_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.INTERSEX_2_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.IRON_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.LEATHER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.LESBIAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.MAID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.MISSING_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.NETHERITE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.OBSIDIAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.OCELOT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.PAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.PRIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.QUARTZ_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.RABBIT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.STRAY_CAT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.TAMED_CAT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.TRANS_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.WOLF_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.WORLDWIDEPIXEL_COLLAR;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.ARMOR_COLLARS;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.BRACELETS;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.COLLARS;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.COLLAR_ENCHANTABLE;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.CURIOS_BRACELET;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.CURIOS_COLLAR;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.CURIOS_GUISE;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.DYEABLE_COLLARS;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.EQUIPPABLE;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.GUISES;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.HEAD_ARMOR;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.SHEARABLE_COLLARS;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.TRINKETS_COLLAR;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.TRINKETS_GUISE;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.TRINKETS_HAND_BRACELET;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.TRINKETS_OFFHAND_BRACELET;
import static gay.ampflower.mod.pet.registry.tag.PetworksItemTags.WEARABLE_ENCHANTABLE;

/**
 * @author Ampflower
 * @since ${version}
 **/
public class PetworksItemTagGenerator extends FabricTagProvider.ItemTagProvider {
	private static final Logger logger = LogUtils.getLogger();

	public PetworksItemTagGenerator(final FabricDataOutput output, final CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(final RegistryWrapper.WrapperLookup arg) {
		getOrCreateTagBuilder(ARMOR_COLLARS).add(
			LEATHER_COLLAR,
			CHAIN_COLLAR,
			IRON_COLLAR,
			GOLD_COLLAR,
			COPPER_COLLAR,
			QUARTZ_COLLAR,
			AMETHYST_COLLAR,
			EMERALD_COLLAR,
			DIAMOND_COLLAR,
			NETHERITE_COLLAR,
			OBSIDIAN_COLLAR,
			BEDROCK_COLLAR
		);
		getOrCreateTagBuilder(SHEARABLE_COLLARS).add(
			MAID_COLLAR,
			COLLAR,
			WORLDWIDEPIXEL_COLLAR,
			MISSING_COLLAR,
			PRIDE_COLLAR,
			TRANS_COLLAR,
			LESBIAN_COLLAR,
			GAY_COLLAR,
			GENDERQUEER_COLLAR,
			GENDERFLUID_COLLAR,
			AGENDER_COLLAR,
			BIGENDER_COLLAR,
			DEMIBOY_1_COLLAR,
			DEMIBOY_2_COLLAR,
			DEMIGENDER_COLLAR,
			DEMIGIRL_1_COLLAR,
			DEMIGIRL_2_COLLAR,
			INTERSEX_1_COLLAR,
			INTERSEX_2_COLLAR,
			ARO_COLLAR,
			ACE_COLLAR,
			AROACE_COLLAR,
			APLATONIC_COLLAR,
			PAN_COLLAR,
			CLOTH_COLLAR,
			HIDE_COLLAR
		);
		getOrCreateTagBuilder(COLLARS)
			.addTag(SHEARABLE_COLLARS)
			.addTag(ARMOR_COLLARS);
		getOrCreateTagBuilder(TRINKETS_COLLAR).addTag(COLLARS);
		getOrCreateTagBuilder(CURIOS_COLLAR).addTag(COLLARS);

		getOrCreateTagBuilder(BRACELETS).add(
			MAID_COLLAR,
			WORLDWIDEPIXEL_COLLAR,
			MISSING_COLLAR,
			COLLAR,
			PRIDE_COLLAR,
			TRANS_COLLAR,
			LESBIAN_COLLAR,
			GAY_COLLAR,
			GENDERQUEER_COLLAR,
			GENDERFLUID_COLLAR,
			AGENDER_COLLAR,
			BIGENDER_COLLAR,
			DEMIBOY_1_COLLAR,
			DEMIBOY_2_COLLAR,
			DEMIGENDER_COLLAR,
			DEMIGIRL_1_COLLAR,
			DEMIGIRL_2_COLLAR,
			INTERSEX_1_COLLAR,
			INTERSEX_2_COLLAR,
			ARO_COLLAR,
			ACE_COLLAR,
			AROACE_COLLAR,
			APLATONIC_COLLAR,
			PAN_COLLAR
		);

		getOrCreateTagBuilder(TRINKETS_HAND_BRACELET).addTag(BRACELETS);
		getOrCreateTagBuilder(TRINKETS_OFFHAND_BRACELET).addTag(BRACELETS);
		getOrCreateTagBuilder(CURIOS_BRACELET).addTag(BRACELETS);

		getOrCreateTagBuilder(GUISES).add(
			WOLF_GUISE,
			TAMED_CAT_GUISE,
			STRAY_CAT_GUISE,
			OCELOT_GUISE,
			FOX_GUISE,
			RABBIT_GUISE
		);
		getOrCreateTagBuilder(TRINKETS_GUISE).addTag(GUISES);
		getOrCreateTagBuilder(CURIOS_GUISE).addTag(GUISES);

		getOrCreateTagBuilder(HEAD_ARMOR).addTag(ARMOR_COLLARS);
		getOrCreateTagBuilder(EQUIPPABLE)
			.addTag(COLLARS)
			.addTag(GUISES);

		getOrCreateTagBuilder(COLLAR_ENCHANTABLE)
			.addTag(COLLARS);

		getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
			.addTag(COLLAR_ENCHANTABLE)
			.addTag(GUISES);

		getOrCreateTagBuilder(WEARABLE_ENCHANTABLE)
			.addOptionalTag(ItemTags.EQUIPPABLE_ENCHANTABLE)
			.add(Items.SHIELD);

		getOrCreateTagBuilder(DYEABLE_COLLARS)
			.add(COLLAR,
				CLOTH_COLLAR,
				HIDE_COLLAR,
				LEATHER_COLLAR,
				CHAIN_COLLAR,
				BEDROCK_COLLAR);

		getOrCreateTagBuilder(ItemTags.DYEABLE)
			.addTag(DYEABLE_COLLARS);

		getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
			.add(
				LEATHER_COLLAR,
				CHAIN_COLLAR,
				IRON_COLLAR,
				GOLD_COLLAR,
				COPPER_COLLAR,
				QUARTZ_COLLAR,
				AMETHYST_COLLAR,
				EMERALD_COLLAR,
				DIAMOND_COLLAR,
				NETHERITE_COLLAR,
				OBSIDIAN_COLLAR
			);

		logger.info("Collared and caricaturized :3");
	}
}
