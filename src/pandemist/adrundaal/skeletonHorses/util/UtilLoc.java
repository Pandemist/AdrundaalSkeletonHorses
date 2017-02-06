package pandemist.adrundaal.skeletonHorses.util;

import org.bukkit.Location;

public class UtilLoc {
	public static boolean diff(Location loc1, Location loc2) {
		return loc1.getWorld() != loc2.getWorld() || loc1.getBlockX() != loc2.getBlockX() || loc1.getBlockY() != loc2.getBlockY() || loc1.getBlockZ() != loc2.getBlockZ();
	}
}