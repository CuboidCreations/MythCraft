package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import java.util.Collection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionSplashListener implements Listener {
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPotionSplash(PotionSplashEvent event) {
        if (event.getPotion().getItem().getDurability() == 16495) {
            Collection<LivingEntity> affectedEntities = event.getAffectedEntities();
            for (Entity entity : affectedEntities) {
                if (entity instanceof Player && !entity.isDead()) {
                    Player player = (Player) entity;
                    MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
                    if (mythCraftPlayer.hasStatusEffect("bloodrot")) {
                        mythCraftPlayer.removeStatusEffect("bloodrot");
                        mythCraftPlayer.setBloodRotTimer(12000L);
                    }
                    if (mythCraftPlayer.isVampire()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 1));
                    }
                } else if ((entity instanceof Zombie || entity instanceof PigZombie || entity instanceof Skeleton) && !entity.isDead()) {
                    Monster undead = (Monster) entity;
                    undead.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
                }
            }
        }
    }
}
