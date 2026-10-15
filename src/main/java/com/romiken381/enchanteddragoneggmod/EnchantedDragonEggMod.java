package com.romiken381.enchanteddragoneggmod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantedDragonEggMod implements ModInitializer {
    public static final String MOD_ID = "enchanteddragoneggmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("EnchantedDragonEggMod has been initialized!");
    }
}