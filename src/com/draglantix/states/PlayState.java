package com.draglantix.states;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.world.World;

public class PlayState extends GameState {
	
	private Vector2f delta = new Vector2f(0, 0);
	private float speed = 15;
	
	private World world;
	
	private float zoom = 0;
	
	public PlayState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
		world = new World(1920304);
	}
	
	@Override
	public void tick() {
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_D)) {
			delta.x += speed;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_A)) {
			delta.x -= speed;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_W)) {
			delta.y += speed;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_S)) {
			delta.y -= speed;
		}
		
		Assets.camera.move(delta);
		

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
	
	@Override
	public void render() {
		g.drawMode(g.DRAW_WORLD);
		
		world.render(g, Assets.camera.getPosition());
	}
}
