package gay.ampflower.mod.pet.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.ampflower.mod.pet.item.CollarDyeableItem;
import gay.ampflower.mod.pet.registry.PetworksRecipeSerializers;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class MaidCollarSmithingRecipe implements SmithingRecipe {
	public final Ingredient base;
	public final Ingredient addition;
	public final ItemStack result;

	public MaidCollarSmithingRecipe(final Ingredient base, final Ingredient addition, final ItemStack result) {
		this.base = base;
		this.addition = addition;
		this.result = result;
	}

	@Override
	public boolean testTemplate(final ItemStack stack) {
		return stack.isEmpty();
	}

	@Override
	public boolean testBase(final ItemStack stack) {
		return base.test(stack);
	}

	@Override
	public boolean testAddition(final ItemStack stack) {
		return addition.test(stack);
	}

	@Override
	public boolean matches(final SmithingRecipeInput input, final World world) {
		return testTemplate(input.template())
			&& testBase(input.base())
			&& testAddition(input.addition());
	}

	@Override
	public ItemStack craft(final SmithingRecipeInput input, final RegistryWrapper.WrapperLookup lookup) {
		final var base = input.base();
		final var addition = input.addition();
		final var result = this.result.copy();

		if (base != null) {
			result.applyComponentsFrom(base.getComponents());
		}

		if (!(result.getItem() instanceof CollarDyeableItem collar)) {
			return result;
		}

		final var baseDye = base.get(DataComponentTypes.DYED_COLOR);

		if (baseDye != null) {
			collar.setColor(result, 0, baseDye.rgb());
			result.remove(DataComponentTypes.DYED_COLOR);
		}

		final var dye = addition.get(DataComponentTypes.DYED_COLOR);

		if (dye != null) {
			collar.setColor(result, 1, dye.rgb());
		}

		return result;
	}

	@Override
	public ItemStack getResult(final RegistryWrapper.WrapperLookup registriesLookup) {
		return this.result;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return PetworksRecipeSerializers.MAID_COLLAR_TRANSFORM;
	}

	public static final class Serializer implements RecipeSerializer<MaidCollarSmithingRecipe> {
		private static final MapCodec<MaidCollarSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
					Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition),
					ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
				)
				.apply(instance, MaidCollarSmithingRecipe::new)
		);
		public static final PacketCodec<RegistryByteBuf, MaidCollarSmithingRecipe> PACKET_CODEC = PacketCodec.ofStatic(
			MaidCollarSmithingRecipe.Serializer::write, MaidCollarSmithingRecipe.Serializer::read
		);

		private static MaidCollarSmithingRecipe read(RegistryByteBuf buf) {
			Ingredient base = Ingredient.PACKET_CODEC.decode(buf);
			Ingredient addition = Ingredient.PACKET_CODEC.decode(buf);
			ItemStack result = ItemStack.PACKET_CODEC.decode(buf);
			return new MaidCollarSmithingRecipe(base, addition, result);
		}

		private static void write(RegistryByteBuf buf, MaidCollarSmithingRecipe recipe) {
			Ingredient.PACKET_CODEC.encode(buf, recipe.base);
			Ingredient.PACKET_CODEC.encode(buf, recipe.addition);
			ItemStack.PACKET_CODEC.encode(buf, recipe.result);
		}

		@Override
		public MapCodec<MaidCollarSmithingRecipe> codec() {
			return CODEC;
		}

		@Override
		public PacketCodec<RegistryByteBuf, MaidCollarSmithingRecipe> packetCodec() {
			return PACKET_CODEC;
		}
	}
}
