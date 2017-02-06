package pandemist.adrundaal.skeletonHorses.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
	private static FileConfiguration config;

	public static void  init(FileConfiguration config) {
		Config.config = config;
	}
	public static void destroy() {
		config = null;
	}
	public static String getLang(String key) {
		return ChatColor.translateAlternateColorCodes('&', Config.notNull(config.getString("lang."+key)));
	}
	private static String notNull(String string) {
		return string != null ? string : "";
	}
}

