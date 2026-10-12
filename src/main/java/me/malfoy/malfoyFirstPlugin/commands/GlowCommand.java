// Boolean Command Args and boolean in general
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class GlowCommand {
    public static LiteralCommandNode<CommandSourceStack> create(){
        return Commands.literal("glow")
                .executes(context -> {
                    var source = context.getSource();
                    if (!(source.getExecutor() instanceof Player player)){
                        source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
                        return 0;
                    }
                    boolean isGlowing = player.isGlowing();
                    player.setGlowing(!isGlowing);
                    player.sendMessage(Component.text(
                            isGlowing ? "You are no longer glowing" : "You are now glowing",
                            NamedTextColor.GREEN
                    ));
                    return 1;
                })
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(context -> {
                        var source = context.getSource();
                        if (!(source.getExecutor() instanceof Player player)){
                            source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
                            return 0;
                        }
                        boolean enabled = BoolArgumentType.getBool(context,"enabled");
                        player.setGlowing(enabled);
                        player.sendMessage(Component.text(
                                enabled ? "You are now glowing." : "You are nolonger glowing.",
                                NamedTextColor.GREEN
                        ));
                        return 1;
                    }))

            .build();
    }
}
