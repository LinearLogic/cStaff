package linearlogic.developher.cstaff;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import linearlogic.developher.util.CSCommandHandler;
import linearlogic.developher.util.CSLogger;
import linearlogic.developher.util.CSPermissionsHandler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CSMain extends JavaPlugin
{
	File configFile;
	public static FileConfiguration config;
	public static CSMain instance = null;
	public static String version;

	public void onEnable()
	{
		version = getDescription().getVersion();
		CSLogger.logInfo("Initializing cStaff version " + version);
		
		CSLogger.logInfo("Loading config.yml...");
		configFile = new File(getDataFolder(), "config.yml");
		try {
            firstRunConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
		config = new YamlConfiguration();
		loadConfig();
		
		CSLogger.logInfo("Activating command handler...");
		instance = this;
		CSPermissionsHandler.setup();
		getCommand("cstaff").setExecutor(new CSCommandHandler());
		
		CSLogger.logInfo("Plugin successfully enabled!");
	}

	public void onDisable()
	{
		saveConfig();
		CSLogger.logInfo("Plugin successfully disabled!");
	}

	public static CSMain getInstance() {
		return instance;
    }
	
//===========================Config file handling===========================//
	private void firstRunConfiguration() throws Exception
	{
		if(!configFile.exists())
		{
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
	}
	
	private void copy(InputStream in, File file)
	{
		try
		{
			OutputStream out = new FileOutputStream(file);
			byte[] bBuffer = new byte[1024];
			int len;
			while ((len = in.read(bBuffer)) > 0)
			{
				out.write(bBuffer, 0, len);
			}
			out.close();
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadConfig()
	{
		try
		{
			config.load(configFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveConfig()
	{
		try
		{
			config.save(configFile);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}