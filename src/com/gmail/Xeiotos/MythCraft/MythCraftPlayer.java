package com.gmail.Xeiotos.MythCraft;

import com.gmail.Xeiotos.MythCraft.Spells.Camouflage;
import com.gmail.Xeiotos.MythCraft.Spells.Fly;
import com.gmail.Xeiotos.MythCraft.Spells.Spell;
import com.gmail.Xeiotos.MythCraft.StatusEffects.BloodRot;
import com.gmail.Xeiotos.MythCraft.StatusEffects.NightVision;
import com.gmail.Xeiotos.MythCraft.StatusEffects.Polyphagia;
import com.gmail.Xeiotos.MythCraft.StatusEffects.StatusEffect;
import com.gmail.Xeiotos.MythCraft.StatusEffects.SunBurn;
import com.gmail.Xeiotos.MythCraft.StatusEffects.WaterBurn;
import com.gmail.Xeiotos.MythCraft.StatusEffects.WaterVision;
import java.util.ArrayList;
import org.bukkit.entity.Player;

/**
 *
 * @author Xeiotos
 */
public class MythCraftPlayer {

    private final MythCraft plugin;
    private Player player = null;
    private String race;
    private double maxHealth = -1;
    private int currentVampireSpeed;
    private int maxVampireSpeed;
    private boolean autoNightVision;
    private long bloodRotTimer = 12000;
    private long polyphagiaTimer = 6000;
    private boolean isVampire = false;
    private boolean sprintSwimming = false;
    private boolean swimming = false;
    private int Worshippers;
    private String Worshipping = "no";
    private int currentWarp;
    private int maxWarp;
    private boolean altar = false;
    private int altarX;
    private int altarY;
    private int altarZ;
    private String altarWorld;
    private ArrayList<String> worshipperNames = new ArrayList<>();
    private ArrayList<String> skills = new ArrayList<>();
    private ArrayList<StatusEffect> statusEffects = new ArrayList<>();
    public static ArrayList<MythCraftPlayer> players = new ArrayList<>();

    public MythCraftPlayer(MythCraft plugin, Player player) {
        this.player = player;
        players.add(this);
        this.plugin = plugin;
    }

    public static void removePlayer(MythCraftPlayer player) {
        players.remove(player);
        player = null;
    }

    public static MythCraftPlayer getMythCraftPlayer(Player p) {
        return getMythCraftPlayer(p.getName());
    }

    public static MythCraftPlayer getMythCraftPlayer(String name) {
        if (name != null) {
            for (MythCraftPlayer p : players) {
                if (p.player.getName().equalsIgnoreCase(name)) {
                    return p;
                }
            }
        }
        return null;
    }
    
    public void addSkill(String skill) {
        skill = skill.toLowerCase();
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }
    
    public void removeSkill(String skill) {
        skill = skill.toLowerCase();
        if (skills.contains(skill)) {
            skills.remove(skill);
        }
    }
    
    public ArrayList<String> getSkills() {
        return skills;
    }
    
    public boolean hasSkill(String skill) {
        skill = skill.toLowerCase();
        if (skills.contains(skill)) {
            return true;
        } else {
            return false;
        }
    }

    public void applyStatusEffect(String effect) {
        if (hasStatusEffect(effect.toLowerCase())) {
            return;
        }
        StatusEffect se;
        switch (effect.toLowerCase()) {
            case "bloodrot":
                se = new BloodRot(this);
                statusEffects.add(se);
                se.runTaskTimer(plugin, 20L, 20L);
                break;
            case "polyphagia":
                se = new Polyphagia(this);
                statusEffects.add(se);
                se.runTaskTimer(plugin, 20L, 20L);
                break;
            case "sunburn":
                se = new SunBurn(this);
                statusEffects.add(se);
                se.runTaskTimer(plugin, 20L, 20L);
                break;
            case "waterburn":
                se = new WaterBurn(this);
                statusEffects.add(se);
                se.runTaskTimer(plugin, 20L, 20L);
                break;
            case "watervision":
                se = new WaterVision(this);
                statusEffects.add(se);
                se.runTaskTimer(plugin, 20L, 20L);
                break;
            case "nightvision":
                se = new NightVision(this);
                statusEffects.add(se);
                se.runTaskTimer(plugin, 20L, 20L);
                break;
            default:
                break;
        }
    }
    
    public void removeStatusEffect(String effect) {
        if (hasStatusEffect(effect)) {
            getStatusEffect(effect).removeEffect();
            statusEffects.remove(getStatusEffect(effect));
        }
    }
    
