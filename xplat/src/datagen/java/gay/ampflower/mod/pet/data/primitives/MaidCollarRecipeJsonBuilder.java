package gay.ampflower.mod.pet.data.primitives;

import gay.ampflower.mod.pet.recipe.MaidCollarSmithingRecipe;
import gay.ampflower.mod.pet.registry.PetworksRecipeSerializers;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ampflower
 * @since ${version}
 **/
public class MaidCollarRecipeJsonBuilder {
	private final Ingredient base;
	private final Ingredient addition;
	private final RecipeCategory category;
	private final Item result;
	private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
	private final RecipeSerializer<?> serializer;

	public MaidCollarRecipeJsonBuilder(final Ingredient base, final Ingredient addition, final RecipeCategory category, final Item result) {
		this.base = base;
		this.addition = addition;
		this.category = category;
		this.result = result;
		this.serializer = PetworksRecipeSerializers.MAID_COLLAR_TRANSFORM;
	}

	public MaidCollarRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	public void offerTo(RecipeExporter exporter, Identifier id) {
		this.validate(id);
		final var builder = exporter.getAdvancementBuilder()
			.criterion("has_the_recipe", RecipeUnlockedCriterion.create(id))
			.rewards(AdvancementRewards.Builder.recipe(id))
			.criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
		this.criteria.forEach(builder::criterion);
		exporter.accept(id,
			new MaidCollarSmithingRecipe(base, addition, new ItemStack(result)),
			builder.build(id.withPrefixedPath("recipes/" + category.getName() + '/'))
		);
	}

	private void validate(Identifier recipeId) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + recipeId);
		}
	}
}
