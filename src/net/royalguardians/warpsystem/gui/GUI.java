package net.royalguardians.warpsystem.gui;

import net.royalguardians.warpsystem.WarpSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class GUI implements Listener {


    @EventHandler
    public void onInv(InventoryClickEvent e) {
        if (e.getSlotType() == InventoryType.SlotType.OUTSIDE || e.getAction() == InventoryAction.NOTHING
                || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem() == null) {
            return;
        }
        if (e.getClickedInventory().getName().equals("§6Warp-Menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                String item = e.getCurrentItem().getItemMeta().getDisplayName().replace("§", "&");
                Location loc = WarpSystem.warps.get(item).getLoc();
                if (loc != null)
                    e.getWhoClicked().teleport(loc);
            }
        }
    }
}

