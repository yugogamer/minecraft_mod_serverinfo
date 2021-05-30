package fr.yugogamer.serverinformation.server.handler.admin;

import fr.yugogamer.serverinformation.config.Config;
import fr.yugogamer.serverinformation.server.handler.MinecraftHandler;
import net.minecraft.server.MinecraftServer;

public class ApiHandler extends MinecraftHandler {
    protected static String apiKey =(String) Config.ID_ADMIN.get();

    public ApiHandler(MinecraftServer minecraftServer) {
        super(minecraftServer);
    }
}
