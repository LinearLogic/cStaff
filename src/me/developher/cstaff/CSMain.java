package me.developher.cstaff;

import java.util.logging.Logger;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CSMain extends JavaPlugin
{
	public static CSMain instance = null;

	public static Logger log = Logger.getLogger("Minecraft");

	public void onEnable()
	{
		instance = this;
		CSPermissions.setup();
		getCommand("staff").setExecutor(new CSCommandHandler());
	}

	public void onDisable()
	{
	}

	public static CSMain getInstance() {
		return instance;
   }
}