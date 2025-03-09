package gay.ampflower.mod.pet.util;

import gay.ampflower.mod.pet.registry.PetworksBlockEntities;
import gay.ampflower.mod.pet.registry.PetworksBlockTypes;
import gay.ampflower.mod.pet.registry.PetworksBlocks;
import gay.ampflower.mod.pet.registry.PetworksDataComponentTypes;
import gay.ampflower.mod.pet.registry.PetworksItemGroups;
import gay.ampflower.mod.pet.registry.PetworksItems;
import gay.ampflower.mod.pet.registry.PetworksRecipeSerializers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

import java.util.ServiceLoader;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface Pivot {
	Pivot INSTANCE = ServiceLoader.load(Pivot.class).findFirst().orElseThrow();

	<V, T extends V> T register(RegistryKey<Registry<V>> key, String id, T t);

	ItemGroup.Builder createItemGroupBuilder();

	static <A> void registerCollars(BiConsumer<Item, A> collarConsumer, A cosmetic, A armor, A gimick) {
		collarConsumer.accept(MAID_COLLAR, cosmetic);
		collarConsumer.accept(COLLAR, cosmetic);
		collarConsumer.accept(WORLDWIDEPIXEL_COLLAR, cosmetic);
		collarConsumer.accept(MISSING_COLLAR, cosmetic);
		collarConsumer.accept(PRIDE_COLLAR, cosmetic);
		collarConsumer.accept(TRANS_COLLAR, cosmetic);
		collarConsumer.accept(LESBIAN_COLLAR, cosmetic);
		collarConsumer.accept(GAY_COLLAR, cosmetic);
		collarConsumer.accept(GENDERQUEER_COLLAR, cosmetic);
		collarConsumer.accept(GENDERFLUID_COLLAR, cosmetic);
		collarConsumer.accept(AGENDER_COLLAR, cosmetic);
		collarConsumer.accept(BIGENDER_COLLAR, cosmetic);
		collarConsumer.accept(DEMIBOY_1_COLLAR, cosmetic);
		collarConsumer.accept(DEMIBOY_2_COLLAR, cosmetic);
		collarConsumer.accept(DEMIGENDER_COLLAR, cosmetic);
		collarConsumer.accept(DEMIGIRL_1_COLLAR, cosmetic);
		collarConsumer.accept(DEMIGIRL_2_COLLAR, cosmetic);
		collarConsumer.accept(INTERSEX_1_COLLAR, cosmetic);
		collarConsumer.accept(INTERSEX_2_COLLAR, cosmetic);
		collarConsumer.accept(ARO_COLLAR, cosmetic);
		collarConsumer.accept(ACE_COLLAR, cosmetic);
		collarConsumer.accept(AROACE_COLLAR, cosmetic);
		collarConsumer.accept(APLATONIC_COLLAR, cosmetic);
		collarConsumer.accept(PAN_COLLAR, cosmetic);
		collarConsumer.accept(CLOTH_COLLAR, cosmetic);
		collarConsumer.accept(HIDE_COLLAR, cosmetic);
		collarConsumer.accept(LEATHER_COLLAR, armor);
		collarConsumer.accept(CHAIN_COLLAR, armor);
		collarConsumer.accept(IRON_COLLAR, armor);
		collarConsumer.accept(GOLD_COLLAR, armor);
		collarConsumer.accept(COPPER_COLLAR, armor);
		collarConsumer.accept(QUARTZ_COLLAR, armor);
		collarConsumer.accept(AMETHYST_COLLAR, armor);
		collarConsumer.accept(EMERALD_COLLAR, armor);
		collarConsumer.accept(DIAMOND_COLLAR, armor);
		collarConsumer.accept(NETHERITE_COLLAR, armor);
		collarConsumer.accept(OBSIDIAN_COLLAR, gimick);
		collarConsumer.accept(BEDROCK_COLLAR, gimick);
	}

	static void registerCollars(Consumer<ItemConvertible> collarConsumer) {
		collarConsumer.accept(MAID_COLLAR);
		collarConsumer.accept(COLLAR);
		collarConsumer.accept(WORLDWIDEPIXEL_COLLAR);
		collarConsumer.accept(MISSING_COLLAR);
		collarConsumer.accept(PRIDE_COLLAR);
		collarConsumer.accept(TRANS_COLLAR);
		collarConsumer.accept(LESBIAN_COLLAR);
		collarConsumer.accept(GAY_COLLAR);
		collarConsumer.accept(GENDERQUEER_COLLAR);
		collarConsumer.accept(GENDERFLUID_COLLAR);
		collarConsumer.accept(AGENDER_COLLAR);
		collarConsumer.accept(BIGENDER_COLLAR);
		collarConsumer.accept(DEMIBOY_1_COLLAR);
		collarConsumer.accept(DEMIBOY_2_COLLAR);
		collarConsumer.accept(DEMIGENDER_COLLAR);
		collarConsumer.accept(DEMIGIRL_1_COLLAR);
		collarConsumer.accept(DEMIGIRL_2_COLLAR);
		collarConsumer.accept(INTERSEX_1_COLLAR);
		collarConsumer.accept(INTERSEX_2_COLLAR);
		collarConsumer.accept(ARO_COLLAR);
		collarConsumer.accept(ACE_COLLAR);
		collarConsumer.accept(AROACE_COLLAR);
		collarConsumer.accept(APLATONIC_COLLAR);
		collarConsumer.accept(PAN_COLLAR);
		collarConsumer.accept(CLOTH_COLLAR);
		collarConsumer.accept(HIDE_COLLAR);
		collarConsumer.accept(LEATHER_COLLAR);
		collarConsumer.accept(CHAIN_COLLAR);
		collarConsumer.accept(IRON_COLLAR);
		collarConsumer.accept(GOLD_COLLAR);
		collarConsumer.accept(COPPER_COLLAR);
		collarConsumer.accept(QUARTZ_COLLAR);
		collarConsumer.accept(AMETHYST_COLLAR);
		collarConsumer.accept(EMERALD_COLLAR);
		collarConsumer.accept(DIAMOND_COLLAR);
		collarConsumer.accept(NETHERITE_COLLAR);
		collarConsumer.accept(OBSIDIAN_COLLAR);
		collarConsumer.accept(BEDROCK_COLLAR);
	}

	static void registerGuises(Consumer<ItemConvertible> guiseConsumer) {
		guiseConsumer.accept(WOLF_GUISE);
		guiseConsumer.accept(TAMED_CAT_GUISE);
		guiseConsumer.accept(STRAY_CAT_GUISE);
		guiseConsumer.accept(OCELOT_GUISE);
		guiseConsumer.accept(FOX_GUISE);
		guiseConsumer.accept(RABBIT_GUISE);
	}

	static void init() {
		PetworksBlocks.init();
		PetworksBlockTypes.init();
		PetworksBlockEntities.init();
		PetworksDataComponentTypes.init();
		PetworksItems.init();
		PetworksRecipeSerializers.init();
		PetworksItemGroups.init();
	}
}
