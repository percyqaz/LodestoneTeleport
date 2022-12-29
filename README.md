# What's this?

A Minecraft plugin for Spigot/PaperMC that listens for a right click with a compass

**When you right-click with a lodestone compass:**
- You are teleported **on top** of the lodestone it points to
- It says "Whoosh!" in the chat
- The compass is consumed

**You must**
- Right click into air, not on a block
- Have a valid lodestone compass, pointing to an existing lodestone (in **any dimension**)
- Ensure that there is nothing directly above the lodestone yourself, **the plugin will not check**

**Other features**
- Configurable cooldown times (and warmup times where you must stand still)
- Configurable messages for teleports/reasons why you cannot teleport
- NEW: You can do the same with a recovery compass. If you have died, it will teleport you to where you last died
- How you balance your server so that people actually have any recovery compasses to spend is up to you
- Recovery compass teleports can be turned off in the config

I wrote this for my private server, but it's here for anyone else that may find it useful -  When I googled around (at time of creation) I couldn't find anything.

SEE ALSO: A [Data Pack](https://github.com/NicolasBissig/Lodeport) that does something very similar (not made by me!). 

You can get the .jar file [here](https://github.com/percyqaz/LodestoneTeleport/releases/) - Versions available for 1.16.2 - 1.19.1+
