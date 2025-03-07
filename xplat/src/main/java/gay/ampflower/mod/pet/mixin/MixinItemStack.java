package gay.ampflower.mod.pet.mixin;

import gay.ampflower.mod.pet.registry.PetworksDataComponentTypes;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Ampflower
 * @since 0.1.1
 **/
@Mixin(ItemStack.class)
public class MixinItemStack {

	@Shadow
	private native <T extends TooltipAppender> void appendTooltip(
		ComponentType<T> componentType, Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type
	);

	@Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;appendAttributeModifiersTooltip(Ljava/util/function/Consumer;Lnet/minecraft/entity/player/PlayerEntity;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
	private void onGetTooltip(Item.TooltipContext context, @Nullable PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> callback, List<Text> list) {
		final Consumer<Text> consumer = list::add;

		appendTooltip(PetworksDataComponentTypes.DYED_COLOURS, context, consumer, type);
		appendTooltip(PetworksDataComponentTypes.GLOWING, context, consumer, type);
	}
}
