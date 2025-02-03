package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.bukkit.persistence.PersistentDataContainer;

public class ParrotData extends MobData{
    private final Parrot.Variant variant;

    public ParrotData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Parrot parrot) {
            this.variant = parrot.getVariant();
        } else throw new IllegalArgumentException("Kein Papagei");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Parrot parrot) {
            parrot.setVariant(variant);
        }
    }
}
