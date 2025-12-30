package me.yafuce.yafuce_nerfed_totem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import me.yafuce.yafuce_nerfed_totem.config.ConfigIO;

public class main implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("yafuce's Nerfed Totem");

    @Override
    public void onInitialize() {
        ConfigIO.load();
        LOGGER.info("yafuce's Nerfed Totems initializing");
    }
}
