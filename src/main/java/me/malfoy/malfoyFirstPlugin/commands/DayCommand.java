// Command Tree
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class DayCommand {
    public static LiteralCommandNode<CommandSourceStack> create(){
        return Commands.literal("day")
                .executes(context -> {
                    return setTime(context.getSource(),6000L,"noon");
                })
                .then(Commands.literal("sunrise")
                        .executes(context -> {
                            return setTime(context.getSource(),23000L,"sunrise");
                        }))
                .then(Commands.literal("sunset")
                        .executes(context -> {
                            return setTime(context.getSource(),12000L,"sunset");
                        }))
                .build();
    }

    private static int setTime(CommandSourceStack source, long Time, String timeName){
        if (!(source.getExecutor() instanceof Player player)){
            source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
            return 0;
        }
        player.getWorld().setTime(Time);
        player.sendMessage(Component.text("Set time to "+ timeName + "!", NamedTextColor.GREEN));
        return 1;
    }

}
