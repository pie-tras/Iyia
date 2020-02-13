package com.draglantix.states;

import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.main.GSM;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.main.Settings;

public class GameStateManager extends GSM {

	private GameState currentState;

	private PlayState playState;

	@Override
	public void init() {
		super.init();
		Assets.init(g);
	
		playState = new PlayState(g, this);
		
		setState(Settings.START_STATE);
	}

	@Override
	public void update() {
		super.update();
		currentState.tick();
		currentState.render();
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			Window.close();
		}
	}

	public void setState(States state) {
		if(currentState != null)
			currentState.stop();
		
		switch(state) {
			case PLAY:
				currentState = playState;
				break;
	
			default:
				break;
		}
		
		currentState.start();
	}

}