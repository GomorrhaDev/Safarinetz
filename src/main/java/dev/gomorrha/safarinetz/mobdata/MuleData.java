package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mule;
import org.bukkit.persistence.PersistentDataContainer;

public class MuleData extends MobData{
    private final double jumpStrength;
    private final double speed;
    private final boolean tamed;

    public MuleData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);

        if (entity instanceof Mule mule) {
            this.jumpStrength = mule.getJumpStrength();
            this.speed = mule.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue();
            this.tamed = mule.isTamed();

        } else throw new IllegalArgumentException("Kein Maultier");
    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Mule mule) {
            mule.setJumpStrength(jumpStrength);
            entity.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(speed);
            mule.setTamed(tamed);
        }
    }

}
