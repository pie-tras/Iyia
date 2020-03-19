package com.draglantix.tiles;

import org.joml.Vector2f;

import com.draglantix.entities.EntityManager;
import com.draglantix.flare.collision.AABB;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Texture;
import com.draglantix.flare.util.Color;
import com.draglantix.main.Assets;
import com.draglantix.main.Settings;
import com.draglantix.world.World;

public class Tile {

	private Texture texture;
	private String name;
	private int id;
	private Vector2f pos;
	private AABB bounds;
	private Color color;
	private boolean solid;
	
	public Tile(Texture texture, String name, int id, Vector2f pos, boolean solid, Color color) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		this.pos = pos;
		this.solid = solid;
		if(solid) {
			this.bounds = new AABB(pos, new Vector2f(World.TILE_SIZE), false);
			EntityManager.tileBounds.add(bounds);
		}else {
			bounds = null;
		}
		this.color = color;
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, pos, TileLib.scale, TileLib.rotation, color);
		if(solid && Settings.DEBUG) {
			g.drawImage(Assets.debug, new Vector2f(bounds.getCenter()), new Vector2f(bounds.getScale()), new Vector2f(0,0), new Color(255, 255, 255, 1));
		}
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}
	
}
