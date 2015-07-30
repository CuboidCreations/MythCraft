package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 *
 * @author Xeiotos
 */
public class PlayerInteractEntityListener implements Listener {
 
    private final MythCraft plugin;
    
    public PlayerInteractEntityListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        Entity mob = event.getRightClicked();
        int damage;
        if (mythCraftPlayer.isVampire()) {
            if ((mob instanceof Animals || mob instanceof Squid) && !(mob instanceof Horse) && player.getFoodLevel() < 20) {
                Creature living = (Creature) mob;
                if (!living.isDead()) {
                    damage = plugin.randomGenerator.nextInt((int) living.getMaxHealth()) + 1;
                    living.damage(damage, player);
                    player.setFoodLevel(player.getFoodLevel() + damage);
                    player.setSaturation((float) (player.getSaturation()*1.6 + damage));
                }
            }   
        }
    }
}
