package com.draglantix.entities;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;

public class Player extends Dynamic{

	private Vector2f destination = new Vector2f(0, 0);
	private Vector2f last = new Vector2f();
	private boolean moved = false, sprinting = false;
	private float speed = 1.2f;
	
	public Player(Animation animation, Vector2f position, Vector2f scale) {
		super(animation, position, scale);
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
			move(destination.normalize(speed), true);
		}
		
		handleAnimations();
		
		last = new Vector2f(destination);
		moved = false;
	}
	
	private void handleAnimations() {
		
		if(!moved) {
			if(last.y > 0) {
				this.animation = Assets.IplayerUAnim;
			} else if(last.y < 0) {
				this.animation = Assets.IplayerDAnim;
			}

			if(last.x > 0) {
				this.animation = Assets.IplayerRAnim;
			} else if(last.x < 0) {
				this.animation = Assets.IplayerLAnim;
			}
		}else {
			
			if(sprinting) {
				if(destination.y > 0) {
					this.animation = Assets.playerUAnimS;
				} else if(destination.y < 0) {
					this.animation = Assets.playerDAnimS;
				}
		
				if(destination.x > 0) {
					this.animation = Assets.playerRAnimS;
				} else if(destination.x < 0) {
					this.animation = Assets.playerLAnimS;
				}
			}else {
				if(destination.y > 0) {
					this.animation = Assets.playerUAnim;
				} else if(destination.y < 0) {
					this.animation = Assets.playerDAnim;
				}
		
				if(destination.x > 0) {
					this.animation = Assets.playerRAnim;
				} else if(destination.x < 0) {
					this.animation = Assets.playerLAnim;
				}
			}
		}
		
	}

}
