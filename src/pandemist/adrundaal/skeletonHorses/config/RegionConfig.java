package pandemist.adrundaal.skeletonHorses.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class RegionConfig {
	private static String file;
	private static FileConfiguration config;

	public static void init(String file) {
		RegionConfig.file = file;
		RegionConfig.createIfNotExists(file);
		config = YamlConfiguration.loadConfiguration((File)new File(file));
	}
	private static boolean createIfNotExists(String file) {
		try {
			return new File(file).getParentFile().mkdirs() && new File(file).createNewFile();
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void destroy() {
		RegionConfig.saveConfig();
		config = null;
	}

	public static boolean existsValue(String world, String region) {
		return config.isConfigurationSection(world + "." + region);
	}

	public static String getSkeletonHorseSpawn(String world, String region) {
		return RegionConfig.notNull(config.getString(world+"."+region+".value"));
	}

	public static void setSkeletonHorseSpawn(String world, String region, String value) {
		config.set(world+"."+region+".value", (Object) value);
		saveConfig();
	}

	private static String notNull(String string) {
		return string != null ? string : "";
	}
	private static void saveConfig() {
		try {
			config.save(file);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
