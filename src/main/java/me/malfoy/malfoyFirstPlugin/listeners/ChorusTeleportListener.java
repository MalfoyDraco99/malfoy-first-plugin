package me.malfoy.malfoyFirstPlugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class ChorusTeleportListener implements Listener {

    @EventHandler
    public void OnTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.CONSUMABLE_EFFECT){
            if (player.isSneaking()){
                event.setCancelled(true);
                player.sendMessage(Component.text("no chorus for u", NamedTextColor.RED));
                player.playSound(player.getLocation(), Sound.ENTITY_COW_DEATH, 1,1);
            }
        }
    }
}