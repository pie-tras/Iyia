package com.draglantix.states;

import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.main.GSM;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.main.Settings;

public class GameStateManager extends GSM {

	private GameState currentState;

	private SplashState splashState;
	private IntroState introState;
	private MenuState menuState;
	private PlayState playState;

	@Override
	public void init() {
		super.init();
		Assets.init(g);
		
		splashState = new SplashState(g, this);
		introState = new IntroState(g, this);
		menuState = new MenuState(g, this);
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
			case SPLASH:
				currentState = splashState;
				break;
	
			case INTRO:
				currentState = introState;
				break;
	
			case MENU:
				currentState = menuState;
				break;
	
			case PLAY:
				currentState = playState;
				break;
	
			default:
				break;
		}
		
		currentState.start();
	}

}