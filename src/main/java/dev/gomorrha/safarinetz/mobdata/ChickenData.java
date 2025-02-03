package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataContainer;

public class ChickenData extends MobData{
    private final int eggTime;

    public ChickenData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Chicken chicken) {
            this.eggTime = chicken.getEggLayTime();
        } else throw new IllegalArgumentException("Kein Huhn");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Chicken chicken) {
            chicken.setEggLayTime(eggTime);
        }
    }
}
