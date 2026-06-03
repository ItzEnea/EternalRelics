package dev.eternalrelics.commands;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class EventCommand implements CommandExecutor {
    private final EternalRelicsPlugin plugin;
    public EventCommand(EternalRelicsPlugin plugin) { this.plugin = plugin; }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("eternalrelics.admin")) return true;
        if (args.length == 0) {
            player.sendMessage("§6/event <helmet|chestplate|leggings|boots|awakening>");
            return true;
        }
        String action = args[0].toLowerCase();
        plugin.getEventManager().startFragmentEvent(action);
        player.sendMessage(Component.text("Fragment event started: " + action, NamedTextColor.GREEN));
        return true;
    }
}
