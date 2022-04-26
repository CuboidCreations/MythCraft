![](images/mythcraft.png)

Code for MythCraft Legacy, an RPG plugin made for the Bukkit API developed by Chris Soberon & Jarrod Hammond. 

Be warned, this works for Minecraft 1.7 only and classes haven't been updated for newer versions.

MythCraft is an upcoming plugin implementing various traditional classes, whilst aiming to keep commands to a minimum. It primarily aims to alter the way people play Minecraft, rather than adding command-powered skills. I do this by drawing inspiration from original minecraft features, such as undead burning in the sun, but also from traditional folklore such as the inhuman speed and thoughness of vampires. A primary goal of this plugin is to make sure the player isn't limited by the plugin, but "bent" towards different behaviour.

Read the player manual

Races
Humans
Humans are ordinary players. They don't differ from vanilla players.

Vampires
Vampires are creatures of the night. They feed upon the blood of the living.

[Active]

Can see better at night or in dark places, or when toggled.
Can sprint at inhuman speeds, levelable and controlable.
Have to feed directly off animals.
Corrupt tamed horses into their skeleton and zombie counterparts. (Rideable!)
Can't eat regular food without effects similar to eating zombie meat.
[Passive]

Can gain skills that prevent undead and later dread (scary) creatures of targetting them.
Don't have to breathe, and can thus stay under water indefinitely.
Have to fall further to take fall damage, levelable. [Planned]
[Weakness]

Burn in sunlight over light level 8, unless a potion of fire resistance is consumed.
Take more damage from wooden tools [Planned]
Are harmed greatly by holy water.
[Other]

Vampirism is a disease, and all classes can be afflicted with it by being attacked by an undead enemy.
Mertouched
Mertouched are creatures of the sea and waters. They prefer being underwater, but can also walk the earth. They are great builders.

[Active]

Can swim fast by pressing shift, whilst not standing on a block, levalable and controlable.
Can "sprintswim" by sprinting whilst standing on a block. Uses the food bar and stops when hitting a block similar to regular sprinting.
[Passive]

Can see clearly underwater.
Are partially human, and can thus walk the earth.
Can breathe underwater.
Break blocks normally in water. [Planned]
Elves
Elves are elegant and magical beings. They live longer than other species and prefer building in high places. They are excellent thieves.

[Active]

Turn invisible when sneaking using XP as 'fuel'.
Can climb logs, fences and bricks. (configurable) [Planned]
Can double-jump. [Planned]
Can sprint faster. Not levelable. [Planned]
[Passive]

+10 maximum health. [Planned]
[Weakness]

Weak to meelee attacks.
Dwarves
Dwarves are the tough creatures of the earth. They can deal with anything thrown at them. They make for both excellent miners, warriors, and builders.

[Active]

Can start a mining frenzy, instantly mining anything for a few seconds, levelable. [Planned]
[Passive]

Are skilled in mining, and mine faster, levelable. [Planned]
Do more damage with an axe than with a sword. [Planned]
Can gain a skill that applies regeneration to them once a day, when dropped below 20% health, levelable. [Planned]
[Weakness]

Can't swim, and sink. [Planned]
Demons
Demons are creatures of fire. They are swift like acrobats. They prefer being in the Nether. They make for awesome warriors and thieves.

[Active]

Can shoot fireballs, provided they have them in their inventory. [Planned]
Can warp through walls, levelable, uses XP.
Can alter their momentum. Levelable, uses XP.
Can teleport from the overworld into the top of the nether.
While on fire, they burn any enemy they hit. [Planned]
[Passive]

Immune to fire, but still burn.
Immune to lava.
Don't take fall damage.
Double maximum health in the Nether, also applied to god health. [Planned]
[Weakness]

Take damage in water.
Their ability to alter their momentum interferes with their blocking and archery capacities. (1)
Vampiric demons don't take burn damage in the sun. Instead, they are struck with Weakness II. [Planned]
Valkyries
Valkyries are creatures of the sky. They somewhat lack interest in war, but they make outstanding archers, being able to shoot arrows faster than any other race. Their flight skill allows them to build things others can only dream of.

[Active]

Can fly using XP as 'fuel'.
Can use telekinesis to launch configurable blocks in the air.
Their telekinesis allows them to shoot arrows without a bow, and at great speeds.
They can also shoot multiple arrows at the same time.
[Passive]

Don't take fall damage.
Read more about skills and abilities

Gods and Worship
Introduction
Ascend to the heavens or descend to the depths of hell with the worship system! MythCraft adds a system of worship, allowing players to construct altars in honour of other players! This allows any player to become a god. A player may only construct one altar, but can change his god at any time.

A god gains power through the offering of items. The more offerings a god receives, the more power he receives.

Players are not equal to gods, but they are required for a god to have power. This adds a strategical element to the game: players must keep supporting a god, or he becomes a regular player again.

But it's also possible to raid a god's altars. Two options ensuring full functionality are available for the server operator: Disallow altars in protected areas like Towny plots or WorldGuard regions, or allow breaking them in protected regions.

A god's power is set to zero when he/she dies. The maximum power is defined by how many active worshippers a god has, and increases with each worship action completed. When a player is inactive for a real week (default) he/she does not count towards the god's active worshippers. A god may be inactive for an infite time, without losing worshippers or power.

The ammount of worshippers a god has defines his/her ammount of health, while the ammount of power acts much like the "mana", "magicka" or "MP" from fantasy games.

Read more about Gods & Worship mechanics

Commands and Permissions
After thinking for a while, I decided that allowing people to disable specific skills of this plugin would allow for unbalanced gameplay, and would do more harm than good. Therefore, I will limit the permissions to the essentials.

Command	Function	Permission
/myc	Main MythCraft command. Alias: /mythcraft	MythCraft.MythCraft
/myc info	Prints MythCraft info	MythCraft.info
/myc tree [player]	Prints the player's passive skill tree	MythCraft[.others]
/myc status [player]	Show the player's race, health and status effects	MythCraft.status[.others]
/myc skills	Prints the player's active skills and levels	See individual skills
Permission	Function
MythCraft.vampire	Allows a player to become a vampire
MythCraft.valkyrie	Allows a player to become a valkyrie
Support
BukkitDev ticket system, or add XeiotosQuorzar on Skype.

Source Code
Contact me on Skype (XeiotosQuorzar) if you are interested in seeing the source and have a solid reason. However, this is my first plugin, so please take in account that there are probably better examples to follow, as I devide by zero frequently.

To-Do
...AKA procrastination list.

Fix minor light check bug.
Add commands to disable demon momentum when holding a bow and a sword.
Gods can appoint one or more priests, which can tap into a limited ammount of the god's power.
By default, one god can have one priest per 20 worshippers.
Priests will have the ability to walk in spirit form : Transparant, and unable to change the world, be damaged, or cause damage.
Vampire blood in a bottle, to contract bloodrot instantly.
Altar protection
Add all the godly powers
Find a better way to use the god-creative menu
Upcoming features
These features will not be released in the first version of MythCraft. Instead, I will add them later. Suggestions and bug reports are always welcome! Please do send them in.

I have continued development on MythCraft after a long break! Expect it when a stable version of Bukkit 1.7 is released!

Notes for server operators
Currently: None.

(1) A means to toggle momentum shift for archery is being planned.
