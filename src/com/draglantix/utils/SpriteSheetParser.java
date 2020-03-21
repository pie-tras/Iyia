package com.draglantix.utils;

import org.joml.Vector2f;

import com.draglantix.flare.textures.SpriteSheet;
import com.draglantix.flare.textures.Texture;

public class SpriteSheetParser {
	
	private Texture[] textures;
	
	public SpriteSheetParser(SpriteSheet sheet, int scale) {
		textures = new Texture[(sheet.getWidth() * sheet.getHeight())/scale];
		
		int i = 0;
		for(int y = 0; y < sheet.getHeight()/scale; y++) {
			for(int x = 0; x < sheet.getWidth()/scale; x++) {
				textures[i] = new Texture(sheet.crop(new Vector2f(x * scale, y * scale), new Vector2f(scale)));
				i++;
			}
		}
	}
	
	public Texture get(int i) {
		return textures[i];
	}

}
