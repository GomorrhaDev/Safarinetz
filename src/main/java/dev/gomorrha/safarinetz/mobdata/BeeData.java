package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataContainer;

public class BeeData extends MobData {
    private final boolean hasNectar;
    private final int anger;

    public BeeData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Bee bee) {
            this.hasNectar = bee.hasNectar();
            this.anger = bee.getAnger();
        }else throw new IllegalArgumentException("Keine Biene");
    }
    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);
        if(entity instanceof Bee bee){
            bee.setHasNectar(hasNectar);
            bee.setAnger(anger);
        }
    }
}
