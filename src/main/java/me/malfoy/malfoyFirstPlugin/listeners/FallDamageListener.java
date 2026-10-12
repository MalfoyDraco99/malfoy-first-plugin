// Canceling Listeners
package me.malfoy.malfoyFirstPlugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemType;

public class FallDamageListener implements Listener {
    @EventHandler
    public void OnDamage(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.PLAYER){
            Player player = (Player) entity;
            if (event.getDamageSource().getDamageType() == DamageType.FALL){
                if (player.getInventory().getItemInOffHand().getType() == Material.FEATHER){
                    event.setCancelled(true);
                    player.sendMessage(Component.text("poof", NamedTextColor.GREEN));
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_SPLASH,1,1);
                }
            }
        }
    }

}
