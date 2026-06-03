package dev.eternalrelics.commands;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * /altar command handler
 * Manages altar placements and status
 */
public class AltarCommand implements CommandExecutor {
    
    private final EternalRelicsPlugin plugin;
    
    public AltarCommand(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players");
            return true;
        }
        
        if (!player.hasPermission("eternalrelics.admin")) {
            player.sendMessage(Component.text("You don't have permission", NamedTextColor.RED));
            return true;
        }
        
        if (args.length == 0) {
            player.sendMessage("§6/altar <helmet|chestplate|leggings|boots|open|close|status>");
            return true;
        }
        
        String action = args[0].toLowerCase();
        
        switch (action) {
            case "open" -> {
                plugin.getAltarManager().openAltars();
                player.sendMessage(Component.text("Altars opened!", NamedTextColor.GREEN));
            }
            case "close" -> {
                plugin.getAltarManager().closeAltars();
                player.sendMessage(Component.text("Altars closed!", NamedTextColor.RED));
            }
            case "status" -> {
                String status = plugin.getAltarManager().isOpen() ? "§aOPEN" : "§cCLOSED";
                player.sendMessage(Component.text("Altars are currently: " + status, NamedTextColor.YELLOW));
            }
            default -> player.sendMessage("§cUnknown altar command!");
        }
        
        return true;
    }
}
