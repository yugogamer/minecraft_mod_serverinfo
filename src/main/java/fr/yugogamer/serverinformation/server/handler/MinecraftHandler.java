package fr.yugogamer.serverinformation.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;

public class MinecraftHandler implements HttpHandler {
    private MinecraftServer minecraftServer;

    public MinecraftHandler(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
