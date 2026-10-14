// Listeners
package me.malfoy.malfoyFirstPlugin.listeners;

import me.malfoy.malfoyFirstPlugin.MalfoyFirstPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private final MalfoyFirstPlugin plugin;

    public ConnectionListener(MalfoyFirstPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!(plugin.getConfig().getBoolean("join-effects.enabled"))){
            return;
        }
        Player player = event.getPlayer();
        var message = plugin.getConfig().getString("join-effects.message");
        var formattedMessage = MiniMessage.miniMessage().deserialize(message, Placeholder.unparsed("player", event.getPlayer().getName()));
        event.joinMessage(formattedMessage);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.quitMessage(Component.text("Goodbye ", NamedTextColor.RED)
                .append(Component.text(player.getName(), NamedTextColor.GOLD))
                .append(Component.text(", Sad to see you go!", NamedTextColor.RED)));
    }

}