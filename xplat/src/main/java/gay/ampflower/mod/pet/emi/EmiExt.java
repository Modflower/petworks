package gay.ampflower.mod.pet.emi;

import com.mojang.logging.LogUtils;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.GeneratedSlotWidget;
import dev.emi.emi.api.widget.SlotWidget;
import gay.ampflower.mod.pet.recipe.MaidCollarSmithingRecipe;
import gay.ampflower.mod.pet.registry.PetworksDataComponentTypes;
import gay.ampflower.mod.pet.registry.tag.PetworksItemTags;
import gay.ampflower.mod.pet.util.Util;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.DyeColor;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
@EmiEntrypoint
public class EmiExt implements EmiPlugin {
	private static final Logger logger = LogUtils.getLogger();
	private static final DyeItem[] dyes = Arrays.stream(DyeColor.values()).map(DyeItem::byColor).toArray(DyeItem[]::new);

	private static final EmiStack[] DYES = Arrays.stream(dyes).map(EmiStack::of).toArray(EmiStack[]::new);

	@Override
	public void register(final EmiRegistry registry) {

		// I feel like Emi is going to dislike this.

		final var manager = registry.getRecipeManager();


		registry.addRecipe(EmiWorldInteractionRecipe.builder()
			.id(Util.synthetic("world/collar/shear", Util.slashify(PetworksItemTags.SHEARABLE_COLLARS)))
			.leftInput(EmiIngredient.of(PetworksItemTags.SHEARABLE_COLLARS))
			.rightInput(damage(EmiStack.of(Items.SHEARS), 1), false)
			.output(EmiStack.EMPTY)
			.supportsRecipeTree(false)
			.build()
		);

		registerCyclingPickaxe(registry);

		Util.iterate(PetworksItemTags.DYEABLE_COLLARS).forEach(entry ->
			registerDyeableCollar(registry, entry));

		final var collars = EmiIngredient.of(PetworksItemTags.COLLARS);
		final var glowingCollars = mutate(collars.copy(), collar -> collar.getItemStack().set(PetworksDataComponentTypes.GLOWING, true));

		registry.addRecipe(EmiWorldInteractionRecipe.builder()
			.id(Util.synthetic("world/collar/glow", Util.slashify(PetworksItemTags.COLLARS)))
			.leftInput(collars)
			.rightInput(EmiStack.of(Items.GLOW_INK_SAC), false)
			.output(EmiStack.EMPTY, slotMutator(glowingCollars, Util.rng.nextInt()))
			.supportsRecipeTree(false)
			.build()
		);

		registry.addRecipe(EmiWorldInteractionRecipe.builder()
			.id(Util.synthetic("world/collar/dark", Util.slashify(PetworksItemTags.COLLARS)))
			.leftInput(glowingCollars)
			.rightInput(EmiStack.of(Items.INK_SAC), false)
			.output(EmiStack.EMPTY, slotMutator(collars, Util.rng.nextInt()))
			.supportsRecipeTree(false)
			.build()
		);

		for (final var entry : manager.listAllOfType(RecipeType.SMITHING)) {
			if (!(entry.value() instanceof MaidCollarSmithingRecipe maidCollarRecipe)) {
				continue;
			}
			registry.addRecipe(new MaidCollarEmiRecipe(maidCollarRecipe, entry.id()));
		}
	}

	private static void registerCyclingPickaxe(final EmiRegistry registry) {
		final ToolItem[] pickaxes = Util.iterate(ItemTags.PICKAXES)
			.map(RegistryEntry::value)
			.filter(item -> item instanceof ToolItem)
			.map(item -> (ToolItem) item)
			.toArray(ToolItem[]::new);

		final EmiIngredient pickaxesIngredient = damage(EmiIngredient.of(ItemTags.PICKAXES), 1);

		final int unique = Util.rng.nextInt();

		final var armorCollars = EmiIngredient.of(PetworksItemTags.ARMOR_COLLARS);

		registry.addRecipe(EmiWorldInteractionRecipe.builder()
			.id(Util.synthetic("world/collar/pickaxe", Util.slashify(PetworksItemTags.ARMOR_COLLARS)))
			.leftInput(armorCollars, slotMutator(fetcher(armorCollars), unique))
			.rightInput(pickaxesIngredient, false, slotMutator(r -> {
				r.nextInt();
				return fetch(r, pickaxesIngredient);
			}, unique))
			.output(EmiStack.EMPTY, slotMutator(r -> {
				final var damaged = Util.getRandom(armorCollars.getEmiStacks(), r).getItemStack().copy();
				damaged.setDamage((int) (fetch(r, pickaxes).getMaterial().getMiningSpeedMultiplier() * 0.75f));
				return EmiStack.of(damaged);
			}, unique))
			.supportsRecipeTree(false)
			.build()
		);
	}

