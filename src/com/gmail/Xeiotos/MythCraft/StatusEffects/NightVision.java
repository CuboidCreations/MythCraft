package com.gmail.Xeiotos.MythCraft.StatusEffects;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Xeiotos
 */
public class NightVision extends StatusEffect {

    public NightVision(MythCraftPlayer mythCraftPlayer) {
        super(mythCraftPlayer, "NightVision");
    }

    @Override
    public void run() {
        if (mythCraftPlayer.isVampire()) {

            World world = player.getWorld();

            if (mythCraftPlayer.getAutoNightVision()) {
                Block playerBlock = player.getLocation().getBlock().getRelative(BlockFace.UP); //determines the block at (in) the player

                boolean nivi;

                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) { //If player has night vision potion
                    nivi = true;
                } else {
                    nivi = false;
                }

                if (plugin.util.getSunLight(world, playerBlock) > 8) {
                    if (nivi) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    }
                } else if (plugin.util.getSunLight(world, playerBlock) <= 8) {
                    if (!nivi) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true), true);
                    }
                }
            }

        }
    }
    
    @Override
    public void removeEffect() {
        this.cancel();
    }
}
