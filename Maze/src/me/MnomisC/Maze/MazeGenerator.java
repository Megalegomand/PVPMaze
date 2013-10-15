package me.MnomisC.Maze;

import java.util.Collections;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

/*
 * recursive backtracking algorithm
 * shamelessly borrowed from the ruby at
 * http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
 */
public class MazeGenerator {
	private final int length;
	private final int width;
	private final int x, y, z;
	private final int[][] maze;
	private final int height = 3;

	public MazeGenerator(int length, int width, int x, int y, int z) {
		this.length = length;
		this.width = width;
		this.x = x;
		this.y = y;
		this.z = z;
		maze = new int[this.length][this.width];
		generateMaze(0, 0);
	}

	public void displayOld() {
		for (int i = 0; i < width; i++) {
			// draw the north edge
			for (int j = 0; j < length; j++) {
				System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
			}
			System.out.println("+");
			// draw the west edge
			for (int j = 0; j < length; j++) {
				System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
			}
			System.out.println("|");
		}
		// draw the bottom line
		for (int j = 0; j < length; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}

	public void display() {
		for (int i = 0; i < width; i++) {
			// draw the north edge
			for (int j = 0; j < length; j++) {
				System.out.print((maze[j][i] & 1) == 0 ? "[][]" : "[]  ");
			}
			System.out.println("[]");
			// draw the west edge
			for (int j = 0; j < length; j++) {
				System.out.print((maze[j][i] & 8) == 0 ? "[]  " : "    ");
			}
			System.out.println("[]");
		}
		// draw the bottom line
		for (int j = 0; j < length; j++) {
			System.out.print("[][]");
		}
		System.out.println("[]");
	}

	public void build() {
		Material block = Material.getMaterial(98);
		int x = this.x;
		int z = this.z;
		for (int y = this.y; y < height + this.y; y++) {
			x = this.x;
			z = this.z;
			for (int i = 0; i < width; i++) {
				// draw the north edge
				for (int j = 0; j < length; j++) {

					Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
					if ((maze[j][i] & 1) == 0) {

						loc.getBlock().setType(block);
						x++;
						loc.setX(x);
						loc.getBlock().setType(block);
					} else {
						loc.getBlock().setType(block);
						x++;
						loc.setX(x);
						loc.getBlock().setType(Material.AIR);
					}
					x++;
				}
				Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
				loc.getBlock().setType(block);
				x = this.x;
				z++;

				// draw the west edge
				for (int j = 0; j < length; j++) {

					Location loc2 = new Location(Bukkit.getWorld("world"), x, y, z);
					if ((maze[j][i] & 8) == 0) {
						loc2.getBlock().setType(block);
						x++;
						loc2.setX(x);
						loc2.getBlock().setType(Material.AIR);
					} else {
						loc2.getBlock().setType(Material.AIR);
						x++;
						loc2.setX(x);
						loc2.getBlock().setType(Material.AIR);
					}
					x++;
				}
				Location loc3 = new Location(Bukkit.getWorld("world"), x, y, z);
				loc3.getBlock().setType(block);
				x = this.x;
				z++;
			}

			// draw the bottom line
			for (int j = 0; j < length; j++) {

				Location loc2 = new Location(Bukkit.getWorld("world"), x, y, z);

				loc2.getBlock().setType(block);
				x++;
				loc2.setX(x);
				loc2.getBlock().setType(block);
				x++;
			}
			Location loc3 = new Location(Bukkit.getWorld("world"), x, y, z);
			loc3.getBlock().setType(block);
			x = this.x;
		}
	}

	private void generateMaze(int clength, int cwidth) {
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (DIR dir : dirs) {
			int nlength = clength + dir.dlength;
			int nwidth = cwidth + dir.dwidth;
			if (between(nlength, length) && between(nwidth, width) && (maze[nlength][nwidth] == 0)) {
				maze[clength][cwidth] |= dir.bit;
				maze[nlength][nwidth] |= dir.opposite.bit;
				generateMaze(nlength, nwidth);
			}
		}
	}

	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}

	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dlength;
		private final int dwidth;
		private DIR opposite;

		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}

		private DIR(int bit, int dlength, int dwidth) {
			this.bit = bit;
			this.dlength = dlength;
			this.dwidth = dwidth;
		}
	};

	public static void main(String[] args) {
		int length = args.length >= 1 ? (Integer.parseInt(args[0])) : 5;
		int width = args.length == 2 ? (Integer.parseInt(args[1])) : 5;
		MazeGenerator maze = new MazeGenerator(length, width, 0, 100, 0);
		maze.displayOld();
		maze.display();
	}

}