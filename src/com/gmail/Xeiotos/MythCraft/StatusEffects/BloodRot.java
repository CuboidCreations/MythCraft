package com.gmail.Xeiotos.MythCraft.StatusEffects;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Xeiotos
 */
public class BloodRot extends StatusEffect {
    
    public BloodRot(MythCraftPlayer mythCraftPlayer) {
        super(mythCraftPlayer, "Bloodrot");
    }
    
    @Override
    public void run() {
        if (player.isOnline()) {
            if (!player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1));
            }
            
            mythCraftPlayer.setBloodRotTimer(mythCraftPlayer.getBloodRotTimer() - 20);
            if (mythCraftPlayer.getBloodRotTimer() <= 0L) {
                mythCraftPlayer.setVampire(true);
                mythCraftPlayer.setBloodRotTimer(12000L);
                mythCraftPlayer.setPolyphagiaTimer(6000L);
                mythCraftPlayer.applyStatusEffect("polyphagia");
                player.getWorld().strikeLightningEffect(player.getLocation());
                player.sendMessage(ChatColor.RED + "Your bloodrot has progressed into polyphagic vampirism.");
                mythCraftPlayer.removeStatusEffect(effectName);
            }
        } else {
            this.cancel();
        }
    }

    @Override
    public void removeEffect() {
        player.removePotionEffect(PotionEffectType.WEAKNESS);
        mythCraftPlayer.setBloodRotTimer(12000L);
        this.cancel();
    }
}
