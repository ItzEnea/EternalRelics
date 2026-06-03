package dev.eternalrelics.core.manager;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages ability cooldowns with BossBar UI
 * Handles smooth cooldown countdown and removal
 */
public class CooldownManager {
    
    private static final Logger logger = LoggerFactory.getLogger(CooldownManager.class);
    private final EternalRelicsPlugin plugin;
    
    // Cooldown tracking: UUID -> abilityId -> endTime (milliseconds)
    private final Map<UUID, Map<String, Long>> playerCooldowns = new HashMap<>();
    
    // BossBar UI tracking: UUID -> abilityId -> BossBar
    private final Map<UUID, Map<String, BossBar>> playerBossBars = new HashMap<>();
    
    public CooldownManager(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Starts a cooldown for a player ability
     * @param player The player
     * @param abilityId The ability identifier
     * @param durationSeconds The cooldown duration in seconds
     */
    public void startCooldown(Player player, String abilityId, long durationSeconds) {
        UUID playerId = player.getUniqueId();
        long endTime = System.currentTimeMillis() + (durationSeconds * 1000);
        
        // Store cooldown
        playerCooldowns.computeIfAbsent(playerId, k -> new HashMap<>())
            .put(abilityId, endTime);
        
        // Create BossBar
        BossBar bar = Bukkit.createBossBar(
            "§f" + abilityId.replace("-", " ").toUpperCase() + " - " + durationSeconds + "s",
            BarColor.BLUE,
            BarStyle.SOLID
        );
        bar.addPlayer(player);
        bar.setProgress(1.0);
        
        playerBossBars.computeIfAbsent(playerId, k -> new HashMap<>())
            .put(abilityId, bar);
        
        // Schedule update task
        scheduleCooldownUpdate(playerId, abilityId, durationSeconds);
    }
    
    /**
     * Checks if a player has an active cooldown
     * @param player The player
     * @param abilityId The ability identifier
     * @return True if on cooldown
     */
    public boolean isOnCooldown(Player player, String abilityId) {
        UUID playerId = player.getUniqueId();
        
        if (!playerCooldowns.containsKey(playerId)) {
            return false;
        }
        
        Long endTime = playerCooldowns.get(playerId).get(abilityId);
        if (endTime == null) {
            return false;
        }
        
        long now = System.currentTimeMillis();
        if (now >= endTime) {
            // Cooldown expired
            removeCooldown(player, abilityId);
            return false;
        }
        
        return true;
    }
    
    /**
     * Gets remaining cooldown time in seconds
     * @param player The player
     * @param abilityId The ability identifier
     * @return Remaining seconds, or 0 if no cooldown
     */
    public long getRemainingCooldown(Player player, String abilityId) {
        UUID playerId = player.getUniqueId();
        
        if (!playerCooldowns.containsKey(playerId)) {
            return 0;
        }
        
        Long endTime = playerCooldowns.get(playerId).get(abilityId);
        if (endTime == null) {
            return 0;
        }
        
        long remaining = (endTime - System.currentTimeMillis()) / 1000;
        return Math.max(0, remaining);
    }
    
    /**
     * Removes a cooldown
     * @param player The player
     * @param abilityId The ability identifier
     */
    public void removeCooldown(Player player, String abilityId) {
        UUID playerId = player.getUniqueId();
        
        if (playerCooldowns.containsKey(playerId)) {
            playerCooldowns.get(playerId).remove(abilityId);
        }
        
        // Remove BossBar
        if (playerBossBars.containsKey(playerId)) {
            BossBar bar = playerBossBars.get(playerId).remove(abilityId);
            if (bar != null) {
                bar.removeAll();
            }
        }
    }
    
    /**
     * Schedule cooldown countdown updates
     */
    private void scheduleCooldownUpdate(UUID playerId, String abilityId, long durationSeconds) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            Player player = Bukkit.getPlayer(playerId);
            if (player == null || !isOnCooldown(player, abilityId)) {
                return; // Task will be cancelled
            }
            
            long remaining = getRemainingCooldown(player, abilityId);
            BossBar bar = playerBossBars.get(playerId).get(abilityId);
            
            if (bar != null) {
                double progress = (double) remaining / durationSeconds;
                bar.setProgress(Math.max(0, progress));
                bar.setTitle("§f" + abilityId.replace("-", " ").toUpperCase() + " - " + remaining + "s");
            }
            
            if (remaining <= 0) {
                removeCooldown(player, abilityId);
                // Cancel task
                throw new RuntimeException("Cooldown task cancel");
            }
        }, 20L, 20L); // Update every second
    }
    
    /**
     * Clean up cooldowns for disconnected players
     */
    public void cleanup() {
        playerCooldowns.clear();
        
        // Remove all boss bars
        for (Map<String, BossBar> bars : playerBossBars.values()) {
            for (BossBar bar : bars.values()) {
                bar.removeAll();
            }
        }
        playerBossBars.clear();
    }
}
