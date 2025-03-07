package gay.ampflower.mod.pet.data.generators;

import gay.ampflower.mod.pet.data.primitives.MaidCollarRecipeJsonBuilder;
import gay.ampflower.mod.pet.item.GuiseItem;
import gay.ampflower.mod.pet.registry.PetworksItems;
import gay.ampflower.mod.pet.util.Util;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

/**
 * @author Ampflower
 * @since ${version}
 **/
public class PetworksRecipeGenerator extends FabricRecipeProvider {
	public PetworksRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void generate(final RecipeExporter exporter) {
		createCollarRecipe(PetworksItems.CLOTH_COLLAR, ItemTags.WOOL).offerTo(exporter);
		createCollarRecipe(PetworksItems.HIDE_COLLAR, Items.RABBIT_HIDE).offerTo(exporter);
		createCollarRecipe(PetworksItems.LEATHER_COLLAR, Items.LEATHER).offerTo(exporter);
		createCollarRecipe(PetworksItems.CHAIN_COLLAR, Items.CHAIN).offerTo(exporter);
		createCollarRecipe(PetworksItems.IRON_COLLAR, Items.IRON_INGOT).offerTo(exporter);
		createCollarRecipe(PetworksItems.GOLD_COLLAR, Items.GOLD_INGOT).offerTo(exporter);
		createCollarRecipe(PetworksItems.COPPER_COLLAR, Items.COPPER_INGOT).offerTo(exporter);
		createCollarRecipe(PetworksItems.QUARTZ_COLLAR, Items.QUARTZ).offerTo(exporter);
		createCollarRecipe(PetworksItems.AMETHYST_COLLAR, Items.AMETHYST_SHARD).offerTo(exporter);
		createCollarRecipe(PetworksItems.EMERALD_COLLAR, Items.EMERALD).offerTo(exporter);
		createCollarRecipe(PetworksItems.DIAMOND_COLLAR, Items.DIAMOND).offerTo(exporter);
		createCollarRecipe(PetworksItems.OBSIDIAN_COLLAR, Items.OBSIDIAN).offerTo(exporter);
		createCollarRecipe(PetworksItems.BEDROCK_COLLAR, Items.BEDROCK).offerTo(exporter);

		SmithingTransformRecipeJsonBuilder.create(
			Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
			Ingredient.ofItems(PetworksItems.DIAMOND_COLLAR),
			Ingredient.ofItems(Items.NETHERITE_INGOT),
			RecipeCategory.COMBAT,
			PetworksItems.NETHERITE_COLLAR
		).criterion("has_materials",
			RecipeProvider.conditionsFromItemPredicates(
				ItemPredicate.Builder
					.create()
					.items(PetworksItems.DIAMOND_COLLAR, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT)
					.build()
			)
		).offerTo(exporter, "netherite_collar");

		new MaidCollarRecipeJsonBuilder(
			Ingredient.ofItems(PetworksItems.CLOTH_COLLAR),
			Ingredient.ofItems(PetworksItems.HIDE_COLLAR),
			RecipeCategory.COMBAT,
			PetworksItems.MAID_COLLAR
		).criterion(
			"has_materials",
			RecipeProvider.conditionsFromItemPredicates(
				ItemPredicate.Builder
					.create()
					.items(PetworksItems.CLOTH_COLLAR, PetworksItems.HIDE_COLLAR)
					.build()
			)
		).offerTo(exporter, Util.id("maid_collar"));

		createGuiseRecipe(PetworksItems.FOX_GUISE).offerTo(exporter);
		createGuiseRecipe(PetworksItems.WOLF_GUISE).offerTo(exporter);
		createGuiseRecipe(PetworksItems.RABBIT_GUISE).offerTo(exporter);
		createGuiseRecipe(PetworksItems.OCELOT_GUISE).offerTo(exporter);
		createGuiseRecipe(PetworksItems.STRAY_CAT_GUISE).offerTo(exporter);
		createGuiseRecipe(PetworksItems.TAMED_CAT_GUISE).offerTo(exporter);
	}

	private CraftingRecipeJsonBuilder createCollarRecipe(ItemConvertible output, Item input) {
		return createCollarRecipe(output, Ingredient.ofItems(input))
			.criterion("has_materials",
				RecipeProvider.conditionsFromItemPredicates(
					ItemPredicate.Builder
						.create()
						.items(Items.STRING, input)
						.build()
				)
			);
	}

	private CraftingRecipeJsonBuilder createCollarRecipe(ItemConvertible output, TagKey<Item> input) {
		return createCollarRecipe(output, Ingredient.fromTag(input))
			.criterion("has_materials",
				RecipeProvider.conditionsFromItemPredicates(
					ItemPredicate.Builder
						.create()
						.items(Items.STRING)
						.tag(input)
						.build()
				)
			);
	}

	private CraftingRecipeJsonBuilder createCollarRecipe(ItemConvertible output, Ingredient input) {
		return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input('#', input)
			.input('S', Items.STRING)
			.pattern("S# ")
			.pattern("# #")
			.pattern(" #S")
			.showNotification(false);
	}

	private CraftingRecipeJsonBuilder createGuiseRecipe(ItemConvertible output) {
		if (!(output instanceof GuiseItem guise)) {
			throw new IllegalArgumentException("got: " + output);
		}
		final var input = guise.getComponent();
		return ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
			.input('#', input)
			.input('B', Items.BAMBOO)
			.input('S', Items.SUGAR_CANE)
			.input('P', Items.PAPER)
			.input('L', Items.LAPIS_LAZULI)
			.pattern("BLB")
			.pattern("S#S")
			.pattern("BPB")
			.showNotification(false)
			.criterion("has_materials",
				RecipeProvider.conditionsFromItemPredicates(
					ItemPredicate.Builder
						.create()
						.items(Items.LAPIS_LAZULI, input)
						.build()
				)
			);
	}
}
