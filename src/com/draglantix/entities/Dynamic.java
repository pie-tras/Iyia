package com.draglantix.entities;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.draglantix.flare.audio.AudioMaster;
import com.draglantix.flare.audio.Source;
import com.draglantix.flare.textures.Animation;
import com.draglantix.tiles.Tile;
import com.draglantix.tiles.TileLib;
import com.draglantix.tiles.TileMap;
import com.draglantix.utils.DragonMath;

public abstract class Dynamic extends Entity{

	protected Source source;
	
	protected boolean alive = true, interacting = false, swimming = false;
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
		if(interacting && this.animation.hasPlayed()) {
			interacting = false;
			this.animation.setHasPlayed(false);
		}
		if(animation != null) {
			this.texture = animation.getTexture();
		}
		source.setPosition(DragonMath.worldPos(this.position));
		alive = checkLiving();
	}

	public void move(Vector2i dir) {
		Vector2i open = checkCollisions(new Vector2i(dir));
		interact(dir);
		this.position.add(open);
		stand(TileMap.getTile(this.position));
	}
	
	private Vector2i checkCollisions(Vector2i dir) {
		
		Vector2i dx = this.position.add(dir.x, 0, new Vector2i());
		Vector2i dy = this.position.add(0, dir.y, new Vector2i());
		
		Tile tx = TileMap.getTile(dx);
		Tile ty = TileMap.getTile(dy);
		
		Entity ex = EntityManager.getEntity(dx);
		Entity ey = EntityManager.getEntity(dy);
		
		if(tx != null && tx.isSolid()) {
			dir.x = 0;
		}
		
		if(ty != null && ty.isSolid()) {
			dir.y = 0;
		}
		
		if(ex != null) {
			if(ex.pushable) {
				Tile nextX = TileMap.getTile(this.position.add(dir.x, 0, new Vector2i()));
				if(nextX != null && !nextX.isSolid()) {
					ex.setPosition(nextX.getPos());
				}
			}else {
				dir.x = 0;
			}
		}
		
		if(ey != null) {
			if(ey.pushable) {
				Tile nextY = TileMap.getTile(this.position.add(0, dir.y, new Vector2i()));
				if(nextY != null && !nextY.isSolid()) {
					ey.setPosition(nextY.getPos());
				}
			}else {
				dir.y = 0;
			}
		}
		
		if(ey != null) {
			if(ey.pushable) {
			
			}else {
				
			}
		}
		
		return dir;
	}
	
	private void interact(Vector2i dir) {
		Tile t = TileMap.getTile(this.position.add(dir, new Vector2i()));
		
		String out = null;
		
		if(t.getName() == "door_open") {
			out = "door_closed";
		}
		
		if(t.getName() == "door_closed") {
			out = "door_open";
		}
		
		if(out != null) {
			TileMap.setTile(TileLib.createTile(TileLib.TILE_IDS.get(out), t.getPos()), t.getPos());
			interacting = true;
			this.animation.setHasPlayed(false);
		}
		
	}
	
	private void stand(Tile t) {
		if(t.getName() == "water") {
			swimming = true;
		}else {
			swimming = false;
		}
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
