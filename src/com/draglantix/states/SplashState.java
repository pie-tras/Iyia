package com.draglantix.states;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Color;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;

public class SplashState extends GameState {

	public SplashState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
	}

	@Override
	public void tick() {
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			gsm.setState(States.INTRO);
		}
	}

	@Override
	public void render() {
		g.drawImage(Assets.log, new Vector2f(0, 0), new Vector2f(16, 16), new Vector2f(0, 0),
				new Color(0, 255, 255, 1));
	}

}
