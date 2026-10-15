package com.romiken381.enchanteddragoneggmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

public class DragonEggItem extends Item {
    private int currentPhase = 0;
    private long lastPhaseChangeTime = 0;
    private static final long PHASE_COOLDOWN = 15000; // 15 seconds in milliseconds

    public DragonEggItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastPhaseChangeTime >= PHASE_COOLDOWN) {
                currentPhase = (currentPhase + 1) % 4;
                lastPhaseChangeTime = currentTime;
                applyPhaseEffects(player);
                player.sendSystemMessage(Component.literal("Phase changed to: " + (currentPhase + 1)));
            } else {
                player.sendSystemMessage(Component.literal("Phase change is on cooldown. Please wait.")));
            }
        }

        return InteractionResultHolder.success(stack);
    }

    private void applyPhaseEffects(Player player) {
        // Clear existing effects
        player.removeAllEffects();

        switch (currentPhase) {
            case 0: // Phase 1
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 1)); // Speed II
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 1)); // Resistance II
                player.setHealth(60.0f); // 30 hearts
                break;
            case 1: // Phase 2
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 7)); // Speed VIII
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, 1)); // Strength II
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 0)); // Resistance I
                player.setHealth(14.0f); // 7 hearts
                break;
            case 2: // Phase 3
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 0)); // Speed I
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, 9)); // Strength X
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 0)); // Resistance I
                player.setHealth(20.0f); // 10 hearts
                // No weapon attack cooldown
                // Axe hits instantly destroy equipped enemy armor
                break;
            case 3: // Phase 4
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 2)); // Speed III
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 0)); // Resistance I
                player.setHealth(200.0f); // 100 hearts
                // Player model and physical size 10 times normal
                break;
        }
    }
}