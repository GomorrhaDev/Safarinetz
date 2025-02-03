package dev.gomorrha.safarinetz.mobdata;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.persistence.PersistentDataContainer;

public class PandaData extends MobData{
    private final Panda.Gene mainGene;
    private final Panda.Gene hiddenGene;

    public PandaData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);
        if (entity instanceof Panda panda) {
            this.mainGene = panda.getMainGene();
            this.hiddenGene = panda.getHiddenGene();

        } else throw new IllegalArgumentException("Kein Panda");

    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Panda panda) {
            panda.setMainGene(mainGene);
            panda.setHiddenGene(hiddenGene);
        }
    }
}
