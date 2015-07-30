package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

public class EntityTameListener implements Listener {
 
    private final MythCraft plugin;
    
    public EntityTameListener(MythCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityTame(EntityTameEvent event) {
        Entity mob = event.getEntity();
        if (mob instanceof Horse) {
            Horse horse = (Horse) mob;
            Player player = (Player) event.getOwner();
            MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
            if (mythCraftPlayer.isVampire()) {
                if (!(horse.getVariant() == Horse.Variant.UNDEAD_HORSE || horse.getVariant() == Horse.Variant.SKELETON_HORSE || horse.getVariant() == Horse.Variant.MULE) && !horse.isDead()) {
                    if (plugin.randomGenerator.nextInt(2) == 0) {
                        horse.setVariant(Horse.Variant.UNDEAD_HORSE);
                    } else {
                        horse.setVariant(Horse.Variant.SKELETON_HORSE);
                    }
                }
            }
        }
    }
}
