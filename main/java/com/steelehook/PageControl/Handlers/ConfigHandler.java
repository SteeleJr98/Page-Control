package com.steelehook.PageControl.Handlers;

import com.steelehook.PageControl.PageControl;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {


    public static Configuration configuration;

    public static int checkTimer = 5;

    public static void init(String configDir) {

        if (configuration == null) {
            File path = new File(configDir + "/" + PageControl.MODID + ".cfg");
            configuration = new Configuration(path);
            loadConfig();
        }
    }

    public static void loadConfig() {
        checkTimer = configuration.getInt("a", Configuration.CATEGORY_GENERAL, 5, 1, 20, "Seconds the Online Detector Checks For Its Owner(s)");
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if(event.modID.equalsIgnoreCase(PageControl.MODID)) {
            loadConfig();
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
