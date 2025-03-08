package gay.ampflower.mod.pet.util;

import gay.ampflower.mod.pet.block.entity.CollarBlockEntity;
import gay.ampflower.mod.pet.item.CollarDyeableItem;
import gay.ampflower.mod.pet.item.GuiseItem;
import gay.ampflower.mod.pet.registry.PetworksBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

import java.util.ServiceLoader;

import static gay.ampflower.mod.pet.registry.PetworksItems.BEDROCK_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.CHAIN_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.CLOTH_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.FOX_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.HIDE_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.LEATHER_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.MAID_COLLAR;
import static gay.ampflower.mod.pet.registry.PetworksItems.OCELOT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.RABBIT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.STRAY_CAT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.TAMED_CAT_GUISE;
import static gay.ampflower.mod.pet.registry.PetworksItems.WOLF_GUISE;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface ClientPivot {
	ClientPivot INSTANCE = ServiceLoader.load(ClientPivot.class).findFirst().orElseThrow();

	static void registerItemTint(ColorProviderInjector<ItemColorProvider, Item> provider) {
		provider.apply((stack, index) -> ((GuiseItem) stack.getItem()).getColor(index),
			WOLF_GUISE, TAMED_CAT_GUISE, STRAY_CAT_GUISE, OCELOT_GUISE, FOX_GUISE, RABBIT_GUISE);

		provider.apply((stack, index) -> ((CollarDyeableItem) stack.getItem()).getColor(stack, index),
			MAID_COLLAR,
			COLLAR,
			CLOTH_COLLAR,
			HIDE_COLLAR,
			LEATHER_COLLAR,
			CHAIN_COLLAR,
			BEDROCK_COLLAR);
	}

	static void registerBlockTint(ColorProviderInjector<BlockColorProvider, Block> provider) {
		provider.apply((state, world, pos, tintIndex) ->
				world != null && world.getBlockEntity(pos) instanceof CollarBlockEntity collar
					? collar.getColor(tintIndex)
					: -1,
			PetworksBlocks.MAID_COLLAR, PetworksBlocks.MAID_COLLAR_WALL,
			PetworksBlocks.COLLAR, PetworksBlocks.COLLAR_WALL,
			PetworksBlocks.CLOTH_COLLAR, PetworksBlocks.CLOTH_COLLAR_WALL,
			PetworksBlocks.HIDE_COLLAR, PetworksBlocks.HIDE_COLLAR_WALL,
			PetworksBlocks.LEATHER_COLLAR, PetworksBlocks.LEATHER_COLLAR_WALL,
			PetworksBlocks.CHAIN_COLLAR, PetworksBlocks.CHAIN_COLLAR_WALL,
			//PetworksBlocks.IRON_COLLAR, PetworksBlocks.IRON_COLLAR_WALL,
			//PetworksBlocks.GOLD_COLLAR, PetworksBlocks.GOLD_COLLAR_WALL,
			//PetworksBlocks.COPPER_COLLAR, PetworksBlocks.COPPER_COLLAR_WALL,
			//PetworksBlocks.QUARTZ_COLLAR, PetworksBlocks.QUARTZ_COLLAR_WALL,
			//PetworksBlocks.AMETHYST_COLLAR, PetworksBlocks.AMETHYST_COLLAR_WALL,
			//PetworksBlocks.EMERALD_COLLAR, PetworksBlocks.EMERALD_COLLAR_WALL,
			//PetworksBlocks.DIAMOND_COLLAR, PetworksBlocks.DIAMOND_COLLAR_WALL,
			//PetworksBlocks.NETHERITE_COLLAR, PetworksBlocks.NETHERITE_COLLAR_WALL,
			//PetworksBlocks.OBSIDIAN_COLLAR, PetworksBlocks.OBSIDIAN_COLLAR_WALL
			PetworksBlocks.BEDROCK_COLLAR, PetworksBlocks.BEDROCK_COLLAR_WALL
		);
	}

	@FunctionalInterface
	interface ColorProviderInjector<P, E> {
		void apply(P provider, E... elements);
	}

	static Entity getPlayer() {
		return MinecraftClient.getInstance().player;
	}
}
