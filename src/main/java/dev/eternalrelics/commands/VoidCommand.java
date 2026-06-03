package dev.eternalrelics.commands;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class VoidCommand implements CommandExecutor {
    private final EternalRelicsPlugin plugin;
    public VoidCommand(EternalRelicsPlugin plugin) { this.plugin = plugin; }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("eternalrelics.admin")) return true;
        if (args.length == 0 || !args[0].equalsIgnoreCase("awaken")) {
            player.sendMessage("§6/void awaken");
            return true;
        }
        plugin.getFactionManager().setVoidMaster(player);
        plugin.getEventManager().broadcastToServer("§5[VOID] The Void Master has awakened!");
        return true;
    }
}
