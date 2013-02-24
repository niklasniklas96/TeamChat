package listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.YonasCode.Teamchat.Main;

public class TeamChat extends Main implements Listener {
	
	@EventHandler
	public void onTeamChatEvent(AsyncPlayerChatEvent event){
		
		if(IsPlayerInTC(event.getPlayer())){
			if(!(event.getMessage().startsWith("@"))){
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(IsPlayerInTC(p)){
						p.sendMessage(Tag + ChatColor.GOLD + event.getPlayer().getName() + ": " + ChatColor.AQUA + event.getMessage());
						event.setCancelled(true);
					}
				}
			} else {
				event.setMessage(event.getMessage().replaceAll("@", ""));
			}
		}
		
	}
	
}
