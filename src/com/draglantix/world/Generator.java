package com.draglantix.world;

import java.util.Random;

public class Generator {
	
	Random random = new Random();
	
	public Generator(int seed) {
		random.setSeed(seed);
	}
	
	public int dungeonTile(int x, int y) {
		return random.nextInt(2);
	}

}
