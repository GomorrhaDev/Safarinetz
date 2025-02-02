package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;

public class FoxData extends MobData{
    private final Fox.Type type;
    private final int age;

    public FoxData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, LivingEntity entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Fox fox) {
            this.age = fox.getAge();
            this.type = fox.getFoxType();
        } else {
            throw new IllegalArgumentException("Kein Fuchs");
        }

    }

    @Override
    public void applyTo(LivingEntity entity) {
        super.applyTo(entity);

        if (entity instanceof Fox fox) {
            fox.setAge(age);
            fox.setFoxType(type);
        }
    }
}
