package com.draglantix.tiles;

import org.joml.Vector2f;

import com.draglantix.flare.collision.Polygon;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Texture;
import com.draglantix.flare.util.Color;
import com.draglantix.flare.util.Functions;
import com.draglantix.world.WorldConfig;

public class Tile {

	private Texture texture;
	private Vector2f position;
	private boolean solid;
	
	private Vector2f rotation = new Vector2f(0, 0);
	private Color color = new Color(255, 255, 255, 1);
	
	private Polygon bounds;
	
	public Tile(Texture texture, Vector2f position, boolean solid) {
		this.texture = texture;
		this.position = position;
		this.solid = solid;
		if(solid) {
			bounds = Functions.generateSquareBound(position, new Vector2f(WorldConfig.TILE_SIZE.x/2, WorldConfig.TILE_SIZE.y/2), false);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, position, WorldConfig.TILE_SIZE, rotation, color);
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public boolean isSolid() {
		return solid;
	}

	public Polygon getBounds() {
		return bounds;
	}

	public void setBounds(Polygon bounds) {
		this.bounds = bounds;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

}
