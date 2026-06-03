package dev.eternalrelics.core.manager;

import dev.eternalrelics.EternalRelicsPlugin;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages faction system (Humans, Cult, Void)
 * Tracks faction membership and applies faction passives
 */
public class FactionManager {
    
    private static final Logger logger = LoggerFactory.getLogger(FactionManager.class);
    private final EternalRelicsPlugin plugin;
    
    public enum Faction {
        HUMAN("§f", "[HUMAN]"),
        CULT("§c", "[CULT]"),
        VOID("§5", "[VOID]");
        
        private final String colorCode;
        private final String prefix;
        
        Faction(String colorCode, String prefix) {
            this.colorCode = colorCode;
            this.prefix = prefix;
        }
        
        public String getColorCode() {
            return colorCode;
        }
        
        public String getPrefix() {
            return prefix;
        }
    }
    
    // Player faction membership: UUID -> Faction
    private final Map<UUID, Faction> playerFactions = new HashMap<>();
    
    // Faction members: Faction -> Set of UUIDs
    private final Map<Faction, Set<UUID>> factionMembers = new HashMap<>();
    
    // Faction leaders
    private UUID cultLeader = null;
    private UUID voidMaster = null;
    private UUID humanKing = null;
    
    public FactionManager(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
        
        // Initialize faction member sets
        for (Faction faction : Faction.values()) {
            factionMembers.put(faction, new HashSet<>());
        }
    }
    
    /**
     * Sets a player's faction
     * @param player The player
     * @param faction The faction to join
     */
    public void setFaction(Player player, Faction faction) {
        UUID playerId = player.getUniqueId();
        
        // Remove from old faction if applicable
        Faction oldFaction = playerFactions.get(playerId);
        if (oldFaction != null) {
            factionMembers.get(oldFaction).remove(playerId);
        }
        
        // Add to new faction
        playerFactions.put(playerId, faction);
        factionMembers.get(faction).add(playerId);
        
        logger.info("Player {} joined faction {}", player.getName(), faction);
    }
    
    /**
     * Gets a player's faction
     * @param player The player
     * @return The player's faction, or null if not assigned
     */
    public Faction getFaction(Player player) {
        return playerFactions.get(player.getUniqueId());
    }
    
    /**
     * Gets all members of a faction
     * @param faction The faction
     * @return Set of UUIDs of members
     */
    public Set<UUID> getFactionMembers(Faction faction) {
        return new HashSet<>(factionMembers.get(faction));
    }
    
    /**
     * Sets the cult leader
     * @param player The player to make leader
     */
    public void setCultLeader(Player player) {
        this.cultLeader = player.getUniqueId();
        setFaction(player, Faction.CULT);
        logger.info("Cult leader set to {}", player.getName());
    }
    
    /**
     * Sets the void master
     * @param player The player to make master
     */
    public void setVoidMaster(Player player) {
        this.voidMaster = player.getUniqueId();
        setFaction(player, Faction.VOID);
        logger.info("Void master set to {}", player.getName());
    }
    
    /**
     * Sets the human king
     * @param player The player to make king
     */
    public void setHumanKing(Player player) {
        this.humanKing = player.getUniqueId();
        setFaction(player, Faction.HUMAN);
        logger.info("Human king set to {}", player.getName());
    }
    
    /**
     * Gets the cult leader
     * @return The cult leader's UUID, or null
     */
    public UUID getCultLeader() {
        return cultLeader;
    }
    
    /**
     * Gets the void master
     * @return The void master's UUID, or null
     */
    public UUID getVoidMaster() {
        return voidMaster;
    }
    
    /**
     * Gets the human king
     * @return The human king's UUID, or null
     */
    public UUID getHumanKing() {
        return humanKing;
    }
}
