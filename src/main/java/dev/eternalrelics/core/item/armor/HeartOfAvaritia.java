package dev.eternalrelics.core.item.armor;

import dev.eternalrelics.core.item.LegendaryArmor;
import org.bukkit.Material;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Heart of Avaritia (Chestplate)
 * Auto-heal at low health with true damage
 * Model Data: 1002
 */
public class HeartOfAvaritia extends LegendaryArmor {
    
    public HeartOfAvaritia() {
        super(
            "heart-avaritia",
            "❤ Heart of Avaritia",
            Material.GOLDEN_CHESTPLATE,
            1002,
            NamedTextColor.RED
        );
        
        addLore("");
        addLore("§7Passive: §fAuto-Heal at 50% Health");
        addLore("§7Effect: §f4 Hearts regenerated");
        addLore("§7Cooldown: §f10 seconds");
        addLore("");
        addLore("§6Legendary Armor");
    }
}
