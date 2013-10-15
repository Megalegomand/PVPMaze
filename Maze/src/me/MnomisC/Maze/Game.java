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
		
		return cells;
	}

}
