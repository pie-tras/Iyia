package com.draglantix.entities;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.utils.DragonMath;
import com.draglantix.world.World;

public class EntityManager {

	private static List<Entity> entities = new ArrayList<Entity>();
	private static Entity[][] entity_map = new Entity[World.TILE_MAP_SIZE][World.TILE_MAP_SIZE];
	
	public static void tick() {
		for(Entity[] et : entity_map) {
			for(Entity e : et) {
				if(e != null) {
					if(DragonMath.isOnScreen(e.getPosition(), new Vector2f(64, 64))) {
						e.tick();
					}
				}
			}
		}
	}
	
	public static void render(Graphics g) {
		for(Entity[] et : entity_map) {
			for(Entity e : et) {
				if(e != null) {
					if(DragonMath.isOnScreen(e.getPosition(), new Vector2f(64, 64))) {
						e.render(g);
					}
				}
			}
		}
	}
	
	public static void addEntity(Entity e) {
		entities.add(e);
		entity_map[e.getPosition().x][e.getPosition().y] = e;
	}
	
	public static Entity getEntity(Vector2i pos) {
		try {
			return entity_map[pos.x][pos.y];
		}catch(Exception e) {
			return null;
		}
	}
	
}
