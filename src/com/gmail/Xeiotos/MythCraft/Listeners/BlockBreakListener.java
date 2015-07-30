package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import com.gmail.Xeiotos.MythCraft.Utilities.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
 
    private final MythCraft plugin;
    
    public BlockBreakListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        Block block = event.getBlock();
        if (block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST) {
            Sign sign = (Sign) block.getState();
            if (sign.getLine(0).equalsIgnoreCase("[Altar]")) {
                if (plugin.util.isOnline(sign.getLine(1))) {
                    mythCraftPlayer.setAltar(false);
                    mythCraftPlayer.getPlayer().sendMessage(ChatColor.RED + "Your altar was destroyed!");
                } else {
                    plugin.configManager.loadConfigFiles(new ConfigManager.ConfigPath(sign.getLine(1), "playerConfig.yml", "players/" + sign.getLine(1) + ".yml"));
                    plugin.configManager.getFileConfig(sign.getLine(1)).set("PersistentData.Altar", false);
                    plugin.configManager.saveConfig(sign.getLine(1), true);
                    plugin.configManager.unloadConfig(sign.getLine(1));
                }
            }
        }
    }
}