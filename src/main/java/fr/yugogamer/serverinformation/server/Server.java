package fr.yugogamer.serverinformation.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.yugogamer.serverinformation.config.Config;
import fr.yugogamer.serverinformation.server.handler.MinecraftHandler;
import fr.yugogamer.serverinformation.server.handler.PlayerList;
import fr.yugogamer.serverinformation.server.handler.SendMessage;
import fr.yugogamer.serverinformation.server.handler.Status;
import fr.yugogamer.serverinformation.server.handler.admin.ExecuteCommande;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private MinecraftServer minecraftServer;

    private int port;

    public Server(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        this.port = Config.SERVER_PORT.get();
        System.out.println("Server Port :"+ Config.SERVER_PORT.get());

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(this.port), 0);
            System.out.println("HTTP server started");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HTTP server error");
        }
        server.createContext("/status", new Status(this.minecraftServer));
        server.createContext("/playerlist", new PlayerList(this.minecraftServer));
        server.createContext("/sendmessage", new SendMessage(this.minecraftServer));
        if (Config.ADMIN_API.get())
        {
            server.createContext("/admin/execute", new ExecuteCommande(this.minecraftServer));
        }
        server.start();
    }
}
