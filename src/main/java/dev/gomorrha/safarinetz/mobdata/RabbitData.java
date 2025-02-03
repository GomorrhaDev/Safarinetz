package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Rabbit;
import org.bukkit.persistence.PersistentDataContainer;

public class RabbitData extends MobData{
    private final Rabbit.Type type;
    private final int carrotTicks;

    public RabbitData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Rabbit rabbit) {
            this.type = rabbit.getRabbitType();
            this.carrotTicks = rabbit.getMoreCarrotTicks();
        } else throw new IllegalArgumentException("Kein Hase");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Rabbit rabbit) {
            rabbit.setRabbitType(type);
            rabbit.setMoreCarrotTicks(carrotTicks);
        }
    }
}
