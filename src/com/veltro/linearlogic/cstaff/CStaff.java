package com.veltro.linearlogic.cstaff;

import org.bukkit.plugin.java.JavaPlugin;

public class CStaff extends JavaPlugin {

	public void onEnable() {
		getLogger().info("Loading config...");
		saveDefaultConfig();

		getLogger().info("Activating command handler...");
		getCommand("cstaff").setExecutor(new CSCommand(this));
		
		getLogger().info("Enabled!");
	}

	public void onDisable() {
		saveConfig();
		getLogger().info("Disabled!");
	}
}
