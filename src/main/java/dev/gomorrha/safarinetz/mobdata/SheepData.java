package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.DyeColor;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Sheep;
import org.bukkit.persistence.PersistentDataContainer;

public class SheepData extends MobData{
    private final DyeColor color;
    private final boolean sheared;
    private final int age;

    public SheepData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, LivingEntity entity) {
        super(entityType, customName, health, nbtData, entity);
        if(entity instanceof Sheep sheep){
            this.color = sheep.getColor();
            this.sheared = sheep.isSheared();
            this.age = sheep.getAge();
        } else{
            throw new IllegalArgumentException("Kein Schaf");
        }

    }

    @Override
    public void applyTo(LivingEntity entity) {
        super.applyTo(entity);

        if (entity instanceof Sheep sheep) {
            sheep.setColor(color);
            sheep.setSheared(sheared);
            sheep.setAge(age);
        }
    }


}
