package orion.mob.utils;

import net.minecraft.server.v1_16_R3.AttributeBase;
import net.minecraft.server.v1_16_R3.AttributeModifiable;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.attribute.Attribute;

import java.lang.reflect.Field;
import java.util.Map;

public class FonctionUtils {

    public static EntityTypes getEntityTypesFromString(String type) {
        switch (type){
            case "entity.minecraft.bee":
                return EntityTypes.BEE;
            case "entity.minecraft.cat":
                return EntityTypes.CAT;
            default:
                return EntityTypes.COD;
        }
    }

    private static Field attributeField;

    static {
        try {
            attributeField = net.minecraft.server.v1_16_R3.AttributeMapBase.class.getDeclaredField("b");
            attributeField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void registerGenericAttribute(org.bukkit.entity.Entity entity, Attribute attribute) throws IllegalAccessException {
        net.minecraft.server.v1_16_R3.AttributeMapBase attributeMapBase = ((org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity)entity).getHandle().getAttributeMap();
        Map<AttributeBase, AttributeModifiable> map = (Map<AttributeBase, AttributeModifiable>) attributeField.get(attributeMapBase);
        net.minecraft.server.v1_16_R3.AttributeBase attributeBase = org.bukkit.craftbukkit.v1_16_R3.attribute.CraftAttributeMap.toMinecraft(attribute);
        net.minecraft.server.v1_16_R3.AttributeModifiable attributeModifiable = new net.minecraft.server.v1_16_R3.AttributeModifiable(attributeBase, net.minecraft.server.v1_16_R3.AttributeModifiable::getAttribute);
        map.put(attributeBase, attributeModifiable);
    }
}
