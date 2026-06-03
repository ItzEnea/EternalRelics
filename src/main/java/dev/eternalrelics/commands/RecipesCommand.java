package dev.eternalrelics.commands;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class RecipesCommand implements CommandExecutor {
    private final EternalRelicsPlugin plugin;
    public RecipesCommand(EternalRelicsPlugin plugin) { this.plugin = plugin; }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        // TODO: Open GUI recipe system
        player.sendMessage(Component.text("Recipe GUI coming soon!", NamedTextColor.YELLOW));
        return true;
    }
}
