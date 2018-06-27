package net.darkhax.nightmares;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    public static Configuration config;
    
    public static float nightmareChance;

    public ConfigurationHandler (File cfgFile) {

        config = new Configuration(cfgFile);
        this.syncConfigData();
    }

    private void syncConfigData () {

        nightmareChance = config.getFloat("attackChance", Configuration.CATEGORY_GENERAL, 0.05f, 0, 1f, "The chance that a nightmare will happen when the player sleeps.");
        		
        if (config.hasChanged()) {

            config.save();
        }
    }
}