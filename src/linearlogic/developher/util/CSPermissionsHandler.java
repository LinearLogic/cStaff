package linearlogic.developher.util;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;

public class CSPermissionsHandler
{
	public static Permission permission;

	public static void setup()
	{
		try
			{
				Class.forName("net.milkbowl.vault.permission.Permission");
				RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
				permission = (Permission)permissionProvider.getProvider();
			} catch (ClassNotFoundException localClassNotFoundException) {
				localClassNotFoundException.printStackTrace();
			}
			if (permission == null);
			}

	public static boolean has(CommandSender sender, String node)
	{
		if (sender.isOp()) {
			return true;
		}
		if (permission != null) {
			return permission.has(sender, node);
		}
		return false;
	}
}