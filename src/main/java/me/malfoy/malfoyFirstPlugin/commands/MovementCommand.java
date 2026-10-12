// Command Args "challenge"
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Locale;

public class MovementCommand {
    public static LiteralCommandNode<CommandSourceStack> create(){
        return Commands.literal("movement")
                .executes(context -> {
                    Entity executor = context.getSource().getExecutor();
                    assert executor != null;
                    executor.sendMessage(Component.text("Use /movement <type> to set that types speed to default\n" +
                            "and use /movement <type> <value> to set that types speed to <value>",NamedTextColor.GREEN));
                    return 1;
                })
                .then(Commands.literal("walk")
                        .executes(context -> {
                            return setSpeed(context.getSource(), 0.2f,SpeedType.WALK);
                        })
                        .then(Commands.argument("value", FloatArgumentType.floatArg(-1,1))
                                .executes(context -> {
                                    return setSpeed(context.getSource(), FloatArgumentType.getFloat(context,"value"),SpeedType.WALK);
                                })))
                .then(Commands.literal("fly")
                        .executes(context -> {
                            return setSpeed(context.getSource(), 0.2f,SpeedType.FLY);
                        })
                        .then(Commands.argument("value", FloatArgumentType.floatArg(-1,1))
                                .executes(context -> {
                                    return setSpeed(context.getSource(), FloatArgumentType.getFloat(context,"value"),SpeedType.FLY);
                                })))
                .build();
    }

    private static int setSpeed(CommandSourceStack source, float amount, SpeedType type){
        if (!(source.getExecutor() instanceof Player player)){
            source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
            return 0;
        }
        if (type == SpeedType.WALK){
            player.setWalkSpeed(amount);
        } else {
            player.setFlySpeed(amount);
        }
        player.sendMessage(Component.text("Your "+type.toString().toLowerCase()+"speed has been set to "+amount+".",NamedTextColor.GREEN));

        return 1;
    }
    private enum SpeedType {
        WALK,
        FLY
    }
}
