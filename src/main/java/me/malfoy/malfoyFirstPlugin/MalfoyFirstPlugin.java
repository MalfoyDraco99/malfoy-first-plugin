package me.malfoy.malfoyFirstPlugin;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.malfoy.malfoyFirstPlugin.commands.*;
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


        // Register Basic Commands
        registerCommand("potato",new PotatoComand());
        // Register Tree Command
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS,event -> {
            event.registrar().register(SpawnCommand.create());
            event.registrar().register(DayCommand.create());
            event.registrar().register(HealCommand.create());
            event.registrar().register(GlowCommand.create());
            event.registrar().register(AnnounceCommand.create());
            event.registrar().register(MovementCommand.create());
            event.registrar().register(AdminCommand.create());
        });
    }

    @Override
    public void onDisable() {
        getLogger().info("malfoy-first-plugin disabled");
    }
}
