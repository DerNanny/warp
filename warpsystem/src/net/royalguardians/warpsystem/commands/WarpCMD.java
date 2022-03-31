package net.royalguardians.warpsystem.commands;

import net.royalguardians.warpsystem.WarpSystem;
import net.royalguardians.warpsystem.files.WarpFile;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        String prefix = "§6[§aWarp-System§6]";
        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                if (args.length == 0) {

                    p.openInventory(WarpFile.getInv());
                } else if (p.hasPermission("warpsystem.admin") && ((args.length == 1) || (args.length == 3) || (args.length > 4))) {
                    p.sendMessage("§c/warp add name slot radius");
                } else if (p.hasPermission("warpsystem.admin") && (args.length == 4)) {
                    if (args[0].equalsIgnoreCase("add")) {
                        try {
                            if (!WarpSystem.getWarpfile().isExist(args[1])) {
                                int slot = Integer.parseInt(args[2]);
                                int radius = Integer.parseInt(args[3]);
                                if (radius != 0) {
                                    Location loc1 = p.getLocation().clone().add(1, 0, 1);
                                    Location loc2 = p.getLocation().clone().subtract(1, 0, 1);
                                    for (int x = loc2.getBlockX(); x < loc1.getBlockX() + 1; x++) {
                                        for (int z = loc2.getBlockZ(); z < loc1.getBlockZ() + 1; z++) {
                                            p.getWorld().getBlockAt(x, p.getLocation().getBlockY() - 1, z).setType(Material.BEDROCK);
                                        }
                                    }
                                }
                                WarpSystem.getWarpfile().addWarp(args[1], p.getItemInHand(), radius, slot, p.getLocation());
                                p.sendMessage(prefix + " §adu hast den Warppunkt " + "§f" +  args[1] + " §4hinzugefügt");
                            }
                        } catch (NumberFormatException e) {
                            p.sendMessage(prefix + "§c/warp add name slot radius");
                        }

                    }


                } else if (p.hasPermission("warpsystem.admin") && (args.length == 2)) {
                    if (args[0].equalsIgnoreCase("remove")) {
                        try {
                            if (WarpSystem.getWarpfile().isExist(args[1])) {
                                WarpSystem.getWarpfile().removeWarp(args[1]);
                                p.sendMessage(prefix + " §adu hast den Warppunkt " + "§f" + args[1] + " §4gelöscht");
                            }
                        } catch (NumberFormatException e) {
                        }
                        return false;
                    }

                } else if (p.hasPermission("warpsystem.admin") && (args.length == 1)) {
                    if (args[0].equalsIgnoreCase("help")) {
                        p.sendMessage(prefix + " §c/warp add name slot radius");
                    }
                }
            }
            return false;
        }

        return false;
    }
}
