package gay.ampflower.mod.pet.item;

import gay.ampflower.mod.pet.component.type.DyedColoursComponent;
import gay.ampflower.mod.pet.registry.PetworksDataComponentTypes;
import it.unimi.dsi.fastutil.ints.IntImmutableList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.block.Block;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.ColorHelper;

import java.util.Arrays;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class CollarDyeableItem extends CollarItem {
	public CollarDyeableItem(final CollarMaterial material, final Block standing, final Block wall, final Settings settings) {
		super(material, standing, wall, settings);
	}

	@Deprecated
	public int getColor(final ItemStack stack) {
		return getColor(stack, 0);
	}

	public int getColor(final ItemStack stack, int index) {
		final var colours = stack.get(PetworksDataComponentTypes.DYED_COLOURS);

		if (colours == null || colours == DyedColoursComponent.DEFAULT) {
			if (index > 0) {
				return -1;
			}
			return DyedColorComponent.getColor(stack, material.getColour());
		}

		final var list = colours.rgbs();
		if (list == null || index >= list.size()) {
			return -1;
		}
		return ColorHelper.Argb.fullAlpha(list.getInt(index));
	}

	public void setColor(final ItemStack stack, int index, int colour) {
		if (index < 0) {
			throw new IllegalArgumentException("index < 0");
		}
		final var colours = stack.getOrDefault(PetworksDataComponentTypes.DYED_COLOURS, DyedColoursComponent.DEFAULT);

		stack.set(PetworksDataComponentTypes.DYED_COLOURS, set(colours, index, colour));
	}

	private DyedColoursComponent set(final DyedColoursComponent component, int index, int colour) {
		// Due to a FastUtil bug, toArray(int[i > size]) will error in the case of ImmutableList.
		// This is the best workaround possible for the time being, within the context of this not
		// being called more than once within a given timeframe
		final IntList list = component.rgbs();
		final int[] colours = Arrays.copyOf(list.toIntArray(), Math.max(list.size(), index + 1));
		if (index > list.size()) {
			Arrays.fill(colours, list.size(), index, -1);
		}
		colours[index] = colour;
		return new DyedColoursComponent(new IntImmutableList(colours), component.showInTooltips());
	}
}
