// Strings and Greedy Strings
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
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
        var announcement = Component.text("Announcement: ", NamedTextColor.GOLD)
                .append(Component.text(message, NamedTextColor.BLUE));
        Bukkit.broadcast(announcement);
        return 1;
    }

    private static void sendActionBarAnnouncement(CommandSourceStack source, String message){
        var actionbar = Component.text("Announcement: ", NamedTextColor.GOLD)
                .append(Component.text(message, NamedTextColor.BLUE));
        Audience players = Audience.audience(Bukkit.getOnlinePlayers());

        players.sendActionBar(actionbar);
    }

    private static void sendTitleAnnouncement(CommandSourceStack source, String message){
        var title = Component.text(message,NamedTextColor.GOLD).decorate(TextDecoration.BOLD);
        var subtitle = Component.text("Server Announcement", NamedTextColor.GRAY);
        Audience players = Audience.audience(Bukkit.getOnlinePlayers());
        players.showTitle(Title.title(title,subtitle));

    }

    private static int sendUsage(CommandSourceStack source){
        var usage = Component.text("Usage:\n" +
                "/announce chat <message>\n" +
                "/announce actionbar <message>\n" +
                "/announce title <message>", NamedTextColor.RED);
        source.getSender().sendMessage(usage);
        return 1;
    }

}
