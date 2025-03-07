package gay.ampflower.mod.pet.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface Droppable {
	ItemStack onDrop(PlayerEntity player, ItemStack stack, boolean retainOwnership);
}
