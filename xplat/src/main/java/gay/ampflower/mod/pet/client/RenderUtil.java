package gay.ampflower.mod.pet.client;

import gay.ampflower.mod.pet.util.Util;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.math.MathHelper;

/**
 * @author Ampflower
 * @since 0.1.0
 **/
public final class RenderUtil {
	public static float pow2(float base) {
		return base * base;
	}

	public static float pow3(float base) {
		// I'll be honest, one quick look at Math.pow tells me this is likely to be a fine enough solution
		return base * base * base;
	}

	public static float pow2a(float base, float add) {
		return base * base + add;
	}

	public static float ipow2(float base) {
		return 1F - pow2(base);
	}

	public static float rad(float deg) {
		return deg * MathHelper.RADIANS_PER_DEGREE;
	}

	public static float deg(float rad) {
		return rad * MathHelper.DEGREES_PER_RADIAN;
	}

	public static float bell(float x) {
		return -1 / (1 + pow2(x)) + 1;
	}

	public static float wrapRadians(float radians) {
		float f = radians % MathHelper.TAU;
		if (f >= MathHelper.PI) {
			f -= MathHelper.TAU;
		}
		if (f < -MathHelper.PI) {
			f += MathHelper.TAU;
		}
		return f;
	}

	public static ModelIdentifier id(String path, String variant) {
		return new ModelIdentifier(Util.id(path), variant);
	}
}
