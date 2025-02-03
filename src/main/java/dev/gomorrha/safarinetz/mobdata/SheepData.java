package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.DyeColor;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.persistence.PersistentDataContainer;

public class SheepData extends MobData{
    private final DyeColor color;
    private final boolean sheared;

    public SheepData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if(entity instanceof Sheep sheep){
            this.color = sheep.getColor();
            this.sheared = sheep.isSheared();
        } else throw new IllegalArgumentException("Kein Schaf");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Sheep sheep) {
            sheep.setColor(color);
            sheep.setSheared(sheared);
        }
    }


}
