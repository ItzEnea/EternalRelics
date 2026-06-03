package dev.eternalrelics.core.manager;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages the Relic of Convergence
 * Handles relic placement, defense timer, and victory conditions
 */
public class RelicManager {
    
    private static final Logger logger = LoggerFactory.getLogger(RelicManager.class);
    private final EternalRelicsPlugin plugin;
    
    // Relic state
    private UUID currentHolder = null;
    private Location relicLocation = null;
    private long defenseStartTime = 0;
    private double relicHealth = 200.0;
    private boolean relicActive = false;
    
    public RelicManager(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Places the relic at a location
     * @param location The location to place
     * @return True if successfully placed
     */
    public boolean placeRelic(Location location) {
        if (relicActive) {
            logger.warn("Relic already active");
            return false;
        }
        
        relicLocation = location;
        relicHealth = 200.0;
        defenseStartTime = System.currentTimeMillis();
        relicActive = true;
        
        logger.info("Relic of Convergence placed at {}", location);
        return true;
    }
    
    /**
     * Picks up the relic
     * @param player The player picking it up
     * @return True if successfully picked up
     */
    public boolean pickupRelic(Player player) {
        if (!relicActive) {
            return false;
        }
        
        currentHolder = player.getUniqueId();
        logger.info("Player {} picked up the relic", player.getName());
        return true;
    }
    
    /**
     * Damages the relic
     * @param damage Amount of damage
     */
    public void damageRelic(double damage) {
        if (!relicActive) {
            return;
        }
        
        relicHealth -= damage;
        
        if (relicHealth <= 0) {
            relicHealth = 0;
            destroyRelic();
        }
    }
    
    /**
     * Destroys the relic
     */
    public void destroyRelic() {
        if (!relicActive) {
            return;
        }
        
        relicActive = false;
        currentHolder = null;
        relicLocation = null;
        relicHealth = 0;
        
        logger.info("Relic of Convergence destroyed");
    }
    
    /**
     * Updates relic countdown (called every second)
     */
    public void updateRelicCountdown() {
        if (!relicActive || defenseStartTime == 0) {
            return;
        }
        
        long elapsed = System.currentTimeMillis() - defenseStartTime;
        long defenseTimer = plugin.getConfigManager().getLong("relic.defense-timer") * 1000;
        
        if (elapsed >= defenseTimer) {
            // Defense timer expired - victory condition
            Player holder = currentHolder != null ? plugin.getServer().getPlayer(currentHolder) : null;
            if (holder != null) {
                announceVictory(holder);
            }
            destroyRelic();
        }
    }
    
    /**
     * Announces victory to the server
     * @param winner The winning player
     */
    private void announceVictory(Player winner) {
        FactionManager.Faction faction = plugin.getFactionManager().getFaction(winner);
        if (faction == null) {
            return;
        }
        
        String message = switch (faction) {
            case HUMAN -> plugin.getConfigManager().getString("relic.victory-messages.HUMAN");
            case CULT -> plugin.getConfigManager().getString("relic.victory-messages.CULT");
            case VOID -> plugin.getConfigManager().getString("relic.victory-messages.VOID");
        };
        
        plugin.getEventManager().broadcastToServer(message);
    }
    
    // ==================== GETTERS ====================
    
    public UUID getCurrentHolder() {
        return currentHolder;
    }
    
    public Location getRelicLocation() {
        return relicLocation;
    }
    
    public double getRelicHealth() {
        return relicHealth;
    }
    
    public boolean isRelicActive() {
        return relicActive;
    }
    
    public long getDefenseTimeRemaining() {
        if (!relicActive) return 0;
        long elapsed = System.currentTimeMillis() - defenseStartTime;
        long total = plugin.getConfigManager().getLong("relic.defense-timer") * 1000;
        return Math.max(0, (total - elapsed) / 1000);
    }
}
