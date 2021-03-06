package com.draglantix.states;

import java.io.File;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.util.Color;
import com.draglantix.flare.window.Window;
import com.draglantix.main.Assets;
import com.draglantix.main.Game;
import com.draglantix.main.Settings;
import com.draglantix.world.World;

public class MenuState extends GameState {

	private float alpha = 0f;
	private boolean fadeOut = false;
	
	private static float screenWidth = Window.getWidth()/Graphics.getScale();
	private static float screenHeight = Window.getHeight()/Graphics.getScale();
	private static float offset = Window.getHeight() / 50f;
	
	private int settingsIndex;
	
	private boolean save1exists, save2exists, save3exists;
	
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
		
		public void resetCursorOffset() {
			MAIN.cursorOffset = (screenHeight/8);
			START.cursorOffset = (screenHeight/2) - (screenHeight/8);
			CREATE.cursorOffset = (screenHeight/2) - (screenHeight/8);
			OPTIONS.cursorOffset = (screenHeight/2) - (screenHeight/8);
			SETBOOL.cursorOffset = (screenHeight/2) - (screenHeight/8);
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
	
	public void init() {
		rescale();
	}
	
	private void checkSaves() {
		 File save1, save2, save3;
	    
         save1 = new File("res/maps/save1.map");
         save2 = new File("res/maps/save2.map");
         save3 = new File("res/maps/save3.map");

         save1exists = save1.exists();
         save2exists = save2.exists();
         save3exists = save3.exists();
	     
	}
	
	private void rescale() {
		screenWidth = Window.getWidth()/Graphics.getScale();
		screenHeight = Window.getHeight()/Graphics.getScale();
		offset = Window.getHeight()/50f;
		MenuSection.MAIN.resetCursorOffset();
	}

	@Override
	public void tick() {
		
		if(Window.hasResized()) {
			 rescale();
		}
		
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
						if(save1exists) {
							World.setCurrentLevel("save1");
							gsm.setState(States.INTRO);
						}
					}else if(currentIndex == 2) {
						if(save2exists) {
							World.setCurrentLevel("save2");
							gsm.setState(States.INTRO);
						}
					}else if(currentIndex == 3) {
						if(save3exists) {
							World.setCurrentLevel("save3");
							gsm.setState(States.INTRO);
						}
					}else {
						currentSection = MenuSection.MAIN;
					}
					break;
				case CREATE:
					if(currentIndex == 0) {
						World.createNewWorld(3294, "save1");
						currentSection = MenuSection.START;
					}else if(currentIndex == 1) {
						World.createNewWorld(3294, "save2");
						currentSection = MenuSection.START;
					}else if(currentIndex == 2) {
						World.createNewWorld(3294, "save3");
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
						switch(settingsIndex) {
							case 0:
								Settings.DEBUG = true;
							default:
								break;
						}
					}else if(currentIndex == 1) {
						switch(settingsIndex) {
							case 0:
								Settings.DEBUG = false;
							default:
								break;
						}
					}
					currentSection = MenuSection.OPTIONS;
					MenuSection.OPTIONS.list =  new String[] {"Debug: " + Settings.DEBUG, "Back"};
					Game.resetSettings();
					
				default:
					break;
			}
			
			currentIndex = 0;
			checkSaves();
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
			g.drawString(Assets.font, "Iyia", new Vector2f((-screenWidth/2) + (offset*.8f), (screenHeight/2) - offset), new Vector2f(screenHeight/10), new Color(255, 255, 255, alpha), g.FONT_LEFT);
		}
		
		for(int i = 0; i < currentSection.getList().length; i++) {
			if(currentSection == MenuSection.START) {
				if(i == 1 && !save1exists) {
					g.drawString(Assets.font, currentSection.getList()[i], new Vector2f((-screenWidth/2) + offset, currentSection.getCursorOffset() - (i * offset)), new Vector2f(screenHeight/30), new Color(55, 55, 55, alpha), g.FONT_LEFT );
				}else if(i == 2 && !save2exists) {
					g.drawString(Assets.font, currentSection.getList()[i], new Vector2f((-screenWidth/2) + offset, currentSection.getCursorOffset() - (i * offset)), new Vector2f(screenHeight/30), new Color(55, 55, 55, alpha), g.FONT_LEFT );			
				}else if(i == 3 && !save3exists) {
					g.drawString(Assets.font, currentSection.getList()[i], new Vector2f((-screenWidth/2) + offset, currentSection.getCursorOffset() - (i * offset)), new Vector2f(screenHeight/30), new Color(55, 55, 55, alpha), g.FONT_LEFT );
				}else {
					g.drawString(Assets.font, currentSection.getList()[i], new Vector2f((-screenWidth/2) + offset, currentSection.getCursorOffset() - (i * offset)), new Vector2f(screenHeight/30), new Color(255, 255, 255, alpha), g.FONT_LEFT );
				}
			}else {
				g.drawString(Assets.font, currentSection.getList()[i], new Vector2f((-screenWidth/2) + offset, currentSection.getCursorOffset() - (i * offset)), new Vector2f(screenHeight/30), new Color(255, 255, 255, alpha), g.FONT_LEFT );
			}
		}
		g.drawImage(Assets.selector, new Vector2f((-screenWidth/2) + (offset*.8f), currentSection.cursorOffset - (currentIndex * offset)), new Vector2f(screenHeight/30), new Vector2f(0, 0), new Color(255, 255, 255, alpha));
	}
}
