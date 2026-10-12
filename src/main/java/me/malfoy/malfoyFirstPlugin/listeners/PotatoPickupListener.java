package me.malfoy.malfoyFirstPlugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PotatoPickupListener implements Listener {
    @EventHandler
    public void onPotatoPickup(EntityPickupItemEvent event){
        if (!(event.getEntity() instanceof Player player)){
            return;
        }
        if (event.getItem().getItemStack().getType() != Material.POTATO){
            return;
        }
        player.setHealth(0.0);
        player.sendMessage(Component.text("Picking up that potato was a bad idea", NamedTextColor.RED));
    }

}
