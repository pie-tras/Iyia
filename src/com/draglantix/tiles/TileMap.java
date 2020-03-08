package com.draglantix.tiles;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Reader;
import com.draglantix.world.World;

public class TileMap {
	
	private Tile[][] map = new Tile[World.TILE_MAP_SIZE][World.TILE_MAP_SIZE];
	
	private String name;
	
	public TileMap(String name) {
		load(name);
	}
	
	public void render(Graphics g) {
		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[x].length; y++) {
				map[x][y].render(g);
			}
		}
	}
	
	public void load(String name) {
		String raw = Reader.loadFileAsString("maps/"+name + ".map");
		String[] tokens = raw.split("\\s+");
				
		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[x].length; y++) {
				
				int id = Reader.parseInt(tokens[x+(y*(World.TILE_MAP_SIZE))]);
				
				map[x][y] = TileLib.createTile(id, new Vector2f((x * World.TILE_SIZE) - ((World.TILE_SIZE * World.TILE_MAP_SIZE)/2),
																(y * World.TILE_SIZE) - ((World.TILE_SIZE * World.TILE_MAP_SIZE)/2)));
			}
		}
	}

	public String getName() {
		return name;
	}
	
}
