package dev.eternalrelics.core.item.armor;

import dev.eternalrelics.core.item.LegendaryArmor;
import org.bukkit.Material;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Sun Crown (Helmet)
 * Movement speed boost and day vision
 * Model Data: 1001
 */
public class SunCrown extends LegendaryArmor {
    
    public SunCrown() {
        super(
            "sun-crown",
            "☀ Sun Crown",
            Material.GOLDEN_HELMET,
            1001,
            NamedTextColor.GOLD
        );
        
        addLore("");
        addLore("§7Passive: §fSwiftness I");
        addLore("§7Passive: §fDay Vision");
        addLore("");
        addLore("§6Legendary Armor");
    }
}
