package dev.eternalrelics.core.manager;

import dev.eternalrelics.EternalRelicsPlugin;
import dev.eternalrelics.core.item.LegendaryArmor;
import dev.eternalrelics.core.item.LegendaryWeapon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages all legendary items (armor & weapons)
 * Handles item creation, tracking, particles, and uniqueness enforcement
 */
public class ItemManager {
    
    private static final Logger logger = LoggerFactory.getLogger(ItemManager.class);
    private final EternalRelicsPlugin plugin;
    private final NamespacedKey legendaryKeyId;
    private final NamespacedKey legendaryKeyOwner;
    private final NamespacedKey legendaryKeyType;
    
    // In-memory cache of existing legendary items
    private final Map<String, UUID> existingLegendaries = new HashMap<>();
    
    public ItemManager(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
        this.legendaryKeyId = new NamespacedKey(plugin, "legendary_id");
        this.legendaryKeyOwner = new NamespacedKey(plugin, "legendary_owner");
        this.legendaryKeyType = new NamespacedKey(plugin, "legendary_type");
    }
    
    /**
     * Creates a legendary armor piece
     * @param armor The armor type to create
     * @return The ItemStack of the legendary armor, or null if it already exists
     */
    public ItemStack createLegendaryArmor(LegendaryArmor armor) {
        // Check if this armor already exists
        if (existingLegendaries.containsKey(armor.getId())) {
            logger.warn("Attempted to create legendary armor '{}' but it already exists", armor.getId());
            return null;
        }
        
        ItemStack item = armor.buildItem();
        UUID itemId = UUID.randomUUID();
        
        // Add persistent data
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        pdc.set(legendaryKeyId, PersistentDataType.STRING, itemId.toString());
        pdc.set(legendaryKeyType, PersistentDataType.STRING, armor.getId());
        
        item.getItemMeta().getPersistentDataContainer().set(legendaryKeyId, PersistentDataType.STRING, itemId.toString());
        
        // Track in memory
        existingLegendaries.put(armor.getId(), itemId);
        
        logger.info("Created legendary armor: {} (ID: {})", armor.getId(), itemId);
        return item;
    }
    
    /**
     * Creates a legendary weapon
     * @param weapon The weapon type to create
     * @return The ItemStack of the legendary weapon, or null if it already exists
     */
    public ItemStack createLegendaryWeapon(LegendaryWeapon weapon) {
        // Check if this weapon already exists
        if (existingLegendaries.containsKey(weapon.getId())) {
            logger.warn("Attempted to create legendary weapon '{}' but it already exists", weapon.getId());
            return null;
        }
        
        ItemStack item = weapon.buildItem();
        UUID itemId = UUID.randomUUID();
        
        // Add persistent data
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        pdc.set(legendaryKeyId, PersistentDataType.STRING, itemId.toString());
        pdc.set(legendaryKeyType, PersistentDataType.STRING, weapon.getId());
        
        // Track in memory
        existingLegendaries.put(weapon.getId(), itemId);
        
        logger.info("Created legendary weapon: {} (ID: {})", weapon.getId(), itemId);
        return item;
    }
    
    /**
     * Checks if a legendary item already exists
     * @param legendaryId The legendary item ID
     * @return True if the item exists on the server
     */
    public boolean exists(String legendaryId) {
        return existingLegendaries.containsKey(legendaryId);
    }
    
    /**
     * Gets the UUID of a legendary item if it exists
     * @param legendaryId The legendary item ID
     * @return The UUID of the item, or null if it doesn't exist
     */
    public UUID getLegendaryUUID(String legendaryId) {
        return existingLegendaries.get(legendaryId);
    }
    
    /**
     * Marks a legendary item as no longer existing (destroyed)
     * @param legendaryId The legendary item ID
     */
    public void removeLegendary(String legendaryId) {
        existingLegendaries.remove(legendaryId);
        logger.info("Removed legendary item: {}", legendaryId);
    }
    
    /**
     * Updates particle effects for held legendary items
     * Called every 2 ticks for performance
     */
    public void updateHeldItemParticles() {
        if (!plugin.getConfigManager().getBoolean("particles.enabled")) {
            return;
        }
        
        for (Player player : Bukkit.getOnlinePlayers()) {
            ItemStack item = player.getInventory().getItemInMainHand();
            
            if (item == null || item.getItemMeta() == null) {
                continue;
            }
            
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            
            // Check if item is legendary
            if (pdc.has(legendaryKeyId, PersistentDataType.STRING)) {
                String itemType = pdc.get(legendaryKeyType, PersistentDataType.STRING);
                // TODO: Play particle effects based on item type
            }
        }
    }
    
    /**
     * Load all existing legendary items from persistence
     */
    public void loadLegendaryItems() {
        existingLegendaries.clear();
        Map<String, String> saved = plugin.getPersistenceManager().loadLegendaryItems();
        
        for (Map.Entry<String, String> entry : saved.entrySet()) {
            existingLegendaries.put(entry.getKey(), UUID.fromString(entry.getValue()));
        }
        
        logger.info("Loaded {} legendary items from persistence", existingLegendaries.size());
    }
    
    /**
     * Save all legendary items to persistence
     */
    public void saveLegendaryItems() {
        Map<String, String> toSave = new HashMap<>();
        
        for (Map.Entry<String, UUID> entry : existingLegendaries.entrySet()) {
            toSave.put(entry.getKey(), entry.getValue().toString());
        }
        
        plugin.getPersistenceManager().saveLegendaryItems(toSave);
    }
}
