package com.draglantix.utils;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.world.World;

public class DragonMath {

	public static Vector2f worldPos(Vector2i tilePos) {
		return new Vector2f((tilePos.x * World.TILE_SIZE) - ((World.TILE_SIZE * World.TILE_MAP_SIZE)/2),
				(tilePos.y * World.TILE_SIZE) - ((World.TILE_SIZE * World.TILE_MAP_SIZE)/2));
	}
	
	public static int floor(float num) {
		return (int) Math.floor((double) num);
	}
	
	public static int ceil(float num) {
		return (int) Math.ceil((double) num);
	}
	
	public static boolean isOnScreen(Vector2i pos, Vector2f padding) {
		
		Vector2f loc = worldPos(pos);
		
		Vector2f initial = new Vector2f(
				Assets.camera.getPosition().x / Graphics.getScale()
						- Window.getWidth() / 2 / Graphics.getScale(),
				Assets.camera.getPosition().y / Graphics.getScale()
						- Window.getHeight() / 2 / Graphics.getScale());
		Vector2f end = new Vector2f(
				Assets.camera.getPosition().x / Graphics.getScale()
						+ Window.getWidth() / 2 / Graphics.getScale(),
				Assets.camera.getPosition().y / Graphics.getScale()
						+ Window.getHeight() / 2 / Graphics.getScale());
		
		if(loc.x > initial.x - (padding.x/2) && loc.x < end.x + (padding.x/2) 
				&& loc.y > initial.y - (padding.y/2) && loc.y < end.y + (padding.y/2)) {
			return true;
		}
		return false;
	}
}
