package com.draglantix.world;

import java.io.File;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Writer;
import com.draglantix.tiles.TileMap;

public class World {

	public static final int WORLD_SIZE = 20;
	public static final int TILE_MAP_SIZE = 64;
	public static final int TILE_SIZE = 16;

	private static int seed;
	
	private TileMap tilemap;
	
	private Generator generator;
	
	public World(int seed) {
		
		World.seed = seed;
		
		generator = new Generator(seed);
		
		createNewWorld("level1");
		
		tilemap = new TileMap("level1");
	}
	
	public void createNewWorld(String name) {
		File f = Writer.CreateFile("res/maps/"+name + ".map");
		String mapString = "";
		for(int x = 0; x < TILE_MAP_SIZE; x++) {
			for(int y = 0; y < TILE_MAP_SIZE; y++) {
				int id = generator.dungeonTile(x, y);
				mapString += id + " ";
			}
			mapString += "\n";
		}
		Writer.WriteFile(f, mapString);
	}
	
	public void render(Graphics g, Vector2f camera) {
		tilemap.render(g);
	}

	public static int getSeed() {
		return seed;
	}
		
}
