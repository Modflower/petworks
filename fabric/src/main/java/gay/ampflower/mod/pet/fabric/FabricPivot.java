package gay.ampflower.mod.pet.fabric;

import gay.ampflower.mod.pet.util.VanillaPivot;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;

/**
 * @author Ampflower
 * @since 0.2.0
 **/
public class FabricPivot extends VanillaPivot {
	@Override
	public ItemGroup.Builder createItemGroupBuilder() {
		return FabricItemGroup.builder();
	}
}
