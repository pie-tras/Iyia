package com.draglantix.tiles;

import org.joml.Vector2i;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Texture;
import com.draglantix.flare.util.Color;
import com.draglantix.utils.DragonMath;

public class Tile {
	
	private Texture texture;
	private boolean solid;
	private String name;
	private int id;
	private Vector2i pos;
	private Color color;
	
	public Tile(Texture texture, String name, int id, Vector2i pos, boolean solid, boolean flag, Color color) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		this.pos = pos;
		this.solid = solid;
		this.color = color;
	}

	public void render(Graphics g) {
		g.drawImage(texture, DragonMath.worldPos(pos), TileLib.scale, TileLib.rotation, color);
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
	
	public Vector2i getPos() {
		return pos;
	}

	public void setPos(Vector2i pos) {
		this.pos = pos;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	
}
