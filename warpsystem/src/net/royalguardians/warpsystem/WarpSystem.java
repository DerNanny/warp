package net.royalguardians.warpsystem;


import net.royalguardians.warpsystem.commands.WarpCMD;
import net.royalguardians.warpsystem.files.WarpFile;
import net.royalguardians.warpsystem.gui.GUI;
import net.royalguardians.warpsystem.listener.PlayerListener;
import net.royalguardians.warpsystem.listener.ProtectionListener;
import net.royalguardians.warpsystem.listener.Warp;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarpSystem extends JavaPlugin {

public static WarpSystem instance;
public static HashMap<String, Warp> warps = new HashMap<>();
public static List<Warp> warplist = new ArrayList<>();
public static String name = "§6Warp-Menu";
public static int slot = 9;
public static WarpFile warpfile;

    @Override
    public void onEnable() {
        instance = this;
        warpfile = new WarpFile("warps.yml");


        getCommand("warp").setExecutor(new WarpCMD());
        Bukkit.getPluginManager().registerEvents(new GUI(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);



    }

    @Override
    public void onDisable() {


    }

    public static WarpSystem getInstance() {
        return instance;
    }

    public static WarpFile getWarpfile() {
        return warpfile;
    }

}
