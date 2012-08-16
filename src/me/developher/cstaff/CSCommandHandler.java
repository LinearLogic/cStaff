package me.developher.cstaff;

import java.util.ArrayList;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CSCommandHandler
  implements CommandExecutor
{
	ArrayList<Player> staff = new ArrayList();
	ArrayList<String> staff2 = new ArrayList();
	ArrayList<Player> donor = new ArrayList();
	ArrayList<String> donor2 = new ArrayList();

	public boolean onCommand(CommandSender sender, Command c, String cL, String[] args)
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

		for (Player String : this.donor) {
			if (!this.donor2.contains(String.getName())) {
				this.donor2.add(String.getName());
			}
		}

     String allplayers = ChatColor.BLUE + "      --=" + ChatColor.DARK_AQUA + 
       " There are " + ChatColor.BLUE + "(" + ChatColor.DARK_AQUA + 
       Bukkit.getOnlinePlayers().length + ChatColor.BLUE + "/" + 
       ChatColor.DARK_AQUA + Bukkit.getMaxPlayers() + ChatColor.BLUE + 
       ") " + ChatColor.DARK_AQUA + "users currently online." + 
       ChatColor.BLUE + " =--";

     sender.sendMessage(ChatColor.BLUE + "----------------------[" + 
       ChatColor.DARK_AQUA + "Voxela" + ChatColor.BLUE + 
       "]-----------------------");
     sender.sendMessage("");
     sender.sendMessage(ChatColor.BLUE + allplayers);
     if (this.staff.size() == 0) {
       sender.sendMessage("");
       sender.sendMessage(ChatColor.BLUE + 
         "There are currently no staff online!");
       sender.sendMessage("");
       sender.sendMessage(ChatColor.DARK_AQUA + "Donors Online" + ChatColor.BLUE + " (" + ChatColor.DARK_AQUA + String.valueOf(this.donor.size()) + ChatColor.BLUE + ")" + ChatColor.BLUE + ": " + ChatColor.GRAY + this.donor2.toString());
       this.donor2.clear();
       this.donor.clear();
       sender.sendMessage("");
       sender.sendMessage(ChatColor.BLUE + 
         "-------------------------------------------------");

       return true;
    }
     sender.sendMessage("");
     sender.sendMessage(ChatColor.DARK_AQUA + 
       "Staff Online" + ChatColor.BLUE + " (" + ChatColor.DARK_AQUA + String.valueOf(this.staff.size()) + ChatColor.BLUE + ")" + ChatColor.BLUE + 
       ChatColor.BLUE + 
       ": " + 
       ChatColor.GRAY + 
       this.staff2.toString());
     this.staff.clear();
     this.staff2.clear();
     sender.sendMessage("");
     sender.sendMessage(ChatColor.DARK_AQUA + "Donors Online" + ChatColor.BLUE + " (" + ChatColor.DARK_AQUA + String.valueOf(this.donor.size()) + ChatColor.BLUE + ")" + ChatColor.BLUE + ": " + ChatColor.GRAY + this.donor2.toString());
     this.donor2.clear();
     this.donor.clear();
     sender.sendMessage("");
     sender.sendMessage(ChatColor.BLUE + 
       "----------------------------------------------------");

     return true;
  }
}
