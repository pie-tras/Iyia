package com.draglantix.entities;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.draglantix.flare.textures.Animation;

public class Sheep extends Dynamic{

	public Sheep(Animation animation, Vector2i position, Vector2f scale) {
		super(animation, position, scale);
	}
	
	@Override
	public void tick() {
		super.tick();
	}

}
