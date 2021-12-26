package orion.mob;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import orion.mob.commands.MobDataCommands;
import orion.mob.commands.TestCommand;
import orion.mob.database.Database;
import orion.mob.mobs.MobManager;

public final class Mob extends JavaPlugin {

    static Database db = new Database("localhost", 27017);
    MobManager manager = new MobManager();

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getPluginManager();

        this.getCommand("test").setExecutor(new TestCommand(this));
        this.getCommand("mobdata").setExecutor(new MobDataCommands());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, manager, 0, 100L);
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Database getDb() {
        return db;
    }

    public MobManager getManager() {
        return manager;
    }
}
