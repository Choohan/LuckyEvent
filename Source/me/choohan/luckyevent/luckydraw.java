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

public class luckydraw implements CommandExecutor{
	
	// Define main and plugin variable
	main plugin;

	public luckydraw(main instance) {
		plugin = instance;
		}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		
		// /luckyevent
		if(args.length == 0){
		List<String> list = plugin.getConfig().getStringList("Rows");
	    for (String playerlist : list) {
	      if ((sender instanceof Player))
	      {
	    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("HPrefix")).replaceAll("<player>", sender.getName())));
	      }
	    }
	    return true;
		}
	    
		
		// /luckyevent give
		if (args[0].equalsIgnoreCase("give")){
			
			if(sender.hasPermission("luckyevent.hourreward.give")){
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("HPrefix") + "&aEvery player get the hourly rewards now!"));
	        	
        	List<String> list = plugin.getConfig().getStringList("CMD");
        	int index = ThreadLocalRandom.current().nextInt(list.size());
        	String cmd = list.get(index);
                for(Player p : Bukkit.getOnlinePlayers()){
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("<player>", p.getName()));
    	    }
			}else 
			{
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("HPrefix") + " &4You have no permission to do this!"));
			}
		}else
		// /luckyevent giveall
			if (args[0].equalsIgnoreCase("giveall")){
				
				if(sender.hasPermission("luckyevent.hourreward.giveall")){
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("HPrefix") + "&aEvery player get the hourly rewards now!"));
	        	List<String> list = plugin.getConfig().getStringList("CMD");
	    	    for (String cmdlist : list) {
	                for(Player p : Bukkit.getOnlinePlayers()){
	            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmdlist.replaceAll("<player>", p.getName()));
	    	    }
	    	    }
				}else 
				{
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("HPrefix") + " &4You have no permission to do this!"));
				}
			}else
			        	
		// /luckyevent <anything>
		{

			List<String> list = plugin.getConfig().getStringList("Rows");
		    for (String playerlist : list) {
		      if ((sender instanceof Player))
		      {
		    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("HPrefix")).replaceAll("<player>", sender.getName())));
		      }
		    }
		}
			
			
			
		return false;
	}
}
