package me.choohan.luckyevent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class generalcommand implements CommandExecutor{
	
	// Define main and plugin variable
	main plugin;

	public generalcommand(main instance) {
		plugin = instance;
		}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		
		// /luckyevent
		if(args.length == 0){
		List<String> list = plugin.getConfig().getStringList("GRows");
	    for (String playerlist : list) {
	      if ((sender instanceof Player))
	      {
	    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("Prefix")).replaceAll("<player>", sender.getName())));
	      }
	    }
	    return true;
		}
	    
		
		// /luckyevent version
		if (args[0].equalsIgnoreCase("version")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + " &aThis server using &6Lucky Event V4.0 &aby ChooHan"));
		} else
		// /luckyevent reload
		if (args[0].equalsIgnoreCase("reload")){
			if(sender.hasPermission("luckyevent.hourreward.give")){
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + " &4Reloaded"));
			}else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + " &4You have no permission to do this!"));
			}
		} else
			        	
		// /luckyevent <anything>
		{

			List<String> list = plugin.getConfig().getStringList("GRows");
		    for (String playerlist : list) {
		      if ((sender instanceof Player))
		      {
		    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("Prefix")).replaceAll("<player>", sender.getName())));
		      }
		    }
		}
			
			
			
		return false;
	}
}
