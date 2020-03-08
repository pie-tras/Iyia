package com.draglantix.states;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.entities.EntityManager;
import com.draglantix.entities.Player;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.world.World;

public class PlayState extends GameState {
	
	private World world;
	
	private float zoom = 0;
	
	private float world_size = (World.TILE_MAP_SIZE * World.TILE_SIZE * Graphics.getScale());
	
	private static Player player;
	
	public PlayState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
	}
	
	public void init() {
		world = new World();
		player = new Player(Assets.IplayerDAnim, new Vector2f(0, 0), new Vector2f(16, 16));
		EntityManager.dynamics.add(player);
	}
	
	@Override
	public void tick() {
		EntityManager.tick();
		updateCamera();
	}
	
	@Override
	public void render() {
		g.drawMode(g.DRAW_WORLD);
		world.render(g);
		EntityManager.render(g);
	}
	
	public void updateCamera() {
		Assets.camera.move(player.getPosition());
		Assets.camera.correctCamera(new Vector2f((-world_size/2) - ((World.TILE_SIZE * Graphics.getScale())/2), (-world_size/2) + ((World.TILE_SIZE * Graphics.getScale())/2)),
				new Vector2f((world_size - (world_size/2)) - ((World.TILE_SIZE * Graphics.getScale())/2), (world_size - (world_size/2)) - ((World.TILE_SIZE * Graphics.getScale())/2)));
	
		zoom = 0;
		
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_Q)) {
			zoom += 0.01;
			Assets.camera.setZoom(Assets.camera.getZoom() + zoom);
		}
		
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_E)) {
			zoom += 0.01;
			Assets.camera.setZoom(Assets.camera.getZoom() - zoom);
		}
	
	}
}
