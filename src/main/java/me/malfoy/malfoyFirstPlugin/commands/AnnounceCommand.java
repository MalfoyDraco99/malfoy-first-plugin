// Strings and Greedy Strings
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class AnnounceCommand {
    public static LiteralCommandNode<CommandSourceStack> create(){
        return Commands.literal("announce")
                .then(Commands.argument("message", StringArgumentType.greedyString())
                        .executes(context -> {
                            var message = StringArgumentType.getString(context,"message");
                            Bukkit.getServer().broadcast(Component.text("ANNOUNCEMENT: ", NamedTextColor.GOLD)
                                    .append(Component.text(message, NamedTextColor.BLUE)));
                            return 1;
                        }))
                .build();
    }
}
