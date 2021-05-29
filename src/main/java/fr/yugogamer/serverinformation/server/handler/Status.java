package fr.yugogamer.serverinformation.server.handler;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.io.OutputStream;

public class Status extends MinecraftHandler {
    public Status(MinecraftServer minecraftServer) {
        super(minecraftServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        JsonObject response = new JsonObject();
        response.addProperty("name",this.minecraftServer.getName());
        response.addProperty("version",this.minecraftServer.getMinecraftVersion());
        response.addProperty("description",this.minecraftServer.getMOTD());
        response.addProperty("maxplayer",this.minecraftServer.getMaxPlayers());
        response.addProperty("currentplayer", this.minecraftServer.getPlayerList().getCurrentPlayerCount());
        response.addProperty("whitelist", this.minecraftServer.getPlayerList().isWhiteListEnabled());
        response.addProperty("modded",this.minecraftServer.getServerConfiguration().isModded());
        response.addProperty("hardcore", this.minecraftServer.getServerConfiguration().isHardcore());
        response.addProperty("gametype",this.minecraftServer.getServerConfiguration().getGameType().getName());
        response.addProperty("pvp",this.minecraftServer.isPVPEnabled());
        String sendToClient = response.toString();
        httpExchange.sendResponseHeaders(200, sendToClient.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(sendToClient.getBytes());
        os.close();
    }
}
