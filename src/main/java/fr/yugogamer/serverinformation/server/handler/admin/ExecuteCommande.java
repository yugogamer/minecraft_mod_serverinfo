package fr.yugogamer.serverinformation.server.handler.admin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.io.OutputStream;

public class ExecuteCommande extends ApiHandler {
    public ExecuteCommande(MinecraftServer minecraftServer) {
        super(minecraftServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        JsonObject response = new JsonObject();
        String command = httpExchange.getRequestHeaders().get("command").get(0);
        String receive_api = httpExchange.getRequestHeaders().get("adminid").get(0);

        if (receive_api != null && !receive_api.equals(this.apiKey))
        {
            response.addProperty("status","error, id not matching");
        }else if (command != null)
        {
            int result = this.minecraftServer.getCommandManager().handleCommand(this.minecraftServer.getCommandSource(), command);
            response.addProperty("status","Succes");
            response.addProperty("return_int",result);
        }
        String sendToClient = response.toString();
        httpExchange.sendResponseHeaders(200, sendToClient.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(sendToClient.getBytes());
        os.close();
    }
}
