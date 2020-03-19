package com.draglantix.entities;

import org.joml.Vector2f;

import com.draglantix.flare.audio.AudioMaster;
import com.draglantix.flare.audio.Source;
import com.draglantix.flare.collision.AABB;
import com.draglantix.flare.textures.Animation;

public abstract class Dynamic extends Entity{

	protected Source source;
	
	protected boolean alive = true;
	protected float health = 1f;
	protected float speed = 1f;

	protected Animation animation = null;

	protected AABB bounds;
	
	public Dynamic(Animation animation, Vector2f position, Vector2f scale, AABB bounds) {
		super(animation.getTexture(), position, scale);
		this.animation = animation;
		this.bounds = bounds;
		source = new Source(1.5f, 10f, 0);
		source.setPosition(this.position);
		AudioMaster.sources.add(source);
	}
	
	@Override
	public void tick() {
		if(animation != null) {
			this.texture = animation.getTexture();
		}
		source.setPosition(position);
		alive = checkLiving();
	}
	
	private boolean checkLiving() {
		return health > 0f;
	}

	public void move(Vector2f dir) {
		if(bounds != null) {
			bounds.getCenter().add(dir);
			EntityManager.checkCollisions(this);
			position = new Vector2f(bounds.getCenter());
		}else {
			position.add(dir);
		}
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
}
