package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;

public class DonkeyData extends MobData{
    private final double jumpStrength;
    private final double speed;
    private final int age;
    private final boolean tamed;

    public DonkeyData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, LivingEntity entity) {
        super(entityType, customName, health, nbtData, entity);

        if (entity instanceof Donkey donkey) {
            this.jumpStrength = donkey.getJumpStrength();
            this.speed = donkey.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue();
            this.age = donkey.getAge();
            this.tamed = donkey.isTamed();

        } else {
            throw new IllegalArgumentException("Kein Esel");
        }
    }

    @Override
    public void applyTo(LivingEntity entity) {
        super.applyTo(entity);

        if (entity instanceof Donkey donkey) {
            donkey.setJumpStrength(jumpStrength);
            entity.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(speed);
            donkey.setAge(age);
            donkey.setTamed(tamed);
        }
    }

}
