package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Mule;
import org.bukkit.persistence.PersistentDataContainer;

public class DonkeyData extends MobData{
    private final double jumpStrength;
    private final double speed;
    private final boolean tamed;
    private final boolean isCarryingChest;

    public DonkeyData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);

        if (entity instanceof Donkey) {
            Donkey donkey = (Donkey) entity;
            this.jumpStrength = donkey.getJumpStrength();
            this.speed = donkey.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue();
            this.tamed = donkey.isTamed();
            this.isCarryingChest = donkey.isCarryingChest();

        } else if (entity instanceof Mule) {
            Mule mule = (Mule) entity;
            this.jumpStrength = mule.getJumpStrength();
            this.speed = mule.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue();
            this.tamed = mule.isTamed();
            this.isCarryingChest = mule.isCarryingChest();

        } else throw new IllegalArgumentException("Kein Esel");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Donkey donkey) {
            donkey.setJumpStrength(jumpStrength);
            donkey.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(speed);
            donkey.setTamed(tamed);
            donkey.setCarryingChest(true);
        }
        if (entity instanceof Mule mule) {
            mule.setJumpStrength(jumpStrength);
            mule.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(speed);
            mule.setTamed(tamed);
            mule.setCarryingChest(true);
        }
    }

}