    public StatusEffect getStatusEffect(String statusEffect) {
        if (statusEffect != null) {
            for (StatusEffect se : statusEffects) {
                if (se.getName().equalsIgnoreCase(statusEffect)) {
                    return se;
                }
            }
        }
        return null;
    }
    
    public ArrayList<String> getStatusEffects() {
        if (statusEffects.isEmpty()) {
            return null;
        } else {
            ArrayList<String> statusEffectNames = new ArrayList<>();
            for (StatusEffect se : statusEffects) {
                statusEffectNames.add(se.getName().toLowerCase());
            }
            return statusEffectNames;
        }
    }
    
    public boolean hasStatusEffect(String statusEffect) {
        statusEffect = statusEffect.toLowerCase();
        if (getStatusEffect(statusEffect) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void castSpell(String spell) {
        switch (spell.toLowerCase()) {
            case "fly":
                new Fly(this, 10).runTaskTimer(plugin, 20L, 20L);
            case "camouflage":
                new Camouflage(this, 10).runTaskTimer(plugin, 20L, 20L);
        }
    }
    
    public void castSpell(Spell spell) {
        castSpell(spell.getName());
    }
    
    public void setAutoNightVision(boolean bool) {
        this.autoNightVision = bool;
    }
    
    public boolean getAutoNightVision() {
        return autoNightVision;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRace() {
        return this.race;
    }
    
    public boolean hasAltar() {
        return altar;
    }
    
    public void setAltar(boolean bool) {
        this.altar = bool;
    }
    
    public void setAltar(int x, int y, int z, String world) {
        this.altar = true;
        this.altarX = x;
        this.altarY = y;
        this.altarZ = z;
        this.altarWorld = world;
    }
    
    public int getAltarX() {
        return altarX;
    }
    
    public int getAltarY() {
        return altarY;
    }
    
    public int getAltarZ() {
        return altarZ;
    }
    
    public String getAltarWorld() {
        return altarWorld;
    }
    
    public void addWorshipper(String worshipper) {
        if (this.worshipperNames.add(worshipper)) {
            Worshippers++;
        }
    }
    
    public void removeWorshipper(String worshipper) {
        if (this.worshipperNames.remove(worshipper)) {
            Worshippers--;            
        }
    }
    
    public void setWorshippers(int integer) {
        this.Worshippers = integer;
    }
    
    public void setWorshippers(ArrayList<String> worshipperNames) {
        this.worshipperNames = worshipperNames;
    }
    
    public int getWorshippers() {
        return Worshippers;
    }
    
    public String getGod() {
        return Worshipping;
    }
    
    public void setGod(String god) {
        this.Worshipping = god;
    }
    
    public ArrayList<String> getWorshipperNames() {
        return worshipperNames;
    }
    
    public double getMaxHealth() {
        return this.maxHealth;
    }
    
    public int getCurrentWarp() {
        return this.currentWarp;
    }
    
    public void setCurrentWarp(int integer) {
        this.currentWarp = integer;
    }
    
    public int getMaxWarp() {
        return this.maxWarp;
    }
    
    public void setMaxWarp(int integer) {
        this.maxWarp = integer;
    }
    
    public void setMaxHealth(double health) {
        this.maxHealth = health;
    }

    public void setVampire(boolean bool) {
        this.isVampire = bool;
    }

    public void setBloodRotTimer(Long time) {
        this.bloodRotTimer = time;
    }

    public Long getBloodRotTimer() {
        return bloodRotTimer;
    }

    public void setPolyphagiaTimer(Long time) {
        this.polyphagiaTimer = time;
    }

    public Long getPolyphagiaTimer() {
        return polyphagiaTimer;
    }

    public boolean isVampire() {
        return isVampire;
    }

    public Player getPlayer() {
        return player;
    }
    
    public void setMaxVampireSpeed(int speed) {
        this.maxVampireSpeed = speed;
    }
    
    public int getMaxVampireSpeed() {
        return maxVampireSpeed;
    }
    
    public void setCurrentVampireSpeed(int speed) {
        this.currentVampireSpeed = speed;
    }
    
    public int getCurrentVampireSpeed() {
        return currentVampireSpeed;
    }
    
    public boolean isSwimming() {
        return swimming;
    }
    
    public void setSwimming(boolean bool) {
        this.swimming = bool;
    }
    
    public void setSprintSwimming(boolean bool) {
        this.sprintSwimming = bool;
    }
    
    public boolean isSprintSwimming() {
        return sprintSwimming;
    }

    public MythCraft getPlugin() {
        return plugin;
    }
}