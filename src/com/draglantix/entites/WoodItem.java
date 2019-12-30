package com.draglantix.entites;

import org.joml.Vector2f;

import com.draglantix.flare.textures.Texture;

public class WoodItem extends Dynamic{

	private boolean pickedUp = false;
	
	public WoodItem(Texture texture, Vector2f position, Vector2f scale) {
		super(texture, position, scale);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(pickedUp) {
			this.health = 0;
		}
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

}
