package dev.eternalrelics.core.manager;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages events, fragment spawning, and timed broadcasts
 * Handles crafting window timing and fragment holder tracking
 */
public class EventManager {
    
    private static final Logger logger = LoggerFactory.getLogger(EventManager.class);
    private final EternalRelicsPlugin plugin;
    
    // Fragment holders: fragmentId -> playerName
    private final Map<String, String> fragmentHolders = new HashMap<>();
    
    // Last broadcast times to prevent spam
    private final Map<String, Long> lastBroadcastTimes = new HashMap<>();
    
    public EventManager(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Starts a fragment event for an armor piece
     * @param armorType The armor type (helmet, chestplate, leggings, boots)
     */
    public void startFragmentEvent(String armorType) {
        logger.info("Fragment event started: {}", armorType);
        broadcastToServer("§6[⚔] Fragment of " + armorType + " has spawned!");
    }
    
    /**
     * Ends a fragment event
     * @param armorType The armor type
     */
    public void endFragmentEvent(String armorType) {
        logger.info("Fragment event ended: {}", armorType);
        fragmentHolders.remove("fragment_" + armorType);
    }
    
    /**
     * Records a fragment holder
     * @param fragmentId The fragment ID
     * @param playerName The player holding the fragment
     */
    public void setFragmentHolder(String fragmentId, String playerName) {
        fragmentHolders.put(fragmentId, playerName);
    }
    
    /**
     * Broadcasts fragment holder locations every 50 seconds
     */
    public void broadcastFragmentHolders() {
        for (Map.Entry<String, String> entry : fragmentHolders.entrySet()) {
            Player player = Bukkit.getPlayerExact(entry.getValue());
            if (player != null) {
                String msg = String.format(
                    "§6[Fragment Tracker] §f%s §7holds §f%s §7at §6%d %d %d",
                    entry.getValue(),
                    entry.getKey(),
                    (int) player.getLocation().getX(),
                    (int) player.getLocation().getY(),
                    (int) player.getLocation().getZ()
                );
                broadcastToServer(msg);
            }
        }
    }
    
    /**
     * Checks if it's within the crafting window
     * @return True if crafting is allowed
     */
    public boolean isCraftingWindowOpen() {
        int startHour = plugin.getConfigManager().getInt("crafting.start-hour");
        int endHour = plugin.getConfigManager().getInt("crafting.end-hour");
        
        LocalDateTime now = LocalDateTime.now(ZoneId.of(plugin.getConfigManager().getString("crafting.timezone")));
        int currentHour = now.getHour();
        
        if (startHour < endHour) {
            return currentHour >= startHour && currentHour < endHour;
        } else {
            return currentHour >= startHour || currentHour < endHour;
        }
    }
    
    /**
     * Checks crafting window broadcasts
     * Called every second by the scheduler
     */
    public void checkCraftingWindowBroadcasts() {
        if (!plugin.getConfigManager().getBoolean("crafting.broadcasts.enabled")) {
            return;
        }
        
        LocalDateTime now = LocalDateTime.now(ZoneId.of(plugin.getConfigManager().getString("crafting.timezone")));
        String timeKey = now.format(DateTimeFormatter.ofPattern("HH:mm"));
        
        // Check if broadcast should happen
        long lastTime = lastBroadcastTimes.getOrDefault(timeKey, 0L);
        if (System.currentTimeMillis() - lastTime < 60000) { // Only once per minute
            return;
        }
        
        // Map times to messages
        if (timeKey.equals("16:00")) {
            broadcastToServer(plugin.getConfigManager().getString("crafting.broadcasts.messages.opening"));
            lastBroadcastTimes.put(timeKey, System.currentTimeMillis());
        } else if (timeKey.equals("19:00")) {
            broadcastToServer(plugin.getConfigManager().getString("crafting.broadcasts.messages.closing"));
            lastBroadcastTimes.put(timeKey, System.currentTimeMillis());
        }
    }
    
    /**
     * Broadcasts a message to all players
     * @param message The message to broadcast
     */
    public void broadcastToServer(String message) {
        Bukkit.broadcastMessage(message);
    }
}
