package gay.ampflower.mod.pet.util;

import com.mojang.logging.LogUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.slf4j.Logger;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class VanillaPivot implements Pivot {
	private static final Logger logger = LogUtils.getLogger();

	@Override
	public <V, T extends V> T register(final RegistryKey<Registry<V>> key, final String id, final T t) {
		@SuppressWarnings("unchecked") final var registry = (Registry<V>) Registries.REGISTRIES.getOrEmpty(key.getValue()).orElseThrow();
		return Registry.register(registry, Util.id(id), t);
	}

	@Override
	public ItemGroup.Builder createItemGroupBuilder() {
		logger.error("Stray call to VanillaPivot#createItemGroupBuilder(), providing dummy.", new Throwable());
		return ItemGroup.create(null, -1);
	}
}
