package com.veltro.linearlogic.cstaff;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class CSMain extends JavaPlugin {

	public CSCommandHandler csch = new CSCommandHandler(this);
	File configFile;
	public static FileConfiguration config;
	public static CSMain plugin = null;

	public void onEnable() {
		getLogger().info("Loading config.yml...");
		configFile = new File(getDataFolder(), "config.yml");
		try {
            firstRunConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
		config = new YamlConfiguration();
		loadConfig();
		
		getLogger().info("Activating command handler...");
		plugin = this;
		getCommand("cstaff").setExecutor(new CSCommandHandler(this));
		
		getLogger().info("Plugin successfully enabled!");
	}

	public void onDisable() {
		loadConfig();
		saveConfig();
		getLogger().info("Plugin successfully disabled!");
	}

	public static CSMain getInstance() {
		return plugin;
    }
	
//===========================Config file handling===========================//
	private void firstRunConfiguration() throws Exception {
		if(!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
	}
	
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] bBuffer = new byte[1024];
			int len;
			while ((len = in.read(bBuffer)) > 0)
				out.write(bBuffer, 0, len);
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		try {
			config.load(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveConfig() {
		try {
			config.save(configFile);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
