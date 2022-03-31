package net.royalguardians.warpsystem.listener;

import net.royalguardians.warpsystem.WarpSystem;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ProtectionListener implements Listener {
// mit allem was spieler interagiert

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = (Player) e.getPlayer();
        if(!p.hasPermission("warpsystem.admin"))
        e.setCancelled(isInWarp(e.getBlock().getLocation()));
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = (Player) e.getPlayer();
        if(!p.hasPermission("warpsystem.admin"))
            e.setCancelled(isInWarp(e.getBlock().getLocation()));
    }

    public static boolean isInWarp(Location pLoc) {
        for (Warp w : WarpSystem.warplist){
            if (pLoc.getX() >= w.getLoc().getX() + w.getR()) continue;
            if (pLoc.getX() < w.getLoc().getX() - w.getR() - 1) continue;
            if (pLoc.getZ() >= w.getLoc().getZ() + w.getR()) continue;
            if (pLoc.getZ() < w.getLoc().getZ() - w.getR() - 1) continue;
            return true;
        }
        return false;
        }
    }










