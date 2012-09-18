package linearlogic.developher.cstaff;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CSCommandHandler
  implements CommandExecutor
{
	private CSMain plugin;
	public CSCommandHandler(CSMain wr)
	{
		this.plugin = wr;
	}
	
	public static String version;
	ArrayList<Player> staff = new ArrayList<Player>();
	ArrayList<String> staff2 = new ArrayList<String>();
	ArrayList<Player> donor = new ArrayList<Player>();
	ArrayList<String> donor2 = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length == 0)
		{
			sendOnlineInfo(sender);
			return true;
		}
		else
		{
			if (args[0].equalsIgnoreCase("version"))
			{
				if (sender.hasPermission("cstaff.version"))
				{
					version = this.plugin.getDescription().getVersion();
					sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "]" + ChatColor.WHITE + " This server is running version " + version);
					return true;
				}
				sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "]" + ChatColor.RED + " Uh oh. No permissions!");
				return true;
				
			}
			if (args[0].equalsIgnoreCase("reload"))
			{
				if (sender.hasPermission("cstaff.reload"))
				{
					this.plugin.loadConfig();
					sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "]" + ChatColor.GREEN + " Reload complete!");
					return true;
				}
				sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "]" + ChatColor.RED + " Uh oh. No permissions!");
				return true;
			}
			if (args[0].equalsIgnoreCase("help"))
			{
				sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "]" + ChatColor.RED + " Command usage:");
				sender.sendMessage(ChatColor.WHITE + "/staff " + ChatColor.DARK_AQUA + "-->" + ChatColor.GRAY + " Lists staff members and donors online\n" + ChatColor.WHITE + "/staff version " + ChatColor.DARK_AQUA + "-->" + ChatColor.GRAY + " Returns the version of cStaff this server is running\n" + ChatColor.WHITE + "/staff reload " + ChatColor.DARK_AQUA + "-->" + ChatColor.GRAY + " Reloads the config, applying color changes");
				return true;
			}
			sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "]" + ChatColor.RED + " Not sure what you're trying to do. Here's some help:");
			sender.sendMessage(ChatColor.WHITE + "/staff " + ChatColor.DARK_AQUA + "-->" + ChatColor.GRAY + " Lists staff members and donors online\n" + ChatColor.WHITE + "/staff version " + ChatColor.DARK_AQUA + "-->" + ChatColor.GRAY + " Returns the version of cStaff this server is running\n" + ChatColor.WHITE + "/staff reload " + ChatColor.DARK_AQUA + "-->" + ChatColor.GRAY + " Reloads the config, applying color changes");
			return true;
		}
	}
	
	public void sendOnlineInfo(CommandSender sender)
	{
		for (Player plr : Bukkit.getServer().getOnlinePlayers()) {
			if (this.staff.contains(plr)) {
				continue;
			}
			if (!CSMain.config.getBoolean("ops.show-as-staff")) {
				if (!plr.hasPermission("cstaff.staff")) {
					continue;
				}
				this.staff.add(plr);
				continue;
			}
			if ((!plr.hasPermission("cstaff.staff")) && (!plr.isOp())) {
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
			if (this.donor.contains(plr)) {
				continue;
			}
			if (!CSMain.config.getBoolean("ops.show-as-donors")) {
				if (!plr.hasPermission("cstaff.donor")) {
					continue;
				}
				this.donor.add(plr);
				continue;
			}
			if ((!plr.hasPermission("cstaff.donor")) && (!plr.isOp())) {
				continue;
			}
			this.donor.add(plr);
		}

		for (Player str : this.donor) {
			if (!this.donor2.contains(str.getName())) {
				this.donor2.add(str.getName());
			}
		}
		
		switch (CSMain.config.getInt("ColorScheme"))
		{
			default:
			case 1:
				String playerCount1 = ChatColor.BLUE + "      --=" + ChatColor.DARK_AQUA + " There are " + ChatColor.BLUE + "(" + ChatColor.DARK_AQUA + Bukkit.getOnlinePlayers().length + ChatColor.BLUE + "/" + ChatColor.DARK_AQUA + Bukkit.getMaxPlayers() + ChatColor.BLUE + ") " + ChatColor.DARK_AQUA + "users currently online." + ChatColor.BLUE + " =--";
				
				sender.sendMessage(ChatColor.BLUE + "----------------------[" + ChatColor.DARK_AQUA + "cStaff" + ChatColor.BLUE + "]----------------------");
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
				
				sender.sendMessage(ChatColor.DARK_GREEN + "----------------------[" + ChatColor.GREEN + "cStaff" + ChatColor.DARK_GREEN + "]----------------------");
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
				
				sender.sendMessage(ChatColor.BLACK + "----------------------[" + ChatColor.DARK_GRAY + "cStaff" + ChatColor.BLACK + "]----------------------");
				sender.sendMessage("");
				sender.sendMessage(ChatColor.BLACK + playerCount3);
				sender.sendMessage("");
				if (this.staff.size() == 0) {
					sender.sendMessage(ChatColor.DARK_GRAY + "There are currently no staff members online!");
				}
				else if (!(this.staff.size() == 0)) {
					sender.sendMessage(ChatColor.DARK_GRAY + "Staff Online" + ChatColor.BLACK + " (" + ChatColor.DARK_GRAY + String.valueOf(this.staff.size()) + ChatColor.BLACK + ")" + ChatColor.BLACK + ChatColor.BLACK + ": " + ChatColor.GRAY + this.staff2.toString());
					this.staff.clear();
					this.staff2.clear();
				}
				sender.sendMessage("");
				if (this.donor.size() == 0) {
					sender.sendMessage(ChatColor.DARK_GRAY + "There are currently no donors online!");
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
				
				sender.sendMessage(ChatColor.DARK_RED + "----------------------[" + ChatColor.RED + "cStaff" + ChatColor.DARK_RED + "]----------------------");
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
				
				sender.sendMessage(ChatColor.DARK_PURPLE + "----------------------[" + ChatColor.LIGHT_PURPLE + "cStaff" + ChatColor.DARK_PURPLE + "]----------------------");
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
				
				sender.sendMessage(ChatColor.GOLD + "----------------------[" + ChatColor.YELLOW + "cStaff" + ChatColor.GOLD + "]----------------------");
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
			case 7:
				String playerCount7 = ChatColor.DARK_RED + "      --=" + ChatColor.DARK_RED + " There are " + ChatColor.DARK_RED + "(" + ChatColor.DARK_RED + Bukkit.getOnlinePlayers().length + ChatColor.DARK_GREEN + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers() + ChatColor.DARK_GREEN + ") " + ChatColor.DARK_RED + "users currently online." + ChatColor.DARK_GREEN + " =--";
				
				sender.sendMessage(ChatColor.BLACK + "-" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--[" + ChatColor.DARK_RED + "cStaff" + ChatColor.BLACK + "]--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--");
				sender.sendMessage("");
				sender.sendMessage(ChatColor.DARK_RED + playerCount7);
				sender.sendMessage("");
				if (this.staff.size() == 0) {
					sender.sendMessage(ChatColor.DARK_RED + "There are currently no staff members online!");
				}
				else if (!(this.staff.size() == 0)) {
					sender.sendMessage(ChatColor.DARK_RED + "Staff Online" + ChatColor.BLACK + " (" + ChatColor.DARK_RED + String.valueOf(this.staff.size()) + ChatColor.BLACK + ")" + ChatColor.DARK_RED + ChatColor.DARK_RED + ": " + ChatColor.GRAY + this.staff2.toString());
					this.staff.clear();
					this.staff2.clear();
				}
				sender.sendMessage("");
				if (this.donor.size() == 0) {
					sender.sendMessage(ChatColor.RED + "There are currently no donors online!");
				}
				else if (!(this.donor.size() == 0)) {
					sender.sendMessage(ChatColor.DARK_RED + "Donors Online" + ChatColor.DARK_RED + " (" + ChatColor.DARK_RED + String.valueOf(this.donor.size()) + ChatColor.DARK_GREEN + ")" + ChatColor.DARK_RED + ": " + ChatColor.GRAY + this.donor2.toString());
					this.donor2.clear();
					this.donor.clear();
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.BLACK + "-" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + ChatColor.BLACK + "--" + ChatColor.DARK_RED + ChatColor.BLACK + "--" + ChatColor.DARK_RED + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--" + ChatColor.BLACK + "--" + ChatColor.DARK_RED + "--");
				break;
		}
		
		
	}
}
