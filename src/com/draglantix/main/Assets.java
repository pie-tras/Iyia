package com.draglantix.main;

import org.joml.Vector2f;

import com.draglantix.flare.audio.AudioMaster;
import com.draglantix.flare.audio.Source;
import com.draglantix.flare.graphics.Camera;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.textures.Font;
import com.draglantix.flare.textures.SpriteSheet;
import com.draglantix.flare.textures.Texture;
import com.draglantix.world.World;

public class Assets {

	public static Camera camera;
	public static Font font;

	public static Texture log;

	public static SpriteSheet tiles;

	public static Texture stone1, stone2, sand, corner1,
	corner2, corner3, corner4, flat1, flat2, flat3,
	flat4, grass, bone, solid1, solid2, solid3,
	solid4, solid5, solid6, solid7, solid8, solid9, campfire;
	
	public static World world;
	
	public static Texture debug;
	
	public static Texture border, dark;
	
	public static SpriteSheet draglantix;
	public static Animation logoAnim;
	
	public static int darkloop;
	
	public static int campfireSFX, steps1, steps2, steps3, scream;
	
	public static Source musicSource;
	
	public static Texture title, selector, titlecampfire;

	public static SpriteSheet fire;
	public static Animation fireAnim;
	
	public static SpriteSheet psID, psIU, psIL, psIR, psD, psU, psL, psR;
	public static SpriteSheet enID, enIU, enIL, enIR, enD, enU, enL, enR;
	public static Animation IplayerLAnim, IplayerRAnim, IplayerUAnim, IplayerDAnim,
							playerLAnim, playerRAnim, playerUAnim, playerDAnim,
							playerLAnimS, playerRAnimS, playerUAnimS, playerDAnimS,
							IenemyLAnim, IenemyRAnim, IenemyUAnim, IenemyDAnim,
							enemyLAnim, enemyRAnim, enemyUAnim, enemyDAnim;
	
	public static Texture bar;
	
	public static void init(Graphics g) {

		camera = new Camera(new Vector2f(0, 0), 0, 0, .07f, g);
		font = new Font(new SpriteSheet("textures/font.png"), 8);
		g.setCamera(camera);
		g.setScale(3);

		log = new Texture("textures/log.png");

		tiles = new SpriteSheet("textures/tiles.png");
		grass = new Texture(tiles.crop(new Vector2f(0, 0), new Vector2f(16, 16)));
		
		stone1 = new Texture(tiles.crop(new Vector2f(0, 0), new Vector2f(16, 16)));
		stone2 = new Texture(tiles.crop(new Vector2f(16, 0), new Vector2f(16, 16)));
		sand = new Texture(tiles.crop(new Vector2f(32, 0), new Vector2f(16, 16)));
		corner1 = new Texture(tiles.crop(new Vector2f(48, 0), new Vector2f(16, 16)));
		corner2 = new Texture(tiles.crop(new Vector2f(64, 0), new Vector2f(16, 16)));
		corner3 = new Texture(tiles.crop(new Vector2f(0, 16), new Vector2f(16, 16)));
		corner4 = new Texture(tiles.crop(new Vector2f(16, 16), new Vector2f(16, 16)));
		flat1 = new Texture(tiles.crop(new Vector2f(32, 16), new Vector2f(16, 16)));
		flat2 = new Texture(tiles.crop(new Vector2f(48, 16), new Vector2f(16, 16)));
		flat3 = new Texture(tiles.crop(new Vector2f(64, 16), new Vector2f(16, 16)));
		flat4 = new Texture(tiles.crop(new Vector2f(0, 32), new Vector2f(16, 16)));
		grass = new Texture(tiles.crop(new Vector2f(16, 32), new Vector2f(16, 16)));
		bone = new Texture(tiles.crop(new Vector2f(32, 32), new Vector2f(16, 16)));
		solid1 = new Texture(tiles.crop(new Vector2f(48, 32), new Vector2f(16, 16)));
		solid2 = new Texture(tiles.crop(new Vector2f(64, 32), new Vector2f(16, 16)));
		solid3 = new Texture(tiles.crop(new Vector2f(0, 48), new Vector2f(16, 16)));
		solid4 = new Texture(tiles.crop(new Vector2f(16, 48), new Vector2f(16, 16)));
		solid5 = new Texture(tiles.crop(new Vector2f(32, 48), new Vector2f(16, 16)));
		solid6 = new Texture(tiles.crop(new Vector2f(48, 48), new Vector2f(16, 16)));
		solid7 = new Texture(tiles.crop(new Vector2f(64, 48), new Vector2f(16, 16)));
		solid8 = new Texture(tiles.crop(new Vector2f(0, 64), new Vector2f(16, 16)));
		solid9 = new Texture(tiles.crop(new Vector2f(16, 64), new Vector2f(16, 16)));
		campfire = new Texture(tiles.crop(new Vector2f(32, 64), new Vector2f(16, 16)));

		world = new World();
		
		debug = new Texture("textures/debug.png");
		
		border = new Texture("textures/border.png");
		dark = new Texture("textures/dark.png");
		
		draglantix = new SpriteSheet("textures/draglantix.png");
		logoAnim = new Animation(3, 3, 64, 20, draglantix, 9, false);
		
		darkloop = AudioMaster.loadSound("sfx/darkloop.wav");
		
		campfireSFX = AudioMaster.loadSound("sfx/campfire.wav");
		steps1 = AudioMaster.loadSound("sfx/steps1.wav");
		steps2 = AudioMaster.loadSound("sfx/steps2.wav");
		steps3 = AudioMaster.loadSound("sfx/steps3.wav");
		scream = AudioMaster.loadSound("sfx/scream.wav");
		
		musicSource = new Source(1.5f, 1000, 0);
		musicSource.setLooping(true);
		musicSource.setPosition(new Vector2f(0, 0));
		AudioMaster.sources.add(musicSource);
		
		title = new Texture("textures/ethuzhi.png");
		selector = new Texture("textures/selector.png");
		
		fire = new SpriteSheet("textures/fire.png");
		fireAnim = new Animation(3, 3, 32, 20, fire, 7, true);
		
		titlecampfire = new Texture("textures/titlecampfire.png");
		
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
		
		enID = new SpriteSheet("textures/enemy/enemyID.png");
		enIU = new SpriteSheet("textures/enemy/enemyIU.png");
		enIL = new SpriteSheet("textures/enemy/enemyIL.png");
		enIR = new SpriteSheet("textures/enemy/enemyIR.png");

		enD = new SpriteSheet("textures/enemy/enemyD.png");
		enU = new SpriteSheet("textures/enemy/enemyU.png");
		enL = new SpriteSheet("textures/enemy/enemyL.png");
		enR = new SpriteSheet("textures/enemy/enemyR.png");
		
		IenemyDAnim = new Animation(2, 2, 16, 2, enID, 4, true);
		IenemyUAnim = new Animation(2, 2, 16, 2, enIU, 4, true);
		IenemyLAnim = new Animation(2, 2, 16, 2, enIL, 4, true);
		IenemyRAnim = new Animation(2, 2, 16, 2, enIR, 4, true);

		enemyDAnim = new Animation(2, 2, 16, 12, enD, 4, true);
		enemyUAnim = new Animation(2, 2, 16, 12, enU, 4, true);
		enemyLAnim = new Animation(2, 2, 16, 12, enL, 4, true);
		enemyRAnim = new Animation(2, 2, 16, 12, enR, 4, true);
		
		bar = new Texture("textures/bar.png");
	}
}
