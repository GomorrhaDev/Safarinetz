package dev.gomorrha.safarinetz.listeners;

import dev.gomorrha.safarinetz.mobdata.*;
import dev.gomorrha.safarinetz.Safarinetz;
import dev.gomorrha.safarinetz.utils.Factory;
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

        if(!(target instanceof Animals animal)) return;
        if(animal instanceof SkeletonHorse || animal instanceof ZombieHorse) return;
        if (animal.getHealth() <= 0) return;
        if(!Factory.isSafarinetzItem(netzItem)) return;

        catchMob(p, animal, netzItem);

    }

    private void catchMob(Player p, Animals entity, ItemStack netz) {
        double healthPercent = 100 / entity.getAttribute(Attribute.MAX_HEALTH).getValue() * entity.getHealth();

        MobData mobData;
        switch (entity.getType().toString()) {
            case "WOLF":
                mobData = new WolfData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "RABBIT":
                mobData = new RabbitData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "PARROT":
                mobData = new ParrotData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "PANDA":
                mobData = new PandaData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "OCELOT":
                mobData = new OcelotData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "MUSHROOM_COW":
            case "MOOSHROOM":
                mobData = new MushroomCowData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "CHICKEN":
                mobData = new ChickenData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "LLAMA":
                mobData = new LlamaData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "GOAT":
                mobData = new GoatData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "FROG":
                mobData = new FrogData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "CAT":
                mobData = new CatData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "BEE":
                mobData = new BeeData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "AXOLOTL":
                mobData = new AxolotlData(
                        entity.getType(),
                        entity.getCustomName(),
                        healthPercent,
                        entity.getPersistentDataContainer(),
                        entity
                );
                break;
            case "DONKEY":
            case "CHESTED_HORSE":
            case "MULE":
                mobData = new DonkeyData(
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
