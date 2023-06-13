# What's this?

A Minecraft plugin for Spigot/PaperMC that listens for a right click with a compass

**When you right-click with a lodestone compass:**
- You are teleported **on top** of the lodestone it points to
- It says "Whoosh!" in the chat
- The compass is consumed (This can be turned off in the config)

**You must**
- Right click into air, not on a block
- Have a valid lodestone compass, pointing to an existing lodestone (in **any dimension**)
- Ensure that there is nothing directly above the lodestone yourself, **the plugin will not check**

**Things you can configure**
- The messages for teleports/reasons why you cannot teleport, for if your server is not in English
- How long you must wait (a cooldown) before teleporting again
- How long you must wait and stand still (a warmup) after clicking the compass before the teleportation takes effect
- Enable or disable teleports with a recovery compass. If you have died, it will teleport you to where you last died
- Enable or disable teleporting between dimensions with compasses
- Enable or disable compasses being consumed when teleporting
- NEW: Enable or disable teleporting with compasses via the inventory. To do this, Shift + Right click the compass or stack of compasses

<img alt="Preview gif" src="https://github.com/percyqaz/LodestoneTeleport/blob/master/demo.gif?raw=true" />

I wrote this for my private server, but it's here and on the Spigot forums for anyone else that may find it useful.

SEE ALSO: A [Data Pack](https://github.com/NicolasBissig/Lodeport) that does something very similar (not made by me!). 

You can get the .jar file [here](https://github.com/percyqaz/LodestoneTeleport/releases/) - Versions available for 1.16.2 - 1.20.1+
