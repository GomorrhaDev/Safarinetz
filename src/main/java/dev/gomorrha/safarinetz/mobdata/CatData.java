package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.DyeColor;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataContainer;

public class CatData extends MobData{
    private final Cat.Type type;
    private final DyeColor dyeColor;

    public CatData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if(entity instanceof Cat cat){
            this.type = cat.getCatType();
            this.dyeColor = cat.getCollarColor();
        } else throw new IllegalArgumentException("Keine Katze");
    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Cat cat){
            cat.setCatType(type);
            cat.setCollarColor(dyeColor);
        }
    }
}
