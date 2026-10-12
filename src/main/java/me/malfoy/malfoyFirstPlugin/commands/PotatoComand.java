package me.malfoy.malfoyFirstPlugin.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PotatoComand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (!(commandSourceStack.getExecutor() instanceof Player player)){
            commandSourceStack.getSender().sendMessage(Component.text("Only a player can use this command."));
            return;
        }
        ItemStack potato = new ItemStack(Material.POTATO,1);
        player.getWorld().dropItem(player.getTargetBlock(null,40).getLocation().add(0,1,0), potato);
        player.sendMessage(Component.text("Oh look, a potato! what could go wrong?", NamedTextColor.YELLOW));
    }
}
