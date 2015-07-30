package com.gmail.Xeiotos.MythCraft.StatusEffects;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Xeiotos
 */
public class SunBurn extends StatusEffect {

    
    public SunBurn(MythCraftPlayer mythCraftPlayer) {
        super(mythCraftPlayer, "SunBurn");
    }
    
    @Override
    public void run() {
        if (mythCraftPlayer.isVampire()) {
            World world = player.getWorld();


            boolean fireres;

            if (player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) { //If player is resistant to fire
                fireres = true;
            } else {
                fireres = false;
            }


            //Burn vampires

            Block playerBlock = player.getLocation().getBlock().getRelative(BlockFace.UP); //determines the block at (in) the player

            if (plugin.util.getSunLight(world, playerBlock) > 8) {
                if ((player.getFireTicks() <= 0) && (!fireres) && (!world.hasStorm() || playerBlock.getBiome() == Biome.DESERT)) {
                    if (!plugin.util.isWater(player.getLocation().getBlock())) {
                        player.setFireTicks(Integer.MAX_VALUE);
                    }
                }
            } else if (player.getFireTicks() >= 1000) {
                player.setFireTicks(40);
            }
            
        } else {
            this.cancel();
        }
    }
    
    @Override
    public void removeEffect() {
        this.cancel();
    }
}
