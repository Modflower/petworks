package gay.ampflower.mod.pet.support;

/**
 * @author Ampflower
 * @since 0.2.0
 **/
public record TrinketSlot(String group, String path) {
	@Override
	public String toString() {
		return group + '/' + path;
	}
}
