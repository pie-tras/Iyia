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
	
	public static Texture stone, stone_wall;
	
	public static SpriteSheet psID, psIU, psIL, psIR, psD, psU, psL, psR;
	
	public static Animation IplayerLAnim, IplayerRAnim, IplayerUAnim, IplayerDAnim,
	playerLAnim, playerRAnim, playerUAnim, playerDAnim,
	playerLAnimS, playerRAnimS, playerUAnimS, playerDAnimS;
	
	public static void init(Graphics g) {

		camera = new Camera(new Vector2f(0, 0), 0, 0, .07f);
		font = new Font(new SpriteSheet("textures/font.png"), 8, 1);
		g.setCamera(camera);
		Graphics.setScale(4);
		
		debug = new Texture("textures/debug.png");
		
		draglantix = new SpriteSheet("textures/draglantix.png");
		logoAnim = new Animation(3, 3, 64, 20, draglantix, 9, false);
		
		selector = new Texture("textures/selector.png");
		
		tiles = new SpriteSheet("textures/tiles.png");
		
		stone = new Texture(tiles.crop(new Vector2f(0, 0), new Vector2f(8, 8)));
		stone_wall = new Texture(tiles.crop(new Vector2f(0, 8), new Vector2f(8, 8)));
		
		psID = new SpriteSheet("textures/player/playerID.png");
		psIU = new SpriteSheet("textures/player/playerIU.png");
		psIL = new SpriteSheet("textures/player/playerIL.png");
		psIR = new SpriteSheet("textures/player/playerIR.png");

		psD = new SpriteSheet("textures/player/playerD.png");
		psU = new SpriteSheet("textures/player/playerU.png");
		psL = new SpriteSheet("textures/player/playerL.png");
		psR = new SpriteSheet("textures/player/playerR.png");
		
		IplayerDAnim = new Animation(2, 2, 16, 2, psID, 4, true);
		IplayerUAnim = new Animation(2, 2, 16, 2, psIU, 4, true);
		IplayerLAnim = new Animation(2, 2, 16, 2, psIL, 4, true);
		IplayerRAnim = new Animation(2, 2, 16, 2, psIR, 4, true);

		playerDAnim = new Animation(2, 2, 16, 6, psD, 4, true);
		playerUAnim = new Animation(2, 2, 16, 6, psU, 4, true);
		playerLAnim = new Animation(2, 2, 16, 6, psL, 4, true);
		playerRAnim = new Animation(2, 2, 16, 6, psR, 4, true);
		
		playerDAnimS = new Animation(2, 2, 16, 12, psD, 4, true);
		playerUAnimS = new Animation(2, 2, 16, 12, psU, 4, true);
		playerLAnimS = new Animation(2, 2, 16, 12, psL, 4, true);
		playerRAnimS = new Animation(2, 2, 16, 12, psR, 4, true);
	}
}
