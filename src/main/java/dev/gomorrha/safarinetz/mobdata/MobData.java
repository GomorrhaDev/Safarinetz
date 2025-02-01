
package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MobData implements Serializable {
    private final EntityType entityType;
    private final String customName;
    private final double health;
    private final Map<String, String> nbtData;



    public MobData(EntityType entityType, String customName, double health,
                   PersistentDataContainer nbtData, LivingEntity entity) {
        this.entityType = entityType;
        this.customName = customName;
        this.health = health;
        this.nbtData = new HashMap<>();

        nbtData.getKeys().forEach(key -> {
            String value = nbtData.get(key, PersistentDataType.STRING);
            if (value != null) {
                this.nbtData.put(key.toString(), value);
            }
        });
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void applyTo(LivingEntity entity) {
        if (customName != null) {
            entity.setCustomName(customName);
            entity.setCustomNameVisible(true);
        }

        entity.setHealth(entity.getAttribute(Attribute.MAX_HEALTH).getValue() * (health/100));

        nbtData.forEach((keyStr, value) -> {
            try {
                org.bukkit.NamespacedKey key = org.bukkit.NamespacedKey.fromString(keyStr);
                if (key != null) {
                    entity.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
                }
            } catch (IllegalArgumentException ignored) {

            }
        });
    }
}