package net.royalguardians.warpsystem.files;

import net.royalguardians.warpsystem.listener.Warp;
import net.royalguardians.warpsystem.WarpSystem;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;

public class WarpFile {

    private File warpfile;
    private static YamlConfiguration config;
    public static Inventory inv = Bukkit.createInventory(null, WarpSystem.slot, WarpSystem.name);
    public WarpFile(String name){
  File folder = new File(WarpSystem.getInstance().getDataFolder().getPath());
  if(!folder.exists())folder.mkdir();
        warpfile = new File(WarpSystem.getInstance().getDataFolder().getPath(), name);

            if (!warpfile.exists()) WarpSystem.getInstance().saveResource(name, true);
            config = YamlConfiguration.loadConfiguration(warpfile);
            loadWarps();

        }

        public void addWarp(String name, ItemStack it, int r, int slot, Location loc){
        config.set(name + ".slot", slot);
        config.set(name + ".radius", r);
        config.set(name + ".ItemStack", it);

        config.set(name + ".x", loc.getBlockX() + 0.5);
        config.set(name + ".y", loc.getBlockY());
        config.set(name + ".z", loc.getBlockZ() + 0.5);
        config.set(name + ".Yaw", loc.getYaw());
        config.set(name + ".Pitch", loc.getPitch());
        config.set(name + ".world", loc.getWorld().getName());
        save();
            Warp warp = new Warp(loc, r, slot);
            WarpSystem.warps.put(name, warp);
            WarpSystem.warplist.add(warp);
            ItemMeta m = it.getItemMeta();
            m.setDisplayName(name.replace("&", "§"));
            it.setItemMeta(m);
            if(WarpSystem.slot>=slot){
                inv.setItem(slot, it);
            }
        }
        public void removeWarp(String name){
        config.set(name, null);
        save();
        Warp w = WarpSystem.warps.get(name);
        inv.setItem(w.getSlot(), new ItemStack(Material.AIR));
        WarpSystem.warplist.remove(w);
        WarpSystem.warps.remove(name);
        }
        public static void loadWarps() {
            for (String name : config.getConfigurationSection("").getKeys(false)) {
                int slot = config.getInt(name + ".slot");
                if (WarpSystem.slot >= slot) {
                double x = config.getDouble(name + ".x");
                int y = config.getInt(name + ".y");
                double z = config.getDouble(name + ".z");
                float Yaw = (float) config.getDouble(name + ".Yaw");
                float Pitch = (float) config.getDouble(name + ".Pitch");
                String world = config.getString(name + ".world");
                int r = config.getInt(name + ".radius");
                ItemStack it = config.getItemStack(name + ".ItemStack");
                Location loc = new Location(Bukkit.getWorld(world), x, y, z, Yaw, Pitch);
                Warp warp = new Warp(loc, r, slot);
                WarpSystem.warps.put(name, warp);
                WarpSystem.warplist.add(warp);
                    ItemMeta m = it.getItemMeta();
                    m.setDisplayName(name.replace("&", "§"));
                    it.setItemMeta(m);
                    inv.setItem(slot, it);
                    }
                }
            }

        public static Inventory getInv(){
        Inventory copy = Bukkit.createInventory(null, inv.getSize(), inv.getName());
        copy.setContents(inv.getContents());
        return copy;
        }

        public boolean isExist(String name){
        if (config.getString(name + ".world")!=null) return true;
        return false;
        }
        public void save(){
        try {
            config.save(warpfile);
        }catch(IOException e){
            e.printStackTrace();
        }

        }

    public File getWarpfile() {
        return warpfile;
    }

    public  YamlConfiguration getConfig() {
        return config;
    }
}


