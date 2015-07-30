package com.gmail.Xeiotos.MythCraft.Listeners;

import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftPrepareListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCraftPrepare(PrepareItemCraftEvent event) {
        if (!event.isRepair()) {
            ItemStack result = event.getRecipe().getResult();
            if (result.getTypeId() == 373 && result.getDurability() == 16495) {
                ItemMeta meta = result.getItemMeta();
                meta.setDisplayName(ChatColor.AQUA + "Holy Water");
                meta.setLore(Arrays.asList(ChatColor.RED + "Damages undead creatures", ChatColor.RED + "and cures bloodrot"));
                result.setItemMeta(meta);
                event.getInventory().setResult(result);
            }
        }
    }
}
