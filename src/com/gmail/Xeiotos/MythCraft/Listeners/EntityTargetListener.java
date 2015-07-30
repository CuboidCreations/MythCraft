package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTargetListener implements Listener {
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityTarget(EntityTargetEvent event) {
        Entity entity = event.getEntity();
        Entity target = event.getTarget();
        EntityTargetEvent.TargetReason reason = event.getReason();
        if (entity instanceof Zombie || entity instanceof Skeleton || entity instanceof Skeleton) {
            if (target instanceof Player && (reason == EntityTargetEvent.TargetReason.CLOSEST_PLAYER || reason == EntityTargetEvent.TargetReason.RANDOM_TARGET || reason == EntityTargetEvent.TargetReason.FORGOT_TARGET)) {
                Player player = (Player) target;
                MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
                if (mythCraftPlayer.hasSkill("friendofdead")) {
                    event.setCancelled(true);
                }
            }
        } else if (entity instanceof Monster) {
            if (target instanceof Player && (reason == EntityTargetEvent.TargetReason.CLOSEST_PLAYER || reason == EntityTargetEvent.TargetReason.RANDOM_TARGET || reason == EntityTargetEvent.TargetReason.FORGOT_TARGET)) {
                Player player = (Player) target;
                MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
                if (mythCraftPlayer.hasSkill("friendofdread")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
