package com.draglantix.tiles;

import org.joml.Vector2f;

import com.draglantix.flare.util.Color;
import com.draglantix.main.Assets;
import com.draglantix.world.World;

public class TileLib {

	public static Vector2f scale = new Vector2f(World.TILE_SIZE, World.TILE_SIZE);
	public static Vector2f rotation = new Vector2f(0, 0);
	
	public static Tile createTile(int id, Vector2f pos) {
		
		switch(id){
		
			case 0:
				return new Tile(Assets.stone_background, "stone_background", id, pos, false, new Color(255, 255, 255, 1));
			case 1:
				return new Tile(Assets.stone_wall, "stone_wall", id, pos, true, new Color(255, 255, 255, 1));
			default:
				return null;
		}
	}
	
}
