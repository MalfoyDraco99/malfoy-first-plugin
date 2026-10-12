package me.malfoy.malfoyFirstPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MalfoyFirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("malfoy-first-plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("malfoy-first-plugin disabled");
    }
}
