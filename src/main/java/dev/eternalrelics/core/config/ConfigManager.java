package dev.eternalrelics.core.config;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages configuration loading and caching
 * Provides type-safe access to config values
 */
public class ConfigManager {
    
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private final EternalRelicsPlugin plugin;
    private YamlConfiguration config;
    
    public ConfigManager(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Loads the configuration file
     */
    public void loadConfig() {
        try {
            File configFile = new File(plugin.getDataFolder(), "config.yml");
            
            // Create default config if it doesn't exist
            if (!configFile.exists()) {
                plugin.saveResource("config.yml", false);
            }
            
            config = YamlConfiguration.loadConfiguration(configFile);
            logger.info("Configuration loaded successfully");
        } catch (Exception e) {
            logger.error("Failed to load configuration", e);
        }
    }
    
    /**
     * Reloads the configuration from disk
     */
    public void reloadConfig() {
        loadConfig();
    }
    
    /**
     * Gets a string value from config
     * @param path The config path
     * @return The value, or empty string if not found
     */
    public String getString(String path) {
        return config.getString(path, "");
    }
    
    /**
     * Gets a string value with a default
     * @param path The config path
     * @param def The default value
     * @return The value, or default if not found
     */
    public String getString(String path, String def) {
        return config.getString(path, def);
    }
    
    /**
     * Gets an integer value from config
     * @param path The config path
     * @return The value, or 0 if not found
     */
    public int getInt(String path) {
        return config.getInt(path, 0);
    }
    
    /**
     * Gets a long value from config
     * @param path The config path
     * @return The value, or 0 if not found
     */
    public long getLong(String path) {
        return config.getLong(path, 0L);
    }
    
    /**
     * Gets a double value from config
     * @param path The config path
     * @return The value, or 0.0 if not found
     */
    public double getDouble(String path) {
        return config.getDouble(path, 0.0);
    }
    
    /**
     * Gets a boolean value from config
     * @param path The config path
     * @return The value, or false if not found
     */
    public boolean getBoolean(String path) {
        return config.getBoolean(path, false);
    }
    
    /**
     * Gets a list of strings from config
     * @param path The config path
     * @return The list, or empty list if not found
     */
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }
}
