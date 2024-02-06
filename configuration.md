# Lodestone Teleport configuration guide

In the folder where you run your Minecraft server, under `plugins/Lodestone` you will find a file called `config.yml`  
It can be edited with any text editor!

If you are using a server hosting provider you may access your plugin config a different way, perhaps through some menu when managing your server

It will look something like this:
```yml
cooldownSeconds: 60
warmupSeconds: 2
enableDimensionalTravel: true
enableRecoveryCompass: true
showMessagesInActionBar: true
allowUsingCompassFromInventory: false
allowUsingCompassFromItemFrame: false
teleportationConsumesCompass: true
teleportFailedCompassNotInHand: §4Teleport cancelled because the compass is no longer in your hand!
teleportFailedDifferentDimension: §4You cannot teleport to another dimension!
teleportFailedMovedBeforeTeleport: §4Teleport cancelled because you moved!
teleportFailedWaitForCooldown: §4You cannot teleport for another %cooldown% seconds!
teleportSucceeded: §9Whoosh!
teleportSucceededNamedLocation: §9Whoosh! Teleported to §a%location%
teleportWarmup: §2Teleporting in %warmup% seconds, please stand still!
```

## What these settings do:

| Setting | Default value | What it does |
|---------|---------------|--------------|
| `cooldownSeconds` | `60` | Players must wait this many seconds before they can teleport with another compass. Can't be lower than the warmup time. Set it to 0 to disable having any cooldown time.
| `warmupSeconds` | `2` | This is how many seconds a player must wait, standing still, after right-clicking with a compass before they get teleported. Set it to 0 to disable having any warmup time.
| `enableDimensionalTravel` | `true` | Set it to `false` to disable teleporting to a different dimension from the one you are in.
| `enableRecoveryCompass` | `true` | Set it to `false` to disable recovery compass teleporting. Teleporting with a recovery compass will take you to where you last died.
| `showMessagesInActionBar` | `true` | Set it to `false` to make all of this plugin's messages appear in the chat instead of in the action bar.
| `allowUsingCompassFromInventory` | `false` | Set it to `true` to enable teleporting via compasses in inventories and containers. Shift+right click on any stack of lodestone compasses (or a recovery compass if enabled) to teleport to that location.
| `allowUsingCompassFromItemFrame` | `false` | Set it to `true` to enable teleporting via compasses in item frames. Right click on an item frame containing a lodestone compass to teleport to that location. **Note: The compass is not consumed, even if you have the option for this turned on!**
| `teleportationConsumesCompass` | `true` | Set it to `false` to keep your lodestone compass after teleporting. When `true`, teleporting using a lodestone compass (or recovery compass if enabled) will destroy the compass, encouraging you to keep a stack on you.
| `teleportFailedCompassNotInHand` | `§4Teleport cancelled because the compass is no longer in your hand!` | This message is shown if the warmup timer ends but you are no longer holding the compass to teleport with. `§` is the symbol for color codes so `§4` makes this message red.
| `teleportFailedDifferentDimension` | `§4You cannot teleport to another dimension!` | This message is shown if `enableDimensionalTravel` is `false` and you try to teleport to another dimension. `§` is the symbol for color codes so `§4` makes this message red.
| `teleportFailedMovedBeforeTeleport` | `§4Teleport cancelled because you moved!` | This message is shown if the warmup timer ends but you have moved or died before the teleport can happen. `§` is the symbol for color codes so `§4` makes this message red.
| `teleportFailedWaitForCooldown` | `§4You cannot teleport for another %cooldown% seconds!` | This message is shown to players who try to teleport before their cooldown time is up. The text `%cooldown%` is swapped with the number of seconds remaining when shown.
| `teleportSucceeded` | `§9Whoosh!` | This message is shown to players when they teleport.
| `teleportSucceededNamedLocation` | `§9Whoosh! Teleported to §a%location%` | This message is shown to players when they teleport using a compass with a custom name. The text `%location%` is swapped with the name of the compass when shown.
| `teleportWarmup` | `§2Teleporting in %warmup% seconds, please stand still!` | This message is shown when you start to teleport if warmup timer is enabled. The text `%warmup%` is swapped with the number of seconds when shown. 

> [!NOTE]
> Any of these messages can be disabled by setting it to an empty string, to do this put `''` (those are single quotes) as the value  
> **If you just delete everything after the colon**, like `teleportSucceeded: `, **it will reset the message to default and NOT disable the message**

Need another configuration option or found a bug? Feel free to [open an issue](https://github.com/percyqaz/LodestoneTeleport/issues) and I'll take a look
