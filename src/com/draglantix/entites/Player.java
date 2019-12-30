package com.draglantix.entites;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.util.Functions;
import com.draglantix.flare.util.Timer;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.states.PlayState;

public class Player extends Dynamic{

	private Vector2f destination = new Vector2f(0, 0);
	
	private boolean moved = false, sprinting = false;
	private Vector2f last = new Vector2f();
	
	private boolean handled = false;
	
	public int wood = 0;
	
	private float sprint = 0f;
	private Timer timerSprint;
	
	public Player(Animation animation, Vector2f position, Vector2f scale) {
		super(animation, position, scale, new Vector2f(4, 2));
		setSpeed(1f);
		source.setPitch(1f);
		source.setVolume(0.5f);
		timerSprint = new Timer();
	}

	@Override
	public void tick() {
		super.tick();
		
		if(Window.getInput().isKeyReleased(GLFW.GLFW_KEY_J) && !handled) {
			handleInteractions();
		}
		
		destination.x = 0;
		destination.y = 0;
		
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_D)) {
			destination.x += speed;
			moved = true;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_A)) {
			destination.x -= speed;
			moved = true;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_W)) {
			destination.y += speed;
			moved = true;
		}

		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_S)) {
			destination.y -= speed;
			moved = true;
		}
		
		if(destination.length() != 0) {
			move(destination.normalize(speed), true);
			if(!source.isPlaying()) {
				int step = Functions.rand.nextInt(3);
				if(step == 0) {
					source.play(Assets.steps1);
				}else if(step == 1) {
					source.play(Assets.steps2);
				}else {
					source.play(Assets.steps3);
				}
			}
		}
		
		if(sprinting) {
			sprint += timerSprint.getDelta();
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_K)) {
			setSpeed(1.5f);
			source.setVolume(1f);
			source.setPitch(2f);
			sprinting = true;
		}
		
		if(Window.getInput().isKeyReleased(GLFW.GLFW_KEY_K) || sprint > 10) {
			setSpeed(0.5f);
			source.setVolume(1f);
			source.setPitch(1f);
			sprinting = false;
			sprint = 0;
		}
	
		handleAnimations();
	
		last = new Vector2f(destination);
		moved = false;
		handled = false;
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
	
	public void handleInteractions() {
		if(position.distance(PlayState.campfireLoc) < 45) {
			if(wood > 0 && PlayState.lightBar != 0) {
				wood --;
				PlayState.lightBar += 5;
			}
		}
		
		for(WoodItem item : PlayState.wood) {
			if(position.distance(item.getPosition()) < 20 && item.alive) {
				if(wood < 5) {
					item.setPickedUp(true);
					wood ++;
					handled = true;
					break;
				}
			}
		}
	}
	
}
