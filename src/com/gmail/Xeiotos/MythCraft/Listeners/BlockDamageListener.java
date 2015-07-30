package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlockDamageListener implements Listener {
 
    private final MythCraft plugin;
    
    public BlockDamageListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerBlockDamage(BlockDamageEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        if (mythCraftPlayer.getRace().equalsIgnoreCase("mertouched") && !event.getInstaBreak()) {
            Block playerBlock = player.getLocation().getBlock().getRelative(BlockFace.UP);
            if (plugin.util.isWater(playerBlock)) {
                final Block block = event.getBlock();
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {
                        block.breakNaturally();
                    }
                }, 40L);
            }
        }
    }
}
