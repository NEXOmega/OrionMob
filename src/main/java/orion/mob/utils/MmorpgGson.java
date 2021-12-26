package orion.mob.utils;

import com.google.gson.*;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;
import java.util.UUID;

public class MmorpgGson {
    public static Gson GSON = getPrettyGson();

    private static Gson getPrettyGson() {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();
        builder.registerTypeAdapter(EntityTypes.class, new EntityTypeAdapter());
        builder.registerTypeAdapter(Location.class, new LocationAdapter());
        return builder.create();
    }

    static class EntityTypeAdapter implements JsonSerializer<EntityTypes>, JsonDeserializer<EntityTypes> {
        @Override
        public EntityTypes deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String mob = jsonElement.getAsJsonObject().get("type").getAsString();
            return FonctionUtils.getEntityTypesFromString(mob);
        }

        @Override
        public JsonElement serialize(EntityTypes entityTypes, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", entityTypes.f());
            return jsonObject;
        }
    }

    static class LocationAdapter implements JsonSerializer<Location>, JsonDeserializer<Location> {
        @Override
        public Location deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            World w = Bukkit.getWorld(UUID.fromString(jsonObject.get("world").getAsString()));
            double x = jsonObject.get("x").getAsDouble(), y = jsonObject.get("y").getAsDouble(), z = jsonObject.get("z").getAsDouble();
            float yaw  = jsonObject.get("yaw").getAsFloat(), pitch = jsonObject.get("pitch").getAsFloat();
            return new Location(w, x, y, z, yaw, pitch);
        }

        @Override
        public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("world", String.valueOf(location.getWorld().getUID()));
            jsonObject.addProperty("x", location.getX());
            jsonObject.addProperty("y", location.getY());
            jsonObject.addProperty("z", location.getZ());
            jsonObject.addProperty("yaw", location.getYaw());
            jsonObject.addProperty("pitch", location.getPitch());
            return jsonObject;
        }
    }
}
