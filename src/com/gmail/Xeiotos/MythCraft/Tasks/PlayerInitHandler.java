package com.gmail.Xeiotos.MythCraft.Tasks;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Xeiotos
 */
public class PlayerInitHandler extends BukkitRunnable {
    
    private final MythCraft plugin;
    private final Player player;
    private MythCraftPlayer mythCraftPlayer;
    
    public PlayerInitHandler(MythCraft plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }
    
    @Override
    public void run(){
        
        plugin.persistance.loadPlayerFromFile(player);
        
        mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        
        if (mythCraftPlayer.isVampire()) {
            player.setMaximumAir(Integer.MAX_VALUE - 20);
            mythCraftPlayer.applyStatusEffect("nightvision");
            mythCraftPlayer.applyStatusEffect("sunburn");
        }
        
        if (mythCraftPlayer.getRace().equalsIgnoreCase("Mertouched")) {
            player.setMaximumAir(Integer.MAX_VALUE - 20);
            mythCraftPlayer.applyStatusEffect("watervision");
        } else if (mythCraftPlayer.getRace().equalsIgnoreCase("Valkyrie")) {
            player.setAllowFlight(true);
        } else if (mythCraftPlayer.getRace().equalsIgnoreCase("Demon")) {
            mythCraftPlayer.applyStatusEffect("waterburn");
        }     
        
        player.setMaxHealth(20 + (mythCraftPlayer.getWorshippers()*5));
        player.setHealthScaled(true);
        
        this.cancel();
    }
}
