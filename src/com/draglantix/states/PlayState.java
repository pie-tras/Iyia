package com.draglantix.states;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.draglantix.entites.EntityManager;
import com.draglantix.entites.Ethuzhi;
import com.draglantix.entites.Player;
import com.draglantix.entites.WoodItem;
import com.draglantix.flare.audio.AudioMaster;
import com.draglantix.flare.audio.Source;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Color;
import com.draglantix.flare.util.FBO;
import com.draglantix.flare.util.Functions;
import com.draglantix.flare.util.Timer;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.utils.DragonMath;
import com.draglantix.world.WorldConfig;

public class PlayState extends GameState {

	public static Player player;
	
	private FBO light;
	
	private boolean wakeup = true;
	
	private float vision = 1f;
	
	private float sinIndex = 0;
	
	private Source campfire;
	
	private float musicVolume = 1f;
	
	public static int lightBar = 40;
	
	public static Vector2f campfireLoc = new Vector2f(160, 56);
	public static List<WoodItem> wood = new ArrayList<WoodItem>();
	
	private Timer timer, timer2;
	private float passed = 0f, passed2;
	
	private boolean fireDead = false;
	
	public static boolean dead = false, drawDeathMsg = false;
	
	private Ethuzhi en;
	
	public PlayState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
		Assets.world.init(g);
		player = new Player(Assets.IplayerDAnim, new Vector2f(0, 0), new Vector2f(16, 16));
		EntityManager.dynamics.add(player);
		
		light = new FBO(Window.getWidth(), Window.getHeight());
		
		campfire = new Source(0.8f, 1, 0);
		campfire.setPosition(new Vector2f(160, 56));
		campfire.setLooping(true);
		campfire.setVolume(0.1f);
		campfire.play(Assets.campfireSFX);
		AudioMaster.sources.add(campfire);
		
		float width = WorldConfig.WORLD_SIZE.x * WorldConfig.TILE_SIZE.x;
		float height = WorldConfig.WORLD_SIZE.y * WorldConfig.TILE_SIZE.y;
		
		genWood();
		
		en = new Ethuzhi(Assets.IenemyDAnim, new Vector2f((Functions.rand.nextFloat() * width) - width/2, (Functions.rand.nextFloat() * height) - height/2), new Vector2f(32, 32), player, g);
		EntityManager.dynamics.add(en);
		
