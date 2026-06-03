# EternalRelics Resource Pack

**Complete dark fantasy legendary item texture pack for Paper 1.21.11**

## Structure

```
EternalRelics-ResourcePack/
├── assets/minecraft/
│   ├── textures/
│   │   ├── item/
│   │   │   ├── armor/           # Armor textures
│   │   │   ├── weapons/         # Weapon textures
│   │   │   ├── fragments/       # Fragment textures
│   │   │   └── relic/           # Relic textures
│   │   ├── gui/
│   │   │   ├── altar/           # Altar GUI
│   │   │   ├── recipes/         # Recipe GUI
│   │   │   └── faction/         # Faction overlays
│   │   └── effect/
│   │       └── particles/       # Custom particle textures
│   ├── models/item/             # Item model overrides
│   ├── sounds/
│   │   ├── ability/             # Ability sounds
│   │   ├── craft/               # Crafting sounds
│   │   └── ambient/             # Ambient sounds
│   └── font/
│       └── default.json         # Custom font definitions
├── pack.mcmeta                  # Pack metadata
└── pack.png                      # Pack icon (64x64)
```

## Pack Metadata

```json
{
  "pack": {
    "pack_format": 48,
    "description": "§6§lEternalRelics§r\n§7Legendary Items & Factions"
  }
}
```

## Item Textures

### Armor Pieces (1001-1004)

