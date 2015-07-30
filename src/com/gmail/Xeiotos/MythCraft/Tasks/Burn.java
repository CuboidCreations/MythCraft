package com.gmail.Xeiotos.MythCraft.Tasks;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import java.util.Iterator;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Xeiotos
 */

//TODO Change this to StatusEffect format
public class Burn extends BukkitRunnable{
    
    private final MythCraft plugin;
    
    public Burn(MythCraft plugin) {
        this.plugin = plugin;
    }
    
    public void run() {
        
        //Burn horses
        for (World world : plugin.getServer().getWorlds()) {
            if (plugin.pluginConfig.getList("config.horseWorlds").contains(world.getName())) {
                Iterator<LivingEntity> creatures = world.getLivingEntities().iterator();
                
                while (creatures.hasNext()) {
                        LivingEntity mob = creatures.next();

                    if (!(mob instanceof Player) && (mob instanceof Horse) ) {
                        Horse horse = (Horse) mob;

                        if (horse.getVariant() == Horse.Variant.UNDEAD_HORSE || horse.getVariant() == Horse.Variant.SKELETON_HORSE) {
                            Block creatureBlock = mob.getLocation().getBlock().getRelative(BlockFace.UP);

                            if (plugin.util.getSunLight(world, creatureBlock) > 8 && (horse.getFireTicks()<=0) && !horse.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE) && (!world.hasStorm())) {
                                horse.setFireTicks(Integer.MAX_VALUE);
                            } else if (horse.getFireTicks()>=1000){
                                horse.setFireTicks(40);
                            }
                        }
                    }
                }                        
            }  
        }
    }
}
