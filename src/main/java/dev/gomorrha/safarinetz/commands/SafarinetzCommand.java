package dev.gomorrha.safarinetz.commands;

import dev.gomorrha.safarinetz.utils.Factory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class SafarinetzCommand extends Command {

    public SafarinetzCommand() {
        super("safarinetz");
        this.setDescription("Gibt einem Spieler ein leeres Safarinetz");
        this.setUsage("/safarinetz [Spielername]");
        this.setPermission("safarinetz.give");

        registerCommand();
    }

    private void registerCommand() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register("safarinetz", this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("safarinetz.give")) {
            sender.sendMessage("§cDu hast keine Berechtigung, diesen Befehl zu verwenden.");
            return true;
        }

        if (!(sender instanceof Player) && args.length == 0) {
            sender.sendMessage("§cNur Spieler können sich selbst ein Netz geben.");
            return true;
        }

        Player target;

        if (args.length == 1) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cSpieler '" + args[0] + "' nicht gefunden.");
                return true;
            }
        } else {
            target = (Player) sender;
        }

        ItemStack safarinetz = Factory.createSafarinetzItem();
        target.getInventory().addItem(safarinetz);

        return true;
    }
}
