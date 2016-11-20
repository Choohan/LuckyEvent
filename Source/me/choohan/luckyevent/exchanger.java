package me.choohan.luckyevent;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class exchanger implements CommandExecutor{
	
	// Define main and plugin variable
	main plugin;

	public exchanger(main instance) {
		plugin = instance;
		}
	

	public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {

    	Player player = (Player) sender;
    	ItemStack air = player.getInventory().getItemInMainHand();
    	if (air.getType() == Material.AIR){
    		player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("exprefix") + "You must holding a item to exchange"));
    	}else{

    		int cooldownTime = 3600; // Get number of seconds from wherever you want
            if(cooldowns.containsKey(sender.getName())) {
                long secondsLeft = ((cooldowns.get(sender.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                long minutesLeft = secondsLeft / 60;
                if(secondsLeft>0) {
                    // Still cooling down
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("exprefix") + "&bYou cant use that commands for another &e&L" +  minutesLeft + " &bminutes!"));
                    return true;
                }
            }
            // No cooldown found or cooldown has expired, save new cooldown
            cooldowns.put(sender.getName(), System.currentTimeMillis());
            // Do Command Here
    		player.getInventory().removeItem(air);
        	List<String> list = main.plugin.getConfig().getStringList("EXRewards");
        	int index = ThreadLocalRandom.current().nextInt(list.size());
        	String cmd = list.get(index);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),  cmd.replaceAll("<player>", player.getName()));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("exprefix") + "&fYou succesfully exchange your item"));
    	    
    	}
		return false;
	}
	
}
