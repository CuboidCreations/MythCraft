package com.gmail.Xeiotos.MythCraft.StatusEffects;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Xeiotos
 */
public class WaterVision extends StatusEffect {

    public WaterVision(MythCraftPlayer mythCraftPlayer) {
        super(mythCraftPlayer, "WaterVision");
    }

    @Override
    public void run() {

        if (mythCraftPlayer.getRace().equalsIgnoreCase("Mertouched")) {

            boolean nivi;

            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) { //If player has night vision
                nivi = true;
            } else {
                nivi = false;
            }

            Block playerBlock = player.getLocation().getBlock().getRelative(BlockFace.UP);

            if (plugin.util.isWater(playerBlock) && !nivi) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true), true);
            } else if (playerBlock.getType() == Material.AIR && nivi) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
        }
    }
    
    @Override
    public void removeEffect() {
        this.cancel();
    }
}