package com.draglantix.states;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.world.WorldData;

public class PlayState extends GameState {
	
	private Vector2f delta = new Vector2f(0, 0);
	private float speed = 15;
	
	private WorldData worldData;
	
	public PlayState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
		System.out.println("Gen Started...");
		worldData = new WorldData();
		System.out.println("Gen Finished.");
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
	}
	
	@Override
	public void render() {
		g.drawMode(g.DRAW_WORLD);
		
		for(int x = 0; x < WorldData.WORLD_SIZE; x++) {
			for(int y = 0; y < WorldData.WORLD_SIZE; y++) {
				worldData.map[x][y].render(g);
			}
		}
	}
}
