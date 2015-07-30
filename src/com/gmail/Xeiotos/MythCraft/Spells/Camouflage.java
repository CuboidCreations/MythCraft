package com.gmail.Xeiotos.MythCraft.Spells;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Xeiotos
 */
public class Camouflage extends Spell {
    
    public Camouflage(MythCraftPlayer mythCraftPlayer, int ammount) {
        super(mythCraftPlayer, "Camouflage", ammount);
    }
    
    @Override
    public void run() {
        if (player.isSneaking()) {
            player.giveExp(-ammount);
            if (player.getExp() < 0) {
                if (player.getTotalExperience() == 0) {
                    player.setExp(0);
                    player.setLevel(0);
                    this.cancel();
                } else {
                    player.giveExpLevels(-1);
                    player.setExp(1 + player.getExp());
                }
            }
        } else if (!player.isSneaking()) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            player.giveExp(-ammount);
            if (player.getExp() < 0) {
                if (player.getTotalExperience() == 0) {
                    player.setExp(0);
                    player.setLevel(0);
                } else {
                    player.giveExpLevels(-1);
                    player.setExp(1 + player.getExp());                         
                }
            }
            this.cancel();
        }
    } 
}
