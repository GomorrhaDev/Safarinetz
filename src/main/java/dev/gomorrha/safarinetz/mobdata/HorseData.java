package dev.gomorrha.safarinetz.mobdata;

import dev.gomorrha.safarinetz.utils.Utils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

public class HorseData extends MobData{
    private final Horse.Color color;
    private final Horse.Style style;
    private final double jumpStrength;
    private final double speed;
    private final boolean tamed;
    private final String[] inv;

    public HorseData(EntityType entityType, String customName, double health, PersistentDataContainer nbtData, Animals entity) {
        super(entityType, customName, health, nbtData, entity);

        if (entity instanceof Horse horse) {
            this.color = horse.getColor();
            this.style = horse.getStyle();
            this.jumpStrength = horse.getJumpStrength();
            this.speed = horse.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue();
            this.tamed = horse.isTamed();
            this.inv = new String[horse.getInventory().getSize()];
            for (int i = 0; i < horse.getInventory().getSize(); i++) {
                this.inv[i] = Utils.itemStackToString(horse.getInventory().getItem(i));
            }


        } else throw new IllegalArgumentException("Kein Pferd");
    }

    @Override
    public void applyTo(Animals entity) {
        super.applyTo(entity);

        if (entity instanceof Horse horse) {
            horse.setColor(color);
            horse.setStyle(style);
            horse.setJumpStrength(jumpStrength);
            entity.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(speed);
            horse.setTamed(tamed);
            ItemStack[] newInv = new ItemStack[inv.length];
            for (int i = 0; i < inv.length; i++) {
                newInv[i] = Utils.stringToItemStack(inv[i]);
            }
            horse.getInventory().setContents(newInv);
        }
    }

}
