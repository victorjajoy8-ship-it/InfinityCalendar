package com.infinity.calendar.menu;

import com.infinity.calendar.util.ColorUtil;
import com.infinity.calendar.factory.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CalendarGUI implements InventoryHolder {

    private final Inventory inventory;

    public CalendarGUI(Player player) {
        String title = ColorUtil.colorize("&#3498db&lInfinity Calendar");
        this.inventory = Bukkit.createInventory(this, 54, title);
        setupLayout(player);
    }

    private void setupLayout(Player player) {
        // Ejemplo de Item decorativo de fondo usando ItemsAdder o Vanilla
        ItemStack panel = ItemFactory.resolveItem("minecraft:gray_stained_glass_pane");
        ItemMeta meta = panel.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ColorUtil.colorize(" "));
            panel.setItemMeta(meta);
        }

        // Rellenar bordes del menú interactivo
        for (int i = 0; i < 54; i++) {
            if (i < 9 || i > 44 || i % 9 == 0 || (i + 1) % 9 == 0) {
                this.inventory.setItem(i, panel);
            }
        }
        
        // Aquí se renderizan los slots del calendario dinámicamente desde tu config.yml
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }
}
