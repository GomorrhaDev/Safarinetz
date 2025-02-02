package dev.gomorrha.safarinetz.listeners;

import dev.gomorrha.safarinetz.mobdata.*;
import dev.gomorrha.safarinetz.Safarinetz;
import dev.gomorrha.safarinetz.utils.Factory;
import dev.gomorrha.safarinetz.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class CaptureListener implements Listener {

    @EventHandler
    public void onAnimalCapture(EntityDamageByEntityEvent ev){
        if(ev.isCancelled()) return;
        if(!(ev.getDamager() instanceof Player)) return;

        Player p = (Player) ev.getDamager();
        ItemStack netzItem = p.getInventory().getItemInMainHand();

        if(!(p.isSneaking())) return;

        Entity target = ev.getEntity();

        if(!(target instanceof LivingEntity livingEntity)) return;
        if (livingEntity instanceof Monster || livingEntity instanceof Player || livingEntity instanceof Villager || livingEntity instanceof EnderDragon) return;
        if (livingEntity.getHealth() <= 0) return;
        if(!Factory.isSafarinetzItem(netzItem)) return;

        catchMob(p, livingEntity, netzItem);

    }

    private void catchMob(Player p, LivingEntity entity, ItemStack netz) {
        double healthPercent = 100 / entity.getAttribute(Attribute.MAX_HEALTH).getValue() * entity.getHealth();

        p.sendMessage(entity.getType().toString());

        MobData mobData;
        switch (entity.getType().toString()) {
            case "DONKEY":
            case "CHESTED_HORSE":
                mobData = new DonkeyData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "MULE":
                mobData = new MuleData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "TURTLE":
                mobData = new TurtleData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "FOX":
                mobData = new FoxData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "HORSE":
                mobData = new HorseData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "SHEEP":
                mobData = new SheepData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            default:
                mobData = new MobData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
        }


        String serializedData = Safarinetz.getGson().toJson(mobData);

        ItemStack newNetzItem = Factory.createSafarinetzItem(entity, serializedData);

        entity.remove();

        if(netz.getAmount() > 1){
            netz.setAmount(netz.getAmount() - 1);
        } else {
            p.getInventory().setItemInMainHand(null);
        }

        p.getInventory().addItem(newNetzItem);
    }

}
