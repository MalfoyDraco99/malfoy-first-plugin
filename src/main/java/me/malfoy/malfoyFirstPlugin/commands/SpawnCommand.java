// Command Tree
package me.malfoy.malfoyFirstPlugin.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnCommand {
    public static LiteralCommandNode<CommandSourceStack> create(){
        return Commands.literal("spawn")
                .then(Commands.literal("zombie") // /spawn zombie
                        .requires(commandSourceStack -> {
                            return commandSourceStack.getSender().hasPermission("malfoysplugin.spawn.zombie");
                        })
                        .executes(context -> {
                            return spawnMob(context.getSource(), EntityType.ZOMBIE);
                        }))
                .then(Commands.literal("creeper")
                        .requires(commandSourceStack -> {
                            return commandSourceStack.getSender().hasPermission("malfoysplugin.spawn.creeper");
                        })
                        .executes(context -> {
                            return spawnMob(context.getSource(), EntityType.CREEPER);
                        }))
                .build();
    }

    private static int spawnMob(CommandSourceStack source,EntityType type){
        if (!(source.getExecutor() instanceof Player player)){
            source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
            return 0;
        }
        player.getWorld().spawnEntity(player.getLocation(), type);
        player.sendMessage(Component.text("Spawned a " + type.key().value() + "!", NamedTextColor.GREEN));
        return 1;
    }
}
