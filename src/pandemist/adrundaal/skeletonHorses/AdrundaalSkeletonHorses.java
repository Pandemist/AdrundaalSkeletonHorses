package pandemist.adrundaal.skeletonHorses;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import pandemist.adrundaal.skeletonHorses.config.Config;
import pandemist.adrundaal.skeletonHorses.config.RegionConfig;
import pandemist.adrundaal.skeletonHorses.managers.CommandManager;
import pandemist.adrundaal.skeletonHorses.managers.SpawnManager;

public class AdrundaalSkeletonHorses extends JavaPlugin {
	public void onEnable() {
		super.onEnable();
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		Config.init(this.getConfig());
		RegionConfig.init(this.getDataFolder()+File.separator + "regions.yml");
		Bukkit.getPluginManager().registerEvents((Listener) new SpawnManager(), (Plugin)this);
		CommandManager commandManager = new CommandManager();
		this.getCommand("regionSkeletonHorse").setExecutor((CommandExecutor)commandManager);
	}
	public void onDisable() {
		super.onDisable();
		Config.destroy();
		RegionConfig.destroy();
	}
}