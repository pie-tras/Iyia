package com.draglantix.world;

import java.util.Random;

public class HeightGenerator {

	private static final float AMPLITUDE = 255f;
	private static final int OCTAVES = 5;
	private static final float ROUGHNESS = 0.3f;
	
	private Random rand = new Random();
	private int seed;
	private final int RAND_1 = 45367565;
	private final int RAND_2 = 86436752;

	public HeightGenerator() {
		this.seed = rand.nextInt(1000000000);
	}
	
	 public float generateHeight(int x, int y) {
	    float total = 0;
	    float d = (float) Math.pow(2, OCTAVES-1);
	    for(int i=0;i<OCTAVES;i++){
	        float freq = (float) (Math.pow(2, i) / d);
	        float amp = (float) Math.pow(ROUGHNESS, i) * AMPLITUDE;
	        total += getInterpolatedNoise(x*freq, y*freq) * amp;
	    }
	    
	    total += AMPLITUDE;
	    total /= 2;
	    
	    return total;
	}
	
	private float getInterpolatedNoise(float x, float y) {
		int intX = (int) x;
		int intY = (int) y;
		float fracX = x - intX;
		float fracY = y - intY;
		
		float v1 = getSmoothNoise(intX, intY);
		float v2 = getSmoothNoise(intX + 1, intY);
		float v3 = getSmoothNoise(intX, intY + 1);
		float v4 = getSmoothNoise(intX + 1, intY + 1);
		float i1 = interpolate(v1, v2, fracX);
		float i2 = interpolate(v3, v4, fracX);
		return interpolate(i1, i2, fracY);	
	}
	
	private float interpolate(float a, float b, float blend) {
		double theta = blend * Math.PI;
		float f = (float) ((1f - Math.cos(theta)) * 0.5f);
		return a * (1f - f) + b * f;
	}
	
	private float getSmoothNoise(int x, int y) {
		float corners = (getNoise(x - 1, y - 1) + getNoise(x + 1, y - 1)
		+ getNoise(x - 1, y + 1)+ getNoise(x + 1, y + 1)) / 16f;
		
		float sides = (getNoise(x - 1, y) + getNoise(x + 1, y)
		+ getNoise(x, y - 1) + getNoise(x, y + 1)) / 8f;
		
		float center = getNoise(x, y) / 4f;
		return corners + sides + center;
	}
	
	private float getNoise(int x, int y) {
		rand.setSeed(x * RAND_1 + y * RAND_2 + seed);
		return rand.nextFloat() * 2f - 1f;
	}
	
}