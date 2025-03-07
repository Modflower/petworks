package gay.ampflower.mod.pet.registry;

import gay.ampflower.mod.pet.recipe.MaidCollarSmithingRecipe;
import gay.ampflower.mod.pet.util.Pivot;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.RegistryKeys;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class PetworksRecipeSerializers {
	public static final RecipeSerializer<MaidCollarSmithingRecipe> MAID_COLLAR_TRANSFORM = register("maid_collar", new MaidCollarSmithingRecipe.Serializer());

	private static <T extends Recipe<?>, S extends RecipeSerializer<T>> S register(String id, S serializer) {
		return Pivot.INSTANCE.register(RegistryKeys.RECIPE_SERIALIZER, id, serializer);
	}

	public static void init() {
	}
}
