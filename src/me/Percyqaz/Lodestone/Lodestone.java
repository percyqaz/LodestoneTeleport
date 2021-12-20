package me.Percyqaz.Lodestone;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Lodestone extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("cooldownSeconds", 60);
        config.addDefault("warmupSeconds", 2);
        config.addDefault("teleportFailedWaitForCooldown", "§4You cannot teleport for another %cooldown% seconds!");
        config.addDefault("teleportFailedMovedBeforeTeleport", "§4Teleport cancelled because you moved!");
        config.addDefault("teleportFailedCompassNotInHand", "§4Teleport cancelled because the compass is no longer in your hand!");
        config.addDefault("teleportSucceeded", "§9Whoosh!");
        config.addDefault("teleportSucceededNamedLocation", "§9Whoosh! Teleported to §a%location%");

        config.options().copyDefaults(true);
        saveConfig();

        this.getLogger().info("Listening to right clicks");
        getServer().getPluginManager().registerEvents(new CompassListener(this, config), this);
    }
}
