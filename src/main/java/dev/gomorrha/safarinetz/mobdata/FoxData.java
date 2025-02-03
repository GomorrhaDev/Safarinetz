package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.persistence.PersistentDataContainer;

public class FoxData extends MobData{
    private final Fox.Type type;

    public FoxData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Fox fox) {
            this.type = fox.getFoxType();
        } else throw new IllegalArgumentException("Kein Fuchs");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Fox fox) {
            fox.setFoxType(type);
        }
    }
}
