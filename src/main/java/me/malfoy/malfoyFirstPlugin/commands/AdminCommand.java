// Player Selector Args and Resolvers
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminCommand {
    public static LiteralCommandNode<CommandSourceStack> create(){
        return Commands.literal("admin")
                .then(Commands.literal("heal")
                        .executes(context -> {
                            var source = context.getSource();
                            if (!(source.getExecutor() instanceof Player player)){
                                source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
                                return 0;
                            }
                            return healTargets(source,List.of(player));
                        })
                        .then(Commands.argument("targets", ArgumentTypes.players())
                                .executes(context -> {
                                    var resolver = context.getArgument("targets", PlayerSelectorArgumentResolver.class);
                                    var players = resolver.resolve(context.getSource());
                                    return healTargets(context.getSource(),players);
                                })))
                .then(Commands.literal("gamemode")
                        .then(Commands.argument("gamemode",ArgumentTypes.gameMode())
                                .executes(context -> {
                                    var source = context.getSource();
                                    if (!(source.getExecutor() instanceof Player player)){
                                        source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
                                        return 0;
                                    }
                                    var gamemode = context.getArgument("gamemode",GameMode.class);
                                    return changeGamemode(source, gamemode, List.of(player));
                                })
                                .then(Commands.argument("targets", ArgumentTypes.players())
                                        .executes(context -> {
                                            var resolver = context.getArgument("targets", PlayerSelectorArgumentResolver.class);
                                            var players = resolver.resolve(context.getSource());
                                            var gamemode = context.getArgument("gamemode",GameMode.class);
                                            return changeGamemode(context.getSource(),gamemode,players);
                                        }))
                        ))
                .build();
    }

    private static int healTargets(CommandSourceStack source, List<Player> targets){
        for (Player player : targets){
            player.setHealth(20.0);
            player.setFoodLevel(20);
            player.setFireTicks(0);
            player.sendMessage(Component.text("You have been healed.", NamedTextColor.GREEN));
        }
        source.getSender().sendMessage(Component.text("Healed "+ targets.size()+" player(s)", NamedTextColor.GREEN));
        return 1;

    }
    private static int changeGamemode(CommandSourceStack source, GameMode gamemode, List<Player> targets){
        for (Player player : targets){
            player.setGameMode(gamemode);
            player.sendMessage(Component.text("You have been set to gamemode"+ gamemode.toString(), NamedTextColor.GREEN));
        }
        source.getSender().sendMessage(Component.text("You have set "+ targets.size() +" players to gamemode"+ gamemode.toString()));
        return 1;
    }
}
