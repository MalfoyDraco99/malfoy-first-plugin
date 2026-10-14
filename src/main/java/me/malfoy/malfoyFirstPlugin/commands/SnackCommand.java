package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.malfoy.malfoyFirstPlugin.MalfoyFirstPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;

public class SnackCommand {
    public static LiteralCommandNode<CommandSourceStack> create(MalfoyFirstPlugin plugin){
        return Commands.literal("snack")
                .executes(context -> {
                    var source = context.getSource();
                    if (!(source.getExecutor() instanceof Player player)){
                        source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
                        return 0;
                    }

                    var snackamount = plugin.getConfig().getInt("snack-amount");

                    if (snackamount <= 0 || snackamount >= 65){
                        player.sendMessage(Component.text("snack-amount config should be greater then 0 or less then 65."));
                        return 0;
                    }

                    ItemStack cookie = ItemStack.of(Material.COOKIE,snackamount);
                    var leftovers = player.getInventory().addItem(cookie);
                    if (!(leftovers.isEmpty())){
                        Location loc = player.getLocation();
                        ItemStack leftoverItem = leftovers.values().iterator().next();
                        int leftoveramount = leftoverItem.getAmount();
                        player.getWorld().dropItem(loc, leftoverItem);
                        String inventoryFullMessage = plugin.getConfig().getString("snack.messages.inventory-full");
                        assert inventoryFullMessage != null;
                        player.sendRichMessage(inventoryFullMessage,Placeholder.unparsed("remainder",String.valueOf(leftoveramount)));
                        return 1;
                    }
                    String successMessage = plugin.getConfig().getString("snack.messages.success");
                    assert successMessage != null;
                    player.sendRichMessage(successMessage, Placeholder.unparsed("amount",String.valueOf(snackamount)));
                    return 1;

                })
                .build();
    }
}
