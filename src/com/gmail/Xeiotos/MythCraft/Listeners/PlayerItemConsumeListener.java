package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerItemConsumeListener implements Listener {

    private final MythCraft plugin;

    public PlayerItemConsumeListener(MythCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        if (mythCraftPlayer.isVampire()) {
            ItemStack item = event.getItem();
            if (plugin.blocks.getList("food").contains(item.getTypeId())) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 600, 5, true), true);
            }
            if (item.getType() == Material.POTION && item.getDurability() == 4335) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 2));
            }
        } else if (mythCraftPlayer.hasStatusEffect("bloodrot")) {
            mythCraftPlayer.removeStatusEffect("bloodrot");
            mythCraftPlayer.setBloodRotTimer(12000L);
        } else if (mythCraftPlayer.hasStatusEffect("polyphagia")) {
            mythCraftPlayer.removeStatusEffect("polyphagia");
            mythCraftPlayer.setPolyphagiaTimer(6000L);
        }
    }
}
