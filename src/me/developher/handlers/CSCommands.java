package me.developher.handlers;

import me.developher.cstaff.CSMain;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CSCommands
  implements CommandExecutor
{
	ArrayList<Player> staff = new ArrayList<Player>();
	ArrayList<String> staff2 = new ArrayList<String>();
	ArrayList<Player> donor = new ArrayList<Player>();
	ArrayList<String> donor2 = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		for (Player plr : Bukkit.getServer().getOnlinePlayers()) {
			if (((!CSPermissions.permission.has(plr, "cstaff.staff")) && (!plr.isOp())) || (this.staff.contains(plr))) {
				continue;
			}
			this.staff.add(plr);
		}

		for (Player str : this.staff) {
			if (!this.staff2.contains(str.getName())) {
				this.staff2.add(str.getName());
			}
		}

		for (Player plr : Bukkit.getServer().getOnlinePlayers()) {
			if (((!CSPermissions.permission.has(plr, "cstaff.donor")) && (!plr.isOp())) || (this.donor.contains(plr))) {
				continue;
			}
			this.donor.add(plr);
		}

		for (Player str : this.donor) {
			if (!this.donor2.contains(str.getName())) {
				this.donor2.add(str.getName());
			}
		}
		sendOnlineInfo(sender);
		return true;
	}
	
	public void sendOnlineInfo(CommandSender sender)
	{
		switch (CSMain.config.getInt("ColorScheme"))
		{
			default:
			case 1:
				String playerCount1 = ChatColor.BLUE + "      --=" + ChatColor.DARK_AQUA + " There are " + ChatColor.BLUE + "(" + ChatColor.DARK_AQUA + Bukkit.getOnlinePlayers().length + ChatColor.BLUE + "/" + ChatColor.DARK_AQUA + Bukkit.getMaxPlayers() + ChatColor.BLUE + ") " + ChatColor.DARK_AQUA + "users currently online." + ChatColor.BLUE + " =--";
				
				sender.sendMessage(ChatColor.BLUE + "----------------------[" + ChatColor.DARK_AQUA + "Voxela" + ChatColor.BLUE + "]----------------------");
				sender.sendMessage("");
				sender.sendMessage(ChatColor.BLUE + playerCount1);
				sender.sendMessage("");
				if (this.staff.size() == 0) {
					sender.sendMessage(ChatColor.BLUE + "There are currently no staff members online!");
				}
				else if (!(this.staff.size() == 0)) {
					sender.sendMessage(ChatColor.DARK_AQUA + "Staff Online" + ChatColor.BLUE + " (" + ChatColor.DARK_AQUA + String.valueOf(this.staff.size()) + ChatColor.BLUE + ")" + ChatColor.BLUE + ChatColor.BLUE + ": " + ChatColor.GRAY + this.staff2.toString());
					this.staff.clear();
					this.staff2.clear();
				}
				sender.sendMessage("");
				if (this.donor.size() == 0) {
					sender.sendMessage(ChatColor.BLUE + "There are currently no donors online!");
				}
				else if (!(this.donor.size() == 0)) {
					sender.sendMessage(ChatColor.DARK_AQUA + "Donors Online" + ChatColor.BLUE + " (" + ChatColor.DARK_AQUA + String.valueOf(this.donor.size()) + ChatColor.BLUE + ")" + ChatColor.BLUE + ": " + ChatColor.GRAY + this.donor2.toString());
					this.donor2.clear();
					this.donor.clear();
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.BLUE + "---------------------------------------------------");
				break;
			case 2:
				String playerCount2 = ChatColor.DARK_GREEN + "      --=" + ChatColor.GREEN + " There are " + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + Bukkit.getOnlinePlayers().length + ChatColor.DARK_GREEN + "/" + ChatColor.GREEN + Bukkit.getMaxPlayers() + ChatColor.DARK_GREEN + ") " + ChatColor.GREEN + "users currently online." + ChatColor.DARK_GREEN + " =--";
				
				sender.sendMessage(ChatColor.DARK_GREEN + "----------------------[" + ChatColor.GREEN + "Voxela" + ChatColor.DARK_GREEN + "]----------------------");
				sender.sendMessage("");
				sender.sendMessage(ChatColor.DARK_GREEN + playerCount2);
				sender.sendMessage("");
				if (this.staff.size() == 0) {
					sender.sendMessage(ChatColor.DARK_GREEN + "There are currently no staff members online!");
				}
				else if (!(this.staff.size() == 0)) {
					sender.sendMessage(ChatColor.GREEN + "Staff Online" + ChatColor.DARK_GREEN + " (" + ChatColor.GREEN + String.valueOf(this.staff.size()) + ChatColor.DARK_GREEN + ")" + ChatColor.DARK_GREEN + ChatColor.DARK_GREEN + ": " + ChatColor.GRAY + this.staff2.toString());
					this.staff.clear();
					this.staff2.clear();
				}
				sender.sendMessage("");
				if (this.donor.size() == 0) {
					sender.sendMessage(ChatColor.DARK_GREEN + "There are currently no donors online!");
				}
				else if (!(this.donor.size() == 0)) {
					sender.sendMessage(ChatColor.GREEN + "Donors Online" + ChatColor.DARK_GREEN + " (" + ChatColor.GREEN + String.valueOf(this.donor.size()) + ChatColor.DARK_GREEN + ")" + ChatColor.DARK_GREEN + ": " + ChatColor.GRAY + this.donor2.toString());
					this.donor2.clear();
					this.donor.clear();
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.DARK_GREEN + "---------------------------------------------------");
				break;
			case 3:
				String playerCount3 = ChatColor.BLACK + "      --=" + ChatColor.DARK_GRAY + " There are " + ChatColor.BLACK + "(" + ChatColor.DARK_GRAY + Bukkit.getOnlinePlayers().length + ChatColor.BLACK + "/" + ChatColor.DARK_GRAY + Bukkit.getMaxPlayers() + ChatColor.BLACK + ") " + ChatColor.DARK_GRAY + "users currently online." + ChatColor.BLACK + " =--";
				
				sender.sendMessage(ChatColor.BLACK + "----------------------[" + ChatColor.DARK_GRAY + "Voxela" + ChatColor.BLACK + "]----------------------");
				sender.sendMessage("");
				sender.sendMessage(ChatColor.BLACK + playerCount3);
				sender.sendMessage("");
				if (this.staff.size() == 0) {
					sender.sendMessage(ChatColor.BLACK + "There are currently no staff members online!");
				}
				else if (!(this.staff.size() == 0)) {
					sender.sendMessage(ChatColor.DARK_GRAY + "Staff Online" + ChatColor.BLACK + " (" + ChatColor.DARK_GRAY + String.valueOf(this.staff.size()) + ChatColor.BLACK + ")" + ChatColor.BLACK + ChatColor.BLACK + ": " + ChatColor.GRAY + this.staff2.toString());
					this.staff.clear();
					this.staff2.clear();
				}
				sender.sendMessage("");
				if (this.donor.size() == 0) {
					sender.sendMessage(ChatColor.BLACK + "There are currently no donors online!");
				}
				else if (!(this.donor.size() == 0)) {
					sender.sendMessage(ChatColor.DARK_GRAY + "Donors Online" + ChatColor.BLACK + " (" + ChatColor.DARK_GRAY + String.valueOf(this.donor.size()) + ChatColor.BLACK + ")" + ChatColor.BLACK + ": " + ChatColor.GRAY + this.donor2.toString());
					this.donor2.clear();
					this.donor.clear();
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.BLACK + "---------------------------------------------------");
				break;
			case 4:
				String playerCount4 = ChatColor.DARK_RED + "      --=" + ChatColor.RED + " There are " + ChatColor.DARK_RED + "(" + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.DARK_RED + "/" + ChatColor.RED + Bukkit.getMaxPlayers() + ChatColor.DARK_RED + ") " + ChatColor.RED + "users currently online." + ChatColor.DARK_RED + " =--";
				
				sender.sendMessage(ChatColor.DARK_RED + "----------------------[" + ChatColor.RED + "Voxela" + ChatColor.DARK_RED + "]----------------------");
				sender.sendMessage("");
				sender.sendMessage(ChatColor.DARK_RED + playerCount4);
				sender.sendMessage("");
				if (this.staff.size() == 0) {
					sender.sendMessage(ChatColor.DARK_RED + "There are currently no staff members online!");
				}
				else if (!(this.staff.size() == 0)) {
					sender.sendMessage(ChatColor.RED + "Staff Online" + ChatColor.DARK_RED + " (" + ChatColor.RED + String.valueOf(this.staff.size()) + ChatColor.DARK_RED + ")" + ChatColor.DARK_RED + ChatColor.DARK_RED + ": " + ChatColor.GRAY + this.staff2.toString());
					this.staff.clear();
					this.staff2.clear();
				}
				sender.sendMessage("");
				if (this.donor.size() == 0) {
					sender.sendMessage(ChatColor.DARK_RED + "There are currently no donors online!");
				}
				else if (!(this.donor.size() == 0)) {
					sender.sendMessage(ChatColor.RED + "Donors Online" + ChatColor.DARK_RED + " (" + ChatColor.RED + String.valueOf(this.donor.size()) + ChatColor.DARK_RED + ")" + ChatColor.DARK_RED + ": " + ChatColor.GRAY + this.donor2.toString());
					this.donor2.clear();
					this.donor.clear();
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.DARK_RED + "---------------------------------------------------");
				break;
			case 5:
				String playerCount5 = ChatColor.DARK_PURPLE + "      --=" + ChatColor.LIGHT_PURPLE + " There are " + ChatColor.DARK_PURPLE + "(" + ChatColor.LIGHT_PURPLE + Bukkit.getOnlinePlayers().length + ChatColor.DARK_PURPLE + "/" + ChatColor.LIGHT_PURPLE + Bukkit.getMaxPlayers() + ChatColor.DARK_PURPLE + ") " + ChatColor.LIGHT_PURPLE + "users currently online." + ChatColor.DARK_PURPLE + " =--";
				
				sender.sendMessage(ChatColor.DARK_PURPLE + "----------------------[" + ChatColor.LIGHT_PURPLE + "Voxela" + ChatColor.DARK_PURPLE + "]----------------------");
				sender.sendMessage("");
				sender.sendMessage(ChatColor.DARK_PURPLE + playerCount5);
				sender.sendMessage("");
				if (this.staff.size() == 0) {
					sender.sendMessage(ChatColor.DARK_PURPLE + "There are currently no staff members online!");
				}
				else if (!(this.staff.size() == 0)) {
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Staff Online" + ChatColor.DARK_PURPLE + " (" + ChatColor.LIGHT_PURPLE + String.valueOf(this.staff.size()) + ChatColor.DARK_PURPLE + ")" + ChatColor.DARK_PURPLE + ChatColor.DARK_PURPLE + ": " + ChatColor.GRAY + this.staff2.toString());
					this.staff.clear();
					this.staff2.clear();
				}
				sender.sendMessage("");
				if (this.donor.size() == 0) {
					sender.sendMessage(ChatColor.DARK_PURPLE + "There are currently no donors online!");
				}
				else if (!(this.donor.size() == 0)) {
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Donors Online" + ChatColor.DARK_PURPLE + " (" + ChatColor.LIGHT_PURPLE + String.valueOf(this.donor.size()) + ChatColor.DARK_PURPLE + ")" + ChatColor.DARK_PURPLE + ": " + ChatColor.GRAY + this.donor2.toString());
					this.donor2.clear();
					this.donor.clear();
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.DARK_PURPLE + "---------------------------------------------------");
				break;
			case 6:
				String playerCount6 = ChatColor.GOLD + "      --=" + ChatColor.YELLOW + " There are " + ChatColor.GOLD + "(" + ChatColor.YELLOW + Bukkit.getOnlinePlayers().length + ChatColor.GOLD + "/" + ChatColor.YELLOW + Bukkit.getMaxPlayers() + ChatColor.GOLD + ") " + ChatColor.YELLOW + "users currently online." + ChatColor.GOLD + " =--";
				
				sender.sendMessage(ChatColor.GOLD + "----------------------[" + ChatColor.YELLOW + "Voxela" + ChatColor.GOLD + "]----------------------");
				sender.sendMessage("");
				sender.sendMessage(ChatColor.GOLD + playerCount6);
				sender.sendMessage("");
				if (this.staff.size() == 0) {
					sender.sendMessage(ChatColor.GOLD + "There are currently no staff members online!");
				}
				else if (!(this.staff.size() == 0)) {
					sender.sendMessage(ChatColor.YELLOW + "Staff Online" + ChatColor.GOLD + " (" + ChatColor.YELLOW + String.valueOf(this.staff.size()) + ChatColor.GOLD + ")" + ChatColor.GOLD + ChatColor.GOLD + ": " + ChatColor.GRAY + this.staff2.toString());
					this.staff.clear();
					this.staff2.clear();
				}
				sender.sendMessage("");
				if (this.donor.size() == 0) {
					sender.sendMessage(ChatColor.GOLD + "There are currently no donors online!");
				}
				else if (!(this.donor.size() == 0)) {
					sender.sendMessage(ChatColor.YELLOW + "Donors Online" + ChatColor.GOLD + " (" + ChatColor.YELLOW + String.valueOf(this.donor.size()) + ChatColor.GOLD + ")" + ChatColor.GOLD + ": " + ChatColor.GRAY + this.donor2.toString());
					this.donor2.clear();
					this.donor.clear();
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.GOLD + "---------------------------------------------------");
				break;
		}
	}
}
