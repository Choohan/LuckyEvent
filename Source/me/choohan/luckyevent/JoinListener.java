package me.choohan.luckyevent;
 
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.choohan.luckyevent.*;
 
public class JoinListener implements Listener{
	
	// Define main and plugin variable
    main plugin;
 
    public void onEnable(){
    	
    	// register Events
 
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(this, plugin);
    }
 
    public JoinListener(main main) {
        // TODO Auto-generated constructor stub
        main.getServer().getPluginManager().registerEvents(this, main);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
       
    	// Define player variable
        Player player = e.getPlayer();
        
        // Define PlayerUUID variable
    	UUID PlayerUUID = player.getUniqueId();
    	
    	// Add UUID to player.yml
        if (!main.plugin.getPlayerConfig().isSet("Players." + PlayerUUID)){
        
        main.plugin.getPlayerConfig().set("Players." + player.getUniqueId() + ".monsterkills", 0);
        main.plugin.getPlayerConfig().set("Players." + player.getUniqueId() + ".playerkills", 0);
        
        main.plugin.saveDefaultConfig();
        
        try {
           main.plugin.getPlayerConfig().save(main.plugin.getPlayerConfigFile());
        } catch (IOException fpe) {
           //Whatever you want to print/etc for error purposes
        }
         
        }
    }
 
    @EventHandler
    public void killMonster(EntityDeathEvent e){
    	
    	// Monster Killed
    	Entity deadEntity = e.getEntity();
    	// Killer
    	Entity killer = e.getEntity().getKiller();
    	
    	// Monster Death Response 
    	if (killer instanceof Player &&  deadEntity instanceof Monster){
    		
    		// Define player
    		Player player = (Player) killer;
    		
    		// Define killcount
    		int killcount = main.plugin.getPlayerConfig().getInt("Players." + player.getUniqueId() + ".monsterkills");
    		
    		// Response
    		main.plugin.getPlayerConfig().set("Players." + player.getUniqueId() + ".monsterkills", killcount + 1);

            main.plugin.saveDefaultConfig();
            
            try {
               main.plugin.getPlayerConfig().save(main.plugin.getPlayerConfigFile());
            } catch (IOException fpe) {
               //Whatever you want to print/etc for error purposes
            }
             
            
    	
    	}
    }
   
    @EventHandler
    public void killPlayer(EntityDeathEvent e){
    	
    	// Player killed
    	Entity deadEntity = e.getEntity();
    	
    	// Killer
    	Entity killer = e.getEntity().getKiller();
    	
    	
    	// Player Death Response
    	if (killer instanceof Player && deadEntity instanceof Player){
    		
    		// Define player
    		Player player = (Player) killer;
    		
    		// Define killcount
    		int killcount = main.plugin.getPlayerConfig().getInt("Players." + player.getUniqueId() + ".playerkills");
    		
    		// Response
    		main.plugin.getPlayerConfig().set("Players." + player.getUniqueId() + ".playerkills", killcount + 1);
    		

            main.plugin.saveDefaultConfig();
            
            try {
               main.plugin.getPlayerConfig().save(main.plugin.getPlayerConfigFile());
            } catch (IOException fpe) {
               //Whatever you want to print/etc for error purposes
            }
             
            
    	}
    	
    }
    
   @EventHandler
   public void PlayerDeath (PlayerDeathEvent e){

   		// Define Death Player
	   	Player deathplayer = (Player) e.getEntity();

		
		// Define killcount
		int killcount2 = main.plugin.getPlayerConfig().getInt("Players." + deathplayer.getUniqueId() + ".playerkills");

		int killcount3 = main.plugin.getPlayerConfig().getInt("Players." + deathplayer.getUniqueId() + ".monsterkills");
		
		// Response
		main.plugin.getPlayerConfig().set("Players." + deathplayer.getUniqueId() + ".playerkills", killcount2 - 1);
		main.plugin.getPlayerConfig().set("Players." + deathplayer.getUniqueId() + ".monsterkills", killcount3 - 1);
		

       main.plugin.saveDefaultConfig();
       
       try {
          main.plugin.getPlayerConfig().save(main.plugin.getPlayerConfigFile());
       } catch (IOException fpe) {
          //Whatever you want to print/etc for error purposes
       }
   }
}
