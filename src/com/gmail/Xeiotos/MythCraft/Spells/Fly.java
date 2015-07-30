package com.gmail.Xeiotos.MythCraft.Spells;

import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.Sound;

/**
 *
 * @author Xeiotos
 */
public class Fly extends Spell {
    
    public Fly(MythCraftPlayer mythCraftPlayer, int ammount) {
        super(mythCraftPlayer, "Fly", ammount);
    }
    
    @Override
    public void run() {
        if (player.isFlying()) {
            player.giveExp(-ammount);
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);
            if (player.getExp() < 0) {
                if (player.getTotalExperience() == 0) {
                    player.setExp(0);
                    player.setLevel(0);
                    player.setFlying(false);
                    this.cancel();
                } else {
                    player.giveExpLevels(-1);
                    player.setExp(1 + player.getExp());
                }
            }
        } else if (!player.isFlying()) {
            player.setFlying(false);
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
