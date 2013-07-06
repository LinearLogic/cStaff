package com.veltro.linearlogic.cstaff;

import org.bukkit.plugin.java.JavaPlugin;

public class CSMain extends JavaPlugin {

	public CSCommandHandler csch = new CSCommandHandler(this);
	public static CSMain plugin = null;

	public void onEnable() {
		getLogger().info("Loading config...");
		saveDefaultConfig();

		getLogger().info("Activating command handler...");
		plugin = this;
		getCommand("cstaff").setExecutor(new CSCommandHandler(this));
		
		getLogger().info("Enabled!");
	}

	public void onDisable() {
		saveConfig();
		getLogger().info("Disabled!");
	}

	public static CSMain getInstance() {
		return plugin;
    }
}
