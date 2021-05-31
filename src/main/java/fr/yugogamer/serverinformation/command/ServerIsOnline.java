package fr.yugogamer.serverinformation.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.yugogamer.serverinformation.config.Config;
import fr.yugogamer.serverinformation.server.Server;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.entity.Entity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;;

public class ServerIsOnline {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralArgumentBuilder<CommandSource> serverCommande = Commands.literal("apionline").executes(ServerIsOnline::sendPigLatinMessage);

        dispatcher.register(serverCommande);
    }


    /**
     * Read the command's "message" argument, convert it to pig latin, then send as a chat message
     */
    static int sendPigLatinMessage(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        String text = "API server is online";
        if (!Server.isOnline)
        {
            text = "Server is down";
        }
        ITextComponent sendMessage = new StringTextComponent(text);

        commandContext.getSource().getEntity().sendMessage(sendMessage,Util.DUMMY_UUID);
        return 1;
    }
}
