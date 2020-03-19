package com.draglantix.main;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Camera;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.textures.Font;
import com.draglantix.flare.textures.SpriteSheet;
import com.draglantix.flare.textures.Texture;

public class Assets {

	public static Camera camera;
	public static Font font;
	
	public static Texture debug;

	public static SpriteSheet draglantix;
	public static Animation logoAnim;
	
	public static Texture selector;
	
	private static SpriteSheet tiles;
	
	public static Texture stone_background, stone_wall;
	
	public static SpriteSheet player;
	
	public static Animation playerIdleL, playerIdleR, playerWalkL, playerWalkR, playerAttackL, playerAttackR;

	public static void init(Graphics g) {

		camera = new Camera(new Vector2f(0, 0), 0, 0, .07f);
		font = new Font(new SpriteSheet("textures/font.png"), 8, 1);
		g.setCamera(camera);
		Graphics.setScale(4);
		
		debug = new Texture("textures/debug.png");
		
		draglantix = new SpriteSheet("textures/draglantix.png");
		logoAnim = new Animation(3, 3, 64, 20, draglantix, 0, 9, false);
		
		selector = new Texture("textures/selector.png");
		
		tiles = new SpriteSheet("textures/tiles.png");
		
		stone_background = new Texture(tiles.crop(new Vector2f(0, 0), new Vector2f(8, 8)));
		stone_wall = new Texture(tiles.crop(new Vector2f(0, 8), new Vector2f(8, 8)));
		
		player = new SpriteSheet("textures/player.png");
		
		playerIdleL = new Animation(6, 6, 16, 4, player, 0, 2, true);
		playerIdleR = new Animation(6, 6, 16, 4, player, 2, 2, true);
		playerWalkL = new Animation(6, 6, 16, 16, player, 4, 8, true);
		playerWalkR = new Animation(6, 6, 16, 16, player, 12, 8, true);
		playerAttackL = new Animation(6, 6, 16, 16, player, 20, 4, true);
		playerAttackR = new Animation(6, 6, 16, 16, player, 24, 4, true);
	
	}
}
