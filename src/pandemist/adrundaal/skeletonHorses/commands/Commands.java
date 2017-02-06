package pandemist.adrundaal.skeletonHorses.commands;

import org.bukkit.command.CommandSender;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.worldedit.util.command.binding.Text;

import pandemist.adrundaal.skeletonHorses.config.Config;
import pandemist.adrundaal.skeletonHorses.config.RegionConfig;

public class Commands {
	@Command(aliases={"regionelytra"}, desc="Change Region status")
	public void setRegionElytraValue(String world, String region, @Text String value, CommandSender sender) {
		if(value.toLowerCase().equals("enable")) {
			RegionConfig.setSkeletonHorseSpawn(world,region,value);
			sender.sendMessage(chatTranslate(Config.getLang("set-skeletonHorse-allow"),region));
		}else if(value.toLowerCase().equals("disable")) {
			RegionConfig.setSkeletonHorseSpawn(world,region,value);
			sender.sendMessage(chatTranslate(Config.getLang("set-skeletonHorse-deny"),region));
		}else{
			sender.sendMessage(chatTranslate(Config.getLang("value-not-allowed"),region));
		}
	}
	public static String chatTranslate(String s,String region) {
		return s.replace("%REGION%", region);
	}
}
