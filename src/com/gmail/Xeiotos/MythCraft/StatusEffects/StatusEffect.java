package com.gmail.Xeiotos.MythCraft.StatusEffects;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Xeiotos
 */
public abstract class StatusEffect extends BukkitRunnable {
    
    protected final MythCraft plugin;
    protected final String effectName;
    protected final Player player;
    protected final MythCraftPlayer mythCraftPlayer;
    
    public StatusEffect(MythCraftPlayer mythCraftPlayer, String effectName) {
        this.mythCraftPlayer = mythCraftPlayer;
        this.player = mythCraftPlayer.getPlayer();
        this.effectName = effectName;
        this.plugin = mythCraftPlayer.getPlugin();
    }
    
    public String getName() {
        return effectName;
    }
    
    public abstract void removeEffect();
    
    @Override
    public abstract void run();

}
