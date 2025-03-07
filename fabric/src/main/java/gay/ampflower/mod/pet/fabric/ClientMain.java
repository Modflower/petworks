package gay.ampflower.mod.pet.fabric;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import gay.ampflower.mod.pet.client.HeadRendererSuppressor;
import gay.ampflower.mod.pet.fabric.client.FabricCollarRenderer;
import gay.ampflower.mod.pet.fabric.client.ItemModelSwapper;
import gay.ampflower.mod.pet.registry.PetworksBlocks;
import gay.ampflower.mod.pet.util.ClientPivot;
import gay.ampflower.mod.pet.util.Pivot;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static net.minecraft.client.render.model.json.ModelTransformationMode.FIXED;
import static net.minecraft.client.render.model.json.ModelTransformationMode.GUI;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class ClientMain implements ClientModInitializer {
	public static final FabricCollarRenderer collarRenderer = new FabricCollarRenderer();

	public static final Set<ModelIdentifier> manualLoadModels = new HashSet<>();

	private static void registerCollar(Item collar, Void unused) {
		HeadRendererSuppressor.register(collar);
		ArmorRenderer.register(collarRenderer, collar);
		TrinketRendererRegistry.registerRenderer(collar, collarRenderer);
		final var itemId = Registries.ITEM.getId(collar);
		final var world = new ModelIdentifier(itemId.withSuffixedPath("_worn"), "inventory");
		final var gui = new ModelIdentifier(itemId, "inventory");
		ItemModelSwapper.register(collar, world, Map.of(GUI, gui, FIXED, gui));
		manualLoadModels.add(world);
	}

	@Override
	public void onInitializeClient() {
		ClientPivot.registerBlockTint(ColorProviderRegistry.BLOCK::register);
		ClientPivot.registerItemTint(ColorProviderRegistry.ITEM::register);

		Pivot.registerCollars(ClientMain::registerCollar, null, null, null);

		BlockRenderLayerMap.INSTANCE.putBlock(PetworksBlocks.MAID_COLLAR, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PetworksBlocks.MAID_COLLAR_WALL, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PetworksBlocks.LEATHER_COLLAR, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PetworksBlocks.LEATHER_COLLAR_WALL, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PetworksBlocks.CHAIN_COLLAR, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PetworksBlocks.CHAIN_COLLAR_WALL, RenderLayer.getCutoutMipped());
	}
}
