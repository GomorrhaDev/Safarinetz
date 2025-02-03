package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.persistence.PersistentDataContainer;

public class FrogData extends MobData{
    private final Frog.Variant variant;

    public FrogData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Frog frog) {
            this.variant = frog.getVariant();
        } else throw new IllegalArgumentException("Kein Frosch");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Frog frog) {
            frog.setVariant(variant);
        }
    }
}
