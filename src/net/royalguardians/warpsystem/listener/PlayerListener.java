package net.royalguardians.warpsystem.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static net.royalguardians.warpsystem.listener.ProtectionListener.isInWarp;

public class PlayerListener implements Listener {

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        if (isInWarp(e.getEntity().getLocation()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        Player target = (Player) e.getEntity();
        Player killer = ((Player) e.getEntity()).getKiller();
        if (isInWarp(e.getEntity().getLocation()))
            if (e.getDamager().hasPermission("warpsystem.damage"))
                target.getLastDamage();
         else {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onFall(EntityDamageEvent e){
        if (isInWarp(e.getEntity().getLocation())) {
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL){
                e.setCancelled(true);
            }
        }
    }
}








