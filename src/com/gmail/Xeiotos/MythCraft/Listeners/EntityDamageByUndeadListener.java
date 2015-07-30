package com.gmail.Xeiotos.MythCraft.Listeners;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageByUndeadListener implements Listener {
 
    private final MythCraft plugin;
    
    public EntityDamageByUndeadListener(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageByUndead(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (!(event instanceof EntityDamageByEntityEvent)) {
            return;
        }
        Entity attacker = ((EntityDamageByEntityEvent) event).getDamager();
        Player defender = (Player) event.getEntity();
        MythCraftPlayer mythCraftPlayerDefender = MythCraftPlayer.getMythCraftPlayer(defender);
        if ((attacker instanceof Zombie || attacker instanceof PigZombie || attacker instanceof Player) && plugin.randomGenerator.nextInt(500) == 13) {
            if (attacker instanceof Player) {
                Player player = (Player) attacker;
                MythCraftPlayer mythCraftPlayerAttacker = MythCraftPlayer.getMythCraftPlayer(player);
                if (!(mythCraftPlayerAttacker.isVampire()) || mythCraftPlayerDefender.hasStatusEffect("bloodrot") || mythCraftPlayerDefender.isVampire()) {
                    return;
                }
            }
            MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(defender);
            defender.sendMessage(ChatColor.RED + "You have contracted bloodrot.");
            mythCraftPlayer.applyStatusEffect("bloodrot");
        }
    }
}
