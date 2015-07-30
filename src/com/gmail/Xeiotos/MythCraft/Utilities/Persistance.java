package com.gmail.Xeiotos.MythCraft.Utilities;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import java.util.ArrayList;
import org.bukkit.entity.Player;

/**
 *
 * @author Xeiotos
 */
public class Persistance {

    private final MythCraft plugin;

    public Persistance(MythCraft plugin) {
        this.plugin = plugin;
    }

    public void savePlayerToFile(Player player) {
        savePlayerToFile(MythCraftPlayer.getMythCraftPlayer(player));
    }

    public void savePlayerToFile(MythCraftPlayer mythCraftPlayer) {
        String playerName = mythCraftPlayer.getPlayer().getName();

        plugin.configManager.getFileConfig(playerName).set("Player.Race", mythCraftPlayer.getRace());
        plugin.configManager.getFileConfig(playerName).set("Player.Health", mythCraftPlayer.getMaxHealth());
        plugin.configManager.getFileConfig(playerName).set("Skills.Vampire.Speed", mythCraftPlayer.getMaxVampireSpeed());
        plugin.configManager.getFileConfig(playerName).set("Skills.Demon.Warp", mythCraftPlayer.getMaxWarp());
        plugin.configManager.getFileConfig(playerName).set("PersistentData.Vampire.CurrentSpeed", mythCraftPlayer.getCurrentVampireSpeed());
        plugin.configManager.getFileConfig(playerName).set("PersistentData.Demon.CurrentWarp", mythCraftPlayer.getCurrentWarp());
        plugin.configManager.getFileConfig(playerName).set("PersistentData.BloodRotTimer", mythCraftPlayer.getBloodRotTimer());
        plugin.configManager.getFileConfig(playerName).set("PersistentData.PolyphagiaTimer", mythCraftPlayer.getPolyphagiaTimer());
        plugin.configManager.getFileConfig(playerName).set("Status.isVampire", mythCraftPlayer.isVampire());
        if (mythCraftPlayer.getStatusEffects() != null) {
            plugin.configManager.getFileConfig(playerName).set("Status.StatusEffects", mythCraftPlayer.getStatusEffects());            
        } else {
            plugin.configManager.getFileConfig(playerName).set("Status.StatusEffects", "");
        }
        plugin.configManager.getFileConfig(playerName).set("Skills.NamedSkills", mythCraftPlayer.getSkills());
        plugin.configManager.getFileConfig(playerName).set("PersistentData.WorshipperNames", mythCraftPlayer.getWorshipperNames());
        plugin.configManager.getFileConfig(playerName).set("PersistentData.Altar", mythCraftPlayer.hasAltar());
        plugin.configManager.getFileConfig(playerName).set("PersistentData.Vampire.AutoNightVision", mythCraftPlayer.getAutoNightVision());
        plugin.configManager.getFileConfig(playerName).set("PersistentData.Worshipping", mythCraftPlayer.getGod());
        
        if (mythCraftPlayer.hasAltar()) {
            plugin.configManager.getFileConfig(playerName).set("PersistentData.AltarLocation.X", mythCraftPlayer.getAltarX());
            plugin.configManager.getFileConfig(playerName).set("PersistentData.AltarLocation.Y", mythCraftPlayer.getAltarY());
            plugin.configManager.getFileConfig(playerName).set("PersistentData.AltarLocation.Z", mythCraftPlayer.getAltarZ());
            plugin.configManager.getFileConfig(playerName).set("PersistentData.AltarLocation.World", mythCraftPlayer.getAltarWorld());
        }

        plugin.util.reConfig(playerName);
    }

    public void loadPlayerFromFile(Player player) {
        MythCraftPlayer mythCraftPlayer = new MythCraftPlayer(plugin, player);
        String playerName = player.getName();

        mythCraftPlayer.setRace(plugin.configManager.getFileConfig(playerName).getString("Player.Race"));
        mythCraftPlayer.setMaxHealth(plugin.configManager.getFileConfig(playerName).getDouble("Player.MaxHealth"));
        mythCraftPlayer.setMaxVampireSpeed(plugin.configManager.getFileConfig(playerName).getInt("Skills.Vampire.Speed"));
        mythCraftPlayer.setMaxWarp(plugin.configManager.getFileConfig(playerName).getInt("Skills.Demon.Warp"));
        mythCraftPlayer.setCurrentVampireSpeed(plugin.configManager.getFileConfig(playerName).getInt("PersistentData.Vampire.CurrentSpeed"));
        mythCraftPlayer.setCurrentWarp(plugin.configManager.getFileConfig(playerName).getInt("PersistentData.Demon.CurrentWarp"));
        mythCraftPlayer.setBloodRotTimer(plugin.configManager.getFileConfig(playerName).getLong("PersistentData.BloodRotTimer")); //Important to do this before adding the status effects
        mythCraftPlayer.setPolyphagiaTimer(plugin.configManager.getFileConfig(playerName).getLong("PersistentData.PolyphagiaTimer"));
        mythCraftPlayer.setVampire(plugin.configManager.getFileConfig(playerName).getBoolean("Status.isVampire"));
        mythCraftPlayer.setWorshippers((ArrayList<String>) plugin.configManager.getFileConfig(playerName).getList("PersistentData.WorshipperNames"));
        mythCraftPlayer.setAutoNightVision(plugin.configManager.getFileConfig(playerName).getBoolean("PersistentData.Vampire.AutoNightVision"));
        mythCraftPlayer.setGod(plugin.configManager.getFileConfig(playerName).getString("PersistentData.Worshipping"));
        
        if (plugin.configManager.getFileConfig(playerName).getBoolean("PersistentData.Altar")) {
            mythCraftPlayer.setAltar(
                plugin.configManager.getFileConfig(playerName).getInt("PersistentData.AltarLocation.X"),
                plugin.configManager.getFileConfig(playerName).getInt("PersistentData.AltarLocation.Y"), 
                plugin.configManager.getFileConfig(playerName).getInt("PersistentData.AltarLocation.Z"), 
                plugin.configManager.getFileConfig(playerName).getString("PersistentData.AltarLocation.World")
            );
        }
        
        for (String skill : plugin.configManager.getFileConfig(playerName).getStringList("Skills.NamedSkills")) {
            mythCraftPlayer.addSkill(skill);
        }
        
        for (String se : plugin.configManager.getFileConfig(playerName).getStringList("Status.StatusEffects")) {
            mythCraftPlayer.applyStatusEffect(se);
        }
    }
}