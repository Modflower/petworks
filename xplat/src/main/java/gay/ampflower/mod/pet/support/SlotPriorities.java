package gay.ampflower.mod.pet.support;

/**
 * @author Ampflower
 * @since 0.2.0
 **/
public enum SlotPriorities implements SlotPriority {
	COLLAR {
		@Override
		public TrinketSlot trinketSlot() {
			return new TrinketSlot("chest", "collar");
		}

		@Override
		public String curiosSlot() {
			return "collar";
		}
	},
	NECKLACE {
		@Override
		public TrinketSlot trinketSlot() {
			return new TrinketSlot("chest", "necklace");
		}

		@Override
		public String curiosSlot() {
			return "necklace";
		}
	}
}
