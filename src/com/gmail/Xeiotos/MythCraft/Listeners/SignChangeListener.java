package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import com.gmail.Xeiotos.MythCraft.Utilities.ConfigManager;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {
 
    private final MythCraft plugin;
    
    public SignChangeListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        Block block = event.getBlock();
        if (event.getLine(0).equalsIgnoreCase("[Altar]") && !(mythCraftPlayer.hasAltar())) {
            mythCraftPlayer.setAltar(block.getX(), block.getY(), block.getZ(), block.getWorld().getName());
            event.setLine(1, player.getName());
            player.sendMessage(ChatColor.DARK_GREEN + "Altar constructed!");
            String previousGod = mythCraftPlayer.getGod();
            mythCraftPlayer.setGod(event.getLine(2));
            if (event.getLine(2).length() != 0) {
                player.sendMessage(ChatColor.GREEN + "Now worshipping " + event.getLine(2));
            }
            if (plugin.util.isOnline(previousGod) && !previousGod.equals("no")) {
                MythCraftPlayer mythCraftPlayerPreviousGod = MythCraftPlayer.getMythCraftPlayer(previousGod);
                mythCraftPlayerPreviousGod.removeWorshipper(player.getName());
                mythCraftPlayerPreviousGod.getPlayer().sendMessage(ChatColor.RED + "You are no longer being worshipped by " + player.getName());
            } else if (!previousGod.equals("no")) {
                plugin.configManager.loadConfigFiles(new ConfigManager.ConfigPath(previousGod, "playerConfig.yml", "players/" + previousGod + ".yml"));
                plugin.configManager.getFileConfig(previousGod).set("PersistentData.Worshippers", plugin.configManager.getFileConfig(previousGod).getInt("PersistentData.Worshippers") - 1);
                List list = plugin.configManager.getFileConfig(previousGod).getStringList("PersistentData.WorshipperNames");
                list.remove(player.getName());
                plugin.configManager.getFileConfig(previousGod).set("PersistentData.WorshipperNames", list);
                plugin.util.reConfig(previousGod);
                plugin.configManager.unloadConfig(previousGod);
            }
            if (plugin.util.isOnline(event.getLine(2)) && event.getLine(2).length() != 0) {
                MythCraftPlayer mythCraftPlayerNewGod = MythCraftPlayer.getMythCraftPlayer(event.getLine(2));
                mythCraftPlayerNewGod.addWorshipper(player.getName());
                mythCraftPlayerNewGod.getPlayer().sendMessage(ChatColor.GREEN + "You are now being worshipped by " + player.getName());
            } else if (event.getLine(2).length() != 0) {
                plugin.configManager.loadConfigFiles(new ConfigManager.ConfigPath(event.getLine(2), "playerConfig.yml", "players/" + event.getLine(2) + ".yml"));
                plugin.configManager.getFileConfig(event.getLine(2)).set("PersistentData.Worshippers", plugin.configManager.getFileConfig(event.getLine(2)).getInt("PersistentData.Worshippers") + 1);
                List list = plugin.configManager.getFileConfig(event.getLine(2)).getStringList("PersistentData.WorshipperNames");
                list.add(player.getName());
                plugin.configManager.getFileConfig(event.getLine(2)).set("PersistentData.WorshipperNames", list);
                plugin.util.reConfig(event.getLine(2));
                plugin.configManager.unloadConfig(event.getLine(2));
            }
        } else if (event.getLine(0).equalsIgnoreCase("[Altar]")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You already constructed an altar at X: " + mythCraftPlayer.getAltarX() + " Y: " + mythCraftPlayer.getAltarY() + " Z: " + mythCraftPlayer.getAltarZ() + " in world " + mythCraftPlayer.getAltarZ());
        }
    }
}
