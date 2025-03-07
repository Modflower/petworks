package gay.ampflower.mod.pet.item;

import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public interface CollarMaterial {
	String name();

	int getDurability();

	int getEnchantability();

	int getArmour();

	int getToughness();

	float getKnockbackResistance();

	Ingredient getRepairMaterial();

	RegistryEntry<SoundEvent> getEquipSound();

	BlockSoundGroup getSoundGroup();

	int getColour();

	boolean layeredColour();
}
