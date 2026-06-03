package dev.eternalrelics.listeners;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class ItemEventListener implements Listener {
    private final EternalRelicsPlugin plugin;
    public ItemEventListener(EternalRelicsPlugin plugin) { this.plugin = plugin; }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Load player data
    }
}
