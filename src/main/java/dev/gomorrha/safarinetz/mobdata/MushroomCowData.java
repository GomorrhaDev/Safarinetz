package dev.gomorrha.safarinetz.mobdata;

import io.papermc.paper.potion.SuspiciousEffectEntry;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MushroomCow;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class MushroomCowData extends MobData{
    private final MushroomCow.Variant variant;
    private final List<SuspiciousEffectEntry> nextEffects;

    public MushroomCowData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof MushroomCow mooshroom) {
            this.variant = mooshroom.getVariant();
            this.nextEffects = mooshroom.getStewEffects();
        } else throw new IllegalArgumentException("Keine MushroomCow");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof MushroomCow mooshroom) {
            mooshroom.setVariant(variant);
            mooshroom.setStewEffects(nextEffects);
        }
    }
}
