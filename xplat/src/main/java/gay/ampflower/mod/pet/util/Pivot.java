package gay.ampflower.mod.pet.util;

import gay.ampflower.mod.pet.registry.PetworksBlockEntities;
import gay.ampflower.mod.pet.registry.PetworksBlockTypes;
import gay.ampflower.mod.pet.registry.PetworksBlocks;
import gay.ampflower.mod.pet.registry.PetworksDataComponentTypes;
import gay.ampflower.mod.pet.registry.PetworksItems;
import gay.ampflower.mod.pet.registry.PetworksRecipeSerializers;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

import java.util.ServiceLoader;
import java.util.function.BiConsumer;

import static gay.ampflower.mod.pet.registry.PetworksItems.AMETHYST_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.BEDROCK_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.CHAIN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.CLOTH_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.COPPER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.DIAMOND_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.EMERALD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.GOLD_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.HIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.IRON_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.LEATHER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.MAID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.NETHERITE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.OBSIDIAN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.QUARTZ_COLLAR;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface Pivot {
	Pivot INSTANCE = ServiceLoader.load(Pivot.class).findFirst().orElseThrow();

	<V, T extends V> T register(RegistryKey<Registry<V>> key, String id, T t);

	static <A> void registerCollars(BiConsumer<Item, A> collarConsumer, A cosmetic, A armor, A gimick) {
		collarConsumer.accept(MAID_COLLAR, cosmetic);
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
