package me.malfoy.malfoyFirstPlugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(Component.text("Welcome ", NamedTextColor.RED)
                .append(Component.text(player.getName(), NamedTextColor.GOLD))
                .append(Component.text(", Glad to have you!", NamedTextColor.RED)));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.quitMessage(Component.text("Goodbye ", NamedTextColor.RED)
                .append(Component.text(player.getName(), NamedTextColor.GOLD))
                .append(Component.text(", Sad to see you go!", NamedTextColor.RED)));
    }

}