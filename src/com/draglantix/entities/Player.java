package com.draglantix.entities;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.utils.DragonMath;
import com.draglantix.world.World;

public class Player extends Dynamic{

	private Vector2i destination = new Vector2i(0, 0);
	private float faceX = 1;
	
	public Player(Animation animation, Vector2i position, Vector2f scale) {
		super(animation, position, scale);
	}

	@Override
	public void tick() {
		super.tick();
		
		if(alive) {
			
			destination.x = 0;
			destination.y = 0;
			
			if(Window.getInput().isMouseButtonPressed(0)) {
				this.position = DragonMath.tilePos(DragonMath.convertScreenSpace().add(new Vector2f(World.TILE_SIZE/2)));
			}
		
		}
		
		handleAnimations();
	}
	
	private void handleAnimations() {
		if(!alive) {
			this.animation = Assets.playerDie;
		}else if(interacting) {
			this.animation = Assets.playerInteract;
		}else if(swimming){
			this.animation = Assets.playerSwimming;
		}else {
			this.animation = Assets.playerIdle;
		}
	}

}