	private static void registerDyeableCollar(final EmiRegistry registry, final RegistryEntry<Item> entry) {
		final var item = entry.value();
		final var emiItem = EmiStack.of(item);
		final var waterBucket = EmiStack.of(Items.WATER_BUCKET).setRemainder(EmiStack.of(Items.BUCKET));
		final var waterBottleStack = new ItemStack(Items.POTION);
		waterBottleStack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Potions.WATER));

		final var waterBottle = EmiStack.of(waterBottleStack).setRemainder(EmiStack.of(Items.GLASS_BOTTLE));

		registry.addRecipe(EmiWorldInteractionRecipe.builder()
			.id(Util.synthetic("world/collar_washing/water_bucket", Util.slashify(entry)))
			.leftInput(emiItem, slotMutator(dyeGenerator(item)))
			.rightInput(waterBucket, false)
			.output(emiItem)
			.supportsRecipeTree(false)
			.build());

		registry.addRecipe(EmiWorldInteractionRecipe.builder()
			.id(Util.synthetic("world/collar_washing/water_bottle", Util.slashify(entry)))
			.leftInput(emiItem, slotMutator(dyeGenerator(item)))
			.rightInput(waterBottle, false)
			.output(emiItem)
			.supportsRecipeTree(false)
			.build());

		int unique = Util.rng.nextInt();
		registry.addRecipe(EmiWorldInteractionRecipe.builder()
			.id(Util.synthetic("world/collar_washing/dying", Util.slashify(entry)))
			.leftInput(emiItem)
			.rightInput(EmiIngredient.of(Ingredient.ofItems(dyes)), false, slotMutator(r -> DYES[r.nextInt(DYES.length)], unique))
			.output(emiItem, slotMutator(r -> EmiStack.of(DyedColorComponent.setColor(item.getDefaultStack(), List.of(dyes[r.nextInt(dyes.length)]))), unique))
			.supportsRecipeTree(false)
			.build());
	}

	private static UnaryOperator<SlotWidget> slotMutator(Function<Random, EmiIngredient> generator) {
		return slot -> new GeneratedSlotWidget(generator, Util.rng.nextInt(),
			slot.getBounds().x(), slot.getBounds().y());
	}

	private static UnaryOperator<SlotWidget> slotMutator(Function<Random, EmiIngredient> generator, int unique) {
		return slot -> new GeneratedSlotWidget(generator, unique,
			slot.getBounds().x(), slot.getBounds().y());
	}

	private static UnaryOperator<SlotWidget> slotMutator(EmiIngredient array, int unique) {
		return slot -> new GeneratedSlotWidget(fetcher(array), unique,
			slot.getBounds().x(), slot.getBounds().y());
	}

	private static UnaryOperator<SlotWidget> slotMutator(EmiIngredient[] array, int unique) {
		return slot -> new GeneratedSlotWidget(fetcher(array), unique,
			slot.getBounds().x(), slot.getBounds().y());
	}

	private static EmiStack fetch(Random random, EmiIngredient ingredient) {
		return Util.getRandom(ingredient.getEmiStacks(), random);
	}

	private static <V> V fetch(Random random, V... array) {
		return array[random.nextInt(array.length)];
	}

	private static Function<Random, EmiIngredient> fetcher(EmiIngredient array) {
		return r -> Util.getRandom(array.getEmiStacks(), r);
	}

	private static Function<Random, EmiIngredient> fetcher(EmiIngredient[] array) {
		return r -> array[r.nextInt(array.length)];
	}

	private static Function<Random, EmiIngredient> dyeGenerator(Item item) {
		return r -> {
			var stack = item.getDefaultStack();
			stack.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(r.nextInt(0xFFFFFF + 1), true));
			return EmiStack.of(stack);
		};
	}

	private static EmiIngredient mutate(EmiIngredient ingredient, Consumer<EmiStack> mutator) {
		for (final var stack : ingredient.getEmiStacks()) {
			mutator.accept(stack);
		}
		return ingredient;
	}

	private static EmiIngredient damage(EmiIngredient ingredient, int amount) {
		for (final var stack : ingredient.getEmiStacks()) {
			damage(stack, amount);
		}
		return ingredient;
	}

	private static EmiStack damage(EmiStack stack, int amount) {
		final var damaged = stack.getItemStack().copy();
		damaged.setDamage(amount);
		return stack.setRemainder(EmiStack.of(damaged));
	}
}
