package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import com.gmail.Xeiotos.MythCraft.Tasks.WarpThruHandler;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class PlayerInteractListener implements Listener {
 
    private final MythCraft plugin;
    
    public PlayerInteractListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && plugin.pluginConfig.getList("config.telekinesisBlocks").contains(block.getTypeId()) && player.getItemInHand().getTypeId() == 0 && mythCraftPlayer.getRace().equalsIgnoreCase("Valkyrie")) {
            Location location = block.getLocation();
            Byte blockData = block.getData();
            Material material = block.getType();
            block.setType(Material.AIR);
            FallingBlock physicsBlock = location.getWorld().spawnFallingBlock(location, material, blockData);
            physicsBlock.setVelocity(new Vector(0, 1, 0));
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK && player.getItemInHand().getTypeId() == 373 && (block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST)) {
            Sign sign = (Sign) block.getState();
            if (sign.getLine(0).equalsIgnoreCase("[Altar]") && player.getTotalExperience() >= 50) {
                ItemStack potion = player.getItemInHand();
                if (potion.getDurability() == 0) {
                    ItemMeta meta = potion.getItemMeta();
                    plugin.util.addXP(player, -50);
                    meta.setDisplayName(ChatColor.AQUA + "Holy Water");
                    meta.setLore(Arrays.asList(ChatColor.RED + "Damages undead creatures", ChatColor.RED + "and cures bloodrot"));
                    potion.setDurability((short) 4335);
                    potion.setItemMeta(meta);
                }
            } else if (sign.getLine(0).equalsIgnoreCase("[Altar]") && player.getTotalExperience() < 10) {
                player.sendMessage(ChatColor.RED + "Not enough XP!");
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK && mythCraftPlayer.isVampire() && block.getTypeId() == 92 && player.getFoodLevel() < 20) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 600, 5, true), true);
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK && mythCraftPlayer.getRace().equalsIgnoreCase("demon") && player.getItemInHand().getTypeId() == 0 && player.getTotalExperience() >= 10) {
            BlockFace currentBlockFace = event.getBlockFace().getOppositeFace();
            if (currentBlockFace.equals(BlockFace.DOWN) && block.getType().equals(Material.BEDROCK) && player.getLocation().getBlockY() < 9) {
                player.teleport(new Location(Bukkit.getWorld("world_nether"), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()));
            }
            Block oppositeBlock = player.getWorld().getBlockAt(event.getClickedBlock().getX() + currentBlockFace.getModX(), event.getClickedBlock().getY() + currentBlockFace.getModY(), event.getClickedBlock().getZ() + currentBlockFace.getModZ());
            plugin.warpThruHandler = new WarpThruHandler(plugin, player, oppositeBlock, currentBlockFace);
            plugin.warpThruHandler.runTask(plugin);
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR && mythCraftPlayer.getRace().equalsIgnoreCase("demon") && player.getTotalExperience() >= 10) {
            plugin.util.addXP(player, -10);
            Vector velocity = new Vector();
            velocity = player.getLocation().getDirection().multiply(mythCraftPlayer.getCurrentWarp());
            player.setVelocity(velocity);
        }
    }
}
