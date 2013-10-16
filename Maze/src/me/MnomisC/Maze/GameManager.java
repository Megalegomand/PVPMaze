package me.MnomisC.Maze;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class GameManager {
	
	ArrayList<Game> games = new ArrayList<Game>();
	Maze plugin;
	Settings settings;
	FileConfiguration data;
	
	public GameManager(Maze plugin) {
		this.plugin = plugin;
		settings = plugin.settings;
		data = settings.data;
	}
	
	public void intializeGames() {
		boolean moreGames = data.contains("Games.1");
		int number = 1;
		while(moreGames) {
			System.out.println(number);
			int length, width, x, y, z;
			length = data.getInt("Games." + number + "length");
			width = data.getInt("Games." + number + "width");
			x = data.getInt("Games." + number + "X");
			y = data.getInt("Games." + number + "Y");
			z = data.getInt("Games." + number + "Z");
			if (data.contains("Games." + number)) {
				Game game = new Game(length, width, x, y, z);
				games.add(game);
			}
			number++;
			moreGames = data.contains("Games." + number);
		}
		
		for(int i = 0; i < games.get(0).getCells().size(); i++)  {
			games.get(0).getCells().get(i).getBlock().setType(Material.GOLD_BLOCK);
		}
	}
}
