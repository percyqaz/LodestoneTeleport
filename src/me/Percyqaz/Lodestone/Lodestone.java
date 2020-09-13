package me.Percyqaz.Lodestone;

import org.bukkit.plugin.java.JavaPlugin;

public class Lodestone extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getLogger().info("Listening to right clicks");
        getServer().getPluginManager().registerEvents(new CompassListener(), this);
    }
}
