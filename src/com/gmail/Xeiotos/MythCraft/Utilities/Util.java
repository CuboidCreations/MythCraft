package com.gmail.Xeiotos.MythCraft.Utilities;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Xeiotos
 */
public class Util {
    
    private final MythCraft plugin;
    private long time;
    private byte lightLevel;
    
    public Util(MythCraft plugin) {
	this.plugin = plugin;
    }
    
    public String getItemName(ItemStack item) {
        String str = item.getType().name().replace("_", " ").toLowerCase();
        StringBuilder b = new StringBuilder(str);
        int i = 0;
        do {
            b.replace(i, i + 1, b.substring(i, i + 1).toUpperCase());
            i = b.indexOf(" ", i) + 1;
        } while (i > 0 && i < b.length());
        return b.toString();
    }
    
    public boolean isWater(Block block){
        if((block.getType()==Material.STATIONARY_WATER)||(block.getType()==Material.WATER)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isLava(Block block){
        if((block.getType()==Material.STATIONARY_LAVA)||(block.getType()==Material.LAVA)){
            return true;
        }else{
            return false;
        }
    }
    
    public byte getSunLight(World world, Block block) {       
        time = world.getTime();
        
        if (time < 12000) {
            lightLevel = block.getLightFromSky();
        } else {
            lightLevel = (byte) (block.getLightLevel() - block.getLightFromBlocks());
        }                
        return lightLevel;
    }
    
    public boolean isOnline(String player) {
        if (plugin.getServer().getOfflinePlayer(player).isOnline()){
            return true;
        } else {
            return false;            
        }
    }
    
    public OfflinePlayer playerByName(String player) {
        return plugin.getServer().getOfflinePlayer(player);
    }
    
    public void reConfig(String name) { 
        plugin.configManager.saveConfig(name, true);
        plugin.configManager.getConfig(name).reloadConfig();
        plugin.configManager.getFileConfig(name);
    }
    
    public void addXP(Player player, int ammount) {
        player.giveExp(ammount);
        if (player.getExp() < 0) {
            if (player.getTotalExperience() == 0) {
                player.setExp(0);
                player.setLevel(0);
            } else {
                player.giveExpLevels(-1);
                player.setExp(1 + player.getExp());
            }
        }
    }
    
    public boolean isInteger(String str) {
	if (str == null) {
		return false;
	}
	int length = str.length();
	if (length == 0 || length > 10) {
		return false;
	}
	int i = 0;
	if (str.charAt(0) == '-') {
		if (length == 1) {
			return false;
		}
		i = 1;
	}
	for (; i < length; i++) {
		char c = str.charAt(i);
		if (c <= '/' || c >= ':') {
			return false;
		}
	}
	return true;
    }
    
    public float getEMC(ItemStack i) {
        if (i != null) {
            String s = i.getTypeId() + "-" + i.getDurability();
            String z = plugin.configManager.getFileConfig("emcConfig").getString(s);

            if (z == null) {
                s = i.getTypeId() + "-A";
                z = plugin.configManager.getFileConfig("emcConfig").getString(s);
                if (z == null) {
                    s = i.getTypeId() + "-X";
                    z = plugin.configManager.getFileConfig("emcConfig").getString(s);
                    if (z == null) {
                        return 0.0F;
                    }
                    float dur;
                    if (z.contains("//")) {
                        String[] p = z.split("//");
                        dur = Float.parseFloat(p[0]);
                    } else {
                        dur = Float.parseFloat(z);
                    }
                    float ddur = 1.0F - Float.valueOf(i.getDurability()).floatValue() / getMaxDur(i);
                    return dur * ddur;
                }
                if (z.contains("//")) {
                    String[] p = z.split("//");
                    return Float.parseFloat(p[0]);
                }
                return Float.parseFloat(z);
            }

            if (z.contains("//")) {
                String[] p = z.split("//");
                return Float.parseFloat(p[0]);
            }
            return Float.parseFloat(z);
        }

        return 0.0F;
    }
    
    public float getMaxDur(ItemStack i) {
        switch (i.getTypeId()) {
            case 256:
                return 251.0F;
            case 257:
                return 251.0F;
            case 258:
                return 251.0F;
            case 259:
                return 65.0F;
            case 261:
                return 385.0F;
            case 267:
                return 251.0F;
            case 268:
                return 60.0F;
            case 269:
                return 60.0F;
            case 270:
                return 60.0F;
            case 271:
                return 60.0F;
            case 272:
                return 132.0F;
            case 273:
                return 132.0F;
            case 274:
                return 132.0F;
            case 275:
                return 132.0F;
            case 276:
                return 1562.0F;
            case 277:
                return 1562.0F;
            case 278:
                return 1562.0F;
            case 279:
                return 1562.0F;
            case 283:
                return 33.0F;
            case 284:
                return 33.0F;
            case 285:
                return 33.0F;
            case 286:
                return 33.0F;
            case 290:
                return 60.0F;
            case 291:
                return 132.0F;
            case 292:
                return 251.0F;
            case 293:
                return 1562.0F;
            case 294:
                return 33.0F;
            case 298:
                return 56.0F;
            case 299:
                return 82.0F;
            case 300:
                return 76.0F;
            case 301:
                return 66.0F;
            case 306:
                return 166.0F;
            case 307:
                return 242.0F;
            case 308:
                return 226.0F;
            case 309:
                return 196.0F;
            case 310:
                return 364.0F;
            case 311:
                return 529.0F;
            case 312:
                return 496.0F;
            case 313:
                return 430.0F;
            case 314:
                return 78.0F;
            case 315:
                return 114.0F;
            case 316:
                return 106.0F;
            case 317:
                return 92.0F;
            case 346:
                return 65.0F;
            case 359:
                return 239.0F;
            case 260:
            case 262:
            case 263:
            case 264:
            case 265:
            case 266:
            case 280:
            case 281:
            case 282:
            case 287:
            case 288:
            case 289:
            case 295:
            case 296:
            case 297:
            case 302:
            case 303:
            case 304:
            case 305:
            case 318:
            case 319:
            case 320:
            case 321:
            case 322:
            case 323:
            case 324:
            case 325:
            case 326:
            case 327:
            case 328:
            case 329:
            case 330:
            case 331:
            case 332:
            case 333:
            case 334:
            case 335:
            case 336:
            case 337:
            case 338:
            case 339:
            case 340:
            case 341:
            case 342:
            case 343:
            case 344:
            case 345:
            case 347:
            case 348:
            case 349:
            case 350:
            case 351:
            case 352:
            case 353:
            case 354:
            case 355:
            case 356:
            case 357:
            case 358:
        }
        return 1.0F;
    }
}
