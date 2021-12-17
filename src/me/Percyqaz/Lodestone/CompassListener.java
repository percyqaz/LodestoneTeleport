package me.Percyqaz.Lodestone;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

public class CompassListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void Compass(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (e.getHand() == EquipmentSlot.HAND && item.getType() == Material.COMPASS && e.getAction() == Action.RIGHT_CLICK_AIR) {
            CompassMeta itemMeta = (CompassMeta)item.getItemMeta();
            if (itemMeta.hasLodestone() && itemMeta.isLodestoneTracked()) {
                p.sendMessage(ChatColor.BLUE + "Whoosh!");
                Location pos = itemMeta.getLodestone();
                p.teleport(pos.add(0.5, 1.5, 0.5));
                item.setAmount(item.getAmount() - 1);
                e.setCancelled(true);
            }
        }
    }
}
