package me.MnomisC.Maze;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Maze extends JavaPlugin {

	Logger logger = Logger.getLogger("Minecraft");
	public static Maze plugin = new Maze();
	public PluginManager pm;
	Settings settings;
	GameManager gm;

	public void onEnable() {
		settings = new Settings(this);
		settings.initializeFiles();
		settings.load();
		gm = new GameManager(this);
		gm.intializeGames();
		pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new BukkitListener(this), this);
	}

	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		ArrayList<Game> games = gm.games;
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("join")) {
				if (args.length > 1) {
					if (Integer.parseInt(args[1]) < games.size() && Integer.parseInt(args[1]) >= 0) {
						for (int i = 0; i < games.size(); i++) {
							if (games.get(i).getPlayerList().contains((Player) sender)) {
								sendMessage((Player) sender, ChatColor.RED
								        + "You are allready in a game!");
								return true;
							}
						}
					} else {

						sendMessage((Player) sender, ChatColor.RED
						        + "Not an arena! Type /maze arenas To see avible arenas");
						return true;
					}

					games.get(Integer.parseInt(args[1])).add((Player) sender);
					sendMessage(sender, ChatColor.GREEN + "Succesfully joined game");
					return true;
				} else {
					sendMessage(sender, ChatColor.RED + "Not a valid agument. /maze join <Arena>");
					return true;
				}
			}
		}

		sendMessage(sender, ChatColor.RED + "Not a valid command. Type /maze help");

		return false;
	}

	public void sendMessage(CommandSender player, String msg) {

		player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GOLD + "The Maze" + ChatColor.BLUE
		        + "] " + ChatColor.GOLD + msg);
	}

}
