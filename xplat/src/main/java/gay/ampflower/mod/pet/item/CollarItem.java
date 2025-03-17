package gay.ampflower.mod.pet.item;

import gay.ampflower.mod.pet.component.type.GlowingComponent;
import gay.ampflower.mod.pet.registry.tag.PetworksItemTags;
import gay.ampflower.mod.pet.support.ServerSupport;
import gay.ampflower.mod.pet.support.SlotPriorities;
import gay.ampflower.mod.pet.support.TrinketItem;
import gay.ampflower.mod.pet.support.Trinkets;
import gay.ampflower.mod.pet.util.ItemUtils;
import gay.ampflower.mod.pet.util.Util;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class CollarItem extends VerticallyAttachableBlockItem implements Wearable, TrinketItem, Equipment {
	public final CollarMaterial material;

	public CollarItem(final CollarMaterial material, final Block standing, final Block wall, final Settings settings) {
		super(standing, wall, settings, Direction.DOWN);
		this.material = material;
	}

	//@Override
	//public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(final ItemStack stack, final SlotReference slot, final LivingEntity entity, final UUID uuid) {
	//    final var modifiers = Trinket.super.getModifiers(stack, slot, entity, uuid);

	//    //modifiers.put(EntityAttributes.GENERIC_LUCK, new EntityAttributeModifier(uuid, Util.idString("luck"), 8, EntityAttributeModifier.Operation.ADDITION));

	//    //SlotAttributes.addSlotModifier(modifiers, "chest/bell", uuid, 1.d, EntityAttributeModifier.Operation.ADDITION);

	//    return modifiers;
	//}


	@Override
	public EquipmentSlot getEquivalentSlotType() {
		return EquipmentSlot.HEAD;
	}

	@Override
	public boolean isArmor() {
		return material.getArmour() > 0;
	}

	@Override
	public ActionResult useOnEntity(final ItemStack stack, final PlayerEntity user, final LivingEntity entity, final Hand hand) {
		if (entity instanceof PlayerEntity player) {
			if (player.getInventory().insertStack(stack)) {
				return ActionResult.SUCCESS;
			}
			return ActionResult.FAIL;
		} else if (!Util.isAdventure(user, entity.getPos()) &&
			Trinkets.INSTANCE.equip(entity, stack, SlotPriorities.COLLAR, SlotPriorities.NECKLACE)
		) {
			return ActionResult.SUCCESS;
		}
		return super.useOnEntity(stack, user, entity, hand);
	}

	@Override
	public boolean isEnchantable(final ItemStack stack) {
		return true;
	}

	@Override
	public int getEnchantability() {
		return material.getEnchantability();
	}

	@Override
	public boolean canRepair(final ItemStack stack, final ItemStack ingredient) {
		return material.getRepairMaterial().test(ingredient) || super.canRepair(stack, ingredient);
	}

	@Override
	public TypedActionResult<ItemStack> use(final World world, final PlayerEntity user, final Hand hand) {
		final var stack = user.getStackInHand(hand).copy();
		if (Trinkets.INSTANCE.equip(user, stack, SlotPriorities.COLLAR, SlotPriorities.NECKLACE)) {
			return TypedActionResult.success(ItemStack.EMPTY, true);
		}
		return super.use(world, user, hand);
	}

	@Override
	public boolean onClicked(final ItemStack stack, final ItemStack otherStack, final Slot slot, final ClickType clickType, final PlayerEntity player, final StackReference cursorStackReference) {
		if (clickType != ClickType.RIGHT) {
			return false;
		}

		if (otherStack.isOf(Items.GLOW_INK_SAC) && GlowingComponent.addGlowing(stack, player)) {
			otherStack.decrementUnlessCreative(1, player);
			return true;
		}

		if (otherStack.isOf(Items.INK_SAC) && GlowingComponent.subtractGlowing(stack, player)) {
			otherStack.decrementUnlessCreative(1, player);
			return true;
		}

		if (stack.isDamageable() && otherStack.getItem() instanceof PickaxeItem pickaxe) {
			if (player instanceof ServerPlayerEntity serverPlayer) {
				final var material = pickaxe.getMaterial();
				final float base = material.getMiningSpeedMultiplier() * 0.75f;
				final Consumer<Item> consumer = item -> ServerSupport.broadcastAt(player, item.getBreakSound());
				stack.damage((int) base, serverPlayer.getServerWorld(), serverPlayer, consumer);
				otherStack.damage(1, serverPlayer.getServerWorld(), serverPlayer, consumer);
			}
			return true;
		}

		if (otherStack.getItem() instanceof ShearsItem) {
			if (stack.isIn(PetworksItemTags.SHEARABLE_COLLARS)) {
				stack.setCount(0);
				player.playSound(SoundEvents.ENTITY_SHEEP_SHEAR);
				if (player instanceof ServerPlayerEntity serverPlayer) {
					final Consumer<Item> consumer = item -> ServerSupport.broadcastAt(player, item.getBreakSound());
					otherStack.damage(1, serverPlayer.getServerWorld(), serverPlayer, consumer);
				}
				return true;
			}
		}

		if (!stack.isIn(ItemTags.DYEABLE)) {
			return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
		}

		if (otherStack.getItem() instanceof DyeItem dye) {
			var newStack = DyedColorComponent.setColor(stack, List.of(dye));
			if (!ItemStack.areEqual(stack, newStack)) {
				slot.setStack(newStack, stack);
				player.playSound(SoundEvents.ITEM_DYE_USE, 0.8F, 1.F);
				otherStack.decrementUnlessCreative(1, player);
				return true;
			}
		}

		if (otherStack.isOf(Items.WATER_BUCKET) && stack.contains(DataComponentTypes.DYED_COLOR)) {
			stack.remove(DataComponentTypes.DYED_COLOR);
			player.playSound(SoundEvents.ITEM_BUCKET_EMPTY, 0.8F, 1.F);
			cursorStackReference.set(
				ItemUsage.exchangeStack(otherStack, player, Items.BUCKET.getDefaultStack()));
			return true;
		}

		if (otherStack.isOf(Items.POTION) && ItemUtils.isPotionOf(otherStack, Potions.WATER)
			&& stack.contains(DataComponentTypes.DYED_COLOR)) {
			stack.remove(DataComponentTypes.DYED_COLOR);
			player.playSound(SoundEvents.ITEM_BOTTLE_EMPTY, 0.8F, 1.F);
			cursorStackReference.set(
				ItemUsage.exchangeStack(otherStack, player, Items.GLASS_BOTTLE.getDefaultStack()));
			return true;
		}

		return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
	}

	@Override
	public boolean isAlike(final ItemStack stack) {
		return stack.getItem() instanceof CollarItem;
	}

	@Override
	public int maxWearCount() {
		return 1;
	}

	@Override
	public EquipmentSlot getSlotType() {
		return EquipmentSlot.MAINHAND;
	}

	@Override
	public RegistryEntry<SoundEvent> getEquipSound() {
		return material.getEquipSound();
	}
}
