package net.royalguardians.warpsystem.listener;

import org.bukkit.Location;

public class Warp {
    Location loc;
    int r;
    int slot;

    public Warp(Location loc, int r, int slot){
        this.loc = loc;
        this.r = r;
        this.slot = slot;

    }

    public int getSlot() {
        return slot;
    }

    public int getR() {
        return r;
    }



    public Location getLoc() {
        return loc;
    }
}
