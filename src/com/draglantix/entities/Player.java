package com.draglantix.entities;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;

public class Player extends Dynamic{

	private Vector2i destination = new Vector2i(0, 0);
	private float faceX = 1;
	protected boolean interacting = false;
	
	public Player(Animation animation, Vector2i position, Vector2f scale) {
		super(animation, position, scale);
	}

	@Override
	public void tick() {
		super.tick();
		
		destination.x = 0;
		destination.y = 0;
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_D)) {
			destination.x++;
			if(faceX != destination.x)
				flip();
		}

		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_A)) {
			destination.x--;
			if(faceX != destination.x)
				flip();
		}

		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_W)) {
			destination.y++;
		}

		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_S)) {
			destination.y--;
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_J)) {
			interacting = true;
		}
		
		if(destination.x != 0) {
			faceX = destination.x;
		}
		
		if(destination.length() != 0) {
			move(destination);
		}
		
		handleAnimations();
	}
	
	private void handleAnimations() {
		if(interacting) {
			this.animation = Assets.playerInteract;
		}else {
			this.animation = Assets.playerIdle;
		}
	}

}
