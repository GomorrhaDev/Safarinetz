package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Llama;
import org.bukkit.persistence.PersistentDataContainer;

public class LlamaData extends MobData{
    private final Llama.Color color;

    public LlamaData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Llama llama) {
            this.color = llama.getColor();
        } else throw new IllegalArgumentException("Kein Llama");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Llama llama) {
            llama.setColor(color);
        }
    }
}
