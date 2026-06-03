# EternalRelics Plugin - Technical Documentation

## Architecture Overview

### Manager Pattern

The plugin uses a centralized manager pattern for separation of concerns:

```
EternalRelicsPlugin (Main)
├── ConfigManager         # Configuration loading
├── PersistenceManager    # Data storage
├── ItemManager          # Item tracking & creation
├── AltarManager         # Altar management
├── EventManager         # Events & broadcasts
├── FactionManager       # Faction system
├── CooldownManager      # Ability cooldowns
└── RelicManager         # Relic of Convergence
```

## Core Systems

### 1. Item System

**Unique Item Enforcement**
- Each legendary item has a UUID tracked in memory
- Persistence layer saves to `legendary-items.json`
- Creation checks if item already exists
- Returns null if item exists (prevents duplicates)

**Persistent Data Container**
```java
NamespacedKey id = new NamespacedKey(plugin, "legendary_id");
NamespacedKey type = new NamespacedKey(plugin, "legendary_type");
pdc.set(id, PersistentDataType.STRING, uuid.toString());
pdc.set(type, PersistentDataType.STRING, itemType);
```

### 2. Cooldown System

**BossBar UI Implementation**
- Creates smooth progress bar for cooldown countdown
- Updates every second (20 ticks)
- Automatically removes when cooldown expires
- Title displays remaining seconds

**Cooldown Manager Usage**
```java
cooldownManager.startCooldown(player, "ability-id", 10);
if (cooldownManager.isOnCooldown(player, "ability-id")) {
    // Ability blocked
}
long remaining = cooldownManager.getRemainingCooldown(player, "ability-id");
```

### 3. Configuration System

**Type-Safe Config Access**
```java
int startHour = configManager.getInt("crafting.start-hour");
String message = configManager.getString("messages.prefix");
boolean enabled = configManager.getBoolean("particles.enabled");
```

**Config Hierarchy**
- Root categories: crafting, events, armor, weapons, factions, relic, particles
- Per-item config support for cooldowns, damage, cooldown timers
- Fully commented YAML structure

### 4. Faction System

**Faction Enum**
```java
Faction {
    HUMAN("§f", "[HUMAN]"),
    CULT("§c", "[CULT]"),
    VOID("§5", "[VOID]")
}
```

**Role Support**
- Cult Leader (setCultLeader)
- Void Master (setVoidMaster)
- Human King (setHumanKing)
- Regular Members

**Data Persistence**
- Faction membership saved to `faction-data.json`
- Leader UUIDs stored and loaded on startup

### 5. Crafting Window System

**Time Zone Support**
- Configurable in CET or any timezone
- Timed broadcasts at 15:50, 15:55, 16:00, 18:50, 18:55, 19:00
- Broadcast spam prevention (once per minute)

**Implementation**
```java
public boolean isCraftingWindowOpen() {
    LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Paris"));
    int hour = now.getHour();
    return hour >= 16 && hour < 19;
}
```

### 6. Relic of Convergence

**Game Mechanics**
- Only ONE relic can exist at a time
- 30-minute defense timer once placed
- 200 HP boss health pool
- Pickup/drop tracking for ownership
- Victory broadcasts for each faction

**Countdown Updates**
- Called every 1 second via scheduler
- Hologram countdown (future implementation)
- Auto-victory when timer expires

## Performance Optimizations

### Entity Scanning
```java
// Scheduled every 40 ticks (2 seconds)
getServer().getScheduler().runTaskTimer(plugin, () -> {
    // Entity scan operations
}, 0L, 40L);
```

### Async Operations
```java
// Persistence saves async every 5 minutes
getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> {
    persistenceManager.saveAll();
}, 6000L, 6000L);
```

### Particle Reduction
```java
if (!configManager.getBoolean("particles.enabled")) {
    return;  // Particle system disabled
}

double reduction = configManager.getDouble("particles.reduction-factor");
if (Math.random() > reduction) {
    return;  // Skip particle
}
```

### Cooldown Tracking
- In-memory HashMap (no database queries)
- Automatic cleanup on player disconnect
- O(1) lookup time for cooldown checks

## Custom Event System (Future)

Expected custom events:
```java
LegendaryItemCreatedEvent
LegendaryItemDestroyedEvent
AbilityCastEvent
AbilityCooldownEvent
FactionJoinEvent
RelicPickupEvent
```

## Command Hierarchy

```
/altar <action>
  ├── helmet
  ├── chestplate
  ├── leggings
  ├── boots
  ├── open
  ├── close
  └── status

/event <fragment>
  ├── helmet
  ├── chestplate
  ├── leggings
  ├── boots
  └── awakening

/void awaken

/king <player>

/recipes

/relic <action>
  ├── place
  └── status
```

## Listener Implementation Pattern

All listeners follow this pattern:

```java
public class ExampleListener implements Listener {
    private final EternalRelicsPlugin plugin;
    
    public ExampleListener(EternalRelicsPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onEvent(SomeEvent event) {
        // Implementation
    }
}
```

## Data Persistence Format

### legendary-items.json
```json
{
  "sun-crown": "550e8400-e29b-41d4-a716-446655440000",
  "heart-avaritia": "6ba7b810-9dad-11d1-80b4-00c04fd430c8"
}
```

### faction-data.json
```json
{
  "cult-leader": "550e8400-e29b-41d4-a716-446655440001",
  "void-master": "550e8400-e29b-41d4-a716-446655440002",
  "human-king": "550e8400-e29b-41d4-a716-446655440003",
  "members": {
    "HUMAN": ["uuid1", "uuid2"],
    "CULT": ["uuid3", "uuid4"],
    "VOID": ["uuid5", "uuid6"]
  }
}
```

### relic-data.json
```json
{
  "active": true,
  "holder": "550e8400-e29b-41d4-a716-446655440000",
  "location": {
    "world": "world",
    "x": 100.5,
    "y": 64.0,
    "z": 200.5
  },
  "health": 150.0,
  "defense-start": 1717413600000
}
```

## Build & Deployment

### Maven Build
```bash
mvn clean package
```

Output: `target/EternalRelics-1.0.0.jar`

### Server Installation
1. Place JAR in `plugins/` folder
2. Restart server
3. Edit `plugins/EternalRelics/config.yml`
4. `/reload confirm` or restart

## Debugging

**Enable Debug Mode**
```yaml
debug:
  enabled: true
  verbose: true
  log-item-events: true
  log-ability-triggers: true
```

**Check Logs**
```bash
tail -f logs/latest.log | grep EternalRelics
```

## Future Expansion Points

1. **Custom Enchantment System** - Add custom enchants to items
2. **Quest System** - Integration with legendary item quests
3. **Cosmetics** - Particle effects & trail systems
4. **Trading System** - Player-to-player legendary item trading
5. **Leaderboards** - Track legendary item holders
6. **API** - Public API for other plugins to interact
7. **Database Support** - MySQL/MongoDB persistence
8. **GUI System** - Full recipe browser & status screens

## Dependencies

- Paper 1.21.1+
- Adventure API 4.14.0
- GSON 2.10.1
- SLF4J 2.0.9 (Logging)
- Lombok 1.18.30 (Development)

## License

MIT License - See LICENSE file
