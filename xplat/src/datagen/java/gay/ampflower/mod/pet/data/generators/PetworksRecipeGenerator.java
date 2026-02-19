package gay.ampflower.mod.pet.data.generators;

import gay.ampflower.mod.pet.data.primitives.MaidCollarRecipeJsonBuilder;
import gay.ampflower.mod.pet.item.GuiseItem;
import gay.ampflower.mod.pet.registry.PetworksItems;
import gay.ampflower.mod.pet.util.Util;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
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

		createPrideCollarRecipe(PetworksItems.PRIDE_COLLAR,ItemTags.BANNERS,Dyes.RED,Dyes.ORANGE,Dyes.YELLOW,Dyes.GREEN,Dyes.BLUE,Dyes.PURPLE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.PROGRESS_COLLAR,ItemTags.BANNERS,Dyes.RED,Dyes.ORANGE,Dyes.YELLOW,Dyes.GREEN,Dyes.BLUE,Dyes.PURPLE,Dyes.BLACK).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.TRANS_COLLAR,Items.EGG,Dyes.LIGHT_BLUE,Dyes.PINK,Dyes.WHITE,Dyes.LIGHT_BLUE,Dyes.PINK).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.GENDERQUEER_COLLAR,Items.GLASS_BOTTLE,Dyes.PURPLE,Dyes.WHITE,Dyes.GREEN).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.GENDERFLUID_COLLAR,Items.WATER_BUCKET,Dyes.PINK,Dyes.WHITE,Dyes.MAGENTA,Dyes.BLACK,Dyes.BLUE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.AGENDER_COLLAR,Items.GLASS,Dyes.BLACK,Dyes.LIGHT_GRAY,Dyes.WHITE,Dyes.LIME,Dyes.WHITE,Dyes.LIGHT_GRAY,Dyes.BLACK).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.BIGENDER_COLLAR,Items.LEVER,Dyes.MAGENTA,Dyes.PINK,Dyes.LIGHT_GRAY,Dyes.WHITE,Dyes.LIGHT_GRAY,Dyes.LIGHT_BLUE,Dyes.BLUE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.DEMIBOY_1_COLLAR,Items.RAW_IRON,Dyes.GRAY,Dyes.LIGHT_GRAY,Dyes.LIGHT_BLUE,Dyes.WHITE,Dyes.LIGHT_BLUE,Dyes.LIGHT_GRAY,Dyes.GRAY).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.DEMIBOY_2_COLLAR,Items.RAW_IRON,Dyes.CYAN,Dyes.PURPLE,Dyes.WHITE,Dyes.GREEN,Dyes.CYAN).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.DEMIGENDER_COLLAR,Items.RAW_GOLD,Dyes.GRAY,Dyes.LIGHT_GRAY,Dyes.YELLOW,Dyes.WHITE,Dyes.YELLOW,Dyes.LIGHT_GRAY,Dyes.GRAY).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.DEMIGIRL_1_COLLAR,Items.RAW_COPPER,Dyes.GRAY,Dyes.LIGHT_GRAY,Dyes.PINK,Dyes.WHITE,Dyes.PINK,Dyes.LIGHT_GRAY,Dyes.GRAY).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.DEMIGIRL_2_COLLAR,Items.RAW_COPPER,Dyes.MAGENTA,Dyes.LIGHT_GRAY,Dyes.PINK,Dyes.WHITE,Dyes.PINK,Dyes.PINK,Dyes.GRAY).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.INTERSEX_1_COLLAR,Items.TORCH,Dyes.YELLOW,Dyes.YELLOW,Dyes.PURPLE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.INTERSEX_2_COLLAR,Items.TORCH,Dyes.YELLOW,Dyes.PURPLE,Dyes.YELLOW,Dyes.PURPLE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.NONBINARY_COLLAR,Items.AMETHYST_SHARD,Dyes.YELLOW,Dyes.WHITE,Dyes.PURPLE,Dyes.BLACK).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.ARO_COLLAR,Items.ARROW,Dyes.GREEN,Dyes.LIME,Dyes.WHITE,Dyes.LIGHT_GRAY,Dyes.BLACK).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.DEMIROMANTIC_COLLAR,Items.STRING,Dyes.BLACK,Dyes.WHITE,Dyes.GREEN,Dyes.LIGHT_GRAY).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.ACE_COLLAR,Items.BREAD, Dyes.BLACK,Dyes.LIGHT_GRAY,Dyes.WHITE,Dyes.PURPLE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.DEMISEXUAL_COLLAR,Items.LEATHER,Dyes.BLACK,Dyes.WHITE,Dyes.PURPLE,Dyes.LIGHT_GRAY).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.AROACE_COLLAR,Items.TARGET,Dyes.ORANGE,Dyes.YELLOW,Dyes.WHITE,Dyes.LIGHT_BLUE,Dyes.BLUE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.APLATONIC_COLLAR,Items.STONE_BUTTON,Dyes.PURPLE,Dyes.CYAN,Dyes.LIME,Dyes.YELLOW).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.GREYROSE_COLLAR,Items.ROSE_BUSH,Dyes.CYAN,Dyes.LIGHT_GRAY,Dyes.WHITE,Dyes.LIGHT_GRAY,Dyes.CYAN).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.GREYACE_COLLAR,Items.FLINT,Dyes.PURPLE,Dyes.LIGHT_GRAY,Dyes.WHITE,Dyes.LIGHT_GRAY,Dyes.PURPLE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.GREYARO_COLLAR,Items.IRON_NUGGET,Dyes.GREEN,Dyes.LIGHT_GRAY,Dyes.WHITE,Dyes.LIGHT_GRAY,Dyes.GREEN).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.LESBIAN_COLLAR,Items.HONEYCOMB,Dyes.ORANGE,Dyes.ORANGE,Dyes.WHITE,Dyes.PINK,Dyes.MAGENTA).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.GAY_COLLAR,Items.STONE_BRICK_WALL,Dyes.GREEN,Dyes.LIME,Dyes.LIME,Dyes.WHITE,Dyes.LIGHT_BLUE,Dyes.LIGHT_BLUE,Dyes.BLUE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.BISEXUAL_COLLAR,ItemTags.FENCE_GATES,Dyes.MAGENTA,Dyes.PURPLE,Dyes.BLUE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.PAN_COLLAR,Items.CHEST,Dyes.PINK,Dyes.YELLOW,Dyes.LIGHT_BLUE).offerTo(exporter);
		createPrideCollarRecipe(PetworksItems.POLYAMORY_COLLAR,Items.BUNDLE,Items.GOLD_NUGGET,Dyes.WHITE,Dyes.LIGHT_BLUE,Dyes.RED,Dyes.PURPLE).offerTo(exporter);

		createSpecialCollarRecipe(PetworksItems.MISSING_COLLAR,Items.AMETHYST_SHARD,Items.OBSIDIAN).offerTo(exporter);

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

	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, Item catalyst, Item flag1, Item flag2, Item flag3) {
		return ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input(PetworksItems.CLOTH_COLLAR)
			.input(catalyst)
			.input(flag1)
			.input(flag2)
			.input(flag3)
			.criterion(FabricRecipeProvider.hasItem(PetworksItems.CLOTH_COLLAR), FabricRecipeProvider.conditionsFromItem(PetworksItems.CLOTH_COLLAR));
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, Ingredient catalyst, Item flag1, Item flag2, Item flag3) {
		return ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input(PetworksItems.CLOTH_COLLAR)
			.input(catalyst)
			.input(flag1)
			.input(flag2)
			.input(flag3)
			.criterion(FabricRecipeProvider.hasItem(PetworksItems.CLOTH_COLLAR), FabricRecipeProvider.conditionsFromItem(PetworksItems.CLOTH_COLLAR));
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, TagKey<Item> catalyst, Item flag1, Item flag2, Item flag3) {
		return createPrideCollarRecipe(output, Ingredient.fromTag(catalyst),flag1,flag2,flag3)
			.criterion("has_materials",
				RecipeProvider.conditionsFromItemPredicates(
					ItemPredicate.Builder
						.create()
						.items(Items.STRING)
						.tag(catalyst)
						.build()
				)
			);
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, Item catalyst, Item flag1, Item flag2, Item flag3, Item flag4) {
		return ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input(PetworksItems.CLOTH_COLLAR)
			.input(catalyst)
			.input(flag1)
			.input(flag2)
			.input(flag3)
			.input(flag4)
			.criterion(FabricRecipeProvider.hasItem(PetworksItems.CLOTH_COLLAR), FabricRecipeProvider.conditionsFromItem(PetworksItems.CLOTH_COLLAR));
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, Item catalyst, Item flag1, Item flag2, Item flag3, Item flag4, Item flag5) {
		return ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input(PetworksItems.CLOTH_COLLAR)
			.input(catalyst)
			.input(flag1)
			.input(flag2)
			.input(flag3)
			.input(flag4)
			.input(flag5)
			.criterion(FabricRecipeProvider.hasItem(PetworksItems.CLOTH_COLLAR), FabricRecipeProvider.conditionsFromItem(PetworksItems.CLOTH_COLLAR));
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, Item catalyst, Item flag1, Item flag2, Item flag3, Item flag4, Item flag5, Item flag6) {
		return ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input(PetworksItems.CLOTH_COLLAR)
			.input(catalyst)
			.input(flag1)
			.input(flag2)
			.input(flag3)
			.input(flag4)
			.input(flag5)
			.input(flag6)
			.criterion(FabricRecipeProvider.hasItem(PetworksItems.CLOTH_COLLAR), FabricRecipeProvider.conditionsFromItem(PetworksItems.CLOTH_COLLAR));
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, Ingredient catalyst, Item flag1, Item flag2, Item flag3, Item flag4, Item flag5, Item flag6) {
		return ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input(PetworksItems.CLOTH_COLLAR)
			.input(catalyst)
			.input(flag1)
			.input(flag2)
			.input(flag3)
			.input(flag4)
			.input(flag5)
			.input(flag6)
			.criterion(FabricRecipeProvider.hasItem(PetworksItems.CLOTH_COLLAR), FabricRecipeProvider.conditionsFromItem(PetworksItems.CLOTH_COLLAR));
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, TagKey<Item> catalyst, Item flag1, Item flag2, Item flag3, Item flag4, Item flag5, Item flag6) {
		return createPrideCollarRecipe(output, Ingredient.fromTag(catalyst),flag1,flag2,flag3,flag4,flag5,flag6)
			.criterion("has_materials",
				RecipeProvider.conditionsFromItemPredicates(
					ItemPredicate.Builder
						.create()
						.items(Items.STRING)
						.tag(catalyst)
						.build()
				)
			);
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, Item catalyst, Item flag1, Item flag2, Item flag3, Item flag4, Item flag5, Item flag6, Item flag7) {
		return ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input(PetworksItems.CLOTH_COLLAR)
			.input(catalyst)
			.input(flag1)
			.input(flag2)
			.input(flag3)
			.input(flag4)
			.input(flag5)
			.input(flag6)
			.input(flag7)
			.criterion(FabricRecipeProvider.hasItem(PetworksItems.CLOTH_COLLAR), FabricRecipeProvider.conditionsFromItem(PetworksItems.CLOTH_COLLAR));
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, Ingredient catalyst, Item flag1, Item flag2, Item flag3, Item flag4, Item flag5, Item flag6, Item flag7) {
		return ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input(PetworksItems.CLOTH_COLLAR)
			.input(catalyst)
			.input(flag1)
			.input(flag2)
			.input(flag3)
			.input(flag4)
			.input(flag5)
			.input(flag6)
			.input(flag7)
			.criterion(FabricRecipeProvider.hasItem(PetworksItems.CLOTH_COLLAR), FabricRecipeProvider.conditionsFromItem(PetworksItems.CLOTH_COLLAR));
	}
	private CraftingRecipeJsonBuilder createPrideCollarRecipe(ItemConvertible output, TagKey<Item> catalyst, Item flag1, Item flag2, Item flag3, Item flag4, Item flag5, Item flag6,Item flag7) {
		return createPrideCollarRecipe(output, Ingredient.fromTag(catalyst),flag1,flag2,flag3,flag4,flag5,flag6,flag7)
			.criterion("has_materials",
				RecipeProvider.conditionsFromItemPredicates(
					ItemPredicate.Builder
						.create()
						.items(Items.STRING)
						.tag(catalyst)
						.build()
				)
			);
	}
	private CraftingRecipeJsonBuilder createSpecialCollarRecipe(ItemConvertible output, Ingredient input1, Ingredient input2) {
		return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
			.input('1', input1)
			.input('2', input2)
			.input('S', Items.STRING)
			.pattern("S1 ")
			.pattern("2 2")
			.pattern(" 1S")
			.showNotification(false);
	}
	private CraftingRecipeJsonBuilder createSpecialCollarRecipe(ItemConvertible output, Item input1, Item input2) {
		return createSpecialCollarRecipe(output, Ingredient.ofItems(input1), Ingredient.ofItems(input2))
			.criterion("has_materials",
				RecipeProvider.conditionsFromItemPredicates(
					ItemPredicate.Builder
						.create()
						.items(Items.STRING, input1, input2)
						.build()
				)
			);
	}

	public final class Dyes {
		public static final Item RED = Items.RED_DYE;
		public static final Item ORANGE = Items.ORANGE_DYE;
		public static final Item YELLOW = Items.YELLOW_DYE;
		public static final Item GREEN = Items.GREEN_DYE;
		public static final Item LIME = Items.LIME_DYE;
		public static final Item LIGHT_BLUE = Items.LIGHT_BLUE_DYE;
		public static final Item CYAN = Items.CYAN_DYE;
		public static final Item BLUE = Items.BLUE_DYE;
		public static final Item PURPLE = Items.PURPLE_DYE;
		public static final Item MAGENTA = Items.MAGENTA_DYE;
		public static final Item PINK = Items.PINK_DYE;
		public static final Item WHITE = Items.WHITE_DYE;
		public static final Item LIGHT_GRAY = Items.LIGHT_GRAY_DYE;
		public static final Item GRAY = Items.GRAY_DYE;
		public static final Item BLACK = Items.BLACK_DYE;
		public static final Item BROWN = Items.BROWN_DYE;
	}
}
