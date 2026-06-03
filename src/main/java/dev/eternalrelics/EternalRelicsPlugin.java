package dev.eternalrelics;

import dev.eternalrelics.core.manager.ItemManager;
import dev.eternalrelics.core.manager.AltarManager;
import dev.eternalrelics.core.manager.EventManager;
import dev.eternalrelics.core.manager.FactionManager;
import dev.eternalrelics.core.manager.CooldownManager;
import dev.eternalrelics.core.manager.RelicManager;
import dev.eternalrelics.core.persistence.PersistenceManager;
import dev.eternalrelics.core.config.ConfigManager;
import dev.eternalrelics.commands.AltarCommand;
import dev.eternalrelics.commands.EventCommand;
import dev.eternalrelics.commands.VoidCommand;
import dev.eternalrelics.commands.KingCommand;
import dev.eternalrelics.commands.RecipesCommand;
import dev.eternalrelics.commands.RelicCommand;
import dev.eternalrelics.listeners.ItemEventListener;
import dev.eternalrelics.listeners.AltarInteractionListener;
import dev.eternalrelics.listeners.ArmorAbilityListener;
import dev.eternalrelics.listeners.WeaponAbilityListener;
import dev.eternalrelics.listeners.FragmentEventListener;
import dev.eternalrelics.listeners.FactionListener;
import dev.eternalrelics.listeners.RelicListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EternalRelics - A legendary items and faction conflict plugin for Paper 1.21.11
 * 
 * This plugin implements:
 * - Legendary armor system (4 unique pieces with passives)
 * - Legendary weapon system (15 unique weapons with abilities)
 * - Altar crafting system with timed windows
 * - Fragment event system with player tracking
 * - Faction system (Humans, Cult, Void)
 * - Relic of Convergence gameplay mechanic
 * - GUI recipe system
 * - Particle, VFX, and SFX effects
 * - Persistent data storage
 * 
 * Architecture: Manager/Service pattern with separation of concerns
 * Performance: Optimized for 100+ player servers with async-safe operations
 */
public class EternalRelicsPlugin extends JavaPlugin {
    
    private static final Logger logger = LoggerFactory.getLogger(EternalRelicsPlugin.class);
    private static EternalRelicsPlugin instance;
    
    // Core Managers
    private ConfigManager configManager;
    private PersistenceManager persistenceManager;
    private ItemManager itemManager;
    private AltarManager altarManager;
    private EventManager eventManager;
    private FactionManager factionManager;
    private CooldownManager cooldownManager;
    private RelicManager relicManager;
    
    @Override
    public void onLoad() {
        instance = this;
        logger.info("EternalRelics plugin loading...");
        
        // Create data folders
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
    }
    
    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        
        try {
            // Initialize configuration system
            logger.info("Loading configuration...");
            this.configManager = new ConfigManager(this);
            configManager.loadConfig();
            
            // Initialize persistence layer
            logger.info("Initializing persistence layer...");
            this.persistenceManager = new PersistenceManager(this);
            persistenceManager.initialize();
            
            // Initialize managers
            logger.info("Initializing core managers...");
            this.itemManager = new ItemManager(this);
            this.altarManager = new AltarManager(this);
            this.eventManager = new EventManager(this);
            this.factionManager = new FactionManager(this);
            this.cooldownManager = new CooldownManager(this);
            this.relicManager = new RelicManager(this);
            
            // Register commands
            logger.info("Registering commands...");
            registerCommands();
            
            // Register event listeners
            logger.info("Registering event listeners...");
            registerListeners();
            
            // Start scheduled tasks
            logger.info("Starting scheduled tasks...");
            startScheduledTasks();
            
            long duration = System.currentTimeMillis() - startTime;
            logger.info("EternalRelics enabled successfully! (took {}ms)", duration);
            getLogger().info("§6========================================");
            getLogger().info("§6  EternalRelics v1.0.0");
            getLogger().info("§6  Legendary Items & Faction Conflict");
            getLogger().info("§6========================================");
            
        } catch (Exception e) {
            logger.error("Failed to enable EternalRelics plugin", e);
            getLogger().severe("§cEternalRelics failed to start! Check console for errors.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
    
    @Override
    public void onDisable() {
        logger.info("EternalRelics plugin disabling...");
        
        try {
            // Save all persistent data
            if (persistenceManager != null) {
                persistenceManager.saveAll();
            }
            
            // Clean up resources
            if (cooldownManager != null) {
                cooldownManager.cleanup();
            }
            
            logger.info("EternalRelics disabled successfully!");
        } catch (Exception e) {
            logger.error("Error during plugin shutdown", e);
        }
    }
    
    /**
     * Register all plugin commands
     */
    private void registerCommands() {
        getCommand("altar").setExecutor(new AltarCommand(this));
        getCommand("event").setExecutor(new EventCommand(this));
        getCommand("void").setExecutor(new VoidCommand(this));
        getCommand("king").setExecutor(new KingCommand(this));
        getCommand("recipes").setExecutor(new RecipesCommand(this));
        getCommand("relic").setExecutor(new RelicCommand(this));
    }
    
    /**
     * Register all event listeners
     */
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ItemEventListener(this), this);
        getServer().getPluginManager().registerEvents(new AltarInteractionListener(this), this);
        getServer().getPluginManager().registerEvents(new ArmorAbilityListener(this), this);
        getServer().getPluginManager().registerEvents(new WeaponAbilityListener(this), this);
        getServer().getPluginManager().registerEvents(new FragmentEventListener(this), this);
        getServer().getPluginManager().registerEvents(new FactionListener(this), this);
        getServer().getPluginManager().registerEvents(new RelicListener(this), this);
    }
    
    /**
     * Start all scheduled tasks
     */
    private void startScheduledTasks() {
        // Crafting window broadcasts (every minute)
        getServer().getScheduler().runTaskTimer(this, () -> {
            eventManager.checkCraftingWindowBroadcasts();
        }, 0L, 20L); // Every 1 second check
        
        // Fragment holder tracking (every 50 seconds)
        getServer().getScheduler().runTaskTimer(this, () -> {
            eventManager.broadcastFragmentHolders();
        }, 0L, 1000L); // Every 50 seconds
        
        // Relic countdown updates
        getServer().getScheduler().runTaskTimer(this, () -> {
            relicManager.updateRelicCountdown();
        }, 0L, 20L); // Every 1 second
        
        // Particle effects for held legendary items
        getServer().getScheduler().runTaskTimer(this, () -> {
            itemManager.updateHeldItemParticles();
        }, 0L, 2L); // Every 0.1 second
        
        // Auto-save persistence data every 5 minutes
        getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            persistenceManager.saveAll();
        }, 6000L, 6000L);
    }
    
    // ==================== GETTERS ====================
    
    public static EternalRelicsPlugin getInstance() {
        return instance;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }
    
    public ItemManager getItemManager() {
        return itemManager;
    }
    
    public AltarManager getAltarManager() {
        return altarManager;
    }
    
    public EventManager getEventManager() {
        return eventManager;
    }
    
    public FactionManager getFactionManager() {
        return factionManager;
    }
    
    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }
    
    public RelicManager getRelicManager() {
        return relicManager;
    }
}
