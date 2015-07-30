package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player defender = (Player) event.getEntity();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(defender);
        if (!(event instanceof EntityDamageByEntityEvent)) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.LAVA) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK) || event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (mythCraftPlayer.getRace().equalsIgnoreCase("Demon")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
