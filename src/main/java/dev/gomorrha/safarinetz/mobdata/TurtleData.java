package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Turtle;
import org.bukkit.persistence.PersistentDataContainer;

public class TurtleData extends MobData{
    private final int age;

    public TurtleData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, LivingEntity entity) {
        super(entityType, customName, health, nbtData, entity);

        if (entity instanceof Turtle turtle) {

            age = turtle.getAge();
        } else {
            throw new IllegalArgumentException("Keine Schildkröte");
        }

    }

    @Override
    public void applyTo(LivingEntity entity) {
        super.applyTo(entity);

        if (entity instanceof Turtle turtle) {
            turtle.setAge(age);
        }
    }
}
