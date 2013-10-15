package me.MnomisC.Maze;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitListener implements Listener {

	ArrayList<Game> games;
	Maze plugin;

	public BukkitListener(Maze plugin) {

		this.plugin = plugin;
		games = plugin.gm.games;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDeath(EntityDeathEvent event) {

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			for (int i = 0; i < games.size(); i++) {
				if ((games.get(i)).getPlayerList().contains(player)) {
					Game game = games.get(i);
					player.teleport(Bukkit.getWorld("world").getSpawnLocation());
					game.remove(player);
				}
			}
		}
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		for (int i = 0; i < games.size(); i++) {
			if ((games.get(i)).getPlayerList().contains(player)) {
				Game game = games.get(i);
				player.teleport(Bukkit.getWorld("world").getSpawnLocation());
				game.remove(player);
			}
		}
	}
}
