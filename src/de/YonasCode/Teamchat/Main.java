package de.YonasCode.Teamchat;

import java.util.HashMap;
import java.util.Map;

import listener.TeamChat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static boolean CanUseGoldenTouch;
	
	public static String Tag           = ChatColor.GREEN + "[TeamChat]";
	public static String noPermissions = Tag + ChatColor.DARK_RED + "Sry, aber du hast keine Rechte um diesen Befehl nutzen zu können.";
	public static HashMap<String, String> teamchat = new HashMap<String, String>();
	
	public void onEnable(){
		System.out.println("[TeamChat] is now enabled.");
		getServer().getPluginManager().registerEvents(new TeamChat(), this);
	}
	
	public void onDisable(){
		System.out.println("[TeamChat] is now disabled.");
	}
	
	public static boolean IsPlayerInTC(Player p){
		boolean r;
			r = teamchat.containsKey(p.getName());
		return r;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		
		if(sender instanceof Player){
			
			Player pl = (Player)sender;
			
			if(commandLable.equalsIgnoreCase("tc") || commandLable.equalsIgnoreCase("teamchat")){
				
				if(pl.hasPermission("teamchat.use")){
					if(args.length == 0){
					if(IsPlayerInTC(pl)){
						teamchat.remove(pl.getName());
						pl.sendMessage(Tag + ChatColor.AQUA + "Du hast erfolgreich die Team Konversation verlassen.");
						for(Player p : getServer().getOnlinePlayers()){
							if(IsPlayerInTC(p)){
								p.sendMessage(Tag + ChatColor.AQUA + "Der Spieler " + ChatColor.GOLD + pl.getName() + ChatColor.AQUA + " hat die Team Konversation verlassen.");
							}
						}
						return true;
					} else {
						teamchat.put(pl.getName(), pl.getName());
						pl.sendMessage(Tag + ChatColor.AQUA + "Du bist erfolgreich der Team Konversation beigetreten.");
						for(Player p : getServer().getOnlinePlayers()){
							if(IsPlayerInTC(p)){
								if(!(p.getName().equalsIgnoreCase(pl.getName()))){
									p.sendMessage(Tag + ChatColor.AQUA + "Der Spieler " + ChatColor.GOLD + pl.getName() + ChatColor.AQUA + " ist der Team Konversation beigetreten.");
								}
							}
						}
						return true;
					}
					} else {
						if(args[0].equalsIgnoreCase("list")){
							String message = "";
							Map<String, String>tclistmap = teamchat;
							String[] tclist = tclistmap.keySet().toArray(new String[tclistmap.size()]);
							for(int i = 0; i < tclist.length; i++){
								message = message + ChatColor.AQUA + tclist[i] + ChatColor.GOLD + " | ";
							}
							pl.sendMessage(Tag + ChatColor.AQUA + "Spieler im Chat: " + message);
						}
						return true;
					}
					
				} else {
					pl.sendMessage(noPermissions);
					return true;
				}
				
			}
			
		}
		
		return CanUseGoldenTouch;
	}

}
