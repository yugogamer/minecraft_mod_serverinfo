package fr.yugogamer.serverinformation.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.yugogamer.serverinformation.server.handler.MinecraftHandler;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private MinecraftServer minecraftServer;

    public Server(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/test", new MinecraftHandler(minecraftServer));
        server.start();
    }
}
