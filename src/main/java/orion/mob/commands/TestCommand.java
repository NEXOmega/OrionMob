package orion.mob.commands;

import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import orion.mob.Mob;
import orion.mob.mobs.samples.SampleEntityCreature;
import orion.mob.mobs.samples.SampleHostile;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

public class TestCommand implements TabExecutor {
    Mob mob;

    public TestCommand(Mob mob) {
        this.mob = mob;
    }
    UUID uuid;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            if(args[0].equalsIgnoreCase("save")) {
                uuid = UUID.randomUUID();
                System.out.println(uuid);
                SampleEntityCreature creature = new SampleHostile(uuid, EntityTypes.CAT, ((CraftWorld)((Player) commandSender).getWorld()).getHandle(), "test",10, 1, false, ((Player) commandSender).getLocation(), 2);

                Mob.getDb().addMobData(creature);
            } else if(args[0].equalsIgnoreCase("load")) {
                try {
                    SampleEntityCreature creature = Mob.getDb().loadMobData(String.valueOf(uuid));
                    mob.getManager().add(creature);
                    creature.spawn();
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else if(args[0].equalsIgnoreCase("spawn")) {
                SampleEntityCreature creature = new SampleHostile(UUID.randomUUID(), EntityTypes.CAT, ((CraftWorld)((Player) commandSender).getWorld()).getHandle(), "test",10, 1, false, ((Player) commandSender).getLocation(), 2);
                mob.getManager().add(creature);
                try {
                    creature.spawn();
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
