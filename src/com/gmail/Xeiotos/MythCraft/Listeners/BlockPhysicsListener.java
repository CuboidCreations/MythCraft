package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import com.gmail.Xeiotos.MythCraft.Utilities.ConfigManager;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class BlockPhysicsListener implements Listener {
 
    private final MythCraft plugin;
    
    public BlockPhysicsListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST) {
            Sign sign = (Sign) block.getState();
            org.bukkit.material.Sign signData = (org.bukkit.material.Sign) block.getState().getData();
            Block attachedBlock = block.getRelative(signData.getAttachedFace());
            if (attachedBlock.getType() == Material.AIR && sign.getLine(0).equalsIgnoreCase("[Altar]")) {
                if (plugin.util.isOnline(sign.getLine(1))) {
                    MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(sign.getLine(1));
                    mythCraftPlayer.setAltar(false);
                    mythCraftPlayer.getPlayer().sendMessage(ChatColor.RED + "Your altar was destroyed!");
                } else {
                    plugin.configManager.loadConfigFiles(new ConfigManager.ConfigPath(sign.getLine(1), "playerConfig.yml", "players/" + sign.getLine(1) + ".yml"));
                    plugin.configManager.getFileConfig(sign.getLine(1)).set("PersistentData.Altar", false);
                    plugin.configManager.saveConfig(sign.getLine(1), true);
                    plugin.configManager.unloadConfig(sign.getLine(1));
                }
                if (sign.getLine(2).length() != 0) {
                    if (plugin.util.isOnline(sign.getLine(2))) {
                        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(sign.getLine(2));
                        mythCraftPlayer.removeWorshipper(sign.getLine(1));
                        mythCraftPlayer.getPlayer().sendMessage(ChatColor.RED + "You are no longer being worshipped by " + sign.getLine(1));
                    } else {
                        plugin.configManager.loadConfigFiles(new ConfigManager.ConfigPath(sign.getLine(2), "playerConfig.yml", "players/" + sign.getLine(2) + ".yml"));
                        plugin.configManager.getFileConfig(sign.getLine(2)).set("PersistentData.Worshippers", plugin.configManager.getFileConfig(sign.getLine(2)).getInt("PersistentData.Worshippers") + 1);
                        List list = plugin.configManager.getFileConfig(sign.getLine(2)).getStringList("PersistentData.WorshipperNames");
                        list.add(sign.getLine(1));
                        plugin.configManager.getFileConfig(sign.getLine(2)).set("PersistentData.WorshipperNames", list);
                        plugin.util.reConfig(sign.getLine(2));
                        plugin.configManager.unloadConfig(sign.getLine(2));
                    }
                }
            }
        }
    }
}
