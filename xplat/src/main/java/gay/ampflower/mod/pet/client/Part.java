package gay.ampflower.mod.pet.client;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public enum Part {
	UNKNOWN,
	HEAD,
	CHEST,
	LEGS,
	FEET,
	HANDS(true, true),
	HAND(true, false),
	OFFHAND(false, true),
	;

	public final boolean mainHand, offHand;

	Part() {
		this(false, false);
	}

	Part(boolean mainHand, boolean offHand) {
		this.mainHand = mainHand;
		this.offHand = offHand;
	}

	public boolean isHand() {
		return mainHand | offHand;
	}
}
