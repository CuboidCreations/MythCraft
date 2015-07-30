package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerToggleSneakListener implements Listener {
 
    private final MythCraft plugin;
    
    public PlayerToggleSneakListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        Block playerBlock = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if (mythCraftPlayer.getRace().equalsIgnoreCase("Mertouched")) {
            if (mythCraftPlayer.isSwimming()) {
                mythCraftPlayer.setSwimming(false);
            } else if (plugin.util.isWater(playerBlock)) {
                mythCraftPlayer.setSwimming(true);
            }
        } else if (mythCraftPlayer.getRace().equalsIgnoreCase("Elf")) {
            if (event.isSneaking() && !(player.getTotalExperience() < 10)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, true), true);
                mythCraftPlayer.castSpell("camouflage");
            }
        }
    }
}
