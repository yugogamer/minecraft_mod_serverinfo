package fr.yugogamer.serverinformation.server.handler;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import fr.yugogamer.serverinformation.config.Config;
import net.minecraft.server.MinecraftServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class GetServerImage  extends MinecraftHandler {
    public GetServerImage(MinecraftServer minecraftServer) {
        super(minecraftServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        File file = new File("server-icon.png");
        System.out.println(file.getAbsoluteFile());
        //File file = this.minecraftServer.getWorldIconFile();
        if (file == null)
        {
            System.out.println(file.getAbsoluteFile());
            file = new File("server-icon.png");
            System.out.println(file.getAbsoluteFile());
        }

        httpExchange.sendResponseHeaders(200, file.length());
        httpExchange.getResponseHeaders().add("Content-Type", "image/png");

        OutputStream outputStream=httpExchange.getResponseBody();
        Files.copy(file.toPath(), outputStream);
        outputStream.close();
    }
}