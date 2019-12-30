package com.draglantix.states;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Color;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;

public class MenuState extends GameState {

	private float alpha = 0f;
	private boolean fadeOut = false;
	
	private Vector2f[] selectorPos = {new Vector2f(-32, 0), new Vector2f(-32, -32)};
	private int current = 0;
	
	public MenuState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
	}

	@Override
	public void tick() {
		if(!fadeOut) {
			if(alpha < 1) {
				alpha += 0.005f;
			}else {
				alpha = 1f;
			}
		}else {
			if(alpha > 0) {
				alpha -= 0.01f;
			}else {
				alpha = 0f;
				gsm.setState(States.PLAY);
			}
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			if(current == 0) {
				fadeOut = true;
			}else {
				Window.close();
			}
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_S)) {
			current ++;
			if(current > selectorPos.length-1) {
				current = 0;
			}
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_W)) {
			current --;
			if(current < 0) {
				current = selectorPos.length-1;
			}
		}
	}

	@Override
	public void render() {
		g.drawImage(Assets.title, new Vector2f(0, 50), new Vector2f(128, 32), new Vector2f(0, 0), new Color(255, 255, 255, alpha));
		g.drawString(Assets.font, "Start", new Vector2f(0, 0), new Vector2f(6, 6), new Color(255, 255, 255, alpha), g.FONT_CENTER);
		g.drawString(Assets.font, "Quit", new Vector2f(0, -32), new Vector2f(6, 6), new Color(255, 255, 255, alpha), g.FONT_CENTER);
		g.drawImage(Assets.selector, selectorPos[current], new Vector2f(8, 8), new Vector2f(0, 0), new Color(255, 255, 255, alpha));
		g.drawImage(Assets.titlecampfire, new Vector2f(100, -35), new Vector2f(28, 28), new Vector2f(0, 0), new Color(255, 255, 255, alpha));
		g.drawImage(Assets.fireAnim.getTexture(), new Vector2f(100, -16), new Vector2f(64, 64), new Vector2f(0, 0), new Color(255, 100, 50, alpha));
	}

}
