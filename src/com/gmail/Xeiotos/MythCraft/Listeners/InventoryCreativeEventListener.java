package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryCreativeEventListener implements Listener {
 
    private final MythCraft plugin;
    
    public InventoryCreativeEventListener(MythCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryCreativeEvent(InventoryCreativeEvent event) {
        /*        Player player = (Player) event.getWhoClicked();
         * ItemStack item = event.getCursor();
         * float cost = plugin.util.getEMC(item) * item.getAmount();
         * InventoryAction action = event.getAction();
         * if (item.getTypeId() == 0) {
         * return;
         * }
         * if (plugin.util.getEMC(item) == 0) {
         * event.setCancelled(true);
         * player.sendMessage(ChatColor.RED + plugin.util.getItemName(item) + " is blocked or unsupported.");
         * }
         * if (!item.hasItemMeta()) {
         * ItemMeta meta = item.getItemMeta();
         * String name = plugin.util.getItemName(item);
         * meta.setDisplayName(name + "\ufff0");
         * item.setItemMeta(meta);
         * if (plugin.util.getEMC(item) * item.getAmount() <= player.getTotalExperience()) {
         * plugin.util.addXP(player, (int) -cost);
         * } else if (plugin.util.getEMC(item) * item.getAmount() > player.getTotalExperience()) {
         * for (int i = 1; i < item.getAmount(); i++) {
         * if (plugin.util.getEMC(item) * i <= player.getTotalExperience()) {
         * event.setCancelled(true);
         * player.getInventory().addItem(new ItemStack(item.getType(), i, item.getDurability()));
         * return;
         * }
         * }
         * event.setCancelled(true);
         * player.sendMessage(ChatColor.RED + "Not enough energy!");
         * }
         * }*/
    }
}
