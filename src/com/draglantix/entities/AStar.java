package com.draglantix.entities;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

import com.draglantix.tiles.Tile;
import com.draglantix.tiles.TileMap;
import com.draglantix.world.World;

public class AStar {
	
	private boolean[][] colliders = new boolean[World.TILE_MAP_SIZE][World.TILE_MAP_SIZE];
	
	private static List<Vector2i> open = new ArrayList<Vector2i>();
	private static List<Vector2i> closed = new ArrayList<Vector2i>();
	
	public AStar() {
		for(int x = 0; x < World.TILE_MAP_SIZE; x++) {
			for(int y = 0; y < World.TILE_MAP_SIZE; y++) {
				Tile t = TileMap.getTile(new Vector2i(x, y));
				if(t.isSolid()) {
					colliders[x][y] = false;
				}else {
					colliders[x][y] = true;
				}
			}
		}
	}
	
	public static List<Vector2i> solve(Vector2i start, Vector2i end) {
		boolean alive = true;
		Vector2i current = start;
		
		List<Vector2i> path = new ArrayList<Vector2i>();
		
		while(alive) {
			evaluate(current);
			
			for(Vector2i v : open) {
				
			}
			
			if(current.equals(end)) {
				alive = false;
			}
		}	
		
	}
	
	private static void evaluate(Vector2i pos) {
		Vector2i[] targets = {new Vector2i(pos.x + 1, pos.y),new Vector2i(pos.x + 1, pos.y - 1),new Vector2i(pos.x + 1, pos.y + 1),new Vector2i(pos.x, pos.y + 1),
				new Vector2i(pos.x, pos.y - 1),new Vector2i(pos.x - 1, pos.y),new Vector2i(pos.x, pos.y - 1),new Vector2i(pos.x, pos.y + 1)};
		
		for(Vector2i t : targets) {
			try {
				if(colliders[pos.x][pos.y]) {
					open.add(t);
				}
			}catch(Exception e) {}
		}
	}

}
