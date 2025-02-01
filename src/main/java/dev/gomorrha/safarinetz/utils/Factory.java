package dev.gomorrha.safarinetz.utils;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.gomorrha.safarinetz.mobdata.HorseData;
import dev.gomorrha.safarinetz.mobdata.MobData;
import dev.gomorrha.safarinetz.Safarinetz;
import dev.gomorrha.safarinetz.mobdata.SheepData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;


public class Factory {

    public static ItemStack createCatcherItem() {
        ItemStack catcher = new ItemStack(Material.LEAD);
        ItemMeta meta = catcher.getItemMeta();
        meta.setDisplayName("§6Safarinetz");
        meta.setLore(java.util.Arrays.asList(
                "§7Shift + Schlagen um ein Mob zu fangen",
                "§7Rechtsklick um es freizulassen"
        ));
        catcher.setItemMeta(meta);
        return catcher;
    }

    public static void releaseMob(Player player, String mobData, Location spawnLoc) {
        try {
            JsonObject json = JsonParser.parseString(mobData).getAsJsonObject();
            String entityType = json.get("entityType").getAsString();

            MobData data;

            if (entityType.equalsIgnoreCase("HORSE")) {
                data = Safarinetz.getGson().fromJson(mobData, HorseData.class);
            } else if(entityType.equalsIgnoreCase("SHEEP")){
                data = Safarinetz.getGson().fromJson(mobData, SheepData.class);
            } else {
                data = Safarinetz.getGson().fromJson(mobData, MobData.class);
            }


            Entity entity = player.getWorld().spawnEntity(spawnLoc, data.getEntityType());
            if (entity instanceof LivingEntity livingEntity) {
                data.applyTo(livingEntity);
            }
        } catch (Exception ignored) {
        }
    }


    public static boolean isSafarinetzItem(ItemStack item) {
        if (item == null || !item.getType().equals(Material.LEAD)) return false;
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return false;
        if(meta.getEnchantLevel(Enchantment.DENSITY) != 69) return false;
        return meta.getPersistentDataContainer().has(Safarinetz.getNetzIdentifier());
    }

    public static ItemStack createSafarinetzItem() {
        ItemStack item = new ItemStack(Material.LEAD);
        ItemMeta meta = item.getItemMeta();
        meta.customName(Component.text("§eSafarinetz").decorate(TextDecoration.BOLD));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§fLeeres Netz"));
        lore.add(Component.space());
        lore.add(Component.text("§eDrücke Shift und schlage ein Tier um es einzufangen"));
        lore.add(Component.space());
        meta.lore(lore);
        meta.addEnchant(Enchantment.DENSITY, 69, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.getPersistentDataContainer().set(Safarinetz.getNetzIdentifier(), PersistentDataType.BOOLEAN, true);


        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createSafarinetzItem(LivingEntity entity, String serializedData) {
        ItemStack item = new ItemStack(Material.LEAD);
        ItemMeta meta = item.getItemMeta();

        meta.customName(Component.text("§e" + Utils.getArtikelMitTier(entity.getType())).decorate(TextDecoration.BOLD));

        meta.addEnchant(Enchantment.DENSITY, 69, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.getPersistentDataContainer().set(Safarinetz.getNetzIdentifier(), PersistentDataType.BOOLEAN, true);
        meta.getPersistentDataContainer().set(Safarinetz.getMobDataKey(), PersistentDataType.STRING, serializedData);

        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§7Inhalt: §f" + Utils.typeTranslator(entity.getType())));
        lore.add(Component.space());
        lore.add(Component.text("§eRechtsklick, um das Tier zu spawnen"));
        lore.add(Component.space());
        meta.lore(lore);

        item.setItemMeta(meta);

        return item;
    }



}