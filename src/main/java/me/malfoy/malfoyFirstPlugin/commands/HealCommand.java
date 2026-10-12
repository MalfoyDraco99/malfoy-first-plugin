// Command args double
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jspecify.annotations.Nullable;

public class HealCommand {
    public static LiteralCommandNode<CommandSourceStack> create(){
        return Commands.literal("heal")
                .executes(context -> {
                   return fullyHeal(context.getSource());
                })
                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(1.0,20.0))
                        .executes(context -> {
                          return healAmount(context.getSource(), DoubleArgumentType.getDouble(context,"amount"));
                        }))
                .build();
    }

    private static int fullyHeal(CommandSourceStack source){
        if (!(source.getExecutor() instanceof Player player)){
            source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
            return 0;
        }
        player.setHealth(20);
        player.setSaturation(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.sendMessage(Component.text("You have been fully healed.", NamedTextColor.GREEN));
        return 1;
    }

    private static int healAmount(CommandSourceStack source, double amount){
        if (!(source.getExecutor() instanceof Player player)){
            source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
            return 0;
        }

        player.setHealth(amount);
        player.sendMessage(Component.text("Your health is now ", NamedTextColor.GREEN)
                .append(Component.text("20.0", NamedTextColor.AQUA))
                .append(Component.text("!", NamedTextColor.GREEN)));
        return 1;
    }
}
