package com.draglantix.world;

import java.io.File;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Writer;
import com.draglantix.tiles.TileMap;

public class World {

	public static final int TILE_MAP_SIZE = 32;
	public static final int TILE_SIZE = 8;

	private static int seed;
	
	private TileMap tilemap;
	
	private static String currentLevel;
	
	public World() {
		tilemap = new TileMap(currentLevel);
	}
	
	public static void createNewWorld(int seed, String name) {
		World.seed = seed;
		Generator generator = new Generator(seed);
		
		File f = Writer.CreateFile("res/maps/"+name + ".map");
		String mapString = "";
		for(int x = 0; x < TILE_MAP_SIZE; x++) {
			for(int y = 0; y < TILE_MAP_SIZE; y++) {
				int id = generator.getTile(x, y);
				mapString += id + " ";
			}
			mapString += "\n";
		}
		Writer.WriteFile(f, mapString);
	}
	
	public void render(Graphics g) {
		tilemap.render(g);
	}

	public static int getSeed() {
		return seed;
	}

	public static void setSeed(int seed) {
		World.seed = seed;
	}

	public static String getCurrentLevel() {
		return currentLevel;
	}

	public static void setCurrentLevel(String currentLevel) {
		World.currentLevel = currentLevel;
	}
		
}
