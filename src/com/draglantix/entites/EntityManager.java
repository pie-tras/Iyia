package com.draglantix.entites;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.utils.DragonMath;

public class EntityManager {

	public static List<Dynamic> dynamics = new ArrayList<Dynamic>();	
	public static List<Dynamic> deadDynamics = new ArrayList<Dynamic>();
	
	public static void tick() {
		for(Dynamic d: dynamics) {
			d.tick();
			if(!d.alive) {
				deadDynamics.add(d);
			}
		}
		dynamics.removeAll(deadDynamics);
		deadDynamics.removeAll(deadDynamics);
	}
	
	public static void render(Graphics g) {
		for(Dynamic d: dynamics) {
			if(DragonMath.isOnScreen(d.getPosition(), new Vector2f(64, 64), g)) {
				d.render(g);
				//Debug.renderBounds(d.getBounds(), g);
				d.setOnScreen(true);
			}else {
				d.setOnScreen(false);
			}
		}
	}
	
}
