package fr.yugogamer.serverinformation.server.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.io.OutputStream;

public class PlayerList  extends MinecraftHandler {
    public PlayerList(MinecraftServer minecraftServer) {
        super(minecraftServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        JsonObject response = new JsonObject();
        String[] playerArray = this.minecraftServer.getOnlinePlayerNames();
        JsonArray responseArray = new JsonArray();
        for (String s : playerArray) {
            responseArray.add(s);
        }
        response.add("players",responseArray);
        response.addProperty("maxplayer",this.minecraftServer.getMaxPlayers());
        response.addProperty("currentplayer", this.minecraftServer.getPlayerList().getCurrentPlayerCount());
        String sendToClient = response.toString();
        httpExchange.sendResponseHeaders(200, sendToClient.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(sendToClient.getBytes());
        os.close();
    }
}