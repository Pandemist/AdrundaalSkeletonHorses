package pandemist.adrundaal.skeletonHorses.managers;

import java.lang.reflect.Type;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pandemist.adrundaal.skeletonHorses.commands.Commands;
import pandemist.adrundaal.skeletonHorses.commands.bindings.BukkitBindings;

import com.google.common.base.Joiner;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandLocals;
import com.sk89q.worldedit.util.command.Dispatcher;
import com.sk89q.worldedit.util.command.fluent.CommandGraph;
import com.sk89q.worldedit.util.command.parametric.Binding;
import com.sk89q.worldedit.util.command.parametric.ParametricBuilder;

public class CommandManager implements CommandExecutor {
	private Dispatcher displatcher;

	public CommandManager() {
		ParametricBuilder builder = new ParametricBuilder();
		builder.addBinding((Binding)new BukkitBindings(), new Type[0]);
		this.displatcher = new CommandGraph().builder(builder).commands().registerMethods((Object)new Commands()).graph().getDispatcher();
	}

	public boolean onCommand(CommandSender commandSender, Command command, String s,String[] string) {
		CommandLocals locals = new CommandLocals();
		locals.put(CommandSender.class, (Object)commandSender);
		if (commandSender instanceof Player) {
			locals.put(Player.class, (Object)commandSender);
		}
		try {
			this.displatcher.call(command.getName() + " " + Joiner.on((String)" ").join((Object[])string), locals, new String[0]);
		}catch(CommandException e) {
			commandSender.sendMessage(e.getMessage());
		}
		return true;
	}
}