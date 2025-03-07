package gay.ampflower.mod.pet.registry.tag;

import gay.ampflower.mod.pet.util.Util;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class PetworksBlockTags {
	public static final TagKey<Block> COLLARS = TagKey.of(RegistryKeys.BLOCK, Util.id("collars"));
}
