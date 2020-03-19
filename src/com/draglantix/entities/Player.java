package com.draglantix.entities;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.collision.AABB;
import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;

public class Player extends Dynamic{

	private Vector2f destination = new Vector2f(0, 0);
	private float lastX = 1;
	private boolean moved = false;
	
	public Player(Animation animation, Vector2f position, Vector2f scale, AABB bounds) {
		super(animation, position, scale, bounds);
		this.speed = 1.2f;
		//this.bounds = null;
	}

	@Override
	public void tick() {
		super.tick();
		destination.x = 0;
		destination.y = 0;
		
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_D)) {
			destination.x++;
			moved = true;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_A)) {
			destination.x--;
			moved = true;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_W)) {
			destination.y++;
			moved = true;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_S)) {
			destination.y--;
			moved = true;
		}
		
		if(destination.length() != 0) {
			move(destination.normalize(speed));
		}
		
		handleAnimations();
		
		if(destination.x != 0) {
			lastX = destination.x;
		}
		
		moved = false;
	}
	
	private void handleAnimations() {
		
		if(moved) {
			if(destination.x > 0) {
				this.animation = Assets.playerWalkR;
			}else if(destination.x < 0) {
				this.animation = Assets.playerWalkL;
			}else {
				if(lastX > 0) {
					this.animation = Assets.playerWalkR;
				} else if(lastX < 0) {
					this.animation = Assets.playerWalkL;
				}
			}
		}else {
			if(lastX > 0) {
				this.animation = Assets.playerIdleR;
			} else if(lastX < 0) {
				this.animation = Assets.playerIdleL;
			}
		}
		
	}

}
