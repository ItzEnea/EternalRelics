package dev.eternalrelics.core.item.armor;

import dev.eternalrelics.core.item.LegendaryArmor;
import org.bukkit.Material;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Footsteps of Midas (Boots)
 * Ground slam with true damage and explosions
 * Model Data: 1004
 */
public class FootstepsOfMidas extends LegendaryArmor {
    
    public FootstepsOfMidas() {
        super(
            "midas-boots",
            "★ Footsteps of Midas",
            Material.GOLDEN_BOOTS,
            1004,
            NamedTextColor.GOLD
        );
        
        addLore("");
        addLore("§7Active: §fGround Slam");
        addLore("§7Damage: §f20 True Damage");
        addLore("§7Radius: §f5 blocks");
        addLore("§7Cooldown: §f15 seconds");
        addLore("");
        addLore("§6Legendary Armor");
    }
}
