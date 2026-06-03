package dev.eternalrelics.core.manager;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages altar placements and crafting
 * Handles altar creation, status, and crafting window checks
 */
public class AltarManager {
    
    private static final Logger logger = LoggerFactory.getLogger(AltarManager.class);
    private final EternalRelicsPlugin plugin;
    
    // Altars by location
    private final Map<Location, String> altars = new HashMap<>();
    
    // Altar state (open/closed)
    private boolean altarsOpen = false;
    
    public AltarManager(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Places an altar at a location
     * @param location The location to place the altar
     * @param type The altar type (helmet, chestplate, etc)
     * @return True if successfully placed
     */
    public boolean placeAltar(Location location, String type) {
        if (altars.containsKey(location)) {
            logger.warn("Altar already exists at {}", location);
            return false;
        }
        
        altars.put(location, type);
        
        // Set block to beacon (altar visual)
        Block block = location.getBlock();
        block.setType(Material.BEACON);
        
        logger.info("Placed {} altar at {}", type, location);
        return true;
    }
    
    /**
     * Removes an altar at a location
     * @param location The location to remove
     * @return True if successfully removed
     */
    public boolean removeAltar(Location location) {
        if (!altars.containsKey(location)) {
            return false;
        }
        
        altars.remove(location);
        location.getBlock().setType(Material.AIR);
        
        logger.info("Removed altar at {}", location);
        return true;
    }
    
    /**
     * Opens all altars for crafting
     */
    public void openAltars() {
        altarsOpen = true;
        logger.info("Altars opened");
    }
    
    /**
     * Closes all altars
     */
    public void closeAltars() {
        altarsOpen = false;
        logger.info("Altars closed");
    }
    
    /**
     * Checks if altars are open
     * @return True if open
     */
    public boolean isOpen() {
        return altarsOpen;
    }
    
    /**
     * Gets the altar type at a location
     * @param location The location
     * @return The altar type, or null if no altar exists
     */
    public String getAltarType(Location location) {
        return altars.get(location);
    }
    
    /**
     * Checks if a location has an altar
     * @param location The location
     * @return True if an altar exists at this location
     */
    public boolean isAltar(Location location) {
        return altars.containsKey(location);
    }
    
    /**
     * Get total number of altars
     * @return The count
     */
    public int getAltarCount() {
        return altars.size();
    }
}
