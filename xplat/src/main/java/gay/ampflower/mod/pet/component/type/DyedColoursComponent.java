package gay.ampflower.mod.pet.component.type;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntImmutableList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * @author Ampflower
 * @since 0.1.1
 **/
public record DyedColoursComponent(IntList rgbs, boolean showInTooltips) implements TooltipAppender {
	public static final Codec<DyedColoursComponent> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codecs.rangedInt(-1, 0xFFFFFF)
				.sizeLimitedListOf(256)
				.optionalFieldOf("rgbs", IntLists.emptyList())
				.forGetter(DyedColoursComponent::rgbs),
			Codec.BOOL.optionalFieldOf("show_in_tooltip", Boolean.TRUE).forGetter(DyedColoursComponent::showInTooltips)
		).apply(instance, DyedColoursComponent::create)
	);

	public static final PacketCodec<ByteBuf, DyedColoursComponent> PACKET_CODEC = PacketCodec.tuple(
		PacketCodecs.INTEGER.collect(PacketCodecs.toList(256)),
		DyedColoursComponent::rgbs,

		PacketCodecs.BOOL,
		DyedColoursComponent::showInTooltips,

		DyedColoursComponent::create);

	public static final DyedColoursComponent DEFAULT = new DyedColoursComponent(IntLists.emptyList(), true);

	public DyedColoursComponent {
		if (rgbs.size() > 256) {
			throw new IllegalStateException("Got " + rgbs.size() + "; should be <= 256");
		}
	}

	public static DyedColoursComponent create(List<Integer> rgbs, boolean showInTooltips) {
		if (rgbs.isEmpty()) {
			return DEFAULT;
		}
		return new DyedColoursComponent(new IntImmutableList(rgbs), showInTooltips);
	}

	@Override
	public void appendTooltip(final Item.TooltipContext context, final Consumer<Text> tooltip, final TooltipType type) {
		if (!this.showInTooltips) {
			return;
		}

		if (type.isAdvanced()) {
			for (final int rgb : this.rgbs) {
				tooltip.accept(Text.translatable("item.color", String.format(Locale.ROOT, "#%06X", rgb)).formatted(Formatting.GRAY));
			}
		} else {
			tooltip.accept(Text.translatable("item.dyed").formatted(Formatting.GRAY, Formatting.ITALIC));
		}
	}
}
