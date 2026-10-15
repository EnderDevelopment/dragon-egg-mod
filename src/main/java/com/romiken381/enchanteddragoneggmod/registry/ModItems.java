package com.romiken381.enchanteddragoneggmod.registry;

import com.romiken381.enchanteddragoneggmod.EnchantedDragonEggMod;
import com.romiken381.enchanteddragoneggmod.item.DragonEggItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Item DRAGON_EGG = new DragonEggItem();

    public static void register() {
        Registry.register(Registry.ITEM, new ResourceLocation(EnchantedDragonEggMod.MOD_ID, "dragon_egg"), DRAGON_EGG);
    }
}