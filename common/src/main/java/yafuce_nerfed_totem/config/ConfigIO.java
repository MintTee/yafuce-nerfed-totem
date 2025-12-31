package yafuce_nerfed_totem.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;

public class ConfigIO {

    private static final File CONFIG_FILE = new File("config/yafuce_nerfed_totem.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Logger LOGGER;

    public static ModConfig CURRENT = new ModConfig();

    // Call this once at startup to set your logger
    public static void setLogger(Logger logger) {
        LOGGER = logger;
    }

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                CURRENT = GSON.fromJson(reader, ModConfig.class);
            } catch (IOException e) {
                if (LOGGER != null) LOGGER.error("Failed to load config file: {}", CONFIG_FILE.getName(), e);
            }
        } else {
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(CURRENT, writer);
        } catch (IOException e) {
            if (LOGGER != null) LOGGER.error("Failed to save config file: {}", CONFIG_FILE.getName(), e);
        }
    }
}