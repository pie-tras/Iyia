package com.draglantix.entities;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.utils.DragonMath;

public class EntityManager {

	public static List<Dynamic> dynamics = new ArrayList<Dynamic>();	
	
	public static void tick() {
		for(Dynamic d: dynamics) {
			d.tick();
		}
	}
	
	public static void render(Graphics g) {
		for(Dynamic d: dynamics) {
			if(DragonMath.isOnScreen(d.getPosition(), new Vector2f(64, 64), g)) {
				d.render(g);
				//Debug.renderBounds(d.getBounds(), g);
			}
		}
	}
	
}
