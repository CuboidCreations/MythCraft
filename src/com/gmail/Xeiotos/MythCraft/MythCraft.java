package com.gmail.Xeiotos.MythCraft;

import com.gmail.Xeiotos.MythCraft.Listeners.*;
import com.gmail.Xeiotos.MythCraft.Utilities.MythCraftCommandExecutor;
import com.gmail.Xeiotos.MythCraft.Utilities.Util;
import com.gmail.Xeiotos.MythCraft.Utilities.ConfigManager;
import com.gmail.Xeiotos.MythCraft.Tasks.Burn;
import com.gmail.Xeiotos.MythCraft.StatusEffects.StatusEffect;
import com.gmail.Xeiotos.MythCraft.Tasks.PlayerInitHandler;
import com.gmail.Xeiotos.MythCraft.Tasks.WarpThruHandler;
import com.gmail.Xeiotos.MythCraft.Utilities.ConfigManager.ConfigPath;
import com.gmail.Xeiotos.MythCraft.Utilities.Persistance;
/*import com.sk89q.worldguard.bukkit.WorldGuardPlugin;*/
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.PluginManager;
/*import org.bukkit.plugin.Plugin;*/


/**
 *
 * @author Xeiotos
 */
public final class MythCraft extends JavaPlugin {
    
    public ConfigManager configManager;
    public Persistance persistance;
    public Burn burn;
    public WarpThruHandler warpThruHandler;
    public StatusEffect statusEffect;
    public Util util;
    public FileConfiguration pluginConfig;
    public FileConfiguration emcConfig;
    public FileConfiguration blocks;
    public Random randomGenerator = new Random();
    
    @Override
    public void onEnable(){
        
        //Some init functions
        
        configManager = new ConfigManager(this);
        persistance = new Persistance(this);
        getPluginConfig();
        util = new Util(this);
        initPresentPlayers();

        //Listeners
        PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new BlockBreakListener(this), this);
        manager.registerEvents(new BlockDamageListener(this), this);
        manager.registerEvents(new BlockPhysicsListener(this), this);
        manager.registerEvents(new CraftPrepareListener(), this);
        manager.registerEvents(new EntityDamageByUndeadListener(this), this);
        manager.registerEvents(new EntityDamageListener(), this);
        manager.registerEvents(new EntityTameListener(this), this);
        manager.registerEvents(new EntityTargetListener(), this);
        manager.registerEvents(new InventoryCreativeEventListener(this), this);
        manager.registerEvents(new PlayerFlyToggleListener(), this);
        manager.registerEvents(new PlayerInteractEntityListener(this), this);
        manager.registerEvents(new PlayerInteractListener(this), this);
        manager.registerEvents(new PlayerItemConsumeListener(this), this);
        manager.registerEvents(new PlayerItemDropListener(this), this);
        manager.registerEvents(new PlayerJoinListener(this), this);
        manager.registerEvents(new PlayerLeaveListener(this), this);
        manager.registerEvents(new PlayerMoveListener(this), this);
        manager.registerEvents(new PlayerToggleSneakListener(this), this);
        manager.registerEvents(new PlayerToggleSprintListener(this), this);
        manager.registerEvents(new PotionSplashListener(), this);
        manager.registerEvents(new SignChangeListener(this), this);
        
        burn = new Burn(this);
        
        //Tasks
        
        //Burn undead horses, 1/2 seconds, 1 second delay
        
        if (configManager.getFileConfig("pluginConfig").getBoolean("config.burnUndeadHorses")) {
            burn.runTaskTimer(this, 20L, 10L);
        }
        
        //Recepies
        ShapelessRecipe grinderRecipe = new ShapelessRecipe(new ItemStack(Material.POTION, 1, (short) 16495)).addIngredient(Material.POTION, 4335).addIngredient(Material.SULPHUR);
        getServer().addRecipe(grinderRecipe);
        
        //Commands
        getCommand("mythcraft").setExecutor(new MythCraftCommandExecutor(this));
        
    }
 
    @Override
    public void onDisable() {
        configManager.saveAllConfigs(true);
        configManager.unloadAllConfigs();
    }
    
    public void initPresentPlayers() { //Players may already be present
       
        for(Player player:getServer().getOnlinePlayers()){
            configManager.loadConfigFiles(
                new ConfigPath(player.getName(), "playerConfig.yml", "players/" + player.getName() + ".yml")
            );
            
            PlayerInitHandler playerInitHandler = new PlayerInitHandler(this, player);
            playerInitHandler.runTask(this);
        }
    }
    
    public FileConfiguration getPluginConfig() {
        
        configManager.loadConfigFiles(
            new ConfigPath("pluginConfig", "config.yml", "config.yml"),
            new ConfigPath("blocks","blocks.yml","blocks.yml"),
            new ConfigPath("emcConfig","emcConfig.yml","emcConfig.yml")
        );
        
        blocks = configManager.getFileConfig("blocks");
        pluginConfig = configManager.getFileConfig("pluginConfig");     
        return pluginConfig;
    }
    
    /*    private WorldGuardPlugin getWorldGuard() {
     * Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
     * 
     * // WorldGuard may not be loaded
     * if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
     * return null;
     * }
     * 
     * return (WorldGuardPlugin) plugin;
     * }*/
}