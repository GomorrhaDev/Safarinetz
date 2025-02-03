package dev.gomorrha.safarinetz.listeners;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.gomorrha.safarinetz.Safarinetz;
import dev.gomorrha.safarinetz.mobdata.*;
import dev.gomorrha.safarinetz.utils.Factory;
import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
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
        releaseMob(p, mobData, event.getInteractionPoint().add(0, 1, 0));

        if(netz.getAmount() > 1){
            netz.setAmount(netz.getAmount() - 1);
            p.getInventory().addItem(Factory.createSafarinetzItem());
        } else {
            p.getInventory().setItemInMainHand(null);
            p.getInventory().setItemInMainHand(Factory.createSafarinetzItem());
        }

    }

    public static void releaseMob(Player player, String mobData, Location spawnLoc) {
        try {
            JsonObject json = JsonParser.parseString(mobData).getAsJsonObject();
            String entityType = json.get("entityType").getAsString();

            MobData data;

            switch (entityType) {
                case "WOLF":
                    data = Safarinetz.getGson().fromJson(mobData, WolfData.class);
                    break;
                case "RABBIT":
                    data = Safarinetz.getGson().fromJson(mobData, RabbitData.class);
                    break;
                case "PARROT":
                    data = Safarinetz.getGson().fromJson(mobData, ParrotData.class);
                    break;
                case "PANDA":
                    data = Safarinetz.getGson().fromJson(mobData, PandaData.class);
                    break;
                case "OCELOT":
                    data = Safarinetz.getGson().fromJson(mobData, OcelotData.class);
                    break;
                case "MUSHROOM_COW":
                case "MOOSHROOM":
                    data = Safarinetz.getGson().fromJson(mobData, MushroomCowData.class);
                    break;
                case "CHICKEN":
                    data = Safarinetz.getGson().fromJson(mobData, ChickenData.class);
                    break;
                case "LLAMA":
                    data = Safarinetz.getGson().fromJson(mobData, LlamaData.class);
                    break;
                case "GOAT":
                    data = Safarinetz.getGson().fromJson(mobData, GoatData.class);
                    break;
                case "FROG":
                    data = Safarinetz.getGson().fromJson(mobData, FrogData.class);
                    break;
                case "CAT":
                    data = Safarinetz.getGson().fromJson(mobData, CatData.class);
                    break;
                case "BEE":
                    data = Safarinetz.getGson().fromJson(mobData, BeeData.class);
                    break;
                case "AXOLOTL":
                    data = Safarinetz.getGson().fromJson(mobData, AxolotlData.class);
                    break;
                case "DONKEY":
                case "CHESTED_HORSE":
                case "MULE":
                    data = Safarinetz.getGson().fromJson(mobData, DonkeyData.class);
                    break;
                case "FOX":
                    data = Safarinetz.getGson().fromJson(mobData, FoxData.class);
                    break;
                case "HORSE":
                    data = Safarinetz.getGson().fromJson(mobData, HorseData.class);
                    break;
                case "SHEEP":
                    data = Safarinetz.getGson().fromJson(mobData, SheepData.class);
                    break;
                default:
                    data = Safarinetz.getGson().fromJson(mobData, MobData.class);
                    break;
            }


                Entity entity = player.getWorld().spawnEntity(spawnLoc, data.getEntityType());
                if (entity instanceof Animals animal) {
                    data.applyTo(animal);
                }
        } catch (Exception ignored) {
        }
    }

}
