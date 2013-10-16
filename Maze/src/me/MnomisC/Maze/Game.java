package me.MnomisC.Maze;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Game {

	ArrayList<Player> players = new ArrayList<Player>();
	int length, width, x, y, z;
	
	public Game(int length, int width, int x, int y, int z) {
		this.length = length;
		this.width = width;
		this.x = x;
		this.y = y;
		this.z = z;
		createMaze();
	}
	
	public void createMaze() {
		
		MazeGenerator maze = new MazeGenerator(length, width, x, y, z);
		maze.build();
	}
	
	public ArrayList<Player> getPlayerList() {
	    
	    return players;
    }
	
	public void remove(Player player) {
		
		player.teleport(Bukkit.getWorld("world").getSpawnLocation());
		players.remove(player);
	}
	
	public void add(Player player) {
		
		players.add(player);
	}
	
	public void start() {
		
		
	}
	
	public ArrayList<Location> getCells() {
		
		ArrayList<Location> cells = new ArrayList<Location>();
		
		for(int x = this.x; x < length + this.x; x = x+2) {
			for(int z = this.z; z < width + this.z; z = z+2) {
				
				Location loc = new Location(Bukkit.getWorld("world"), x, this.y, z);
				cells.add(loc);
			}
		}
		
		return cells;
	}

}
