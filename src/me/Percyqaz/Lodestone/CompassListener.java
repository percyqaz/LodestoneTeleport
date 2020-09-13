package me.Percyqaz.Lodestone;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class CompassListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void Compass(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        if (e.getHand() == EquipmentSlot.HAND && i.getType() == Material.COMPASS && e.getAction() == Action.RIGHT_CLICK_AIR) {
            net.minecraft.server.v1_16_R2.ItemStack item = CraftItemStack.asNMSCopy(i);
            NBTTagCompound tag = item.getOrCreateTag();
            if (tag.hasKey("LodestonePos")) {
                p.sendMessage(ChatColor.BLUE + "Whoosh!");
                NBTTagCompound pos = tag.getCompound("LodestonePos");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                        "execute as " + p.getName()
                                + " in " + tag.getString("LodestoneDimension")
                                + " run tp " + pos.getInt("X") + " " + (pos.getInt("Y") + 2) + " " + pos.getInt("Z"));
                i.setAmount(i.getAmount() - 1);
                e.setCancelled(true);
            }
        }
    }
}
