package com.draglantix.entities;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.draglantix.flare.collision.AABB;
import com.draglantix.flare.collision.AABBCollider;
import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Color;
import com.draglantix.main.Assets;
import com.draglantix.main.Settings;
import com.draglantix.utils.DragonMath;

public class EntityManager {

	public static List<Dynamic> dynamics = new ArrayList<Dynamic>();
	
	public static List<AABB> tileBounds = new ArrayList<AABB>();
	
	public static void tick() {
		for(Dynamic d: dynamics) {
			if(DragonMath.isOnScreen(d.getPosition(), new Vector2f(64, 64))) {
				d.tick();
			}
		}
	}
	
	public static void render(Graphics g) {
		for(Dynamic d: dynamics) {
			if(DragonMath.isOnScreen(d.getPosition(), new Vector2f(64, 64))) {
				d.render(g);
				if(Settings.DEBUG && d.bounds != null) {
					g.drawImage(Assets.debug, new Vector2f(d.bounds.getCenter()), new Vector2f(d.bounds.getScale()), new Vector2f(0,0), new Color(255, 255, 255, 1));
				}
			}
		}
	}
	
	public static void checkCollisions(Dynamic d) {
		
		for(Dynamic other : dynamics) {
			if(other.bounds != null && !d.equals(other)) {
				if(AABBCollider.collide(d.bounds, other.bounds)) {
					Vector2f force = AABBCollider.correct(d.bounds, other.bounds);
					if(!other.bounds.isMovable()) {
						d.bounds.getCenter().add(force);
					}else {
						force.x /= 2;
						force.y /= 2;
						d.bounds.getCenter().add(force);
						other.bounds.getCenter().add(new Vector2f(-force.x, -force.y));
					}
				}
			}
		}
		
		for(AABB other : tileBounds) {
			if(AABBCollider.collide(d.bounds, other)) {
				Vector2f force = AABBCollider.correct(d.bounds, other);
				if(!other.isMovable()) {
					d.bounds.getCenter().add(force);
				}else {
					force.x /= 2;
					force.y /= 2;
					d.bounds.getCenter().add(force);
					other.getCenter().add(new Vector2f(-force.x, -force.y));
				}
			}
		}
	}
	
}
