package com.draglantix.main;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Camera;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.textures.Font;
import com.draglantix.flare.textures.SpriteSheet;
import com.draglantix.flare.textures.Texture;
import com.draglantix.utils.SpriteSheetParser;

public class Assets {

	public static Camera camera;
	public static Font font;
	
	public static Texture debug;

	public static SpriteSheet draglantix;
	public static Animation logoAnim;
	
	public static Texture selector;
	
	public static SpriteSheetParser tiles;
	
	public static SpriteSheet player, sheep, slime;
	
	public static Animation playerIdle, playerInteract, playerDie;

	public static void init(Graphics g) {

		camera = new Camera(new Vector2f(0, 0), 0, 0, .07f);
		font = new Font(new SpriteSheet("textures/font.png"), 8, 1);
		g.setCamera(camera);
		Graphics.setScale(4);
		
		debug = new Texture("textures/debug.png");
		
		draglantix = new SpriteSheet("textures/draglantix.png");
		logoAnim = new Animation(3, 3, 64, 20, draglantix, 0, 9, false);
		
		selector = new Texture("textures/selector.png");
		
		tiles = new SpriteSheetParser(new SpriteSheet("textures/tiles.png"), 8);
		
		player = new SpriteSheet("textures/player.png");
		sheep = new SpriteSheet("textures/sheep.png");
		
		playerIdle = new Animation(3, 3, 8, 4, player, 0, 2, true);
		playerInteract = new Animation(3, 3, 8, 8, player, 2, 2, true);
		playerDie = new Animation(3, 3, 8, 4, player, 4, 4, true);
	
	}
}
