package gay.ampflower.mod.pet.data.generators;

import gay.ampflower.mod.pet.block.CollarBlock;
import gay.ampflower.mod.pet.data.primitives.ForgeSeparateTransformsModel;
import gay.ampflower.mod.pet.data.primitives.GuiLight;
import gay.ampflower.mod.pet.data.primitives.GuiModel;
import gay.ampflower.mod.pet.registry.PetworksBlocks;
import gay.ampflower.mod.pet.registry.PetworksItems;
import gay.ampflower.mod.pet.util.Util;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.When;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Map;
import java.util.Optional;

/**
 * @author Ampflower
 * @since ${version}
 **/
public class PetworksModelGenerator extends FabricModelProvider {
	private static final TextureKey COLLAR = TextureKey.of("collar");
	private static final TextureKey RING = TextureKey.of("ring");

	public PetworksModelGenerator(final FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(final BlockStateModelGenerator generator) {
		collarBlock(generator, PetworksBlocks.MAID_COLLAR, PetworksBlocks.MAID_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.COLLAR, PetworksBlocks.COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.WORLDWIDEPIXEL_COLLAR, PetworksBlocks.WORLDWIDEPIXEL_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.MISSING_COLLAR, PetworksBlocks.MISSING_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.PRIDE_COLLAR, PetworksBlocks.PRIDE_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.TRANS_COLLAR, PetworksBlocks.TRANS_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.LESBIAN_COLLAR, PetworksBlocks.LESBIAN_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.GAY_COLLAR, PetworksBlocks.GAY_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.GENDERQUEER_COLLAR, PetworksBlocks.GENDERQUEER_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.GENDERFLUID_COLLAR, PetworksBlocks.GENDERFLUID_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.AGENDER_COLLAR, PetworksBlocks.AGENDER_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.BIGENDER_COLLAR, PetworksBlocks.BIGENDER_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.DEMIBOY_1_COLLAR, PetworksBlocks.DEMIBOY_1_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.DEMIBOY_2_COLLAR, PetworksBlocks.DEMIBOY_2_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.DEMIGENDER_COLLAR, PetworksBlocks.DEMIGENDER_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.DEMIGIRL_1_COLLAR, PetworksBlocks.DEMIGIRL_1_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.DEMIGIRL_2_COLLAR, PetworksBlocks.DEMIGIRL_2_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.INTERSEX_1_COLLAR, PetworksBlocks.INTERSEX_1_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.INTERSEX_2_COLLAR, PetworksBlocks.INTERSEX_2_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.ARO_COLLAR, PetworksBlocks.ARO_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.ACE_COLLAR, PetworksBlocks.ACE_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.AROACE_COLLAR, PetworksBlocks.AROACE_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.APLATONIC_COLLAR, PetworksBlocks.APLATONIC_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.PAN_COLLAR, PetworksBlocks.PAN_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.CLOTH_COLLAR, PetworksBlocks.CLOTH_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.HIDE_COLLAR, PetworksBlocks.HIDE_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.LEATHER_COLLAR, PetworksBlocks.LEATHER_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.CHAIN_COLLAR, PetworksBlocks.CHAIN_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.IRON_COLLAR, PetworksBlocks.IRON_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.GOLD_COLLAR, PetworksBlocks.GOLD_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.COPPER_COLLAR, PetworksBlocks.COPPER_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.QUARTZ_COLLAR, PetworksBlocks.QUARTZ_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.AMETHYST_COLLAR, PetworksBlocks.AMETHYST_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.EMERALD_COLLAR, PetworksBlocks.EMERALD_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.DIAMOND_COLLAR, PetworksBlocks.DIAMOND_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.NETHERITE_COLLAR, PetworksBlocks.NETHERITE_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.OBSIDIAN_COLLAR, PetworksBlocks.OBSIDIAN_COLLAR_WALL);
		collarBlock(generator, PetworksBlocks.BEDROCK_COLLAR, PetworksBlocks.BEDROCK_COLLAR_WALL);

		proxyCollar(generator,
			PetworksBlocks.COLLAR,
			PetworksBlocks.WORLDWIDEPIXEL_COLLAR,
			PetworksBlocks.MISSING_COLLAR,
			PetworksBlocks.PRIDE_COLLAR,
			PetworksBlocks.TRANS_COLLAR,
			PetworksBlocks.LESBIAN_COLLAR,
			PetworksBlocks.GAY_COLLAR,
			PetworksBlocks.GENDERQUEER_COLLAR,
			PetworksBlocks.GENDERFLUID_COLLAR,
			PetworksBlocks.AGENDER_COLLAR,
			PetworksBlocks.BIGENDER_COLLAR,
			PetworksBlocks.DEMIBOY_1_COLLAR,
			PetworksBlocks.DEMIBOY_2_COLLAR,
			PetworksBlocks.DEMIGENDER_COLLAR,
			PetworksBlocks.DEMIGIRL_1_COLLAR,
			PetworksBlocks.DEMIGIRL_2_COLLAR,
			PetworksBlocks.INTERSEX_1_COLLAR,
			PetworksBlocks.INTERSEX_2_COLLAR,
			PetworksBlocks.ARO_COLLAR,
			PetworksBlocks.ACE_COLLAR,
			PetworksBlocks.AROACE_COLLAR,
			PetworksBlocks.APLATONIC_COLLAR,
			PetworksBlocks.PAN_COLLAR
		);

		proxyCollar(generator,
			PetworksBlocks.CLOTH_COLLAR,
			PetworksBlocks.IRON_COLLAR,
			PetworksBlocks.GOLD_COLLAR,
			PetworksBlocks.COPPER_COLLAR,
			PetworksBlocks.QUARTZ_COLLAR,
			PetworksBlocks.AMETHYST_COLLAR,
			PetworksBlocks.EMERALD_COLLAR,
			PetworksBlocks.DIAMOND_COLLAR
		);

		biProxyCollar(generator,
			PetworksBlocks.CLOTH_COLLAR,
			PetworksBlocks.GOLD_COLLAR,
			PetworksBlocks.NETHERITE_COLLAR
		);

		biProxyCollar(generator,
			PetworksBlocks.CLOTH_COLLAR,
			PetworksBlocks.DIAMOND_COLLAR,
			PetworksBlocks.OBSIDIAN_COLLAR
		);


		proxyCollarAs(generator,
			PetworksBlocks.CLOTH_COLLAR,
			Blocks.BEDROCK,
			PetworksBlocks.BEDROCK_COLLAR
		);
	}

	@Override
	public void generateItemModels(final ItemModelGenerator itemModelGenerator) {
		maidCollarItem(itemModelGenerator, PetworksItems.MAID_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.WORLDWIDEPIXEL_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.MISSING_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.PRIDE_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.TRANS_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.LESBIAN_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.GAY_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.GENDERQUEER_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.GENDERFLUID_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.AGENDER_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.BIGENDER_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.DEMIBOY_1_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.DEMIBOY_2_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.DEMIGENDER_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.DEMIGIRL_1_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.DEMIGIRL_2_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.INTERSEX_1_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.INTERSEX_2_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.ARO_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.ACE_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.AROACE_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.APLATONIC_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.PAN_COLLAR, false);
		genericCollarItem(itemModelGenerator, PetworksItems.CLOTH_COLLAR, true);
		genericCollarItem(itemModelGenerator, PetworksItems.HIDE_COLLAR, true);
		leatherCollarItem(itemModelGenerator, PetworksItems.LEATHER_COLLAR, true);
		chainCollarItem(itemModelGenerator, PetworksItems.CHAIN_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.IRON_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.GOLD_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.COPPER_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.QUARTZ_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.AMETHYST_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.EMERALD_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.DIAMOND_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.NETHERITE_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.OBSIDIAN_COLLAR, true);
		collarItem(itemModelGenerator, PetworksItems.BEDROCK_COLLAR, true);

		registerGuises(itemModelGenerator,
			PetworksItems.WOLF_GUISE,
			PetworksItems.TAMED_CAT_GUISE,
			PetworksItems.STRAY_CAT_GUISE,
			PetworksItems.OCELOT_GUISE,
			PetworksItems.FOX_GUISE,
			PetworksItems.RABBIT_GUISE
		);
	}

	private static void proxyCollar(BlockStateModelGenerator generator, Block original, Block... collars) {
		final var proxy = new Model(Optional.of(ModelIds.getBlockModelId(original)), Optional.empty(), COLLAR, TextureKey.PARTICLE);
		for (final var collar : collars) {
			final var id = TextureMap.getId(collar);
			proxy.upload(ModelIds.getBlockModelId(collar), TextureMap.of(COLLAR, id).put(TextureKey.PARTICLE, id),
				generator.modelCollector);
		}
	}

	private static void biProxyCollar(BlockStateModelGenerator generator, Block original, Block ring, Block... collars) {
		final var proxy = new Model(Optional.of(ModelIds.getBlockModelId(original)), Optional.empty(), COLLAR, RING, TextureKey.PARTICLE);
		final var ringId = TextureMap.getId(ring);
		for (final var collar : collars) {
			final var id = TextureMap.getId(collar);
			proxy.upload(ModelIds.getBlockModelId(collar), TextureMap.of(COLLAR, id).put(RING, ringId).put(TextureKey.PARTICLE, id),
				generator.modelCollector);
		}
	}

	private static void proxyCollarAs(BlockStateModelGenerator generator, Block original, Block sample, Block collar) {
		final var proxy = new Model(Optional.of(ModelIds.getBlockModelId(original)), Optional.empty(), COLLAR, TextureKey.PARTICLE);
		final var id = TextureMap.getId(sample);
		proxy.upload(ModelIds.getBlockModelId(collar), TextureMap.of(COLLAR, id).put(TextureKey.PARTICLE, id), generator.modelCollector);
	}

	private static void collarBlock(BlockStateModelGenerator generator, Block standing, Block wall) {
		final var id = ModelIds.getBlockModelId(standing);
		generator.blockStateCollector.accept(collarBlockState(standing, id, false));
		generator.blockStateCollector.accept(collarBlockState(wall, id, true));
	}

	private static BlockStateSupplier collarBlockState(Block block, Identifier model, boolean wall) {
		return MultipartBlockStateSupplier.create(block)
			.with(When.create().set(CollarBlock.FACING, Direction.NORTH),
				rotateVariant(model, VariantSettings.Rotation.R0, wall))

			.with(When.create().set(CollarBlock.FACING, Direction.EAST),
				rotateVariant(model, VariantSettings.Rotation.R90, wall))

			.with(When.create().set(CollarBlock.FACING, Direction.SOUTH),
				rotateVariant(model, VariantSettings.Rotation.R180, wall))

			.with(When.create().set(CollarBlock.FACING, Direction.WEST),
				rotateVariant(model, VariantSettings.Rotation.R270, wall));
	}

	private static BlockStateVariant rotateVariant(Identifier model, VariantSettings.Rotation Y, boolean wall) {
		final var var = BlockStateVariant.create()
			.put(VariantSettings.MODEL, model)
			.put(VariantSettings.Y, Y);
		if (wall) {
			var.put(VariantSettings.X, VariantSettings.Rotation.R90);
		}
		return var;
	}

	private static void commonCollarItem(ItemModelGenerator generator, Item collar) {
		var id = Registries.ITEM.getId(collar);
		final var blockModel = block(id.getPath(), GuiLight.FRONT);
		id = id.withPrefixedPath("item/");

		final var blockPath = id.withSuffixedPath("_worn");
		final var itemPath = id.withSuffixedPath("_2d");

		generator.register(collar, collarItemModel(itemPath, blockModel));

		blockModel.upload(blockPath, TextureMap.layer0(collar), generator.writer);
	}

	private static void maidCollarItem(ItemModelGenerator generator, Item collar, final boolean leashable) {
		commonCollarItem(generator, collar);
		final var id = Registries.ITEM.getId(collar).withPrefixedPath("item/");
		final var itemModel = leashable ? Models.GENERATED_THREE_LAYERS : Models.GENERATED_TWO_LAYERS;

		final var itemPath = id.withSuffixedPath("_2d");
		itemModel.upload(itemPath, TextureMap.layered(Util.itemId("maid_collar"), Util.itemId("maid_collar_overlay"), Util.itemId("collar_ring")), generator.writer);
	}

	private static void leatherCollarItem(ItemModelGenerator generator, Item collar, boolean leashable) {
		commonCollarItem(generator, collar);
		final var id = Registries.ITEM.getId(collar).withPrefixedPath("item/");
		final var itemModel = leashable ? Models.GENERATED_THREE_LAYERS : Models.GENERATED_TWO_LAYERS;

		final var itemPath = id.withSuffixedPath("_2d");
		itemModel.upload(itemPath, TextureMap.layered(Util.itemId("collar"), Util.itemId("collar_overlay"), Util.itemId("collar_ring")), generator.writer);
	}

	private static void chainCollarItem(ItemModelGenerator generator, Item collar, boolean leashable) {
		commonCollarItem(generator, collar);
		final var id = Registries.ITEM.getId(collar).withPrefixedPath("item/");
		final var itemModel = leashable ? Models.GENERATED_TWO_LAYERS : Models.GENERATED;

		final var itemPath = id.withSuffixedPath("_2d");
		itemModel.upload(itemPath, TextureMap.layered(Util.itemId("chain_collar"), Util.itemId("chain_collar_ring")), generator.writer);
	}

	private static void genericCollarItem(ItemModelGenerator generator, Item collar, final boolean leashable) {
		commonCollarItem(generator, collar);
		final var id = Registries.ITEM.getId(collar).withPrefixedPath("item/");
		final var itemModel = leashable ? Models.GENERATED_TWO_LAYERS : Models.GENERATED;

		final var itemPath = id.withSuffixedPath("_2d");
		itemModel.upload(itemPath, TextureMap.layered(Util.itemId("collar"), Util.itemId("collar_ring")), generator.writer);
	}

	private static void collarItem(ItemModelGenerator generator, Item collar, final boolean leashable) {
		commonCollarItem(generator, collar);
		final var id = Registries.ITEM.getId(collar).withPrefixedPath("item/");
		final var itemModel = leashable ? Models.GENERATED_TWO_LAYERS : Models.GENERATED;

		final var itemPath = id.withSuffixedPath("_2d");
		itemModel.upload(itemPath, TextureMap.layered(id, Util.itemId("collar_ring")), generator.writer);
	}

	private static Model collarItemModel(Identifier sprite, Model model) {
		final var spriteModel = new Model(Optional.of(sprite), Optional.empty());

		return new ForgeSeparateTransformsModel(
			Optional.of(sprite), Optional.empty(), Optional.of("forge:separate_transforms"),
			Optional.of(GuiLight.FRONT), Optional.of(model), Map.of(
			"gui", spriteModel,
			"fixed", spriteModel
		)
		);
	}

	private static void registerGuises(ItemModelGenerator generator, Item... guises) {
		final var model = item("guise_template");
		for (final var guise : guises) {
			model.upload(ModelIds.getItemModelId(guise), new TextureMap(), generator.writer);
		}
	}

	private static Model make(TextureKey... requiredTextureKeys) {
		return new Model(Optional.empty(), Optional.empty(), requiredTextureKeys);// 187
	}

	private static Model block(String parent, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Util.id("block/" + parent)), Optional.empty(), requiredTextureKeys);// 191
	}

	private static Model block(String parent, GuiLight light, TextureKey... requiredTextureKeys) {
		return new GuiModel(Optional.of(Util.id("block/" + parent)), Optional.empty(), Optional.of(light), requiredTextureKeys);// 191
	}

	private static Model item(String parent, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Util.id("item/" + parent)), Optional.empty(), requiredTextureKeys);// 195
	}

	private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Identifier.ofVanilla("block/" + parent)), Optional.of(variant), requiredTextureKeys);// 199
	}
}
