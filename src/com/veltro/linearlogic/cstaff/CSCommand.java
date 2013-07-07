package com.veltro.linearlogic.cstaff;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CSCommand implements CommandExecutor {

	private final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "] ";
	private CStaff plugin;

	public CSCommand(CStaff instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sendOnlineInfo(sender);
			return true;
		}
		if (args[0].equalsIgnoreCase("version")) {
			sender.sendMessage(prefix + "Running " + ChatColor.DARK_AQUA + "v" +
					plugin.getDescription().getVersion() + ChatColor.GRAY + " by LinearLogic and Developher.");
			return true;
		}
		if (args[0].equalsIgnoreCase("reload")) {
			if (sender instanceof Player && !sender.hasPermission("cstaff.reload"))
				return msgNoPerms(sender);
			plugin.reloadConfig();
			sender.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
			return true;
		}
		if (args[0].equalsIgnoreCase("help") || args[0].equals("?")) {
			sender.sendMessage(prefix + ChatColor.DARK_AQUA + " Commands:\n" + ChatColor.AQUA + "/staff" +
					ChatColor.GRAY + " - lists online staff members and donors\n" + ChatColor.AQUA + "/cstaff " +
					"reload" + ChatColor.GRAY + " - reloads the config, applying color scheme changes\n" +
					ChatColor.AQUA + "/cstaff version" + ChatColor.GRAY + " - displays version and authors");
			return true;
		}
		sender.sendMessage(prefix + "Command not recognized. Type " + ChatColor.AQUA + "/cstaff help" +
				ChatColor.GRAY + " for assistance.");
		return true;
	}

	public void sendOnlineInfo(CommandSender sender) {
		ArrayList<String> staff = new ArrayList<String>(), donors = new ArrayList<String>();
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			if (p.hasPermission("cstaff.staff") || (p.isOp() && plugin.getConfig().getBoolean("ops.show-as-staff")))
				staff.add(p.getName());
			if (p.hasPermission("cstaff.donor") || (p.isOp() && plugin.getConfig().getBoolean("ops.show-as-donors")))
				staff.add(p.getName());
		}
		ChatColor c1, c2;
		switch (plugin.getConfig().getInt("ColorScheme")) {
			default:
			case 1:
				c1 = ChatColor.BLUE;
				c2 = ChatColor.DARK_AQUA;
				break;
			case 2:
				c1 = ChatColor.DARK_GREEN;
				c2 = ChatColor.GREEN;
				break;
			case 3:
				c1 = ChatColor.BLACK;
				c2 = ChatColor.DARK_GRAY;
				break;
			case 4:
				c1 = ChatColor.DARK_RED;
				c2 = ChatColor.RED;
				break;
			case 5:
				c1 = ChatColor.DARK_PURPLE;
				c2 = ChatColor.LIGHT_PURPLE;
				break;
			case 6:
				c1 = ChatColor.GOLD;
				c2 = ChatColor.YELLOW;
				break;
		}
		sender.sendMessage(c1 + "----------------------[" + c2 + "cStaff" + c1 + "]----------------------\n\n      --=" +
			c2 + " There are " + c1 + "(" + c2 + plugin.getServer().getOnlinePlayers().length + c1 + "/" + c2 +
			plugin.getServer().getMaxPlayers() + c1 + ") " + c2 + "users currently online." + c1 + " =--\n\n" +
			(staff.size() == 0 ? c1 + "There are currently no staff members online!" : c2 + "Staff online" + c1 +
			" (" + c2 + staff.size() + c1 + "): " + ChatColor.GRAY + staff.toString()) + "\n\n" + (donors.size() == 0
			? c1 + "There are currently no donors online!" : c2 + "Donors online" + c1 + " (" + c2 + donors.size()
			+ c1 + "):" + ChatColor.GRAY + donors.toString()) + c1 + "\n\n-----------------------------------------" +
			"----------");
	}

	public boolean msgNoPerms(CommandSender sender) {
		sender.sendMessage(prefix + ChatColor.RED + "Access denied!");
		return true;
	}
}
