package com.draglantix.tiles;

import java.util.HashMap;
import java.util.Map;

import com.draglantix.flare.textures.Texture;
import com.draglantix.main.Assets;

public class TileData {
	public static Map<Integer, TileData> tileIndex;
	
	public static TileData stone1, stone2, sand, corner1,
		corner2, corner3, corner4, flat1, flat2, flat3,
		flat4, grass, bone, solid1, solid2, solid3,
		solid4, solid5, solid6, solid7, solid8, solid9, campfire;
	
	private Texture texture;
	private boolean isSolid;
	
	public TileData(Texture texture, boolean isSolid) {
		this.texture = texture;
		this.isSolid = isSolid;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public static void init() {
		tileIndex = new HashMap<Integer, TileData>();
		
		stone1 = new TileData(Assets.stone1, false);
		stone2 = new TileData(Assets.stone2, false);
		sand = new TileData(Assets.sand, false);
		corner1 = new TileData(Assets.corner1, false);
		corner2 = new TileData(Assets.corner2, false);
		corner3 = new TileData(Assets.corner3, false);
		corner4 = new TileData(Assets.corner4, false);
		flat1 = new TileData(Assets.flat1, false);
		flat2 = new TileData(Assets.flat2, false);
		flat3 = new TileData(Assets.flat3, false);
		flat4 = new TileData(Assets.flat4, false);
		grass = new TileData(Assets.grass, false);
		bone = new TileData(Assets.bone, false);
		solid1 = new TileData(Assets.solid1, true);
		solid2 = new TileData(Assets.solid2, true);
		solid3 = new TileData(Assets.solid3, true);
		solid4 = new TileData(Assets.solid4, true);
		solid5 = new TileData(Assets.solid5, true);
		solid6 = new TileData(Assets.solid6, true);
		solid7 = new TileData(Assets.solid7, true);
		solid8 = new TileData(Assets.solid8, true);
		solid9 = new TileData(Assets.solid9, true);
		campfire = new TileData(Assets.campfire, true);
		
		tileIndex.put(1, stone1);
		tileIndex.put(2, stone2);
		tileIndex.put(3, sand);
		tileIndex.put(4, corner1);
		tileIndex.put(5, corner2);
		tileIndex.put(6, corner3);
		tileIndex.put(7, corner4);
		tileIndex.put(8, flat1);
		tileIndex.put(9, flat2);
		tileIndex.put(10, flat3);
		tileIndex.put(11, flat4);
		tileIndex.put(12, grass);
		tileIndex.put(13, bone);
		tileIndex.put(14, solid1);
		tileIndex.put(15, solid2);
		tileIndex.put(16, solid3);
		tileIndex.put(17, solid4);
		tileIndex.put(18, solid5);
		tileIndex.put(19, solid6);
		tileIndex.put(20, solid7);
		tileIndex.put(21, solid8);
		tileIndex.put(22, solid9);
		tileIndex.put(23, campfire);
	}
	
	public static TileData getTile(int index) {
		return tileIndex.get(index);
	}
}
