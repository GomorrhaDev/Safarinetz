package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.DyeColor;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.persistence.PersistentDataContainer;

public class WolfData extends MobData{
    private final Wolf.Variant variant;
    private final DyeColor dyeColor;

    public WolfData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Wolf wolf) {
            this.variant = wolf.getVariant();
            this.dyeColor = wolf.getCollarColor();
        } else throw new IllegalArgumentException("Kein Wolf");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Wolf wolf) {
            wolf.setVariant(variant);
            wolf.setCollarColor(dyeColor);
        }
    }
}
