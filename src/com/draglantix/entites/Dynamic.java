package com.draglantix.entites;

import org.joml.Vector2f;

import com.draglantix.flare.audio.AudioMaster;
import com.draglantix.flare.audio.Source;
import com.draglantix.flare.collision.Collisions;
import com.draglantix.flare.collision.Polygon;
import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.textures.Texture;
import com.draglantix.flare.util.Functions;
import com.draglantix.tiles.Tile;
import com.draglantix.world.World;

public abstract class Dynamic extends Entity{

	protected Source source;
	
	protected boolean alive = true;
	protected float health = 1f;
	protected float speed = 1f;

	protected Polygon bounds;
	
	protected Animation animation = null;
	
	public Dynamic(Texture texture, Vector2f position, Vector2f scale) {
		super(texture, position, scale);
		source = new Source(1.5f, 10f, 0);
		source.setPosition(this.position);
		AudioMaster.sources.add(source);
		this.bounds = Functions.generateSquareBound(position, new Vector2f(scale.x/2, scale.y/2), true);
	}
	
	public Dynamic(Animation animation, Vector2f position, Vector2f scale, Vector2f custom) {
		super(animation.getTexture(), position, scale);
		this.animation = animation;
		source = new Source(1.5f, 10f, 0);
		source.setPosition(this.position);
		AudioMaster.sources.add(source);
		this.bounds = Functions.generateSquareBound(position, new Vector2f(scale.x/2 - custom.x, scale.y/2 - custom.y), true);
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
	
	private void checkForCollisions(Vector2f dir) {
		if(bounds.getIsMoveable()) {
			for(Dynamic entity : EntityManager.dynamics) {
				if(!this.equals(entity) && entity.getBounds() != null) {
					Vector2f force = Collisions.testCollision(bounds, entity.getBounds());
					bounds.add(force);
				}
			}
			for(Tile tile : World.boundingTiles) {
				if(tile.getBounds() != null) {
					Vector2f force = Collisions.testCollision(bounds, tile.getBounds());
					bounds.add(force);
				}
			}
		}
	}

	public void move(Vector2f dir, boolean checkCollide) {
		if(bounds != null) {
			bounds.add(dir);
			if(checkCollide)
				checkForCollisions(dir);
			position.set(bounds.getPosition());
		} else {
			position.add(dir);
		}

	}
	
	public Polygon getBounds() {
		return bounds;
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
