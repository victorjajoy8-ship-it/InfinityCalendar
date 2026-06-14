package com.infinity.calendar.factory;

import dev.lone.itemsadder.api.CustomStack;
import com.bitworksmc.headdb.api.HeadAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemFactory {

    public static ItemStack resolveItem(String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            return new ItemStack(Material.BARRIER);
        }

        try {
            // Soporte ItemsAdder
            if (identifier.startsWith("itemsadder:") && Bukkit.getPluginManager().isPluginEnabled("ItemsAdder")) {
                String id = identifier.replace("itemsadder:", "");
                CustomStack customStack = CustomStack.getInstance(id);
                if (customStack != null) return customStack.getItemStack();
            }

            // Soporte HeadDatabase v6+
            if (identifier.startsWith("headdb:") && Bukkit.getPluginManager().isPluginEnabled("HeadDatabase")) {
                String id = identifier.replace("headdb:", "");
                var registration = Bukkit.getServicesManager().getRegistration(HeadAPI.class);
                if (registration != null) {
                    var head = registration.getProvider().getHead(id);
                    if (head.isPresent()) return head.get().getItemStack();
                }
            }

            // Soporte Minecraft Vanilla
            Material material = Material.matchMaterial(identifier.toUpperCase().replace("MINECRAFT:", ""));
            if (material != null) {
                return new ItemStack(material);
            }

        } catch (Exception e) {
            Bukkit.getLogger().severe("[InfinityCalendar] Error procesando el item: " + identifier);
        }

        return new ItemStack(Material.STONE);
    }
}
