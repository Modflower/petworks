package gay.ampflower.mod.pet.util;

import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.EnumMap;

/**
 * VoxelShape emitter that rotates as needed.
 *
 * @author Ampflower
 * @since 0.1.0
 **/
public final class VoxelEmitter {
	private static final double B = 16;

	private final double
		ax, ay, az,
		bx, by, bz;

	private VoxelEmitter(final double ax, final double ay, final double az,
								final double bx, final double by, final double bz) {
		this.ax = ax;
		this.ay = ay;
		this.az = az;

		this.bx = bx;
		this.by = by;
		this.bz = bz;
	}

	/**
	 * Expected: Floor north box in 1x1x1 bound
	 */
	public static VoxelEmitter of(final double ax, final double ay, final double az,
											final double bx, final double by, final double bz) {
		return new VoxelEmitter(ax, ay, az, bx, by, bz);
	}

	/**
	 * Expected: Floor north box in 16x16x16 bound
	 */
	public static VoxelEmitter ofBlock(final double ax, final double ay, final double az,
												  final double bx, final double by, final double bz) {
		return new VoxelEmitter(ax / B, ay / B, az / B, bx / B, by / B, bz / B);
	}

	/**
	 * Identity of the emitter
	 */
	public VoxelShape floorNorth() {
		return VoxelShapes.cuboid(ax, ay, az, bx, by, bz);
	}

	public VoxelShape floorSouth() {
		return VoxelShapes.cuboid(1 - bx, ay, 1 - bz, 1 - ax, by, 1 - az);
	}

	public VoxelShape floorEast() {
		return VoxelShapes.cuboid(1 - bz, ay, ax, 1 - az, by, bx);
	}

	public VoxelShape floorWest() {
		return VoxelShapes.cuboid(az, ay, 1 - bx, bz, by, 1 - ax);
	}

	public VoxelShape ceilingNorth() {
		return VoxelShapes.cuboid(ax, 1 - by, az, bx, 1 - ay, bz);
	}

	public VoxelShape ceilingSouth() {
		return VoxelShapes.cuboid(1 - bx, 1 - by, 1 - bz, 1 - ax, 1 - ay, 1 - az);
	}

	public VoxelShape ceilingEast() {
		return VoxelShapes.cuboid(1 - bz, 1 - by, ax, 1 - az, 1 - ay, bx);
	}

	public VoxelShape ceilingWest() {
		return VoxelShapes.cuboid(az, 1 - by, 1 - bx, bz, 1 - ay, 1 - ax);
	}

	public VoxelShape north() {
		return VoxelShapes.cuboid(ax, az, 1 - by, bx, bz, 1 - ay);
	}

	public VoxelShape south() {
		return VoxelShapes.cuboid(1 - bx, az, ay, 1 - ax, bz, by);
	}

	public VoxelShape east() {
		return VoxelShapes.cuboid(ay, az, ax, by, bz, bx);
	}

	public VoxelShape west() {
		return VoxelShapes.cuboid(1 - by, az, 1 - bx, 1 - ay, bz, 1 - ax);
	}

	public EnumMap<Direction, VoxelShape> toWallMap(Direction... directions) {
		final var ret = new EnumMap<Direction, VoxelShape>(Direction.class);
		for (final Direction direction : directions) {
			ret.put(direction, ofWall(direction));
		}
		return ret;
	}


	public EnumMap<Direction, VoxelShape> toFloorMap(Direction... directions) {
		final var ret = new EnumMap<Direction, VoxelShape>(Direction.class);
		for (final Direction direction : directions) {
			ret.put(direction, ofFloor(direction));
		}
		return ret;
	}

	public EnumMap<Direction, VoxelShape> toCeilingMap(Direction... directions) {
		final var ret = new EnumMap<Direction, VoxelShape>(Direction.class);
		for (final Direction direction : directions) {
			ret.put(direction, ofCeiling(direction));
		}
		return ret;
	}

	public VoxelShape ofWall(Direction direction) {
		return switch (direction) {
			case DOWN -> floorNorth();
			case UP -> ceilingNorth();
			case NORTH -> north();
			case SOUTH -> south();
			case WEST -> west();
			case EAST -> east();
		};
	}

	public VoxelShape ofFloor(Direction direction) {
		return switch (direction) {
			case UP -> ceilingNorth();
			case DOWN, NORTH -> floorNorth();
			case SOUTH -> floorSouth();
			case WEST -> floorWest();
			case EAST -> floorEast();
		};
	}

	public VoxelShape ofCeiling(Direction direction) {
		return switch (direction) {
			case DOWN -> floorNorth();
			case UP, NORTH -> ceilingNorth();
			case SOUTH -> ceilingSouth();
			case WEST -> ceilingWest();
			case EAST -> ceilingEast();
		};
	}
}
