package dev.eternalrelics.core.item.armor;

import dev.eternalrelics.core.item.LegendaryArmor;
import org.bukkit.Material;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Golden Time Steppers (Leggings)
 * Double jump and enhanced movement
 * Model Data: 1003
 */
public class GoldenTimeSteppers extends LegendaryArmor {
    
    public GoldenTimeSteppers() {
        super(
            "time-steppers",
            "⏰ Golden Time Steppers",
            Material.GOLDEN_LEGGINGS,
            1003,
            NamedTextColor.YELLOW
        );
        
        addLore("");
        addLore("§7Active: §fDouble Jump");
        addLore("§7Effect: §f1.5x velocity boost");
        addLore("§7Cooldown: §f8 seconds");
        addLore("");
        addLore("§6Legendary Armor");
    }
}
