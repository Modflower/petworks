package gay.ampflower.mod.pet.emi;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import gay.ampflower.mod.pet.recipe.MaidCollarSmithingRecipe;
import net.minecraft.util.Identifier;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class MaidCollarEmiRecipe extends BasicEmiRecipe {
	protected final EmiIngredient input, addition;
	protected final EmiStack output;

	public MaidCollarEmiRecipe(final MaidCollarSmithingRecipe recipe, final Identifier id) {

		super(VanillaEmiRecipeCategories.SMITHING, id, 112, 18);
		this.input = EmiIngredient.of(recipe.base);
		this.addition = EmiIngredient.of(recipe.addition);
		this.output = EmiStack.of(recipe.result);

		inputs.add(input);
		inputs.add(addition);
		outputs.add(output);
	}

	@Override
	public void addWidgets(final WidgetHolder widgets) {
		widgets.addTexture(EmiTexture.EMPTY_ARROW, 62, 1);
		widgets.addSlot(0, 0);
		widgets.addSlot(input, 18, 0);
		widgets.addSlot(addition, 36, 0);
		widgets.addSlot(output, 94, 0).recipeContext(this);
	}
}
