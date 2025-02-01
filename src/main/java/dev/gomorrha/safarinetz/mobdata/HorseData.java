package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;

public class HorseData extends MobData{
    private final Horse.Color color;
    private final Horse.Style style;
    private final double jumpStrength;
    private final int age;
    private final boolean tamed;

    public HorseData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, LivingEntity entity) {
        super(entityType, customName, health, nbtData, entity);

        if (entity instanceof Horse horse) {
            this.color = horse.getColor();
            this.style = horse.getStyle();
            this.jumpStrength = horse.getJumpStrength();
            this.age = horse.getAge();
            this.tamed = horse.isTamed();
        } else {
            throw new IllegalArgumentException("Kein Pferd");
        }
    }

    @Override
    public void applyTo(LivingEntity entity) {
        super.applyTo(entity);

        if (entity instanceof Horse horse) {
            horse.setColor(color);
            horse.setStyle(style);
            horse.setJumpStrength(jumpStrength);
            horse.setAge(age);
            horse.setTamed(tamed);
        }
    }

}
