package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerToggleSprintListener implements Listener {
 
    private final MythCraft plugin;
    
    public PlayerToggleSprintListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onSprint(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        if (mythCraftPlayer.isVampire()) {
            if (event.isSprinting() == true) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, mythCraftPlayer.getCurrentVampireSpeed(), true), true);
            } else {
                player.removePotionEffect(PotionEffectType.SPEED);
            }
        }
        if (mythCraftPlayer.getRace().equalsIgnoreCase("Mertouched")) {
            if (mythCraftPlayer.isSprintSwimming()) {
                mythCraftPlayer.setSprintSwimming(false);
            } else {
                mythCraftPlayer.setSprintSwimming(true);
            }
        }
    }
}
