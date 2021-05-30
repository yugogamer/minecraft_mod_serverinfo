package fr.yugogamer.serverinformation.server.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import fr.yugogamer.serverinformation.config.Config;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.io.OutputStream;

public class AdminEnable extends MinecraftHandler {
    public AdminEnable(MinecraftServer minecraftServer) {
        super(minecraftServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        JsonObject response = new JsonObject();
        response.addProperty("enable", Config.ADMIN_API.get());
        String sendToClient = response.toString();
        httpExchange.sendResponseHeaders(200, sendToClient.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(sendToClient.getBytes());
        os.close();
    }
}