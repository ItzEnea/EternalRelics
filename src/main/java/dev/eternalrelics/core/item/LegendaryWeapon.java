package dev.eternalrelics.core.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.format.NamedTextColor;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for legendary weapons
 * Provides common structure for all legendary weapons
 */
public abstract class LegendaryWeapon {
    
    protected String id;
    protected String displayName;
    protected Material baseMaterial;
    protected int customModelData;
    protected List<String> lore;
    protected NamedTextColor rarityColor;
    protected double baseDamage;
    
    protected LegendaryWeapon(String id, String displayName, Material baseMaterial, int customModelData, NamedTextColor rarityColor, double baseDamage) {
        this.id = id;
        this.displayName = displayName;
        this.baseMaterial = baseMaterial;
        this.customModelData = customModelData;
        this.rarityColor = rarityColor;
        this.baseDamage = baseDamage;
        this.lore = new ArrayList<>();
    }
    
    /**
     * Builds the ItemStack for this weapon
     */
    public ItemStack buildItem() {
        ItemStack item = new ItemStack(baseMaterial);
        ItemMeta meta = item.getItemMeta();
        
        // Set display name with color
        meta.displayName(Component.text(displayName, rarityColor, TextDecoration.BOLD));
        
        // Set custom model data for resource pack
        meta.setCustomModelData(customModelData);
        
        // Set lore
        List<Component> loreComponents = new ArrayList<>();
        for (String loreLine : lore) {
            loreComponents.add(Component.text(loreLine, NamedTextColor.GRAY));
        }
        meta.lore(loreComponents);
        
        item.setItemMeta(meta);
        return item;
    }
    
    /**
     * Adds a line to the lore
     */
    protected void addLore(String line) {
        this.lore.add(line);
    }
    
    public String getId() {
        return id;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public int getCustomModelData() {
        return customModelData;
    }
    
    public double getBaseDamage() {
        return baseDamage;
    }
}
