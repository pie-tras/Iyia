package com.draglantix.states;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Color;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.main.Settings;

public class MenuState extends GameState {

	private float alpha = 0f;
	private boolean fadeOut = false;
	
	private static float screenWidth = Window.getWidth()/Graphics.getScale();
	private static float screenHeight = Window.getHeight()/Graphics.getScale();
	
	private int settingsIndex;
	
	public enum MenuSection {
		MAIN((screenHeight/8), new String[] {"Start", "Options", "Quit"}),
		START((screenHeight/2) - (screenHeight/8), new String[] {"Create New", "Load Save 1", "Load Save 2", "Load Save 3", "Back"}),
		CREATE((screenHeight/2) - (screenHeight/8), new String[] {"Save 1", "Save 2", "Save 3", "Back"}),
		OPTIONS((screenHeight/2) - (screenHeight/8), new String[] {"Debug: " + Settings.DEBUG, "Back"}),
		SETBOOL((screenHeight/2) - (screenHeight/8), new String[] {"True", "False", "Cancel"});
		
		private float cursorOffset;
		private String[] list;
		
		private MenuSection(float cursorOffset, String[] list) {
			this.cursorOffset = cursorOffset;
			this.list = list;
		}

		public float getCursorOffset() {
			return cursorOffset;
		}
		
		public String[] getList() {
			return list;
		}
	}
	
	private MenuSection currentSection = MenuSection.MAIN;
	private int currentIndex = 0;

	public MenuState(Graphics g, GameStateManager gsm) {
		super(g, gsm);
	}

	@Override
	public void tick() {
		
		fadeMenu();
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE) || Window.getInput().isKeyPressed(GLFW.GLFW_KEY_ENTER)) {
			switch(currentSection) {
				case MAIN:
					if(currentIndex == 0) {
						currentSection = MenuSection.START;
					}else if(currentIndex == 1) {
						currentSection = MenuSection.OPTIONS;
					}else {
						Window.close();
					}
					break;
				case START:
					if(currentIndex == 0) {
						currentSection = MenuSection.CREATE;
					}else if(currentIndex == 1) {
						
					}else if(currentIndex == 2) {
						
					}else if(currentIndex == 3) {
						
					}else {
						currentSection = MenuSection.MAIN;
					}
					break;
				case CREATE:
					if(currentIndex == 0) {
						currentSection = MenuSection.START;
					}else if(currentIndex == 1) {
						currentSection = MenuSection.START;
					}else if(currentIndex == 1) {
						currentSection = MenuSection.START;
					}else {
						currentSection = MenuSection.MAIN;
					}
					break;
				case OPTIONS:
					if(currentIndex == 0) {
						settingsIndex = 0;
						currentSection = MenuSection.SETBOOL;
					}else{
						currentSection = MenuSection.MAIN;
					}
					break;
				case SETBOOL:
					if(currentIndex == 0) {
						//Set selected setting to true
					}else if(currentIndex == 1) {
						//Set selected setting to false
					}else{
						currentSection = MenuSection.OPTIONS;
					}
				default:
					break;
			}
			
			currentIndex = 0;
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_S) || Window.getInput().isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
			currentIndex ++;
			if(currentIndex > currentSection.getList().length-1) {
				currentIndex = 0;
			}
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_W) || Window.getInput().isKeyPressed(GLFW.GLFW_KEY_UP)) {
			currentIndex --;
			if(currentIndex < 0) {
				currentIndex = currentSection.getList().length-1;
			}
		}
	}
	
	private void fadeMenu() {
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
	}

	@Override
	public void render() {
		if(currentSection == MenuSection.MAIN) {
			g.drawString(Assets.font, "Iyia", new Vector2f((-screenWidth/2) + 20f, (screenHeight/2) - 30f), new Vector2f(32, 32), new Color(255, 255, 255, alpha), g.FONT_LEFT);
		}
		
		for(int i = 0; i < currentSection.getList().length; i++) {
			g.drawString(Assets.font, currentSection.getList()[i], new Vector2f((-screenWidth/2) + 32f, currentSection.getCursorOffset() - (i * 32)), new Vector2f(6, 6), new Color(255, 255, 255, alpha), g.FONT_LEFT );
		}
		g.drawImage(Assets.selector, new Vector2f((-screenWidth/2) + 20f, currentSection.cursorOffset - (currentIndex * 32)), new Vector2f(8, 8), new Vector2f(0, 0), new Color(255, 255, 255, alpha));
	}
}
