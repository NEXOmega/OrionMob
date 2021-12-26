package orion.mob.mobs.samples;

import net.minecraft.server.v1_16_R3.*;
import org.bson.Document;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import orion.mob.mobs.pathfinders.MoveTowardsTargetIfRange;
import orion.mob.utils.MmorpgGson;

import java.util.UUID;

public class SampleHostile extends SampleEntityCreature {

    double damages;

    public SampleHostile(UUID uuid, EntityTypes<? extends EntityCreature> entitytypes, World world, String name, float health, float speed, boolean isInvulnerable, Location loc, double damages) {
        super(uuid, entitytypes, world, name, health, speed, isInvulnerable, loc);
        this.damages = damages;

        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE.a(true)).setValue(damages);
    }

    @Override
    public void addPathfinder() {
        this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, this.getSpeed(), true));
        this.goalSelector.a(2, new MoveTowardsTargetIfRange(this, this.getSpeed(), 100, this.getLoc()));

        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
    }

    @Override
    public Document generateDocument() {
        Document document = new Document();
        document.put("name", getName());
        document.put("uuid", getUuid().toString());
        document.put("vitality", getVitality());
        document.put("speed", getSpeed());
        document.put("isInvulnerable", isInvulnerable());
        document.put("location", MmorpgGson.GSON.toJson(getLoc()));
        document.put("class", this.getClass().getName());
        document.put("entityTypes", MmorpgGson.GSON.toJson(this.getEntityType()));
        document.put("damages", damages);
        System.out.println(document);
        return document;
    }


    public static SampleHostile loadDocument(Document document) throws ClassNotFoundException {
        EntityTypes entityTypes = MmorpgGson.GSON.fromJson(document.getString("entityTypes"), EntityTypes.class);
        Location location = MmorpgGson.GSON.fromJson(document.getString("location"), Location.class);
        World w = ((CraftWorld)location.getWorld()).getHandle();

        String name = document.getString("name");
        String uuid = document.getString("uuid");
        float vitality = Float.parseFloat(document.getDouble("vitality").toString());
        float speed = Float.parseFloat(document.getDouble("speed").toString());
        boolean isInvulnerable = document.getBoolean("isInvulnerable");
        Class<? extends SampleEntityCreature> clazz = (Class<? extends SampleEntityCreature>) Class.forName(document.getString("class"));
        double damages = document.getDouble("damages");

        return new SampleHostile(UUID.fromString(uuid), entityTypes, w, name, vitality, speed, isInvulnerable, location, damages);
    }
}
