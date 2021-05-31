package fr.yugogamer.serverinformation.register;

import com.mojang.brigadier.CommandDispatcher;
import fr.yugogamer.serverinformation.command.ServerIsOnline;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerRegister {
    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
        ServerIsOnline.register(commandDispatcher);
    }
}