		timer = new Timer();
		timer2 = new Timer();
	}
	
	public void genWood() {
		float width = WorldConfig.WORLD_SIZE.x * WorldConfig.TILE_SIZE.x;
		float height = WorldConfig.WORLD_SIZE.y * WorldConfig.TILE_SIZE.y;
		
		for(int i = 0; i < 200; i++) {
			WoodItem item = new WoodItem(Assets.log, new Vector2f((Functions.rand.nextFloat() * width) - width/2, (Functions.rand.nextFloat() * height) - height/2), new Vector2f(8, 8));
			wood.add(item);
			EntityManager.dynamics.add(item);
		}
	}

	@Override
	public void tick() {
		
		if(dead) {
			player.setHealth(0);
			en.setHealth(0);
			wakeup = false;
			if(vision == 0) {
				drawDeathMsg = true;
			}
		}
		
		passed2 += timer2.getDelta();
		
		if(passed2 > 100) {
			genWood();
			passed2 = 0;
		}
		
		if(musicVolume > 0.2f) {
			musicVolume -= 0.001f;
			Assets.musicSource.setVolume(musicVolume);
		}
		
		passed += timer.getDelta();
		
		if(passed > 1) {
			lightBar --;
			if(lightBar < 0) {
				lightBar = 0;
			}
			passed = 0;
		}
		
		if(lightBar == 0) {
			fireDead = true;
		}
		
		Assets.musicSource.setPosition(player.getPosition());
		AudioMaster.setListenerData(player.getPosition());
		
		Assets.world.tick();
		updateCamera();
		float width = (WorldConfig.WORLD_SIZE.x * WorldConfig.TILE_SIZE.x * g.getScale());
		float height = (WorldConfig.WORLD_SIZE.y * WorldConfig.TILE_SIZE.y * g.getScale());
		Assets.camera.correctCamera(new Vector2f((-width/2) - ((WorldConfig.TILE_SIZE.x * g.getScale())/2), (-height/2) + ((WorldConfig.TILE_SIZE.y * g.getScale())/2)),
				new Vector2f((width - (width/2)) - ((WorldConfig.TILE_SIZE.x * g.getScale())/2), (height - (height/2)) + ((WorldConfig.TILE_SIZE.y * g.getScale())/2)));
		loopPlayer();
		
		if(wakeup) {
			vision -= 0.005f;
			if(vision < 0) {
				vision = 0;
			}
		}else {
			vision += 0.1f;
			if(vision > 1) {
				vision = 1;
				wakeup = true;
			}
		}
		
		if(sinIndex < 360) {
			sinIndex += 1;
		}else {
			sinIndex = 0;
		}
	}
	
	public void loopPlayer() {
		if(!DragonMath.isOnScreen(new Vector2f(player.getPosition()), new Vector2f(0, 0), g)) {
			wakeup = false;
			Vector2f min = new Vector2f(-(WorldConfig.WORLD_SIZE.x * WorldConfig.TILE_SIZE.x / 2), 
					-(WorldConfig.WORLD_SIZE.y * WorldConfig.TILE_SIZE.y / 2));
			Vector2f max = new Vector2f((WorldConfig.WORLD_SIZE.x * WorldConfig.TILE_SIZE.x / 2),
					(WorldConfig.WORLD_SIZE.y * WorldConfig.TILE_SIZE.y / 2));
		
			Vector2f pos = new Vector2f(0, 0);
			
			if(player.getPosition().x < min.x) {
				pos = new Vector2f(max.x - 5, player.getPosition().y);
				correctPos(pos);
			}else if(player.getPosition().x > max.x) {
				pos = new Vector2f(min.x + 5, player.getPosition().y);
				correctPos(pos);
			}
			
			if(player.getPosition().y < min.y) {
				pos = new Vector2f(player.getPosition().x, max.y - 5);
				correctPos(pos);
			}else if(player.getPosition().y > max.y) {
				pos = new Vector2f(player.getPosition().x, min.y + 5);
				correctPos(pos);
			}
			
		}
		
	}

	private void correctPos(Vector2f pos) {
		player.setPosition(pos);
		player.getBounds().setPosition(pos);
		Assets.camera.setPosition(new Vector2f(pos));
	}
	
	@Override
	public void render() {
		g.drawMode(g.DRAW_WORLD);
		Assets.world.render(g);

		light.bindFrameBuffer();
			
			g.clearColor(new Color(0, 0, 0, 0));
			if(!fireDead) {
				Vector2f pos = new Vector2f(480, 150);
				g.drawLight(pos, 600 + (int)(50 * Math.sin(Math.toRadians(sinIndex * 2))), new Color(255, 108, 50, 1f));
			}
			
		light.unbindFrameBuffer();
		
		if(!fireDead) {
			g.drawImage(Assets.fireAnim.getTexture(), campfireLoc, new Vector2f(32, 32), new Vector2f(0, 0), new Color(255, 100, 50, 1));
		}
		
		g.drawMode(g.DRAW_SCREEN);
		g.drawImage(light.getColorTexture(), new Vector2f(0, 0), new Vector2f(Window.getWidth()/g.getScale() + 3, -Window.getHeight()/g.getScale() - 3), new Vector2f(0, 0), new Color(255, 255, 255, 0.86f));
		//g.drawImage(Assets.border, new Vector2f(0, 0), new Vector2f(Window.getWidth()/g.getScale() + 3, Window.getHeight()/g.getScale() + 3), new Vector2f(0, 0), new Color(255, 255, 255, 0.5f));
		g.drawImage(Assets.dark, new Vector2f(0, 0), new Vector2f(Window.getWidth()/g.getScale() + 3, Window.getHeight()/g.getScale() + 3), new Vector2f(0, 0), new Color(255, 255, 255, vision));
		String hud = "Wood " + player.wood;
		if(player.wood >= 5) {
			hud = hud + " (You can carry no more!)";
		}
		g.drawString(Assets.font, hud, new Vector2f(-(Window.getWidth()/2/g.getScale()) + 10, -(Window.getHeight()/2/g.getScale()) + 10), new Vector2f(4, 4), new Color(255, 255, 255, 1), g.FONT_LEFT);
		g.drawImage(Assets.bar, new Vector2f((Window.getWidth()/2/g.getScale()) - 30, -(Window.getHeight()/2/g.getScale()) + 10), new Vector2f(lightBar, 4), new Vector2f(0, 0), new Color(255, 255, 255, 1));
		g.drawString(Assets.font, "Fuel Remaining:", new Vector2f((Window.getWidth()/2/g.getScale()) - 50, -(Window.getHeight()/2/g.getScale()) + 10), new Vector2f(4, 4), new Color(255, 255, 255, 1), g.FONT_RIGHT);
		
		if(drawDeathMsg) {
			g.drawString(Assets.font, "You have vanished from life without a trace...", new Vector2f(0, 0), new Vector2f(8, 8), new Color(255, 0, 0, 1), g.FONT_CENTER);
			g.drawString(Assets.font, "Press Escape to Quit.", new Vector2f(0, -16), new Vector2f(4, 4), new Color(255, 0, 0, 1), g.FONT_CENTER);
		}
	
	}

	public void updateCamera() {
		Assets.camera.move(player.getPosition());
	}

}
