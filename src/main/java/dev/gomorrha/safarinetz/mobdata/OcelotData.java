package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.persistence.PersistentDataContainer;

public class OcelotData extends MobData{
    private final boolean trust;

    public OcelotData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Ocelot ocelot) {
            this.trust = ocelot.isTrusting();
        } else throw new IllegalArgumentException("Kein Ozelot");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Ocelot ocelot) {
            ocelot.setTrusting(trust);
        }
    }
}
