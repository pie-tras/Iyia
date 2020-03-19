package com.draglantix.entities;

import org.joml.Vector2f;

import com.draglantix.flare.collision.AABB;
import com.draglantix.flare.textures.Animation;

public class Sheep extends Dynamic{

	public Sheep(Animation animation, Vector2f position, Vector2f scale, AABB bounds) {
		super(animation, position, scale, bounds);
	}
	
	@Override
	public void tick() {
		super.tick();
	}

}
