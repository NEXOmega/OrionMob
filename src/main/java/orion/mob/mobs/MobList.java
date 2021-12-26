package orion.mob.mobs;

import net.minecraft.server.v1_16_R3.EntityTypes;
import orion.mob.mobs.data.HostileMobData;
import orion.mob.mobs.data.MobData;
import orion.mob.mobs.samples.SampleEntityCreature;
import orion.mob.mobs.samples.SampleHostile;

public enum MobList {
    PIG(new HostileMobData("piggy", 1, 2, false, EntityTypes.PIG, 2, 3, 4, false), SampleHostile.class);

    MobData data;
    Class<? extends SampleEntityCreature> entityClass;
    MobList(HostileMobData data, Class<SampleHostile> entityClass) {
        this.data = data;
        this.entityClass = entityClass;
    }
}
