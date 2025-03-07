package gay.ampflower.mod.pet.registry;

import gay.ampflower.mod.pet.component.type.DyedColoursComponent;
import gay.ampflower.mod.pet.component.type.GlowingComponent;
import gay.ampflower.mod.pet.util.Util;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.UnaryOperator;

/**
 * @author Ampflower
 * @since 0.1.1
 **/
public final class PetworksDataComponentTypes {
	public static final ComponentType<DyedColoursComponent> DYED_COLOURS = register("dyed_colours", builder -> builder.codec(DyedColoursComponent.CODEC).packetCodec(DyedColoursComponent.PACKET_CODEC).cache());

	public static final ComponentType<GlowingComponent> GLOWING = register("glowing", builder -> builder.codec(GlowingComponent.CODEC).packetCodec(GlowingComponent.PACKET_CODEC).cache());
	private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> operator) {
		return Registry.register(Registries.DATA_COMPONENT_TYPE, Util.id(id), operator.apply(new ComponentType.Builder<>()).build());
	}

	public static void init() {
	}
}
