package gay.ampflower.mod.pet.item;

import gay.ampflower.mod.pet.support.TrinketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public abstract class GuiseItem extends Item implements Guise, Wearable, TrinketItem {
	private final Item component;

	private final int primaryColor, secondaryColor;

	protected GuiseItem(final Item component, final int primaryColor, final int secondaryColor) {
		super(new Item.Settings().maxCount(1));

		this.component = component;

		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
	}

	public int getColor(int tintIndex) {
		return switch (tintIndex) {
			case 1 -> primaryColor;
			case 2 -> secondaryColor;
			default -> -1;
		};
	}

	public Item getComponent() {
		return this.component;
	}

	@Override
	public boolean isAlike(final ItemStack stack) {
		return stack.getItem() instanceof Guise;
	}

	@Override
	public int maxWearCount() {
		return 1;
	}
}
