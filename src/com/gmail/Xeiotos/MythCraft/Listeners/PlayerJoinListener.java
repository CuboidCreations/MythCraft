package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.Tasks.PlayerInitHandler;
import com.gmail.Xeiotos.MythCraft.Utilities.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinListener implements Listener {
 
    private final MythCraft plugin;
    
    public PlayerJoinListener(MythCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        plugin.configManager.loadConfigFiles(new ConfigManager.ConfigPath(player.getName(), "playerConfig.yml", "players/" + player.getName() + ".yml"));
        PlayerInitHandler playerInitHandler = new PlayerInitHandler(plugin, player);
        playerInitHandler.runTask(plugin);
    }
}
