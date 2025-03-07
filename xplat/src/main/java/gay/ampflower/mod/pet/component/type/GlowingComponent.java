package gay.ampflower.mod.pet.component.type;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.ampflower.mod.pet.registry.PetworksDataComponentTypes;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author Ampflower
 * @since 0.2.0
 **/
public record GlowingComponent(int block, int sky) implements TooltipAppender {
	private GlowingComponent(byte block, byte sky) {
		this(block, (int) sky);
	}

	public static final Codec<GlowingComponent> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codecs.rangedInt(0, 15).optionalFieldOf("block", 0).forGetter(GlowingComponent::block),
			Codecs.rangedInt(0, 15).optionalFieldOf("sky", 0).forGetter(GlowingComponent::sky)
		).apply(instance, GlowingComponent::new)
	);

	public static final PacketCodec<ByteBuf, GlowingComponent> PACKET_CODEC = PacketCodec.tuple(
		PacketCodecs.BYTE,
		GlowingComponent::blockByte,
		PacketCodecs.BYTE,
		GlowingComponent::skyByte,
		GlowingComponent::new
	);

	public static final GlowingComponent DEFAULT = new GlowingComponent(0, 0);

	public GlowingComponent addBlock(int amount) {
		return new GlowingComponent(Math.min(block() + amount, 15), sky());
	}

	public GlowingComponent subtractBlock(int amount) {
		return new GlowingComponent(Math.max(block() - amount, 0), sky());
	}

	public GlowingComponent addSky(int amount) {
		return new GlowingComponent(block(), Math.min(sky() + amount, 15));
	}

	public GlowingComponent subtractSky(int amount) {
		return new GlowingComponent(block(), Math.max(sky() - amount, 0));
	}

	public boolean canAddBlock() {
		return block < 15;
	}

	public boolean canSubtractBlock() {
		return block > 0;
	}

	public boolean canAddSky() {
		return sky < 15;
	}

	public boolean canSubtractSky() {
		return sky > 0;
	}

	public Byte blockByte() {
		return (byte) block();
	}

	public Byte skyByte() {
		return (byte) sky();
	}

	public boolean isDark() {
		return this.equals(DEFAULT);
	}

	@Override
	public void appendTooltip(final Item.TooltipContext context, final Consumer<Text> tooltip, final TooltipType type) {
		if (type.isAdvanced()) {
			tooltip.accept(Text.translatable("item.petworks.glowing.advanced", block, sky).formatted(Formatting.YELLOW, Formatting.ITALIC));
		} else {
			tooltip.accept(Text.translatable("item.petworks.glowing").formatted(Formatting.YELLOW, Formatting.ITALIC));
		}
	}

	public static boolean addGlowing(ItemStack stack, @Nullable Entity entity) {
		final var glowing = stack.getOrDefault(PetworksDataComponentTypes.GLOWING, DEFAULT);
		if (!glowing.canAddBlock()) {
			return false;
		}

		stack.set(PetworksDataComponentTypes.GLOWING, glowing.addBlock(1));
		if (entity != null) {
			entity.playSound(SoundEvents.ITEM_GLOW_INK_SAC_USE, 0.8F, 1.F);
		}

		return true;
	}

	public static boolean subtractGlowing(ItemStack stack, @Nullable Entity entity) {
		var glowing = stack.getOrDefault(PetworksDataComponentTypes.GLOWING, DEFAULT);
		if (!glowing.canSubtractBlock()) {
			return false;
		}

		glowing = glowing.subtractBlock(1);

		if (glowing.isDark()) {
			stack.remove(PetworksDataComponentTypes.GLOWING);
		} else {
			stack.set(PetworksDataComponentTypes.GLOWING, glowing);
		}
		if (entity != null) {
			entity.playSound(SoundEvents.ITEM_INK_SAC_USE, 0.8F, 1.F);
		}
		return true;
	}
}
