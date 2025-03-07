package gay.ampflower.mod.pet.item;

import gay.ampflower.mod.pet.util.Util;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;

import java.util.function.Supplier;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public enum CollarMaterials implements CollarMaterial {
	MAID(0, 17, ingredientOfTag(ItemTags.WOOL),
		SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, BlockSoundGroup.WOOL, Util.dyeColor(DyeColor.BLACK), true),
	CLOTH(0, 17, ingredientOfTag(ItemTags.WOOL),
		SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, BlockSoundGroup.WOOL, Util.dyeColor(DyeColor.RED)),
	HIDE(0, 15, () -> Ingredient.ofItems(Items.RABBIT_HIDE),
		SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, BlockSoundGroup.NETHER_SPROUTS, 0xc79e67),
	LEATHER(5, 15, 1, 0, 0,
		() -> Ingredient.ofItems(Items.LEATHER), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, BlockSoundGroup.NETHER_SPROUTS, DyedColorComponent.DEFAULT_COLOR),
	CHAIN(15, 12, 1, 0, 0,
		() -> Ingredient.ofItems(Items.CHAIN), SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, BlockSoundGroup.CHAIN, 0x495065),
	IRON(15, 9, 2, 0, 0,
		() -> Ingredient.ofItems(Items.IRON_INGOT), SoundEvents.ITEM_ARMOR_EQUIP_IRON, BlockSoundGroup.METAL, 0xd8d8d8),
	GOLD(7, 25, 1, 0, 0,
		() -> Ingredient.ofItems(Items.GOLD_INGOT), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, BlockSoundGroup.METAL, 0xfdff76),

	// FIXME: default colours
	COPPER(9, 11, 1, 0, 0,
		() -> Ingredient.ofItems(Items.COPPER_INGOT), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, BlockSoundGroup.METAL, 0xfdff76),
	QUARTZ(22, 8, 2, 1, 0,
		() -> Ingredient.ofItems(Items.QUARTZ), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, BlockSoundGroup.METAL, 0xa1fbe8),
	AMETHYST(22, 20, 2, 1, 0,
		() -> Ingredient.ofItems(Items.AMETHYST_SHARD), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, BlockSoundGroup.METAL, 0xa1fbe8),
	EMERALD(25, 12, 2, 2, 0,
		() -> Ingredient.ofItems(Items.EMERALD), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, BlockSoundGroup.METAL, 0xa1fbe8),

	DIAMOND(33, 10, 3, 2, 0,
		() -> Ingredient.ofItems(Items.DIAMOND), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, BlockSoundGroup.METAL, 0xa1fbe8),
	NETHERITE(37, 15, 3, 3, 0.1F,
		() -> Ingredient.ofItems(Items.NETHERITE_INGOT), SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, BlockSoundGroup.METAL, 0x473e3f),
	OBSIDIAN(50, 5, 1, 3, 0.2F,
		() -> Ingredient.ofItems(Items.OBSIDIAN), SoundEvents.ITEM_ARMOR_EQUIP_IRON, BlockSoundGroup.STONE, 0x3b2754),
	BEDROCK(0, 25, 3, 5, 1,
		() -> Ingredient.ofItems(Items.BEDROCK), SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, BlockSoundGroup.STONE, 0xFFFFFF),
	;

	final int durabilityModifier;
	final int enchantability;
	final int armour;
	final int toughness;
	final float knockbackResistance;
	final Supplier<Ingredient> repairMaterial;
	final RegistryEntry<SoundEvent> equipSound;
	final BlockSoundGroup soundGroup;
	final int colour;
	final boolean layeredColour;


	CollarMaterials(final int durabilityModifier, final int enchantability,
						 final int armour, final int toughness, final float knockbackResistance,
						 final Supplier<Ingredient> repairMaterial,
						 final RegistryEntry<SoundEvent> equipSound, final BlockSoundGroup soundGroup,
						 final int colour, final boolean layeredColor) {
		this.durabilityModifier = durabilityModifier;
		this.enchantability = enchantability;
		this.armour = armour;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairMaterial = repairMaterial;
		this.equipSound = equipSound;
		this.soundGroup = soundGroup;
		this.colour = colour;
		this.layeredColour = layeredColor;
	}

	CollarMaterials(final int durabilityModifier, final int enchantability,
						 final int armour, final int toughness, final float knockbackResistance,
						 final Supplier<Ingredient> repairMaterial,
						 final RegistryEntry<SoundEvent> equipSound, final BlockSoundGroup soundGroup,
						 final int colour) {
		this(durabilityModifier,
			enchantability,
			armour,
			toughness,
			knockbackResistance,
			repairMaterial,
			equipSound,
			soundGroup,
			colour,
			false);
	}

	CollarMaterials(final int durabilityModifier, final int enchantability,
						 final Supplier<Ingredient> repairMaterial,
						 final RegistryEntry<SoundEvent> equipSound, final BlockSoundGroup soundGroup,
						 final int colour) {
		this(durabilityModifier, enchantability, 0, 0, 0, repairMaterial, equipSound, soundGroup, colour);
	}

	CollarMaterials(final int durabilityModifier, final int enchantability,
						 final Supplier<Ingredient> repairMaterial,
						 final RegistryEntry<SoundEvent> equipSound, final BlockSoundGroup soundGroup,
						 final int colour, final boolean layeredColour) {
		this(durabilityModifier, enchantability, 0, 0, 0, repairMaterial, equipSound, soundGroup, colour, layeredColour);
	}

	private static Supplier<Ingredient> ingredientOfTag(TagKey<Item> tagKey) {
		return () -> Ingredient.fromTag(tagKey);
	}

	public final int getDurability() {
		return 9 * durabilityModifier;
	}

	public final int getEnchantability() {
		return enchantability;
	}

	public final int getArmour() {
		return armour;
	}

	public final int getToughness() {
		return toughness;
	}

	public final float getKnockbackResistance() {
		return knockbackResistance;
	}

	public final Ingredient getRepairMaterial() {
		return repairMaterial.get();
	}

	public final RegistryEntry<SoundEvent> getEquipSound() {
		return equipSound;
	}

	public final BlockSoundGroup getSoundGroup() {
		return soundGroup;
	}

	public final int getColour() {
		return colour;
	}

	public final boolean layeredColour() {
		return layeredColour;
	}
}
