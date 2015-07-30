/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.Xeiotos.MythCraft.Tasks;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Chris
 */
public class WarpThruHandler extends BukkitRunnable {
    
    private final MythCraft plugin;
    private final Player player;
    
    private BlockFace currentBlockFace = null;
    private Block oppositeBlock = null;
    
    public WarpThruHandler(MythCraft plugin, Player player, Block oppositeBlock, BlockFace currentBlockFace) {
        this.plugin = plugin;
        this.player = player;
        this.oppositeBlock = oppositeBlock;
        this.currentBlockFace = currentBlockFace;
    }

    @Override
    public void run() {
        if (player.isOnline()) {
            for (int i=0; i<plugin.configManager.getFileConfig(player.getName()).getInt("Skills.Demon.Warp"); i++) {              
                if (oppositeBlock.getType().equals(Material.AIR)) {
                    player.playEffect(player.getLocation(), Effect.SMOKE, 10);
                    if (currentBlockFace.equals(BlockFace.UP)) {
                        player.teleport(new Location(player.getWorld(), oppositeBlock.getX() + 0.5, oppositeBlock.getY() + 1, oppositeBlock.getZ() + 0.5, player.getLocation().getYaw(), player.getLocation().getPitch()));
                    } else {
                        player.teleport(new Location(player.getWorld(), oppositeBlock.getX() + 0.5, oppositeBlock.getY() - 1, oppositeBlock.getZ() + 0.5, player.getLocation().getYaw(), player.getLocation().getPitch()));
                    }
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, (plugin.randomGenerator.nextFloat() + 0.5f));
                    player.playEffect(player.getLocation(), Effect.SMOKE, 10);
                    plugin.util.addXP(player, -10);
                    this.cancel();
                    break;
                }
                
                oppositeBlock = oppositeBlock.getRelative(currentBlockFace);
            }
        } else {
            this.cancel();
        }
    }
}