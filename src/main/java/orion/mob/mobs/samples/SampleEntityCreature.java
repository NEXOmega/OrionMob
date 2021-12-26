package orion.mob.mobs.samples;


import net.minecraft.server.v1_16_R3.*;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import orion.mob.mobs.data.MobData;
import orion.mob.utils.MmorpgGson;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public abstract class SampleEntityCreature extends EntityCreature {

    private final UUID uuid;
    private final String name;
    private final float vitality, speed;
    private final boolean isInvulnerable;
    private final Location loc;

    public SampleEntityCreature(UUID uuid, EntityTypes<? extends EntityCreature> entitytypes, World world, String name, float vitality, float speed, boolean isInvulnerable, Location loc) {
        super(entitytypes, world);
        this.uuid = uuid;
        this.name = name;
        this.vitality = vitality;
        this.speed = speed;
        this.isInvulnerable = isInvulnerable;
        this.loc = loc;
    }

    public void spawn() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.setHealth(this.getHealth());
        this.setName(this.getName() + " " + this.getMaxHealth() + " / " + this.getHealth());
        this.addPathfinder();
        WorldServer w = ((CraftWorld)this.getLoc().getWorld()).getHandle();
        w.addEntity(this);
        System.out.println("Spawned !" + MmorpgGson.GSON.toJson(this.getLoc(), Location.class));
    }

    @Override
    protected boolean damageEntity0(DamageSource damagesource, float f) {
        boolean a = super.damageEntity0(damagesource, f);
        this.setName(name + " " + this.getMaxHealth() + " / " + this.getHealth());
        return a;
    }

    public void setItem(EnumItemSlot slot, org.bukkit.inventory.ItemStack item) {
        this.setSlot(slot, CraftItemStack.asNMSCopy(item));
    }

    public UUID getUuid() {
        return uuid;
    }

    public Location getLoc() {
        return loc;
    }

    @Override
    public String getName() {
        return name;
    }

    public float getVitality() {
        return vitality;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public boolean isInvulnerable() {
        return isInvulnerable;
    }

    public void setName(String name) {
        this.setCustomName(new ChatComponentText(ChatColor.translateAlternateColorCodes('&', name)));
        this.setCustomNameVisible(true);
    }

    public static Class<? extends SampleEntityCreature> getFromName(String name) {
        switch (name) {
            case "hostile":
                return SampleHostile.class;
            default:
                return SampleEntityCreature.class;
        }
    }



    public abstract void addPathfinder();
    public abstract Document generateDocument();
}
