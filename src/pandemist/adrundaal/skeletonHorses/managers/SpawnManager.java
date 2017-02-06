package pandemist.adrundaal.skeletonHorses.managers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import pandemist.adrundaal.skeletonHorses.config.RegionConfig;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class SpawnManager implements Listener {
	@EventHandler
	public void entitySpawn(EntitySpawnEvent event) {
		if(event.getEntityType().equals(EntityType.HORSE)) {
			Horse h=(Horse) event.getEntity();
			if(h.getVariant().equals(Horse.Variant.SKELETON_HORSE)) {
				Location horsePos=h.getLocation();
				ApplicableRegionSet newSet=WGBukkit.getRegionManager((World) horsePos.getWorld()).getApplicableRegions((Vector) BukkitUtil.toVector((Location) horsePos).toBlockPoint());
				for(ProtectedRegion newRegion : newSet) {
					spawnHandling(horsePos.getWorld(), newRegion.getId(), event);
				}
			}
		}
	}

	public void spawnHandling(World world, String id, EntitySpawnEvent event) {
		if(!RegionConfig.existsValue(world.getName(), id)) {
			return;
		}
		if(RegionConfig.getSkeletonHorseSpawn(world.getName(), id).equals("disable")) {
			//	System.out.println(Config.getOptionValue("display-not-allowed-message"));
			event.setCancelled(true);
		}
	}
}
/*

	@EventHandler
	public void playerMove(PlayerMoveEvent event) {
		this.playerChangePosition(event.getPlayer(), event.getFrom(), event.getTo());
	}
	@EventHandler
	public void playerTeleport(PlayerTeleportEvent event) {
		this.playerChangePosition(event.getPlayer(), event.getFrom(), event.getTo());
	}
	private void playerChangePosition(Player player, Location from, Location to) {
		if(UtilLoc.diff(from, to)) {
			ApplicableRegionSet oldSet = WGBukkit.getRegionManager((World)from.getWorld()).getApplicableRegions((Vector)BukkitUtil.toVector((Location)from).toBlockPoint());
			ApplicableRegionSet newSet = WGBukkit.getRegionManager((World)to.getWorld()).getApplicableRegions((Vector)BukkitUtil.toVector((Location)to).toBlockPoint());
			for (ProtectedRegion newRegion : newSet) {
				playerIsElytraFlying(to.getWorld(), newRegion.getId(), player);
				return;
			}
			playerIsElytraFlying(to.getWorld(), "__global__", player);
		}
	}
	private void playerIsElytraFlying(World world, String id, Player player) {
		if (!RegionConfig.existsValue(world.getName(), id)) {
			return;
		}
		if(!player.isGliding()) {
			return;
		}
		if(RegionConfig.getElytraUse(world.getName(), id).equals("deny")) {
			//	System.out.println(Config.getOptionValue("display-not-allowed-message"));
			if(Config.getOptionValue("display-not-allowed-message").equals("true")) {
				player.sendMessage(Config.getLang("flying-not-allowed"));
			}
			player.setGliding(false);
		}
	}*/