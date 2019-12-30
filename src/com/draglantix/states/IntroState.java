package com.draglantix.states;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Color;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;

public class IntroState extends GameState {
	
	private float alpha = 1f;
	private boolean fadeIn = false;
	private int finished = 0;
	
	private String message = "Beware of what lurks in the shadows";

	public IntroState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
		Assets.musicSource.play(Assets.darkloop);
	}

	@Override
	public void tick() {
		
		if(Assets.logoAnim.hasPlayed()) {
			if(fadeIn) {
				alpha += 0.005f;
				if(alpha > 1) {
					alpha = 1;
					fadeIn = false;
				}
			}else {
				alpha -= 0.005f;
				if(alpha < 0) {
					alpha = 0;
					finished += 1;
					fadeIn = true;
				}
			}
		}
		
		if((finished > 0 && Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE)) || finished > 1) {
			gsm.setState(States.MENU);
		}
	}

	@Override
	public void render() {
		g.drawMode(g.DRAW_SCREEN);
		if(finished == 0) {
			g.drawImage(Assets.logoAnim.getTexture(), new Vector2f(0, 0), new Vector2f(128, 128), new Vector2f(0, 0), new Color(255, 255, 255, alpha));
		}else {
			g.drawString(Assets.font, message, new Vector2f(0, 0), new Vector2f(6, 6), new Color(255, 255, 255, alpha), g.FONT_CENTER);
		}
	}

}
