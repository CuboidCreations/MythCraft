package com.gmail.Xeiotos.MythCraft.Spells;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Xeiotos
 */
public abstract class Spell extends BukkitRunnable {
    
    protected final MythCraft plugin;
    protected final String spellName;
    protected final Player player;
    protected final MythCraftPlayer mythCraftPlayer;
    protected final int ammount;
    
    public Spell(MythCraftPlayer mythCraftPlayer, String spellName, int ammount) {
        this.mythCraftPlayer = mythCraftPlayer;
        this.player = mythCraftPlayer.getPlayer();
        this.spellName = spellName;
        this.plugin = mythCraftPlayer.getPlugin();
        this.ammount = ammount;
    }
    
    public String getName() {
        return spellName;
    }
    
    @Override
    public abstract void run();
}
