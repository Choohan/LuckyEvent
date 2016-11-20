package me.choohan.luckyevent;
 
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


import me.choohan.luckyevent.*;
 
public class main
  extends JavaPlugin
{
  public Permission playerPermission = new Permission("luckyevent.hourreward.give");
  public Permission hrreload = new Permission("luckyevent.hourreward.reload");
  public Permission giveall = new Permission("luckyevent.hourreward.giveall");
  public Permission eladd = new Permission("luckyevent.election.add");
  private FileConfiguration player;
  private File playerf;
  private FileConfiguration election;
  private File electionf;
  public static main plugin;
 
 
  public void onEnable()
  {
	  // Define the plugin
	  plugin = this;
	  
	  // Create the file
      createFiles();
      
      // Register listener
      new JoinListener(this);
      
      // Plugin Manager
      PluginManager pm = getServer().getPluginManager();
      pm.addPermission(this.playerPermission);
      pm.addPermission(this.hrreload);
      pm.addPermission(this.giveall);
      pm.addPermission(this.eladd);
      
      // Enable Message
      getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefix") + " &6LuckyEvent 1.0 &ahad been enabled!"));
      
      // Command Register
      registercommands();
      
      // Create Config.yml
      if (!new File(getDataFolder(), "config.yml").exists()) {
    	  getConfig().options().copyDefaults(true);
      }
      saveDefaultConfig();
      
      // Scheduler 
      BukkitScheduler scheduler = getServer().getScheduler();
      scheduler.scheduleSyncRepeatingTask(this, new Runnable()
      {
      public void run()
      {
        for (Player p : Bukkit.getOnlinePlayers())
        {
          List<String> list = main.this.getConfig().getStringList("CMD");
          int index = ThreadLocalRandom.current().nextInt(list.size());
          String cmd = (String)list.get(index);
          Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("<player>", p.getPlayer().getName()));
        }
      }
      }, 0L, 72000L);
  }
 
  public void onDisable()
  {
	  
	  // Disable Message
	  getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefix") + " &6LuckyEvent 1.0 &ahad been disabled!"));
   
	  saveDefaultConfig();
	  
	  // Prevent memmory from leeking
	  plugin = null;
  }
 
  private void registercommands()
  {
    getCommand("luckyevent").setExecutor(new generalcommand(this));
    getCommand("luckyhour").setExecutor(new luckydraw(this));
    getCommand("luckykill").setExecutor(new kill(this));
    getCommand("election").setExecutor(new election(this));
    getCommand("luckyexchange").setExecutor(new exchanger(this));
  }
 
  public FileConfiguration getPlayerConfig() {
      return this.player;
  }
 
  public FileConfiguration getElectionConfig(){
	  return this.election;
  }
  private void createFiles(){
     
	  // Define player.yml
      playerf = new File(getDataFolder(), "player.yml");
      
      // Define election.yml
      electionf = new File(getDataFolder(), "election.yml");
      
      
      // Create player.yml
      if(!playerf.exists()) {
          playerf.getParentFile().mkdirs();
          saveResource("player.yml", false);
      }
      
      // Create election.yml
      if(!electionf.exists()){
    	  electionf.getParentFile().mkdirs();
    	  saveResource("election.yml", false);
      }
     
      
      // Load player.yml
      player = new YamlConfiguration();
      try{
          player.load(playerf);
      } catch (IOException e){
          e.printStackTrace();
      } catch (InvalidConfigurationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
      
      // Load election.yml
      election = new YamlConfiguration();
      try{
    	  election.load(electionf);
    	  
      } catch (IOException e){
    	  e.printStackTrace();
      } catch (InvalidConfigurationException e){
    	  e.printStackTrace();
      }
     
  }
  public File getPlayerConfigFile() {
      return this.playerf;
  }
 
  public File getElectionConfigFile(){
	  return this.electionf;
  }
