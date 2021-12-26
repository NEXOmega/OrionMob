package orion.mob.mobs.data;

import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.entity.EntityType;

import java.util.Arrays;

public class MobData {
    private final String name;
    private final float health, speed;
    private final boolean isInvulnerable;
    private final EntityTypes<? extends EntityCreature> entityType;

    public MobData(String name, float health, float speed, boolean isInvulnerable, EntityTypes<? extends EntityCreature> entityType) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.isInvulnerable = isInvulnerable;
        this.entityType = entityType;
    }

    public String getName() {
        return name;
    }

    public float getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isInvulnerable() {
        return isInvulnerable;
    }

    public EntityTypes<? extends EntityCreature> getEntityType() {
        return entityType;
    }

    public void print() {
        System.out.println("test sample mobData");
    }
}
