package orion.mob.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import orion.mob.Mob;
import orion.mob.mobs.data.MobData;
import orion.mob.utils.FonctionUtils;

import java.util.List;

public class MobDataCommands implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("create")) {
                MobData mobData = new MobData(args[1], Float.parseFloat(args[2]), Float.parseFloat(args[3]), Boolean.parseBoolean(args[4]), FonctionUtils.getEntityTypesFromString("entity.minecraft."+args[5].toLowerCase()));
                //Mob.getDb().addMobData(mobData);
                commandSender.sendMessage("MobData created !");
            } else if(args[0].equalsIgnoreCase("info")) {
                //MobData mb = Mob.getDb().loadMobData(args[1]);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
