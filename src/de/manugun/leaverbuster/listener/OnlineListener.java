package de.manugun.leaverbuster.listener;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.manugun.leaverbuster.main.LeaverBuster;

public class OnlineListener implements Listener {
	
	File file = new File("plugins//LeaverBuster//config.yml");
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		int zeit = cfg.getInt("OnlineWithoutBan");
		
		if(LeaverBuster.NotBan.contains(p)){
			LeaverBuster.NotBan.remove(p);
		}
		Bukkit.getScheduler().runTaskLater(LeaverBuster.main, new Runnable() {
			
			@Override
			public void run() {
				LeaverBuster.NotBan.add(p);
				
			}
		}, zeit*20);
	}
	
	@EventHandler
	public void onBan(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(!LeaverBuster.NotBan.contains(p)){
			String CMD = cfg.getString("BanCMD");
			CMD = CMD.replaceAll("%player%", p.getName());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), CMD);
		}
	}

}
