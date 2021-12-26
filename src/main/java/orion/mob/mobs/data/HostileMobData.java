package orion.mob.mobs.data;

import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;

public class HostileMobData extends MobData{
    private final double attack_speed, damage, rangedDamage;
    private final boolean isRanged;

    public HostileMobData(String name, float health, float speed, boolean isInvulnerable, EntityTypes<? extends EntityCreature> entityType, double attack_speed, double damage, double rangedDamage, boolean isRanged) {
        super(name, health, speed, isInvulnerable, entityType);
        this.attack_speed = attack_speed;
        this.damage = damage;
        this.rangedDamage = rangedDamage;
        this.isRanged = isRanged;
    }

    public double getAttack_speed() {
        return attack_speed;
    }

    public double getDamage() {
        return damage;
    }

    public double getRangedDamage() {
        return rangedDamage;
    }

    public boolean isRanged() {
        return isRanged;
    }
}
