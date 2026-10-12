package me.malfoy.malfoyFirstPlugin;

import me.malfoy.malfoyFirstPlugin.listeners.ConnectionListener;
import me.malfoy.malfoyFirstPlugin.listeners.ChorusTeleportListener;
import me.malfoy.malfoyFirstPlugin.listeners.FallDamageListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MalfoyFirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("malfoy-first-plugin enabled");

        // Register Events
        getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new ChorusTeleportListener(), this);
        getServer().getPluginManager().registerEvents(new FallDamageListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("malfoy-first-plugin disabled");
    }
}
