package com.gmail.Xeiotos.MythCraft.Utilities;

import com.gmail.Xeiotos.MythCraft.MythCraft;
import com.gmail.Xeiotos.MythCraft.MythCraftPlayer;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


/**
 *
 * @author Xeiotos
 */

public class MythCraftCommandExecutor implements CommandExecutor {
    
    private final MythCraft plugin;
    
    public MythCraftCommandExecutor(MythCraft plugin) {
	this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //This part doesn't care if the sender is a player or the console
        if (cmd.getName().equalsIgnoreCase("mythcraft")){ //if the command was mythcraft
            if (sender instanceof Player) { //If player
                Player player = (Player) sender;
                MythCraftPlayer mythCraftPlayer = MythCraftPlayer.getMythCraftPlayer(player);
                String sendername = sender.getName();
                if (args.length == 0){ //checks if base command
                    sender.sendMessage(ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " for Bukkit 1.6.2");
                    sender.sendMessage(ChatColor.DARK_GREEN + "----" + ChatColor.GOLD + "-----");
                    sender.sendMessage(ChatColor.GREEN + "Info" + ChatColor.WHITE + "      Prints MythCraft info");
                    sender.sendMessage(ChatColor.GREEN + "Tree" + ChatColor.WHITE + "     Show you your skill tree");
                    sender.sendMessage(ChatColor.GREEN + "Status" + ChatColor.WHITE + "    Show you your status");
                    sender.sendMessage(ChatColor.GREEN + "Skills" + ChatColor.WHITE + "     Show your available skills");
                    sender.sendMessage(ChatColor.GREEN + "Worship" + ChatColor.WHITE + "   Choose who you worship");
                    sender.sendMessage(ChatColor.GREEN + "Race" + ChatColor.WHITE + "   Choose your race");
                    sender.sendMessage(ChatColor.GREEN + "Altar" + ChatColor.WHITE + "  Remove your altar");
                    
                } else if (args[0].equalsIgnoreCase("race") && args.length == 1) {
                    sender.sendMessage(ChatColor.GOLD + "Choose your race!");
                    sender.sendMessage(ChatColor.GREEN + "Mertouched");
                    sender.sendMessage(ChatColor.GREEN + "Elf");
                    sender.sendMessage(ChatColor.GREEN + "Dwarf");
                    sender.sendMessage(ChatColor.GREEN + "Demon");
                    sender.sendMessage(ChatColor.GREEN + "Valkyrie");
                } else if (args[0].equalsIgnoreCase("race") && args.length == 2) { //Debug Command!
                    if (args[1].equalsIgnoreCase("Mertouched") || args[1].equalsIgnoreCase("Elf") || args[1].equalsIgnoreCase("Dwarf") || args[1].equalsIgnoreCase("Demon") || args[1].equalsIgnoreCase("Valkyrie")) {
                        mythCraftPlayer.setRace(args[1]); 
                        plugin.util.reConfig(sendername);
                        sender.sendMessage(ChatColor.GREEN + "Your are now a(n) " + args[1]);      
                    } else {
                        sender.sendMessage(ChatColor.RED + "Invalid race!");                    
                    }
                    
                    
                //TODO merge altar command with worship command    
                } else if (args[0].equalsIgnoreCase("altar") && args.length == 1) {
                    sender.sendMessage(ChatColor.RED + "Please use /altar remove to remove your altar.");
                    sender.sendMessage(ChatColor.RED + "This cannot be undone, and you will lose your altar's blocks.");
                } else if (args[0].equalsIgnoreCase("altar") && args.length == 2) {
                    if (args[1].equalsIgnoreCase("remove"))  {
                        sender.sendMessage(ChatColor.GREEN + "Altar removed");
                        //TODO update with code to actually remove altar
                    }
                    
                } else if (args[0].equalsIgnoreCase("worship") && args.length == 1) {
                    sender.sendMessage(ChatColor.RED + "Must specify player name.");
                } else if (args[0].equalsIgnoreCase("worship") && args.length == 2) {
                    if (mythCraftPlayer.hasAltar()) { //if player has altar
                        if (!mythCraftPlayer.getGod().equals(args[1])) { //player not already worshipping this player
                            String previousGod = mythCraftPlayer.getGod();                
                            mythCraftPlayer.setGod(args[1]); 

                            sender.sendMessage(ChatColor.GREEN + "Now worshipping " + args[1]);
                            Block block = plugin.getServer().getWorld(mythCraftPlayer.getAltarWorld()).getBlockAt(mythCraftPlayer.getAltarX(), mythCraftPlayer.getAltarY(), mythCraftPlayer.getAltarZ());
                            if (block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
                                Sign sign = (Sign) block.getState();
                                sign.setLine(2, args[1]);
                                sign.update();
                            }

                            // Takes care of the old god
                            if (plugin.util.isOnline(previousGod) && !previousGod.equals("no")) {
                                MythCraftPlayer mythCraftPlayerPreviousGod = MythCraftPlayer.getMythCraftPlayer(previousGod);
                                mythCraftPlayerPreviousGod.removeWorshipper(sendername);
                                mythCraftPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You are no longer being worshipped by " + sendername);
                            } else if (!previousGod.equals("no")) { //offline
                                plugin.configManager.loadConfigFiles(
                                    new ConfigManager.ConfigPath(previousGod, "playerConfig.yml", "players/" + previousGod + ".yml")
                                );
                                plugin.configManager.getFileConfig(previousGod).set("PersistentData.Worshippers", plugin.configManager.getFileConfig(previousGod).getInt("PersistentData.Worshippers")-1);
                                List list = plugin.configManager.getFileConfig(previousGod).getStringList("PersistentData.WorshipperNames");
                                list.remove(sendername);
                                plugin.configManager.getFileConfig(previousGod).set("PersistentData.WorshipperNames", list);
                                plugin.util.reConfig(previousGod);
                                plugin.configManager.unloadConfig(previousGod);
                            }

                            // Takes care of the new god
                            if (plugin.util.isOnline(args[1])) { //if god is online
                                MythCraftPlayer mythCraftPlayerNewGod = MythCraftPlayer.getMythCraftPlayer(args[1]);
                                mythCraftPlayerNewGod.addWorshipper(sendername);
                                plugin.util.playerByName(args[1]).getPlayer().sendMessage(ChatColor.GREEN + "You are now being worshipped by " + sendername);
                            } else { //if god is offline
                                plugin.configManager.loadConfigFiles(
                                    new ConfigManager.ConfigPath(args[1], "playerConfig.yml", "players/" + args[1] + ".yml")
                                );
                                plugin.configManager.getFileConfig(args[1]).set("PersistentData.Worshippers", plugin.configManager.getFileConfig(args[1]).getInt("PersistentData.Worshippers")+1);
                                List list = plugin.configManager.getFileConfig(args[1]).getStringList("PersistentData.WorshipperNames");
                                list.add(sendername);
                                plugin.configManager.getFileConfig(args[1]).set("PersistentData.WorshipperNames", list);
                                plugin.util.reConfig(args[1]);
                                plugin.configManager.unloadConfig(args[1]);
                            }                            
                        } else {
                            sender.sendMessage(ChatColor.RED + "Already worshipping " + args[1]);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "You must construct an altar first!");
                    }
                    
                    
                } else if (args[0].equalsIgnoreCase("info")){ //Info subcommand
                    sender.sendMessage(ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " for Minecraft 1.6.2");
                    sender.sendMessage("Contact info: " + ChatColor.LIGHT_PURPLE + "Skype: XeiotosQuorzar");
                    
                } else if (args[0].equalsIgnoreCase("skills") && args.length < 2) { //If requesting list of skills
                    if (mythCraftPlayer.isVampire()) {
                        sender.sendMessage(ChatColor.GREEN + "Vampiric speed" + ChatColor.WHITE + "  command: /myc skills speed [number]"); 
                        if (mythCraftPlayer.hasSkill("nightvision")) {
                            sender.sendMessage(ChatColor.GREEN + "Night vision" + ChatColor.WHITE + "  command: /myc skills nivi"); 
                        }                        
                    }
                    if (mythCraftPlayer.getRace().equalsIgnoreCase("demon")) {
                        sender.sendMessage(ChatColor.GREEN + "Warp distance" + ChatColor.WHITE + " command: /myc skills warp");
                    }
                } else if (args[0].equalsIgnoreCase("skills") && args.length >= 2 && mythCraftPlayer.isVampire()) {
                    if (args[1].equalsIgnoreCase("nivi") && args.length < 3) {
                        if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        } else {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true), true);
                        }                     
                        if (mythCraftPlayer.getAutoNightVision()) {
                            mythCraftPlayer.setAutoNightVision(false);
                            sender.sendMessage(ChatColor.GREEN + "Set night vision to manual.");
                        }
                    } else if (args[1].equalsIgnoreCase("nivi") && args[2].equalsIgnoreCase("auto") && args.length == 3) {
                       if (mythCraftPlayer.getAutoNightVision()) {
                            mythCraftPlayer.setAutoNightVision(false);
                            sender.sendMessage(ChatColor.GREEN + "Set night vision to manual.");
                        } else {
                            mythCraftPlayer.setAutoNightVision(true);
                            sender.sendMessage(ChatColor.GREEN + "Set night vision to auto.");
                        }  

                    } else if (args[1].equalsIgnoreCase("speed") && args.length < 3) {
                        sender.sendMessage("Current speed: " + mythCraftPlayer.getCurrentVampireSpeed());
                    } else if (args[1].equalsIgnoreCase("speed") && args.length == 3) {
                        int maxspeed = mythCraftPlayer.getMaxVampireSpeed();
                        if (plugin.util.isInteger(args[2])) {
                            if (Integer.parseInt(args[2]) <= maxspeed && Integer.parseInt(args[2]) >= 0) {
                                mythCraftPlayer.setCurrentVampireSpeed(Integer.parseInt(args[2]));
                                sender.sendMessage(ChatColor.GREEN + "Speed set to " + mythCraftPlayer.getCurrentVampireSpeed());
                            } else if (Integer.parseInt(args[2]) > maxspeed) {
                                sender.sendMessage(ChatColor.RED + "Your speed has to be between 0 and " + maxspeed + "!");
                            } else if (Integer.parseInt(args[2]) <= Integer.MAX_VALUE) {
                                sender.sendMessage(ChatColor.RED + "Invalid value!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "Not an integer!");
                        }
                    }
                    
                } else if (args[0].equalsIgnoreCase("skills") && args.length >= 2 && mythCraftPlayer.getRace().equalsIgnoreCase("Demon")) {
                    if (args[1].equalsIgnoreCase("warp") && args.length < 3) {
                        sender.sendMessage("Current speed: " + mythCraftPlayer.getCurrentWarp());
                    } else if (args[1].equalsIgnoreCase("warp") && args.length == 3) {
                        int maxwarp = mythCraftPlayer.getMaxWarp();
                        if (plugin.util.isInteger(args[2])) {
                            if (Integer.parseInt(args[2]) <= maxwarp && Integer.parseInt(args[2]) >= 0) {
                               mythCraftPlayer.setCurrentWarp(Integer.parseInt(args[2]));
                               sender.sendMessage(ChatColor.GREEN + "Warp distance set to " + mythCraftPlayer.getCurrentWarp());
                            } else if (Integer.parseInt(args[2]) > maxwarp) {
                                sender.sendMessage(ChatColor.RED + "Your warp distance has to be between 0 and " + maxwarp + "!");
                            } else if (Integer.parseInt(args[2]) <= Integer.MAX_VALUE) {
                                sender.sendMessage(ChatColor.RED + "Invalid value!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "Not an integer!");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("tree") && args.length < 2) { //Tree subcommand one argument (own tree)
                    if (sender.hasPermission("MythCraft.tree")) {
                        sender.sendMessage(ChatColor.RED + "|" + ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " skill tree" + ChatColor.RED + "|");
                        sender.sendMessage(ChatColor.GRAY + "Active:");
                        if (mythCraftPlayer.isVampire()) { // Vampiric skills
                            sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + "speed " + mythCraftPlayer.getMaxVampireSpeed()); // Vampiric flight
                            sender.sendMessage(ChatColor.GRAY + "Passive:");
                            if (mythCraftPlayer.hasSkill("friendofdead")) {
                                if (mythCraftPlayer.hasSkill("friendofdread")) {
                                    sender.sendMessage(ChatColor.DARK_GREEN + "Friend of Dead -> Friend of Dread"); 
                                } else {
                                    sender.sendMessage(ChatColor.DARK_GREEN + "Friend of Dead " + ChatColor.GRAY + "-> Friend of Dread"); 
                                }
                            } else {
                                sender.sendMessage(ChatColor.GRAY + "Friend of Dead -> Friend of Dread");
                            }
                        }                           
                    }
                    /*} else if (args[0].equalsIgnoreCase("tree") && args.length == 2 && (!plugin.util.isOnline(args[1]))) { //Status subcommand invalid usage player offline
                     * if(sender.hasPermission("MythCraft.tree.others")) {
                     * sender.sendMessage(ChatColor.RED + args[1] + " is offline!");
                     * }
                     * } else if (args[0].equalsIgnoreCase("tree") && args.length == 2 && (plugin.util.isOnline(args[1]))) { //Status subcommand correct usage
                     * if(sender.hasPermission("MythCraft.tree.others")) {
                     * sender.sendMessage(ChatColor.RED + "|" + ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " player status" + ChatColor.RED + "|");
                     * sender.sendMessage(ChatColor.GRAY + "Active:");
                     * if (mythCraftPlayer.isVampire()) {
                     * sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + "speed " + plugin.configManager.getFileConfig(args[1]).getInt("Skills.Vampire.Speed")); // Vampiric speed
                     * sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + "flight " + plugin.configManager.getFileConfig(args[1]).getInt("Skills.Vampire.Flight")); // Vampiric flight
                     * sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + "jump " + plugin.configManager.getFileConfig(args[1]).getInt("Skills.Vampire.Jump")); // Vampiric jump
                     * sender.sendMessage(ChatColor.GRAY + "Passive:");
                     * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Skills.Vampire.FriendofDead")) {
                     * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Skills.Vampire.FriendofDread")) {
                     * sender.sendMessage(ChatColor.DARK_GREEN + "Friend of Dead -> Friend of Dread");
                     * } else {
                     * sender.sendMessage(ChatColor.DARK_GREEN + "Friend of Dead " + ChatColor.GRAY + "-> Friend of Dread");
                     * }
                     * } else {
                     * sender.sendMessage(ChatColor.GRAY + "Friend of Dead -> Friend of Dread");
                     * }
                     * }
                     * } */   
                    
                } else if (args[0].equalsIgnoreCase("status") && args.length < 2) { //Status subcommand with one arg (own status)
                    if(sender.hasPermission("MythCraft.status")) {
                        sender.sendMessage(ChatColor.RED + "|" + ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " player status" + ChatColor.RED + "|");
                        if (mythCraftPlayer.isVampire()) {
                            sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + mythCraftPlayer.getRace() + " " + sendername); // Vampiric + Race + name                      
                        } else {
                            sender.sendMessage(mythCraftPlayer.getRace() + " " + sendername); // Race + name                          
                        }
                        if (mythCraftPlayer.hasStatusEffect("bloodrot")) {
                            sender.sendMessage(ChatColor.RED + "Suffering from bloodrot for " + (mythCraftPlayer.getBloodRotTimer()/20) + " seconds");
                        }
                        if (mythCraftPlayer.hasStatusEffect("polyphagia")) {
                            sender.sendMessage(ChatColor.RED + "Suffering from polyphagia for " + (mythCraftPlayer.getPolyphagiaTimer()/20) + " seconds");
                        }
                    }
                } /*else if (args[0].equalsIgnoreCase("status") && args.length == 2 && (!plugin.util.isOnline(args[1]))) { //Status subcommand invalid usage player offline
                 * if(sender.hasPermission("MythCraft.status.others")) {
                 * sender.sendMessage(ChatColor.RED + args[1] + " is offline!");
                 * }
                 * } else if (args[0].equalsIgnoreCase("status") && args.length == 2 && (plugin.util.isOnline(args[1]))) { //Status subcommand correct usage
                 * if(sender.hasPermission("MythCraft.status.others")) {
                 * sender.sendMessage(ChatColor.RED + "|" + ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " player status" + ChatColor.RED + "|");
                 * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Status.isVampire")) {
                 * sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + plugin.configManager.getFileConfig(args[1]).getString("Player.Race") + " " + args[1]); // Vampiric + Race + name
                 * } else {
                 * sender.sendMessage(plugin.configManager.getFileConfig(args[1]).getString("Player.Race") + " " + args[1]); // Race + name
                 * }
                 * }
                 * }*/
                
            } else if (!(sender instanceof Player)){ //if console
                if (args.length == 0 || args.length < 1){ //checks if base command
                    sender.sendMessage(ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " for Bukkit 1.6.2");
                    sender.sendMessage(ChatColor.DARK_GREEN + "----" + ChatColor.GOLD + "-----");
                    sender.sendMessage(ChatColor.GREEN + "Info" + ChatColor.WHITE + "    Prints MythCraft info");
                    sender.sendMessage(ChatColor.GREEN + "Tree" + ChatColor.WHITE + "    Show you a player's skill tree");
                    sender.sendMessage(ChatColor.GREEN + "Status" + ChatColor.WHITE + "    Show you a player's status");
                    
                } else if (args[0].equalsIgnoreCase("info")){ //if info subcommand was used by console
                    sender.sendMessage(ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " for Minecraft 1.6.2");
                    sender.sendMessage("Author: " + ChatColor.LIGHT_PURPLE + "Xeiotos");
                    sender.sendMessage("Contact info: " + ChatColor.LIGHT_PURPLE + "Skype: XeiotosQuorzar");
                    
                    /*} else if (args[0].equalsIgnoreCase("status") && args.length < 2) { //Status subcommand invalid usage not enough args
                     * sender.sendMessage(ChatColor.RED + "Incorrect usage: Please specify player name!");
                     * } else if (args[0].equalsIgnoreCase("status") && args.length == 2 && (!plugin.util.isOnline(args[1]))) { //Status subcommand invalid usage player offline
                     * sender.sendMessage(ChatColor.RED + args[1] + " is offline!");
                     * } else if (args[0].equalsIgnoreCase("status") && args.length == 2 && (plugin.util.isOnline(args[1]))) { //Status subcommand correct usage
                     * sender.sendMessage(ChatColor.RED + "|" + ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " player status" + ChatColor.RED + "|");
                     * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Status.isVampire")) {
                     * sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + plugin.configManager.getFileConfig(args[1]).getString("Player.Race") + " " + args[1]); // Vampiric + Race + name
                     * } else {
                     * sender.sendMessage(plugin.configManager.getFileConfig(args[1]).getString("Player.Race") + " " + args[1]); // Race + name
                     * }
                     * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Status.BloodRot")) {
                     * sender.sendMessage(ChatColor.RED + "Suffering from bloodrot for " + (plugin.configManager.getFileConfig(args[1]).getLong("PersistentData.BloodRotTimer")/20) + " seconds");
                     * }
                     * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Status.Polyphagia")) {
                     * sender.sendMessage(ChatColor.RED + "Suffering from polyphagia for " + (plugin.configManager.getFileConfig(args[1]).getLong("PersistentData.PolyphagiaTimer")/20) + " seconds");
                     * }
                     * 
                     * } else if (args[0].equalsIgnoreCase("tree") && args.length < 2) { //Status subcommand invalid usage not enough args
                     * sender.sendMessage(ChatColor.RED + "Incorrect usage: Please specify player name!");
                     * } else if (args[0].equalsIgnoreCase("tree") && args.length == 2 && (!plugin.util.isOnline(args[1]))) { //Status subcommand invalid usage player offline
                     * sender.sendMessage(ChatColor.RED + args[1] + " is offline!");
                     * } else if (args[0].equalsIgnoreCase("tree") && args.length == 2 && (plugin.util.isOnline(args[1]))) { //Status subcommand correct usage
                     * sender.sendMessage(ChatColor.RED + "|" + ChatColor.DARK_GREEN + "Myth" + ChatColor.GOLD + "Craft" + " player status" + ChatColor.RED + "|");
                     * sender.sendMessage(ChatColor.GRAY + "Active:");
                     * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Status.isVampire")) {
                     * sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + "speed " + plugin.configManager.getFileConfig(args[1]).getInt("Skills.Vampire.Speed")); // Vampiric speed
                     * sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + "flight " + plugin.configManager.getFileConfig(args[1]).getInt("Skills.Vampire.Flight")); // Vampiric flight
                     * sender.sendMessage(ChatColor.RED + "Vampiric " + ChatColor.WHITE + "jump " + plugin.configManager.getFileConfig(args[1]).getInt("Skills.Vampire.Jump")); // Vampiric jump
                     * sender.sendMessage(ChatColor.GRAY + "Passive:");
                     * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Skills.Vampire.FriendofDead")) {
                     * if (plugin.configManager.getFileConfig(args[1]).getBoolean("Skills.Vampire.FriendofDread")) {
                     * sender.sendMessage(ChatColor.DARK_GREEN + "Friend of Dead -> Friend of Dread");
                     * } else {
                     * sender.sendMessage(ChatColor.DARK_GREEN + "Friend of Dead " + ChatColor.GRAY + "-> Friend of Dread");
                     * }
                     * } else {
                     * sender.sendMessage(ChatColor.GRAY + "Friend of Dead -> Friend of Dread");
                     * }
                     * }*/     
                }
            }
        }
        return true;
    }
}
