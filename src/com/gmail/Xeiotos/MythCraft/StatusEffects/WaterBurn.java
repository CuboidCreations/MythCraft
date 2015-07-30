package com.gmail.Xeiotos.MythCraft.StatusEffects;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Xeiotos
 */

public class WaterBurn extends StatusEffect {

    public WaterBurn(MythCraftPlayer mythCraftPlayer) {
        super(mythCraftPlayer, "WaterBurn");
    }

    @Override
    public void run() {

        if (mythCraftPlayer.getRace().equalsIgnoreCase("Demon")) {
            boolean waterbreath;

            if (player.hasPotionEffect(PotionEffectType.WATER_BREATHING)) { //If player has water breathing
                waterbreath = true;
            } else {
                waterbreath = false;
            }

            Block playerBlock = player.getLocation().getBlock();

            if (plugin.util.isWater(playerBlock) && !waterbreath) {
                player.damage(4);
            }
        }
    }
    
    @Override
    public void removeEffect() {
        this.cancel();
    }
}