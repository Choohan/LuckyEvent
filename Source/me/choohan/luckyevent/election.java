package me.choohan.luckyevent;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class election implements CommandExecutor{
	
	// Define main and plugin variable
	main plugin;

	public election(main instance) {
		plugin = instance;
		}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		
		// /election
		if(args.length == 0){
		List<String> list = plugin.getConfig().getStringList("ERows");
	    for (String playerlist : list) {
	      if ((sender instanceof Player))
	      {
	    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerlist.replaceAll("<prefix>", plugin.getConfig().getString("EPrefix")).replaceAll("<player>", sender.getName())));
	      }
	    }
	    return true;
		}
	    
		// /election add
		if (args[0].equalsIgnoreCase("add") ) {
			if(sender.hasPermission("luckyevent.election.add")){
				
			if (args.length == 1){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("electionadd")));
				

			} else {
				String player = args[1];
				main.plugin.getElectionConfig().set("Elector." + player, 0);
				

		        main.plugin.saveDefaultConfig();
		        
		        try {
		           main.plugin.getElectionConfig().save(main.plugin.getElectionConfigFile());
		        } catch (IOException fpe) {
		           //Whatever you want to print/etc for error purposes
		        }
			}
				
			
			}else 
			{
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("EPrefix") + " &4You have no permission to do this!"));
			}
		} else
		
		// /election choose
		if (args[0].equalsIgnoreCase("Choose") ){
			if (args.length == 1){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("electionadd")));
				

			} else {
			
				String player = args[1];
				Player chooser = (Player) sender;
				if (!main.plugin.getElectionConfig().isSet("Elector." + player)){
					
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("electionchoosefail")));
					
				} else 
					if (main.plugin.getElectionConfig().isSet("Player." + chooser.getUniqueId())){
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("electionalreadychoose")));
					} else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("electionchoosed")));
						int amount = main.plugin.getElectionConfig().getInt("Elector." + player);
						main.plugin.getElectionConfig().set("Elector." + player, amount + 1);
						main.plugin.getElectionConfig().set("Player." + chooser.getUniqueId(), player);
						

				        main.plugin.saveDefaultConfig();
				        
				        try {
				           main.plugin.getElectionConfig().save(main.plugin.getElectionConfigFile());
				        } catch (IOException fpe) {
				           //Whatever you want to print/etc for error purposes
				        }
					}
		}
		}else 

			if (args[0].equalsIgnoreCase("list") ) {
				Set keys = main.plugin.getElectionConfig().getConfigurationSection("Elector").getKeys(false);
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lElector: &a" + keys));
}
		return false;
	}
}
