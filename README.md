# EternalRelics - Legendary Items Plugin for Paper 1.21.11

**High-performance legendary items and faction conflict system for Minecraft servers**

## Features

- 🗡️ **15 Unique Legendary Weapons** with custom abilities and cooldowns
- 🛡️ **4 Legendary Armor Pieces** with passive abilities
- ⛪ **Altar System** for crafting legendary items
- 📍 **Fragment Event System** with player tracking
- 👥 **Faction System** (Humans, Cult, Void)
- 💎 **Relic of Convergence** endgame mechanic
- 🎨 **Custom Resource Pack Integration** with emissive textures
- 📊 **Boss Bar Cooldown UI** for smooth ability feedback
- 💾 **Persistent Data Storage** with JSON
- ⚡ **Fully Optimized** for 100+ player servers

## Requirements

- Minecraft Paper 1.21.1+
- Java 21+
- Gradle 8.0+

## Building

```bash
gradlew shadowJar
```

Output: `build/libs/EternalRelics-1.0.0-all.jar`

## Installation

1. Place the JAR in your server's `plugins/` folder
2. Restart the server
3. Configure `plugins/EternalRelics/config.yml`
4. Install the resource pack (when available)

## Commands

### Admin Commands
- `/altar <helmet|chestplate|leggings|boots|weapon|open|close|status>`
- `/event <helmet|chestplate|leggings|boots|awakening>`
- `/void awaken`
- `/king <player>`
- `/relic <place|status>`

### Player Commands
- `/recipes` - Open legendary recipes GUI

## Architecture

```
EternalRelics/
├── core/
│   ├── manager/          # Business logic managers
│   ├── item/            # Legendary item definitions
│   ├── ability/         # Ability implementations
│   ├── config/          # Configuration management
│   └── persistence/     # Data storage layer
├── commands/            # Command implementations
├── listeners/           # Event handlers
└── gui/                 # GUI components
```

## Performance

- Optimized for 100+ concurrent players
- Async-safe operations
- Smart entity scanning
- Configurable particle limits
- Efficient cooldown tracking

## License

MIT License - see LICENSE file for details
