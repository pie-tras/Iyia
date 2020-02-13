package com.draglantix.world;

import org.joml.Vector2f;

import com.draglantix.flare.util.Color;
import com.draglantix.main.Assets;
import com.draglantix.tiles.Tile;

public class WorldData {
	
	public static final int WORLD_SIZE = 50;
	public static final int TILE_SIZE = 16;
	public static final int MAP_LENGTH = WORLD_SIZE * TILE_SIZE;
	
	public Tile[][] map;
	
	private HeightGenerator generator;
	
	public WorldData() {
		
		generator = new HeightGenerator();
		
		map = new Tile[WORLD_SIZE][WORLD_SIZE];
		for(int x = 0; x < WORLD_SIZE; x++) {
			for(int y = 0; y < WORLD_SIZE; y++) {
				float grad = generator.generateHeight(x * 200, y * 200);
				System.out.println(grad);
				map[x][y] = new Tile(Assets.tile, "tile", new Vector2f((x * TILE_SIZE)-(MAP_LENGTH/2), (y * TILE_SIZE)-(MAP_LENGTH/2)), new Color(grad, grad, grad, grad));
			}
		}
	}
	
}
