package me.MnomisC.Maze;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Settings {

	Plugin plugin;
	public File configFile;
	public FileConfiguration config;
	public File dataFile;
	public FileConfiguration data;

	public Settings(Maze plugin) {

		this.plugin = plugin;
	}

	public void initializeFiles() {

		configFile = new File(this.plugin.getDataFolder(), "config.yml");
		dataFile = new File(this.plugin.getDataFolder(), "data.yml");

		if (!configFile.exists()) {
			this.plugin.saveResource("config.yml", false);
		}
		if (!dataFile.exists()) {
			this.plugin.saveResource("data.yml", false);
		}

		config = new YamlConfiguration();
		data = new YamlConfiguration();

		load();
	}

	public void load() {
		try {
			config.load(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			data.load(dataFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			config.save(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			data.save(dataFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
