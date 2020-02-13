package com.draglantix.main;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Camera;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Font;
import com.draglantix.flare.textures.SpriteSheet;
import com.draglantix.flare.textures.Texture;

public class Assets {

	public static Camera camera;
	public static Font font;

	public static Texture tile;
	
	public static Texture debug;
	
	public static void init(Graphics g) {

		camera = new Camera(new Vector2f(0, 0), 0, 0, .07f, g);
		font = new Font(new SpriteSheet("textures/font.png"), 8);
		g.setCamera(camera);
		g.setScale(4);
		
		tile = new Texture("textures/grass.png");
	}
}
