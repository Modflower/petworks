package gay.ampflower.mod.pet.fabric;

import dev.emi.trinkets.api.TrinketsApi;
import gay.ampflower.mod.pet.fabric.trinket.CollarTrinket;
import gay.ampflower.mod.pet.fabric.trinket.CosmeticCollarTrinket;
import gay.ampflower.mod.pet.fabric.trinket.GimickCollarTrinket;
import gay.ampflower.mod.pet.registry.PetworksItems;
import gay.ampflower.mod.pet.util.Pivot;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.function.SetEnchantmentsLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public class Main implements ModInitializer {

	@Override
	public void onInitialize() {
		Pivot.init();

		Pivot.registerCollars(TrinketsApi::registerTrinket,
			new CosmeticCollarTrinket(),
			new CollarTrinket(),
			new GimickCollarTrinket());

		LootTableEvents.MODIFY.register(((key, tableBuilder, source, registries) -> {
			if (!source.isBuiltin()) {
				return;
			}

			final var id = key.getValue();

			// Not interested in other loot tables.
			if (!Identifier.DEFAULT_NAMESPACE.equals(id.getNamespace())) {
				return;
			}

			switch (id.getPath()) {
				case "chests/ancient_city" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.75F).build())
						.with(ItemEntry.builder(PetworksItems.CHAIN_COLLAR).weight(200))
						.with(ItemEntry.builder(PetworksItems.OBSIDIAN_COLLAR).weight(200))
						.with(ItemEntry.builder(PetworksItems.IRON_COLLAR).weight(50))
						.with(ItemEntry.builder(PetworksItems.AMETHYST_COLLAR).weight(25))
						.with(ItemEntry.builder(PetworksItems.DIAMOND_COLLAR).weight(5))
						.apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30F, 50F))
							.conditionally(RandomChanceLootCondition.builder(0.75F)))
					);
				}
				case "chests/shipwreck_supply" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.15F).build())
						.with(ItemEntry.builder(PetworksItems.LEATHER_COLLAR)
							.apply(new EnchantRandomlyLootFunction.Builder()))
					);
				}
				case "chests/simple_dungeon", "chests/desert_pyramid", "chests/jungle_temple" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.25F).build())
						.with(ItemEntry.builder(PetworksItems.LEATHER_COLLAR).weight(50))
						.with(ItemEntry.builder(PetworksItems.IRON_COLLAR).weight(25))
						.with(ItemEntry.builder(PetworksItems.GOLD_COLLAR).weight(15))
						.with(ItemEntry.builder(PetworksItems.DIAMOND_COLLAR).weight(5))
						.apply(new SetEnchantmentsLootFunction.Builder()
							.conditionally(RandomChanceLootCondition.builder(0.15F)))
					);
				}

				case "chests/bastion_bridge", "chests/bastion_other" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.15F).build())
						.with(ItemEntry.builder(PetworksItems.GOLD_COLLAR))
						.apply(new SetEnchantmentsLootFunction.Builder()
							.conditionally(RandomChanceLootCondition.builder(0.25F)))
					);
				}

				case "chests/bastion_hoglin_stable" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.15F).build())
						.with(ItemEntry.builder(PetworksItems.CHAIN_COLLAR).weight(55))
						.with(ItemEntry.builder(PetworksItems.GOLD_COLLAR).weight(100))
						.with(ItemEntry.builder(PetworksItems.DIAMOND_COLLAR).weight(15))
						.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.75F)))
						.apply(new SetEnchantmentsLootFunction.Builder()
							.conditionally(RandomChanceLootCondition.builder(0.25F)))
					);
				}

				case "chests/bastion_treasure" -> {
					tableBuilder.pool(LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1, 2))
						.conditionally(RandomChanceLootCondition.builder(0.15F).build())
						.with(ItemEntry.builder(PetworksItems.GOLD_COLLAR).weight(15))
						.with(ItemEntry.builder(PetworksItems.DIAMOND_COLLAR).weight(100))
						.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1F)))
						.apply(new SetEnchantmentsLootFunction.Builder()
							.conditionally(RandomChanceLootCondition.builder(0.75F)))
					);
				}

				case "chests/nether_bridge" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.05F).build())
						.with(ItemEntry.builder(PetworksItems.LEATHER_COLLAR).weight(50))
						.with(ItemEntry.builder(PetworksItems.IRON_COLLAR).weight(50))
						.with(ItemEntry.builder(PetworksItems.GOLD_COLLAR).weight(25))
						.with(ItemEntry.builder(PetworksItems.DIAMOND_COLLAR).weight(5))
					);
				}

				case "chests/end_city_treasure" -> {
					// TODO: Retroadd endogena
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.45F).build())
						.with(ItemEntry.builder(PetworksItems.IRON_COLLAR).weight(75))
						.with(ItemEntry.builder(PetworksItems.DIAMOND_COLLAR).weight(50))
						.apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20F, 39F)))
					);
				}

				case "chests/woodland_mansion" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.25F).build())
						.with(ItemEntry.builder(PetworksItems.CHAIN_COLLAR).weight(75))
						.with(ItemEntry.builder(PetworksItems.EMERALD_COLLAR).weight(35))
						.with(ItemEntry.builder(PetworksItems.DIAMOND_COLLAR).weight(10))
					);
				}

				case "chests/stronghold_corridor" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.2F).build())
						.with(ItemEntry.builder(PetworksItems.IRON_COLLAR).weight(50))
						.with(ItemEntry.builder(PetworksItems.LEATHER_COLLAR).weight(5))
						.with(ItemEntry.builder(PetworksItems.GOLD_COLLAR).weight(5))
						.with(ItemEntry.builder(PetworksItems.DIAMOND_COLLAR).weight(5))
					);
				}

				case "chests/ruined_portal" -> {
					tableBuilder.pool(LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.25F).build())
						.with(ItemEntry.builder(PetworksItems.GOLD_COLLAR))
					);
				}

				case "chests/spawn_bonus_chest" -> {
					tableBuilder.pool(LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1, 3))
						.with(ItemEntry.builder(PetworksItems.HIDE_COLLAR))
						.with(ItemEntry.builder(PetworksItems.CLOTH_COLLAR))
					);
				}
			}
		}));
	}
}
