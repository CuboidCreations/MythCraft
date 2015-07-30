package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.util.Vector;

public class PlayerItemDropListener implements Listener {
 
    private final MythCraft plugin;
    
    public PlayerItemDropListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        Item item = event.getItemDrop();
        int itemID = item.getItemStack().getTypeId();
        if (!(item.getItemStack().getAmount() > 1) && itemID != 262) {
            if (plugin.pluginConfig.getList("config.telekinesisBlocks").contains(itemID) && mythCraftPlayer.getRace().equalsIgnoreCase("valkyrie")) {
                item.remove();
                Byte blockData = 0;
                Location playerLocation = player.getLocation();
                Vector playerDirection = new Vector();
                playerDirection = playerLocation.getDirection();
                double playerDirectionY = playerDirection.getY();
                playerDirection = playerDirection.multiply(1.5);
                playerDirection.setY(playerDirectionY);
                FallingBlock physicsBlock = player.getWorld().spawnFallingBlock(playerLocation.getBlock().getRelative(BlockFace.UP).getLocation(), itemID, blockData);
                physicsBlock.setVelocity(playerDirection);
            }
        } else if (itemID == 262) {
            item.remove();
            for (int i = 0; i < item.getItemStack().getAmount(); i++) {
                Arrow projectile = player.launchProjectile(Arrow.class);
                projectile.setVelocity(projectile.getVelocity().multiply(2));
            }
        }
    }
}
