package me.Percyqaz.Lodestone;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import java.util.HashMap;
import java.util.Map;

public class CompassListener implements Listener
{
    Lodestone plugin;
    FileConfiguration config;
    long cooldownTimeMillis;
    long warmupTimeTicks;
    Map<String, Long> cooldowns = new HashMap<>();

    public CompassListener(Lodestone plugin, FileConfiguration config)
    {
        this.config = config;
        this.plugin = plugin;

        cooldownTimeMillis = (long)(config.getInt("cooldownSeconds")) * 1000L;
        warmupTimeTicks = (long)(config.getInt("warmupSeconds")) * 20L;

        // Cooldown must always be at least as long as warmup
        cooldownTimeMillis = Math.max(cooldownTimeMillis, warmupTimeTicks * 50L);
    }

    void resetPlayerCooldown(String name)
    {
        long now = System.currentTimeMillis();
        cooldowns.put(name, now - cooldownTimeMillis);
    }

    /// Returns 0 if no cooldown, else a positive number of seconds cooldown
    int checkPlayerCooldown(String name)
    {
        long now = System.currentTimeMillis();
        if (cooldowns.containsKey(name))
        {
            if (cooldowns.get(name) + cooldownTimeMillis < now)
            {
                cooldowns.put(name, now);
                return 0;
            }
            return (int)((cooldowns.get(name) + cooldownTimeMillis - now) / 1000L) + 1;
        }
        cooldowns.put(name, now);
        return 0;
    }

    void PerformTeleport(Player player, Location oldLoc)
    {
        // Need to check player hasn't moved, is still holding a teleport compass
        if (player.getLocation().distanceSquared(oldLoc) > 0 && !player.isDead())
        {
            player.sendMessage(config.getString("teleportFailedMovedBeforeTeleport"));
            resetPlayerCooldown(player.getName());
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.COMPASS) {
            CompassMeta itemMeta = (CompassMeta) item.getItemMeta();
            if (itemMeta.hasLodestone() && itemMeta.isLodestoneTracked()) {

                String teleportMessage =
                        itemMeta.hasDisplayName()
                                ? config.getString("teleportSucceededNamedLocation").replace("%location%", itemMeta.getDisplayName())
                                : config.getString("teleportSucceeded");
                player.sendMessage(teleportMessage);
                player.spawnParticle(Particle.CLOUD, oldLoc.add(0.0f, 1.0f, 0.0f), 50);

                Location pos = itemMeta.getLodestone();
                player.teleport(pos.add(0.5, 1.5, 0.5));
                item.setAmount(item.getAmount() - 1);

                player.spawnParticle(Particle.CLOUD, player.getLocation().add(0.0f, 1.0f, 0.0f), 50);
                return;
            }
        }

        player.sendMessage(config.getString("teleportFailedCompassNotInHand"));
        resetPlayerCooldown(player.getName());
    }

    /// Returns true if a teleport went through and false otherwise
    Boolean handlePlayerClickCompass(Player player, ItemStack item)
    {
        // Assert: Player's main hand is a compass, they have just right clicked air with it
        // Assert: Item passed in is the main-hand compass

        CompassMeta itemMeta = (CompassMeta)item.getItemMeta();
        if (itemMeta.hasLodestone() && itemMeta.isLodestoneTracked())
        {
            int cooldown = checkPlayerCooldown(player.getName());
            if (cooldown > 0)
            {
                player.sendMessage(config.getString("teleportFailedWaitForCooldown").replace("%cooldown%", String.valueOf(cooldown)));
                return false;
            }

            // Success: Player has right clicked with a valid lodestone compass
            Location oldLoc = player.getLocation();
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
                    plugin,
                    () -> PerformTeleport(player, oldLoc),
                    warmupTimeTicks
            );
            player.spawnParticle(Particle.ENCHANTMENT_TABLE, oldLoc.add(0.0f, 1.0f, 0.0f), 50);
            return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void Compass(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (e.getHand() == EquipmentSlot.HAND && item.getType() == Material.COMPASS && e.getAction() == Action.RIGHT_CLICK_AIR)
        {
            if (handlePlayerClickCompass(p, item)) e.setCancelled(true);
        }
    }
}
