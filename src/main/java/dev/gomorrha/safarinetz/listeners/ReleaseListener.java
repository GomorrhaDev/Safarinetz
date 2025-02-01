package dev.gomorrha.safarinetz.listeners;

import dev.gomorrha.safarinetz.Safarinetz;
import dev.gomorrha.safarinetz.utils.Factory;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ReleaseListener implements Listener {

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;

        Player p = event.getPlayer();
        ItemStack netz = event.getItem();

        if (!Factory.isSafarinetzItem(netz)) return;

        ItemMeta meta = netz.getItemMeta();
        if (meta == null) return;

        String mobData = meta.getPersistentDataContainer().get(Safarinetz.getMobDataKey(), PersistentDataType.STRING);
        if (mobData == null) return;

        event.setCancelled(true);
        Factory.releaseMob(p, mobData, event.getInteractionPoint().add(0, 1, 0));

        if(netz.getAmount() > 1){
            netz.setAmount(netz.getAmount() - 1);
            p.getInventory().addItem(Factory.createSafarinetzItem());
        } else {
            p.getInventory().setItemInMainHand(null);
            p.getInventory().setItemInMainHand(Factory.createSafarinetzItem());
        }

    }

}
