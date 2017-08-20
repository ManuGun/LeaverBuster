package de.manugun.leaverbuster.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.manugun.leaverbuster.listener.OnlineListener;

public class LeaverBuster extends JavaPlugin{
	
	public static ArrayList<Player> NotBan = new ArrayList<>();
	public static LeaverBuster main;
	
	@Override
	public void onEnable() {
		main = this;
		Bukkit.getPluginManager().registerEvents(new OnlineListener(), this);
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Config();
	}
	
	private void Config() {
		File file = new File("plugins//LeaverBuster//config.yml");
		File ordner = new File("plugins//LeaverBuster");
		
		if(!ordner.exists()){
			ordner.mkdirs();
		}
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		cfg.options().header("The command for the gameban will be executed about the spigot server and the time without ban you must be define in seconds");
		
		cfg.addDefault("OnlineWithoutBan", 120);
		cfg.addDefault("BanCMD", "gameban %player%");
		
		cfg.options().copyDefaults(true);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static LeaverBuster getInstance(){
		return main;
	}

}
