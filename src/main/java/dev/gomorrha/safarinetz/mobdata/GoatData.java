package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;

import org.bukkit.entity.Goat;
import org.bukkit.persistence.PersistentDataContainer;

public class GoatData extends MobData{
    private final boolean leftHorn;
    private final boolean rightHorn;

    public GoatData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Goat goat) {
            this.leftHorn = goat.hasLeftHorn();
            this.rightHorn = goat.hasRightHorn();
        } else throw new IllegalArgumentException("Keine Ziege");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Goat goat) {
            goat.setLeftHorn(leftHorn);
            goat.setRightHorn(rightHorn);
        }
    }
}
