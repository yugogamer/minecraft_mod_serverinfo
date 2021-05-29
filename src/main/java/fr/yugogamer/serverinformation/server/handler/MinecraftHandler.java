package fr.yugogamer.serverinformation.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MinecraftHandler implements HttpHandler {
    protected MinecraftServer minecraftServer;

    public MinecraftHandler(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }

    protected Map<String, String> queryToMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
