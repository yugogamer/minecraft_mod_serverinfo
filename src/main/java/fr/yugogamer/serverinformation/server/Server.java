package fr.yugogamer.serverinformation.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.yugogamer.serverinformation.config.Config;
import fr.yugogamer.serverinformation.server.handler.*;
import fr.yugogamer.serverinformation.server.handler.admin.ExecuteCommande;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static boolean isOnline = true;
    private MinecraftServer minecraftServer;

    private int port;

    public Server(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        this.port = Config.SERVER_PORT.get();
        System.out.println("Server Port :"+ Config.SERVER_PORT.get());

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(this.port), 0);
            server.createContext("/status", new Status(this.minecraftServer));
            server.createContext("/playerlist", new PlayerList(this.minecraftServer));
            server.createContext("/sendmessage", new SendMessage(this.minecraftServer));
            server.createContext("/image", new GetServerImage(this.minecraftServer));
            server.createContext("/admin", new AdminEnable(this.minecraftServer));
            if (Config.ADMIN_API.get())
            {
                server.createContext("/admin/execute", new ExecuteCommande(this.minecraftServer));
            }
            server.start();
            System.out.println("HTTP server started");
        } catch (IOException e) {
            isOnline = false;
            e.printStackTrace();
            System.out.println("HTTP server error");
        }
    }
}
