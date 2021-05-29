package fr.yugogamer.serverinformation.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.yugogamer.serverinformation.server.handler.MinecraftHandler;
import fr.yugogamer.serverinformation.server.handler.Status;
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
            System.out.println("HTTP server started");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HTTP server error");
        }
        server.createContext("/status", new Status(this.minecraftServer));
        server.createContext("/playerlist", new Status(this.minecraftServer));
        server.start();
    }
}
