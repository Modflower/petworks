package gay.ampflower.mod.pet.data.primitives;

/**
 * @author Ampflower
 * @since ${version}
 **/
public enum GuiLight {
	FRONT("front"), SIDE("side");

	public final String type;

	GuiLight(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
