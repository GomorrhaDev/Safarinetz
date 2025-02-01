package dev.gomorrha.safarinetz.listeners;

import dev.gomorrha.safarinetz.mobdata.HorseData;
import dev.gomorrha.safarinetz.mobdata.MobData;
import dev.gomorrha.safarinetz.Safarinetz;
import dev.gomorrha.safarinetz.mobdata.SheepData;
import dev.gomorrha.safarinetz.utils.Factory;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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
        if (livingEntity instanceof Monster || livingEntity instanceof Player || livingEntity instanceof Villager) return;
        if (livingEntity.getHealth() <= 0) return;
        if(!Factory.isSafarinetzItem(netzItem)) return;


        catchMob(p, livingEntity, netzItem);


    }

    private void catchMob(Player p, LivingEntity entity, ItemStack netz) {
        double healthPercent = 100 / entity.getAttribute(Attribute.MAX_HEALTH).getValue() * entity.getHealth();

        MobData mobData;
        if(entity instanceof Horse || entity instanceof ChestedHorse){
            mobData = new HorseData(
                    entity.getType(),
                    entity.getCustomName(),
                    healthPercent,
                    entity.getPersistentDataContainer(),
                    entity
            );
        } else if(entity instanceof Sheep){
            mobData = new SheepData(
                    entity.getType(),
                    entity.getCustomName(),
                    healthPercent,
                    entity.getPersistentDataContainer(),
                    entity
            );
        }else {
            mobData = new MobData(
                    entity.getType(),
                    entity.getCustomName(),
                    healthPercent,
                    entity.getPersistentDataContainer(),
                    entity
            );
        }

        String serializedData = Safarinetz.getGson().toJson(mobData);

        ItemMeta meta = netz.getItemMeta();
        meta.getPersistentDataContainer().set(Safarinetz.getMobDataKey(), PersistentDataType.STRING, serializedData);
        meta.setDisplayName("§6Safarinetz §7(" + entity.getType().toString() + ")");
        netz.setItemMeta(meta);

        entity.remove();

        if(netz.getAmount() > 1){
            netz.setAmount(netz.getAmount() - 1);
        } else {
            p.getInventory().setItemInMainHand(null);
        }

        p.getInventory().addItem(netz);
    }
}
