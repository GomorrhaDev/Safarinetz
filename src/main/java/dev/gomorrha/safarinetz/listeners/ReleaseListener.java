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

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (!Factory.isSafarinetzItem(item)) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        String mobData = meta.getPersistentDataContainer().get(Safarinetz.getMobDataKey(), PersistentDataType.STRING);
        if (mobData == null) return;

        event.setCancelled(true);
        Factory.releaseMob(player, mobData, event.getInteractionPoint().add(0, 1, 0));
    }

}
