package dev.eternalrelics.commands;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class KingCommand implements CommandExecutor {
    private final EternalRelicsPlugin plugin;
    public KingCommand(EternalRelicsPlugin plugin) { this.plugin = plugin; }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("eternalrelics.admin")) return true;
        if (args.length == 0) {
            sender.sendMessage("§6/king <player>");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cPlayer not found");
            return true;
        }
        plugin.getFactionManager().setHumanKing(target);
        plugin.getEventManager().broadcastToServer("§f[HUMAN] The Human King has been crowned!");
        return true;
    }
}
