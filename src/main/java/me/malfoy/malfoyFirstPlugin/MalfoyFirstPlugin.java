package me.malfoy.malfoyFirstPlugin;

import me.malfoy.malfoyFirstPlugin.commands.HealCommand;
import me.malfoy.malfoyFirstPlugin.commands.PotatoComand;
import me.malfoy.malfoyFirstPlugin.listeners.ConnectionListener;
import me.malfoy.malfoyFirstPlugin.listeners.ChorusTeleportListener;
import me.malfoy.malfoyFirstPlugin.listeners.FallDamageListener;
import me.malfoy.malfoyFirstPlugin.listeners.PotatoPickupListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MalfoyFirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("malfoy-first-plugin enabled");

        // Register Events
        getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new ChorusTeleportListener(), this);
        getServer().getPluginManager().registerEvents(new FallDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PotatoPickupListener(), this);


        // Register Commands
        registerCommand("heal", List.of("healalias"), new HealCommand());
        registerCommand("potato",new PotatoComand());
    }

    @Override
    public void onDisable() {
        getLogger().info("malfoy-first-plugin disabled");
    }
}
