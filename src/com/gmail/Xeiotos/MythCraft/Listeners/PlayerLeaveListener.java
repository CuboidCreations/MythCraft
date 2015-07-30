package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
 
    private final MythCraft plugin;
    
    public PlayerLeaveListener(MythCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.persistance.savePlayerToFile(player);
        plugin.configManager.unloadConfig(player.getName());
        MythCraftPlayer.removePlayer(MythCraftPlayer.getMythCraftPlayer(player));
    }
}
