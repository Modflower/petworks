package gay.ampflower.mod.pet.data;

import com.mojang.logging.LogUtils;
import gay.ampflower.mod.pet.data.generators.PetworksBlockTagGenerator;
import gay.ampflower.mod.pet.data.generators.PetworksItemTagGenerator;
import gay.ampflower.mod.pet.data.generators.PetworksModelGenerator;
import gay.ampflower.mod.pet.data.generators.PetworksRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

/**
 * @author Ampflower
 * @since ${version}
 **/
public class Main implements DataGeneratorEntrypoint {
	private static final Logger logger = LogUtils.getLogger();

	@Override
	public void onInitializeDataGenerator(final FabricDataGenerator fabricDataGenerator) {
		SharedConstants.isDevelopment = true;

		final var pack = fabricDataGenerator.createPack();
		pack.addProvider(PetworksRecipeGenerator::new);
		pack.addProvider(PetworksItemTagGenerator::new);
		pack.addProvider(PetworksBlockTagGenerator::new);
		pack.addProvider(PetworksModelGenerator::new);

		for (final var translation : Bootstrap.getMissingTranslations()) {
			logger.warn("Missing translation {}", translation);
		}
	}

	public static TagKey<Item> itemTag(String id) {
		return TagKey.of(RegistryKeys.ITEM, Identifier.of(id));
	}
}
