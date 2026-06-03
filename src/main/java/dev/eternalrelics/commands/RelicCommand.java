package dev.eternalrelics.commands;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class RelicCommand implements CommandExecutor {
    private final EternalRelicsPlugin plugin;
    public RelicCommand(EternalRelicsPlugin plugin) { this.plugin = plugin; }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("eternalrelics.admin")) return true;
        if (args.length == 0) {
            player.sendMessage("§6/relic <place|status>");
            return true;
        }
        if (args[0].equalsIgnoreCase("place")) {
            plugin.getRelicManager().placeRelic(player.getLocation());
            player.sendMessage(Component.text("Relic placed!", NamedTextColor.GREEN));
        } else if (args[0].equalsIgnoreCase("status")) {
            if (plugin.getRelicManager().isRelicActive()) {
                player.sendMessage("§6Relic is active with " + plugin.getRelicManager().getRelicHealth() + " HP");
            } else {
                player.sendMessage("§cNo relic active");
            }
        }
        return true;
    }
}
