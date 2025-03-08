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
import static gay.ampflower.mod.pet.registry.PetworksItems.AROACE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.ARO_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.BEDROCK_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.CHAIN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.CLOTH_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.COPPER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.DIAMOND_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.EMERALD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.FOX_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.GAY_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.GENDERFLUID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.GENDERQUEER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.GOLD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.HIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.IRON_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.LEATHER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.LESBIAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.MAID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.MISSING_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.NETHERITE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.OBSIDIAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.OCELOT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.PRIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.QUARTZ_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.RABBIT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.STRAY_CAT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.TAMED_CAT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.TRANS_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.WOLF_GUISE;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface Pivot {
	Pivot INSTANCE = ServiceLoader.load(Pivot.class).findFirst().orElseThrow();

	<V, T extends V> T register(RegistryKey<Registry<V>> key, String id, T t);

	static <A> void registerCollars(BiConsumer<Item, A> collarConsumer, A cosmetic, A armor, A gimick) {
		collarConsumer.accept(MAID_COLLAR, cosmetic);
		collarConsumer.accept(COLLAR, cosmetic);
		collarConsumer.accept(MISSING_COLLAR, cosmetic);
		collarConsumer.accept(PRIDE_COLLAR, cosmetic);
		collarConsumer.accept(TRANS_COLLAR, cosmetic);
		collarConsumer.accept(LESBIAN_COLLAR, cosmetic);
		collarConsumer.accept(GAY_COLLAR, cosmetic);
		collarConsumer.accept(GENDERQUEER_COLLAR, cosmetic);
		collarConsumer.accept(GENDERFLUID_COLLAR, cosmetic);
		collarConsumer.accept(AGENDER_COLLAR, cosmetic);
		collarConsumer.accept(ARO_COLLAR, cosmetic);
		collarConsumer.accept(ACE_COLLAR, cosmetic);
		collarConsumer.accept(AROACE_COLLAR, cosmetic);
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

	static void init() {
		PetworksBlocks.init();
		PetworksBlockTypes.init();
		PetworksBlockEntities.init();
		PetworksDataComponentTypes.init();
		PetworksItems.init();
		PetworksRecipeSerializers.init();
	}
}
