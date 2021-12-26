package orion.mob.mobs;

import org.bukkit.Bukkit;
import orion.mob.mobs.samples.SampleEntityCreature;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public class MobManager implements Runnable{
    private HashSet<SampleEntityCreature> spawns;

    public MobManager() {
        this.spawns = new HashSet<>();
    }

    @Override
    public void run() {
        for (SampleEntityCreature sp : spawns) {
            if(!sp.isAlive()) {
                try {
                    sp.spawn();
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void add(SampleEntityCreature creature) {
        this.spawns.add(creature);
    }
}
