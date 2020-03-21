package com.draglantix.states;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

import com.draglantix.entities.EntityManager;
import com.draglantix.entities.Player;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.utils.DragonMath;
import com.draglantix.world.World;

public class PlayState extends GameState {
	
	private World world;
	
	private float zoom = 0;
	
	private static Player player;
	
	public PlayState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
	}
	
	public void init() {
		world = new World();
		player = new Player(Assets.playerIdle, new Vector2i(15, 20), new Vector2f(8, 8));
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
		Assets.camera.move(DragonMath.worldPos(player.getPosition()));
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