**Sun Crown (Helmet) - 1001**
- Base: Golden helmet silhouette
- Color: Bright gold (#FFD700)
- Emissive: Solar glow on crown peaks
- Animated: 4-frame rotation animation
- Theme: Sun rays, celestial marks

**Heart of Avaritia (Chestplate) - 1002**
- Base: Golden breastplate with heart symbol
- Color: Deep red (#CC0000) with gold accents
- Emissive: Beating heart glow in center
- Animated: 8-frame pulse animation
- Theme: Ornate heart, corruption wisps

**Golden Time Steppers (Leggings) - 1003**
- Base: Golden leg armor with tempo marks
- Color: Gold (#FFD700) with silver highlights
- Emissive: Clock face patterns on thighs
- Animated: 6-frame hour hand rotation
- Theme: Clock mechanisms, temporal runes

**Footsteps of Midas (Boots) - 1004**
- Base: Golden boots with platform soles
- Color: Rich gold (#DAA520) with bronze trim
- Emissive: Ground impact glow pattern
- Animated: Footprint trail effect (4 frames)
- Theme: Gold inlays, power runes

### Legendary Weapons (2001-2015)

**Frostwind Bow - 2001**
- Base: Ornate bow with ice crystals
- Color: Cyan (#00FFFF) with white accents
- Emissive: Icy glow along bow limbs
- Theme: Icicle tips, frozen wind swirls
- Size: Slightly larger than vanilla bow

**Voidguard Shield - 2002**
- Base: Round shield with void symbols
- Color: Deep purple (#660099) with black center
- Emissive: Purple void vortex in center
- Animated: 6-frame void swirl animation
- Theme: Eldritch symbols, corruption marks

**Ironhowl - 2003**
- Base: One-handed axe/hammer hybrid
- Color: Dark gray (#333333) with iron shine
- Emissive: Impact lines that glow
- Animated: 4-frame impact pulse
- Theme: Rough iron, warrior marks

**Bonecracker - 2004**
- Base: Large two-handed axe made of bone
- Color: Bone white (#F5F5DC) with dark tips
- Emissive: Ethereal glow on cutting edge
- Animated: 6-frame skeletal energy pulse
- Theme: Skeleton motifs, death runes

**Abyssal Sword - 2005**
- Base: Long blade with void corruption
- Color: Black (#1A1A1A) with purple edges
- Emissive: Purple void wisps along blade
- Animated: 8-frame void corruption spread
- Theme: Tentacle patterns, abyss corruption

**Phantom Edge - 2006**
- Base: Ethereal-looking sword
- Color: Light purple (#E6E6FA) with white trails
- Emissive: Ghost trails behind blade
- Animated: 10-frame phantom streak
- Theme: Ghost trails, spectral marks

**Crystal Edge - 2007**
- Base: Sword made of crystal shards
- Color: Light purple (#DA70D6) with crystal facets
- Emissive: Crystal shine reflections
- Animated: 4-frame light reflection
- Theme: Crystal geometry, light refraction

**Mooncleaver - 2008**
- Base: Curved blade with lunar markings
- Color: Silver (#C0C0C0) with white glow
- Emissive: Crescent moon glow on blade
- Animated: 12-frame moon phase cycle
- Theme: Moon phases, celestial runes

**Nightfang - 2009**
- Base: Dagger-like sword with shadow effect
- Color: Black (#000000) with dark purple edges
- Emissive: Shadow trailing effect
- Animated: 8-frame shadow spread
- Theme: Shadow wisps, darkness corruption

**Ghostthorn - 2010**
- Base: Spiky ethereal sword
- Color: Gray (#808080) with cyan edges
- Emissive: Phase effect glow
- Animated: 6-frame phasing shimmer
- Theme: Phase marks, spirit thorns

**Titan's Axe - 2011**
- Base: Massive two-headed war axe
- Color: Dark red (#8B0000) with black trim
- Emissive: Deep red impact glow
- Animated: 4-frame crushing wave
- Theme: Power runes, war symbols

**Oblivion Cleaver - 2012**
- Base: Huge cleaver engulfed in void
- Color: Black (#0A0A0A) with cyan vortex
- Emissive: Rotating void vortex center
- Animated: 12-frame void spiral rotation
- Theme: Black hole center, void consumption

**Thundermaul - 2013**
- Base: Hammer with lightning channels
- Color: Yellow (#FFFF00) on dark gray base
- Emissive: Lightning streaks along hammer
- Animated: 6-frame lightning arc
- Theme: Lightning bolts, storm marks

**Sunreaver - 2014**
- Base: Golden blade with solar rays
- Color: Gold (#FFD700) with white highlights
- Emissive: Solar flare rays extending from blade
- Animated: 8-frame solar pulse
- Theme: Sun rays, solar flares

**Void Harvest - 2015**
- Base: Scythe-like weapon with void corruption
- Color: Black (#1A1A1A) with purple void
- Emissive: Void seeds pulsing
- Animated: 8-frame void seed growth
- Theme: Void seeds, corruption spread

### Fragments (9501-9504)

**Fragment of the Sun**
- Color: Golden yellow (#FFD700)
- Shape: Sun burst with 8 points
- Emissive: Bright golden glow
- Animated: 4-frame spin rotation

**Fragment of the Heart**
- Color: Deep red (#CC0000)
- Shape: Ornate heart shape
- Emissive: Pulsing red glow
- Animated: 6-frame heartbeat pulse

**Fragment of Time**
- Color: Silver (#C0C0C0)
- Shape: Clock face/gear hybrid
- Emissive: Silvery glow
- Animated: 8-frame clock hand rotation

**Fragment of Gold**
- Color: Bright gold (#FFD700)
- Shape: Coin/medallion
- Emissive: Golden shine
- Animated: 4-frame coin flip

### Relic of Convergence (9001)

- Base: Ornate pedestal with floating orb
- Color: Multi-color gradient (gold to purple)
- Size: 2x scale (double size)
- Emissive: Rotating multi-color aura
- Animated: 20-frame rotation + 10-frame vertical bounce
- Theme: Convergence of all factions

## Model JSON Examples

### Armor Item Model (sun-crown.json)

```json
{
  "parent": "item/handheld",
  "textures": {
    "layer0": "item/armor/sun_crown",
    "layer1": "item/armor/sun_crown_emissive"
  },
  "display": {
    "thirdperson_righthand": {
      "rotation": [75, 45, 0],
      "translation": [0, 2.5, 0],
      "scale": [0.85, 0.85, 0.85]
    },
    "firstperson_righthand": {
      "rotation": [0, 45, 0],
      "scale": [0.68, 0.68, 0.68]
    }
  }
}
```

### Weapon Item Model (frostwind-bow.json)

```json
{
  "parent": "item/bow",
  "textures": {
    "layer0": "item/weapons/frostwind_bow",
    "layer1": "item/weapons/frostwind_bow_emissive"
  },
  "overrides": [
    {
      "predicate": {"pulling": 0.65},
      "model": "item/weapons/frostwind_bow_pulling_0"
    },
    {
      "predicate": {"pulling": 0.9},
      "model": "item/weapons/frostwind_bow_pulling_1"
    }
  ]
}
```

## Sounds JSON

```json
{
  "altar.activate": {
    "sounds": ["ability/altar_activate"],
    "subtitle": "subtitles.altar.activate"
  },
  "ability.cooldown": {
    "sounds": ["ability/cooldown_ready"],
    "subtitle": "subtitles.ability.ready"
  },
  "weapon.frostwind": {
    "sounds": ["weapons/frostwind_impact"],
    "subtitle": "subtitles.weapon.frostwind"
  },
  "armor.sun.activate": {
    "sounds": ["armor/sun_crown_activate"],
    "subtitle": "subtitles.armor.sun.activate"
  }
}
```

## AnimatedTexture Specifications

All animated textures use the following format:

```json
{
  "animation": {
    "interpolate": true,
    "frametime": 2,
    "frames": [0, 1, 2, 3, 4, 5, 4, 3, 2, 1]
  }
}
```

## Emissive Layer Convention

All emissive textures follow the naming pattern:
- Base texture: `weapon_name.png`
- Emissive layer: `weapon_name_emissive.png`
- Emissive layer is added via `layer1` in item model JSON

## Color Palette

### Faction Colors
- **Human**: Gold (#FFD700), White (#FFFFFF), Silver (#C0C0C0)
- **Cult**: Crimson (#DC143C), Dark Purple (#660099), Black (#1A1A1A)
- **Void**: Deep Purple (#440088), Cyan (#00FFFF), Black (#000000)

### Rarity Colors
- **Legendary**: Gold (#FFD700)
- **Ancient**: Purple (#DA70D6)
- **Ethereal**: Cyan (#00FFFF)

## Installation

1. Create folder: `packs/EternalRelics-ResourcePack/`
2. Copy all files maintaining folder structure
3. Create `pack.png` (64x64) with pack artwork
4. Players add pack via `/datapack add file://path` or manually in launcher
5. Enable in game settings

## Compatibility

- Minecraft 1.21.1+
- Paper 1.21.1+
- Works with all shaders (recommended: Complementary, BSL)
- Compatible with mods that don't conflict with custom models

## Texture Dimensions

- All textures: 16x16 base resolution
- GUI textures: 256x256
- Emissive layers: 16x16 with alpha channel
- Pack icon: 64x64 PNG

## Visual Themes

### SUN Theme
- Primary: Bright gold and white
- Secondary: Yellow and silver
- Emissive: Solar flares and rays
- Particle color: Warm yellow/orange

### VOID Theme
- Primary: Black and deep purple
- Secondary: Cyan and magenta
- Emissive: Void vortex and distortion
- Particle color: Purple and cyan

### CULT Theme
- Primary: Crimson and dark purple
- Secondary: Black and violet
- Emissive: Eldritch corruption
- Particle color: Red and purple

### HUMAN Theme
- Primary: Gold and white
- Secondary: Silver and bronze
- Emissive: Holy light rays
- Particle color: Bright yellow/white
