package dev.gomorrha.safarinetz.utils;

import dev.gomorrha.safarinetz.enums.Artikel;
import org.bukkit.entity.EntityType;

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

    public static HashMap<String, Artikel> getArtikel() {
        return artikel;
    }
}
