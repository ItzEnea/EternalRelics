package dev.eternalrelics.core.item.weapon;

import dev.eternalrelics.core.item.LegendaryWeapon;
import org.bukkit.Material;
import net.kyori.adventure.text.format.NamedTextColor;

public class FrostwindBow extends LegendaryWeapon {
    public FrostwindBow() {
        super("frostwind-bow", "❄ Frostwind Bow", Material.BOW, 2001, NamedTextColor.AQUA, 15.0);
        addLore(""); addLore("§7Passive: Freeze arrows");
        addLore("§7Duration: §f5 seconds"); addLore("§7True Damage: §f15"); addLore("§7Cooldown: §f3 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class VoidguardShield extends LegendaryWeapon {
    public VoidguardShield() {
        super("voidguard-shield", "◆ Voidguard Shield", Material.SHIELD, 2002, NamedTextColor.DARK_PURPLE, 0.0);
        addLore(""); addLore("§7Passive: Void Reflection");
        addLore("§7Absorption: §f8 shields"); addLore("§7Reflect: §f50% damage"); addLore("§7Cooldown: §f5 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class Ironhowl extends LegendaryWeapon {
    public Ironhowl() {
        super("ironhowl", "🔨 Ironhowl", Material.WOODEN_SWORD, 2003, NamedTextColor.DARK_GRAY, 25.0);
        addLore(""); addLore("§7Active: Stun Attack");
        addLore("§7Stun Duration: §f3 seconds"); addLore("§7True Damage: §f25"); addLore("§7Cooldown: §f4 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class Bonecracker extends LegendaryWeapon {
    public Bonecracker() {
        super("bonecracker", "💀 Bonecracker", Material.WOODEN_AXE, 2004, NamedTextColor.WHITE, 20.0);
        addLore(""); addLore("§7Passive: Critical Strike");
        addLore("§7Multiplier: §f2.5x damage"); addLore("§7True Damage: §f20"); addLore("§7Cooldown: §f6 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class AbyssalSword extends LegendaryWeapon {
    public AbyssalSword() {
        super("abyssal-sword", "⚫ Abyssal Sword", Material.WOODEN_SWORD, 2005, NamedTextColor.DARK_PURPLE, 22.0);
        addLore(""); addLore("§7Passive: Life Steal");
        addLore("§7Steal: §f25% damage dealt"); addLore("§7True Damage: §f22"); addLore("§7Cooldown: §f4 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class PhantomEdge extends LegendaryWeapon {
    public PhantomEdge() {
        super("phantom-edge", "👻 Phantom Edge", Material.WOODEN_SWORD, 2006, NamedTextColor.GRAY, 18.0);
        addLore(""); addLore("§7Active: Phantom Clones");
        addLore("§7Clones: §f3 phantoms"); addLore("§7True Damage: §f18"); addLore("§7Cooldown: §f3 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class CrystalEdge extends LegendaryWeapon {
    public CrystalEdge() {
        super("crystal-edge", "💎 Crystal Edge", Material.WOODEN_SWORD, 2007, NamedTextColor.LIGHT_PURPLE, 20.0);
        addLore(""); addLore("§7Active: Crystal Shatter");
        addLore("§7Radius: §f5 blocks"); addLore("§7True Damage: §f20"); addLore("§7Cooldown: §f5 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class Mooncleaver extends LegendaryWeapon {
    public Mooncleaver() {
        super("mooncleaver", "🌙 Mooncleaver", Material.WOODEN_SWORD, 2008, NamedTextColor.WHITE, 20.0);
        addLore(""); addLore("§7Passive: Lunar Damage");
        addLore("§7Increased at night"); addLore("§7True Damage: §f25"); addLore("§7Cooldown: §f6 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class Nightfang extends LegendaryWeapon {
    public Nightfang() {
        super("nightfang", "🌑 Nightfang", Material.WOODEN_SWORD, 2009, NamedTextColor.BLACK, 20.0);
        addLore(""); addLore("§7Active: Shadow Clones");
        addLore("§7Clones: §f2 shadows"); addLore("§7True Damage: §f20"); addLore("§7Cooldown: §f4 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class Ghostthorn extends LegendaryWeapon {
    public Ghostthorn() {
        super("ghostthorn", "☠ Ghostthorn", Material.WOODEN_SWORD, 2010, NamedTextColor.LIGHT_PURPLE, 18.0);
        addLore(""); addLore("§7Active: Phase Strike");
        addLore("§7Phase Duration: §f4 seconds"); addLore("§7True Damage: §f18"); addLore("§7Cooldown: §f5 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class TitansAxe extends LegendaryWeapon {
    public TitansAxe() {
        super("titans-axe", "⛏ Titan's Axe", Material.WOODEN_AXE, 2011, NamedTextColor.DARK_RED, 30.0);
        addLore(""); addLore("§7Active: Crushing Blow");
        addLore("§7Knockback: §f2x strength"); addLore("§7True Damage: §f30"); addLore("§7Cooldown: §f8 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class OblivionCleaver extends LegendaryWeapon {
    public OblivionCleaver() {
        super("oblivion-cleaver", "🕳 Oblivion Cleaver", Material.WOODEN_SWORD, 2012, NamedTextColor.BLACK, 32.0);
        addLore(""); addLore("§7Active: Black Hole");
        addLore("§7Radius: §f8 blocks"); addLore("§7True Damage: §f32"); addLore("§7Cooldown: §f10 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class Thundermaul extends LegendaryWeapon {
    public Thundermaul() {
        super("thundermaul", "⚡ Thundermaul", Material.WOODEN_AXE, 2013, NamedTextColor.YELLOW, 25.0);
        addLore(""); addLore("§7Active: Lightning Strikes");
        addLore("§7Strikes: §f3 lightning bolts"); addLore("§7True Damage: §f25"); addLore("§7Cooldown: §f7 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class Sunreaver extends LegendaryWeapon {
    public Sunreaver() {
        super("sunreaver", "☀ Sunreaver", Material.WOODEN_SWORD, 2014, NamedTextColor.GOLD, 23.0);
        addLore(""); addLore("§7Active: Solar Flare");
        addLore("§7Radius: §f6 blocks"); addLore("§7True Damage: §f23"); addLore("§7Cooldown: §f6 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}

class VoidHarvest extends LegendaryWeapon {
    public VoidHarvest() {
        super("void-harvest", "🌌 Void Harvest", Material.WOODEN_SWORD, 2015, NamedTextColor.DARK_PURPLE, 28.0);
        addLore(""); addLore("§7Active: Void Pull");
        addLore("§7Radius: §f7 blocks"); addLore("§7True Damage: §f28"); addLore("§7Cooldown: §f9 seconds");
        addLore(""); addLore("§6Legendary Weapon");
    }
}
