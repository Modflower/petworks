package gay.ampflower.mod.pet.component.type;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntImmutableList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

/**
 * @author Ampflower
 * @since 0.1.1
 **/
public record DyedColoursComponent(IntList rgbs) {
	public static final Codec<DyedColoursComponent> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codecs.rangedInt(-1, 0xFFFFFF)
				.sizeLimitedListOf(256)
				.optionalFieldOf("rgbs", IntLists.emptyList())
				.forGetter(DyedColoursComponent::rgbs)
		).apply(instance, DyedColoursComponent::create)
	);

	public static final PacketCodec<ByteBuf, DyedColoursComponent> PACKET_CODEC = PacketCodec.tuple(
		PacketCodecs.INTEGER.collect(PacketCodecs.toList(256)),
		DyedColoursComponent::rgbs,
		DyedColoursComponent::create);

	public static final DyedColoursComponent DEFAULT = new DyedColoursComponent(IntLists.emptyList());

	public DyedColoursComponent {
		if (rgbs.size() > 256) {
			throw new IllegalStateException("Got " + rgbs.size() + "; should be <= 256");
		}
	}

	public static DyedColoursComponent create(List<Integer> rgbs) {
		if (rgbs.isEmpty()) {
			return DEFAULT;
		}
		return new DyedColoursComponent(new IntImmutableList(rgbs));
	}
}
