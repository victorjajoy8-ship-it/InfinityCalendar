package com.infinity.calendar;

import com.infinity.calendar.menu.CalendarGUI;
import com.infinity.calendar.util.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class InfinityCalendar extends JavaPlugin implements CommandExecutor {

    private static InfinityCalendar instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        
        if (getCommand("infinitycalendar") != null) {
            getCommand("infinitycalendar").setExecutor(this);
        }

        getLogger().info("InfinityCalendar ha sido activado correctamente en Spigot 1.21.1.");
    }

    @Override
    public void onDisable() {
        getLogger().info("InfinityCalendar deshabilitado por completo.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtil.colorize("&cEste comando solo puede ser ejecutado por jugadores."));
            return true;
        }

        Player player = (Player) sender;
        
        // Abrir el menú interactivo del calendario
        CalendarGUI gui = new CalendarGUI(player);
        gui.open(player);
        return true;
    }

    public static InfinityCalendar getInstance() {
        return instance;
    }
}
