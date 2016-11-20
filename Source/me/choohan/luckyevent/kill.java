package me.choohan.luckyevent;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kill implements CommandExecutor{
	
	// Define main and plugin variable
	main plugin;

	public kill(main instance) {
		plugin = instance;
		}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		
		

		// /luckykill
		if(args.length == 0){
		List<String> list = plugin.getConfig().getStringList("KillRows");
	    for (String playerlist : list) {
	      if ((sender instanceof Player))
	      {
	    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("KPrefix")).replaceAll("<player>", sender.getName())));
	      }
	    }
	    return true;
		}
				
			/// / luckykill stats
			if (args[0].equalsIgnoreCase("stats")){

				List<String> list = plugin.getConfig().getStringList("KillCountMSG");
			    for (String playerlist : list) {
			      if ((sender instanceof Player))
			      {
			    	  Player player = (Player) sender;
			    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<pnumber>", main.plugin.getPlayerConfig().getString("Players." + player.getUniqueId() + ".playerkills")).replaceAll("<mnumber>",  main.plugin.getPlayerConfig().getString("Players." + player.getUniqueId() + ".monsterkills"))));
			      }
			    }
			}else
				
				// /luckykill prewards
				if (args[0].equalsIgnoreCase("prewards")){
					List<String> list = plugin.getConfig().getStringList("KillPlayerRewardCMD");
		        	
					Player player = (Player) sender;
					int index = ThreadLocalRandom.current().nextInt(list.size());
		        	String cmd = list.get(index);
		        	int playerkills = main.plugin.getPlayerConfig().getInt("Players." + player.getUniqueId() + ".playerkills");
		        	if (playerkills > 10 || playerkills == 10 ){
		            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("<player>", player.getName()));
		        	main.plugin.getPlayerConfig().set("Players." + player.getUniqueId() + ".playerkills", playerkills - 10);
		        	} else {

						List<String> list2 = plugin.getConfig().getStringList("GetPlayerReward(Error)");
					    for (String playerlist : list2) {
					      if ((sender instanceof Player))
					      {
					    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<pnumber>", main.plugin.getPlayerConfig().getString("Players." + player.getUniqueId() + ".playerkills")).replaceAll("<mnumber>",  main.plugin.getPlayerConfig().getString("Players." + player.getUniqueId() + ".monsterkills"))));
					      }
					  
					    }
					    }
					}
				else
					// /luckykill mrewards
					if (args[0].equalsIgnoreCase("mrewards")){
						List<String> list = plugin.getConfig().getStringList("KillMonsterRewardCMD");
			        	
						Player player = (Player) sender;
						int index = ThreadLocalRandom.current().nextInt(list.size());
			        	String cmd = list.get(index);
			        	int playerkills = main.plugin.getPlayerConfig().getInt("Players." + player.getUniqueId() + ".monsterkills");
			        	if (playerkills > 10 || playerkills == 10 ){
			            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("<player>", player.getName()));
			        	main.plugin.getPlayerConfig().set("Players." + player.getUniqueId() + ".monsterkills", playerkills - 10);
			        	} else {

							List<String> list2 = plugin.getConfig().getStringList("GetMonsterReward(Error)");
						    for (String playerlist : list2) {
						      if ((sender instanceof Player))
						      {
						    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<pnumber>", main.plugin.getPlayerConfig().getString("Players." + player.getUniqueId() + ".playerkills")).replaceAll("<mnumber>",  main.plugin.getPlayerConfig().getString("Players." + player.getUniqueId() + ".monsterkills"))));
						      }
						  
						    }
			        	}
					}else
			        	
		// /luckyevent <anything>
		{

			List<String> list = plugin.getConfig().getStringList("KillRows");
		    for (String playerlist : list) {
		      if ((sender instanceof Player))
		      {
		    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("KPrefix")).replaceAll("<player>", sender.getName())));
		      }
		    }
		}
			
			
			
		return false;
	}
}
