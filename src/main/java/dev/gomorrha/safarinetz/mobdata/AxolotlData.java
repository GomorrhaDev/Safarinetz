package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataContainer;

public class AxolotlData extends MobData{
    private final Axolotl.Variant variant;

    public AxolotlData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if(entity instanceof Axolotl axolotl){
            variant = axolotl.getVariant();
        } else throw new IllegalArgumentException("Kein Axolotl");
    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Axolotl axolotl) {
            axolotl.setVariant(variant);
        }
    }
}
