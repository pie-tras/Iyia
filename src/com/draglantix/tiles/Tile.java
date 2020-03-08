package com.draglantix.tiles;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Texture;
import com.draglantix.flare.util.Color;

public class Tile {

	private Texture texture;
	private String name;
	private int id;
	private Vector2f pos;
	private Color color;
	
	public Tile(Texture texture, String name, int id, Vector2f pos, Color color) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		this.pos = pos;
		this.color = color;
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, pos, TileLib.scale, TileLib.rotation, color);
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
