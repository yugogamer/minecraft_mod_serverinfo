package fr.yugogamer.serverinformation.server.handler;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SendMessage extends MinecraftHandler {
    public SendMessage(MinecraftServer minecraftServer) {
        super(minecraftServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        JsonObject response = new JsonObject();
        String query = httpExchange.getRequestURI().getQuery();
        Map<String,String> querys = this.queryToMap(query);
        if (querys.get("username") != null && querys.get("message") != null)
        {
            List<ServerPlayerEntity> litPlayer;
            litPlayer = this.minecraftServer.func_241755_D_().getPlayers();
            String message = querys.get("username") + " : " + querys.get("message");
            for (ServerPlayerEntity playerEntity : litPlayer) {
                if (querys.get("player") != null)
                {
                    if (playerEntity.getDisplayName().getString().equals(querys.get("player")))
                    {
                        playerEntity.sendMessage(new StringTextComponent(message), UUID.randomUUID());
                        response.addProperty("status","succes");
                        response.addProperty("playerGetMessage",true);
                    }
                }else
                {
                    playerEntity.sendMessage(new StringTextComponent(message), UUID.randomUUID());
                }
            }
        }else
        {
            response.addProperty("status","error");
            response.addProperty("reason","query missing");
        }
        String sendToClient = response.toString();
        httpExchange.sendResponseHeaders(200, sendToClient.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(sendToClient.getBytes());
        os.close();
    }
}
