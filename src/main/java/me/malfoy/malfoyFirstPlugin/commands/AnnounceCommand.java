// Strings and Greedy Strings
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AnnounceCommand {
    public static LiteralCommandNode<CommandSourceStack> create(){
        return Commands.literal("announce")
                .requires(commandSourceStack -> {
                    return commandSourceStack.getSender().hasPermission("malfoysplugin.announce");
                })
                .executes(context -> {
                    return sendUsage(context.getSource());
                })
                .then(Commands.literal("chat")
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(context -> {
                                    return sendChatAnnouncement(StringArgumentType.getString(context,"message"));
                                })))
                .then(Commands.literal("actionbar")
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(context -> {
                                    sendActionBarAnnouncement(context.getSource(),StringArgumentType.getString(context,"message"));
                                    return 1;
                                })))
                .then(Commands.literal("title")
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(context -> {
                                    sendTitleAnnouncement(context.getSource(),StringArgumentType.getString(context,"message"));
                                    return 1;
                                })))
                .build();
    }
    // if ur reading this ur a cool dude
    private static int sendChatAnnouncement(String message){
        Bukkit.broadcast(Component.text("Announcement: " + message, NamedTextColor.GOLD));
        return 1;
    }

    private static void sendActionBarAnnouncement(CommandSourceStack source, String message){

    }

    private static void sendTitleAnnouncement(CommandSourceStack source, String message){

    }

    private static int sendUsage(CommandSourceStack source){
        if (!(source.getExecutor() instanceof Player player)){
            source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
            return 0;
        }
        player.sendMessage(Component.text("Usage:\n" +
                "/announce chat <message>\n" +
                "/announce actionbar <message>\n" +
                "/announce title <message>", NamedTextColor.RED));
        return 1;
    }

}
