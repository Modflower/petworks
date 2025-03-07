package gay.ampflower.mod.pet.support;

import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvent;

/**
 * @author Ampflower
 * @since 0.2.0
 **/
public final class ServerSupport {
	public static void broadcastAt(Entity entity, SoundEvent soundEvent) {
		final var world = entity.getWorld();
		world.playSoundFromEntity(null, entity, soundEvent, entity.getSoundCategory(), 1, 1);
	}
}
