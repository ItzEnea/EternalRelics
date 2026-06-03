package dev.eternalrelics.core.persistence;

import dev.eternalrelics.EternalRelicsPlugin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages persistent data storage (JSON-based)
 * Handles item tracking, faction data, and game state
 */
public class PersistenceManager {
    
    private static final Logger logger = LoggerFactory.getLogger(PersistenceManager.class);
    private final EternalRelicsPlugin plugin;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File dataDirectory;
    
    // Data files
    private final File legendaryItemsFile;
    private final File factionDataFile;
    private final File relicDataFile;
    
    // In-memory cache
    private Map<String, String> legendaryItems = new HashMap<>();
    private Map<String, Object> factionData = new HashMap<>();
    private Map<String, Object> relicData = new HashMap<>();
    
    public PersistenceManager(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
        this.dataDirectory = new File(plugin.getDataFolder(), "data");
        
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
        
        this.legendaryItemsFile = new File(dataDirectory, "legendary-items.json");
        this.factionDataFile = new File(dataDirectory, "faction-data.json");
        this.relicDataFile = new File(dataDirectory, "relic-data.json");
    }
    
    /**
     * Initialize persistence system
     */
    public void initialize() {
        try {
            loadAllData();
            logger.info("Persistence system initialized");
        } catch (Exception e) {
            logger.error("Failed to initialize persistence", e);
        }
    }
    
    /**
     * Load all data from disk
     */
    private void loadAllData() {
        loadLegendaryItems();
        loadFactionData();
        loadRelicData();
    }
    
    /**
     * Load legendary items from JSON
     */
    private void loadLegendaryItems() {
        try {
            if (legendaryItemsFile.exists()) {
                FileReader reader = new FileReader(legendaryItemsFile);
                legendaryItems = gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());
                reader.close();
                
                if (legendaryItems == null) {
                    legendaryItems = new HashMap<>();
                }
            }
            logger.info("Loaded {} legendary items", legendaryItems.size());
        } catch (Exception e) {
            logger.error("Failed to load legendary items", e);
            legendaryItems = new HashMap<>();
        }
    }
    
    /**
     * Load faction data from JSON
     */
    private void loadFactionData() {
        try {
            if (factionDataFile.exists()) {
                FileReader reader = new FileReader(factionDataFile);
                factionData = gson.fromJson(reader, new TypeToken<Map<String, Object>>(){}.getType());
                reader.close();
                
                if (factionData == null) {
                    factionData = new HashMap<>();
                }
            }
            logger.info("Loaded faction data");
        } catch (Exception e) {
            logger.error("Failed to load faction data", e);
            factionData = new HashMap<>();
        }
    }
    
    /**
     * Load relic data from JSON
     */
    private void loadRelicData() {
        try {
            if (relicDataFile.exists()) {
                FileReader reader = new FileReader(relicDataFile);
                relicData = gson.fromJson(reader, new TypeToken<Map<String, Object>>(){}.getType());
                reader.close();
                
                if (relicData == null) {
                    relicData = new HashMap<>();
                }
            }
            logger.info("Loaded relic data");
        } catch (Exception e) {
            logger.error("Failed to load relic data", e);
            relicData = new HashMap<>();
        }
    }
    
    /**
     * Get legendary items
     */
    public Map<String, String> loadLegendaryItems() {
        return new HashMap<>(legendaryItems);
    }
    
    /**
     * Save legendary items
     */
    public void saveLegendaryItems(Map<String, String> items) {
        try {
            FileWriter writer = new FileWriter(legendaryItemsFile);
            gson.toJson(items, writer);
            writer.close();
            
            this.legendaryItems = new HashMap<>(items);
        } catch (Exception e) {
            logger.error("Failed to save legendary items", e);
        }
    }
    
    /**
     * Save faction data
     */
    public void saveFactionData(Map<String, Object> data) {
        try {
            FileWriter writer = new FileWriter(factionDataFile);
            gson.toJson(data, writer);
            writer.close();
            
            this.factionData = new HashMap<>(data);
        } catch (Exception e) {
            logger.error("Failed to save faction data", e);
        }
    }
    
    /**
     * Save relic data
     */
    public void saveRelicData(Map<String, Object> data) {
        try {
            FileWriter writer = new FileWriter(relicDataFile);
            gson.toJson(data, writer);
            writer.close();
            
            this.relicData = new HashMap<>(data);
        } catch (Exception e) {
            logger.error("Failed to save relic data", e);
        }
    }
    
    /**
     * Save all data to disk
     */
    public void saveAll() {
        saveLegendaryItems(legendaryItems);
        saveFactionData(factionData);
        saveRelicData(relicData);
    }
}
