package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerFlyToggleListener implements Listener {
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerFlyToggle(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        if (mythCraftPlayer.getRace().equalsIgnoreCase("Valkyrie") && player.getGameMode() != GameMode.CREATIVE) {
            if (event.isFlying() == true && player.getTotalExperience() >= 10) {
                mythCraftPlayer.castSpell("fly");
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);
            } else if (player.getTotalExperience() < 10) {
                event.setCancelled(true);
            }
        }
    }
}
