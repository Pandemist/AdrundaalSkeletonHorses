package pandemist.adrundaal.skeletonHorses.commands.bindings;

import org.bukkit.command.CommandSender;

import com.sk89q.worldedit.util.command.parametric.ArgumentStack;
import com.sk89q.worldedit.util.command.parametric.BindingBehavior;
import com.sk89q.worldedit.util.command.parametric.BindingHelper;
import com.sk89q.worldedit.util.command.parametric.BindingMatch;

public class BukkitBindings extends BindingHelper {
	@BindingMatch(type={CommandSender.class}, behavior=BindingBehavior.PROVIDES)
	public CommandSender getCommandSender(ArgumentStack stack) {
		return (CommandSender)stack.getContext().getLocals().get(CommandSender.class);
	}
}

