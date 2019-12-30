package com.draglantix.world;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.draglantix.entites.EntityManager;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Reader;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.tiles.Tile;
import com.draglantix.tiles.TileData;
import com.draglantix.tiles.TileMap;
import com.draglantix.utils.DragonMath;

public class World {

	private TileMap map;

	public static List<Tile> activeTiles = new ArrayList<Tile>();
	public static List<Tile> boundingTiles = new ArrayList<Tile>();
	
	public World() {

	}

	public void init(Graphics g) {
		TileData.init();
		map = new TileMap(parseTileMap("maps/map.dat"), new Vector2f(0, 0), WorldConfig.WORLD_SIZE, g);
	}
	
	public static int[][] parseTileMap(String path) {
		String raw = Reader.loadFileAsString(path);
		String[] tokens = raw.split("\\s+");
		int size = (int) Math.sqrt(tokens.length);
		int[][] ids = new int[size][size];
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				ids[x][y] = Reader.parseInt(tokens[x+(y*(size))]);
			}
		}
		return ids;
	}

	public void tick() {
		EntityManager.tick();
	}

	private void cull(Graphics g) {
		activeTiles.removeAll(activeTiles);
		Vector2f initial = new Vector2f(
				Assets.camera.getPosition().x / WorldConfig.TILE_SIZE.x / g.getScale()
						- Window.getWidth() / 2 / WorldConfig.TILE_SIZE.x / g.getScale() + WorldConfig.WORLD_SIZE.x / 2,
				-Assets.camera.getPosition().y / WorldConfig.TILE_SIZE.y / g.getScale()
						- Window.getHeight() / 2 / WorldConfig.TILE_SIZE.y / g.getScale()
						+ WorldConfig.WORLD_SIZE.y / 2);
		Vector2f end = new Vector2f(
				Assets.camera.getPosition().x / WorldConfig.TILE_SIZE.x / g.getScale()
						+ Window.getWidth() / 2 / WorldConfig.TILE_SIZE.x / g.getScale() + WorldConfig.WORLD_SIZE.x / 2,
				-Assets.camera.getPosition().y / WorldConfig.TILE_SIZE.y / g.getScale()
						+ Window.getHeight() / 2 / WorldConfig.TILE_SIZE.y / g.getScale()
						+ WorldConfig.WORLD_SIZE.y / 2);

		for(int x = DragonMath.floor(Math.max(initial.x - 1, 0)); x < DragonMath.ceil(Math.min(end.x + 1, WorldConfig.WORLD_SIZE.x)); x++) {
			for(int y = DragonMath.floor(Math.max(initial.y - 1, 0)); y < DragonMath
					.ceil(Math.min(end.y + 1, WorldConfig.WORLD_SIZE.y)); y++) {
				if(x < WorldConfig.WORLD_SIZE.x && y < WorldConfig.WORLD_SIZE.y) {
					activeTiles.add(map.getTile(x, y));
				}
			}
		}
	}

	public void render(Graphics g) {
		cull(g);
		renderTiles(g);
		EntityManager.render(g);
	}

	public void renderTiles(Graphics g) {
		for(Tile t: activeTiles) {
			t.render(g);
			if(t.getBounds() != null) {
				//Debug.renderBounds(t.getBounds(), g);
			}
		}
	}

}
