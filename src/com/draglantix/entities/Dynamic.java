package com.draglantix.entities;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.draglantix.flare.audio.AudioMaster;
import com.draglantix.flare.audio.Source;
import com.draglantix.flare.textures.Animation;
import com.draglantix.utils.DragonMath;

public abstract class Dynamic extends Entity{

	protected Source source;
	
	protected boolean alive = true;
	protected float health = 1f;

	protected Animation animation = null;
	
	public Dynamic(Animation animation, Vector2i position, Vector2f scale) {
		super(animation.getTexture(), position, scale);
		this.animation = animation;
		source = new Source(1.5f, 10f, 0);
		source.setPosition(DragonMath.worldPos(this.position));
		AudioMaster.sources.add(source);
	}
	
	@Override
	public void tick() {
		if(animation != null) {
			this.texture = animation.getTexture();
		}
		source.setPosition(DragonMath.worldPos(this.position));
		alive = checkLiving();
	}

	public void move(Vector2i dir) {
		//dir = checkCollisions();
		this.position.add(dir);
	}
	
	private Vector2i checkCollisions() {
		return null;
	}
	
	private boolean checkLiving() {
		return health > 0f;
	}
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
}
