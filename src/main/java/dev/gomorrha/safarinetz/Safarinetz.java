package dev.gomorrha.safarinetz;

import com.google.gson.Gson;
import dev.gomorrha.safarinetz.commands.SafarinetzCommand;
import dev.gomorrha.safarinetz.listeners.CaptureListener;
import dev.gomorrha.safarinetz.listeners.ReleaseListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Safarinetz extends JavaPlugin {
    private static Gson gson;
    private static NamespacedKey mobDataKey;
    private static NamespacedKey netzIdentifier;
    private static Safarinetz plugin;

    @Override
    public void onEnable() {
        mobDataKey = new NamespacedKey(this, "mob_data");
        netzIdentifier = new NamespacedKey(this, "isNetz");
        gson = new Gson();
        plugin = this;

        getLogger().info("Plugin aktiviert");
        getServer().getPluginManager().registerEvents(new CaptureListener(), this);
        getServer().getPluginManager().registerEvents(new ReleaseListener(), this);

        new SafarinetzCommand();
    }


    @Override
    public void onDisable() {
        getLogger().info("Plugin deaktiviert");
    }

    public static Gson getGson(){
        return gson;
    }

    public static NamespacedKey getMobDataKey(){
        return mobDataKey;
    }

    public static NamespacedKey getNetzIdentifier(){
        return netzIdentifier;
    }

    public static Safarinetz getPlugin(){
        return plugin;
    }
}
