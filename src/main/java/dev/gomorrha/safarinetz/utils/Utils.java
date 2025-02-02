package dev.gomorrha.safarinetz.utils;

import dev.gomorrha.safarinetz.enums.Artikel;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Utils {
    private static HashMap<String, Artikel> artikel = new HashMap<>();


    //Ich, wenn ich als Freelancer nach Stunde bezahlt werde
    public static String typeTranslator(EntityType et) {
        if (et == null) return null;

        String name = et.name();

        switch (name) {
            case "HORSE":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Pferd";
            case "DONKEY":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Esel";
            case "MULE":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Maultier";
            case "COW":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Kuh";
            case "MOOSHROOM":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Mooshroom";
            case "SHEEP":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Schaf";
            case "PIG":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Schwein";
            case "CHICKEN":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Huhn";
            case "RABBIT":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Kaninchen";
            case "LLAMA":
            case "TRADER_LLAMA":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Lama";
            case "TURTLE":
                artikel.putIfAbsent(name, Artikel.GEFANGENE);
                return "Schildkröte";
            case "PARROT":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Papagei";
            case "CAT":
                artikel.putIfAbsent(name, Artikel.GEFANGENE);
                return "Katze";
            case "OCELOT":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Ozelot";
            case "FOX":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Fuchs";
            case "WOLF":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Wolf";
            case "GOAT":
                artikel.putIfAbsent(name, Artikel.GEFANGENES);
                return "Ziege";
            case "AXOLOTL":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Axolotl";
            case "FROG":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Frosch";
            case "SALMON":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Lachs";
            case "COD":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Kabeljau";
            case "PUFFERFISH":
                artikel.putIfAbsent(name, Artikel.GEFANGENER);
                return "Kugelfisch";
            default:
                return name;

                //Ich wette ich habe was vergessen
        }
    }

    public static String getArtikelMitTier(EntityType et) {
        String tierName = typeTranslator(et);
        Artikel artikelType = artikel.get(et.name());

        String artikelText = "";

        if (artikelType != null) {
            switch (artikelType) {
                case GEFANGENES:
                    artikelText = "Gefangenes";
                    break;
                case GEFANGENER:
                    artikelText = "Gefangener";
                    break;
                case GEFANGENE:
                    artikelText = "Gefangene";
                    break;
                default:
                    artikelText = "Unbekannt";
                    break;
            }
        }

        return artikelText + " " + tierName;
    }

    public static String itemStackToString(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(item.getType());

        sb.append(":").append(item.getDurability());

        if (!item.getEnchantments().isEmpty()) {
            sb.append(":");
            item.getEnchantments().forEach((enchantment, level) -> {
                sb.append(enchantment.getKey().getKey()).append("=").append(level).append(";");
            });
        }

        return sb.toString();
    }

    public static ItemStack stringToItemStack(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        String[] parts = str.split(":");
        Material material = Material.getMaterial(parts[0]);
        if (material == null) {
            return null;
        }

        short durability = Short.parseShort(parts[1]);
        ItemStack itemStack = new ItemStack(material, 1);
        itemStack.setDurability(durability);

        if (parts.length > 2) {
            String[] enchantments = parts[2].split(";");
            for (String enchantment : enchantments) {
                String[] enchantmentParts = enchantment.split("=");
                Enchantment ench = Enchantment.getByKey(NamespacedKey.minecraft(enchantmentParts[0]));
                if (ench != null) {
                    itemStack.addEnchantment(ench, Integer.parseInt(enchantmentParts[1]));
                }
            }
        }

        return itemStack;
    }

}
