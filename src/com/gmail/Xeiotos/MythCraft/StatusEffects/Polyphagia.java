package com.gmail.Xeiotos.MythCraft.StatusEffects;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Xeiotos
 */
public class Polyphagia extends StatusEffect {
    
    public Polyphagia(MythCraftPlayer mythCraftPlayer) {
        super(mythCraftPlayer, "Polyphagia");
    }
    
    @Override
    public void run() {
        if (player.isOnline()) { 
            if (!player.hasPotionEffect(PotionEffectType.HUNGER)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, 2));
            }
            
            mythCraftPlayer.setPolyphagiaTimer(mythCraftPlayer.getPolyphagiaTimer()- 20);
            if (mythCraftPlayer.getPolyphagiaTimer() <= 0L) {
                mythCraftPlayer.setPolyphagiaTimer(6000L);
                player.getWorld().strikeLightningEffect(player.getLocation());
                player.sendMessage(ChatColor.RED + "Your polyphagia has progressed into vampirism.");
                mythCraftPlayer.removeStatusEffect(effectName);
            }
        } else {
            this.cancel();
        }
    }
    
    @Override
    public void removeEffect() {
        player.removePotionEffect(PotionEffectType.HUNGER);
        mythCraftPlayer.setPolyphagiaTimer(6000L);
        this.cancel();
    }
}
