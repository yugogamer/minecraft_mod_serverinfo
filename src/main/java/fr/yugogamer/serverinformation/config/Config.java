package fr.yugogamer.serverinformation.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static ForgeConfigSpec.BooleanValue SERVER_ACTIVATE;
    public static ForgeConfigSpec.IntValue SERVER_PORT;
    public static ForgeConfigSpec.ConfigValue ID_ADMIN;
    public static ForgeConfigSpec.BooleanValue ADMIN_API;

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    static {

        COMMON_BUILDER.comment("General settings").push("Server");
        COMMON_BUILDER.pop();

        setupServerConfig();



        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static void setupServerConfig() {
        COMMON_BUILDER.comment("Server configuration").push("server_configuration");
        SERVER_ACTIVATE = COMMON_BUILDER.comment("API is active ").define("SERVER_ACTIVATE", true);
        SERVER_PORT = COMMON_BUILDER.comment("Server port")
                .defineInRange("SERVER_PORT", 80, 0, Integer.MAX_VALUE);
        ID_ADMIN = COMMON_BUILDER.comment("Id for acces admin part of the api").define("ID_ADMIN", generateDefaultValue());
        ADMIN_API = COMMON_BUILDER.comment("True for activate").define("ADMIN_API",false);

        COMMON_BUILDER.pop();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        System.out.println(configData);
        spec.setConfig(configData);
    }

    public static String generateDefaultValue()
    {
        int n = 25;

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }


}