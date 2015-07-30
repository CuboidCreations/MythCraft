package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener {

    private final MythCraft plugin;

    public PlayerMoveListener(MythCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        Block playerBlock = player.getLocation().getBlock().getRelative(BlockFace.UP);
        if (mythCraftPlayer.isSwimming() && plugin.util.isWater(playerBlock)) {
            Vector velocity = new Vector();
            velocity = player.getLocation().getDirection().multiply(0.5);
            player.setVelocity(velocity);
        } else if (mythCraftPlayer.isSprintSwimming() && plugin.util.isWater(playerBlock)) {
            Vector velocity = new Vector();
            velocity = player.getLocation().getDirection().multiply(0.65);
            player.setVelocity(velocity);
        }
    }
}
