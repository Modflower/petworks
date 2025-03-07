package gay.ampflower.mod.pet.registry;

import com.mojang.serialization.MapCodec;
import gay.ampflower.mod.pet.block.CollarBlock;
import gay.ampflower.mod.pet.block.WallCollarBlock;
import gay.ampflower.mod.pet.util.Util;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

/**
 * @author Ampflower
 * @since 0.1.1
 **/
public class PetworksBlockTypes {
	public static void init() {
		register("collar", CollarBlock.CODEC);
		register("wall_collar", WallCollarBlock.CODEC);
	}

	private static void register(String id, MapCodec<? extends Block> codec) {
		Registry.register(Registries.BLOCK_TYPE, Util.id(id), codec);
	}
}
