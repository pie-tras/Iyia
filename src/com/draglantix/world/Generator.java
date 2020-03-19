package com.draglantix.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.draglantix.flare.collision.AABB;
import com.draglantix.flare.collision.AABBCollider;

public class Generator {
	
	Random random = new Random();
	
	private int[][] tile;
	
	private List<Vector2i> core_cells;
	private List<Vector2i> cell_stack;
	private List<Vector2i> dead_ends;
	private List<List<Vector2i>> room_connections;
	
	public Generator(int seed) {
		random.setSeed(seed);
	
		tile = new int[World.TILE_MAP_SIZE][World.TILE_MAP_SIZE];
		
		for(int x = 0; x < World.TILE_MAP_SIZE; x++) {
			for(int y = 0; y < World.TILE_MAP_SIZE; y++) {
				tile[x][y] = 1; //Set tiles to walls
			}
		}
		
		core_cells = new ArrayList<Vector2i>();
		cell_stack = new ArrayList<Vector2i>();
		dead_ends = new ArrayList<Vector2i>();
		room_connections = new ArrayList<List<Vector2i>>();
		
		for(int x = 1; x < World.TILE_MAP_SIZE-2; x+=2) {
			for(int y = 1; y < World.TILE_MAP_SIZE-2; y+=2) {
				core_cells.add(new Vector2i(x, y));
			}
		}
		
		generateRooms();
		generateMaze();
		connectDoors();
		decayBranches();
	}
	
	private void generateRooms() {
		
		List<AABB> rooms = new ArrayList<AABB>();
		
		int maxWidth = 9;
		int maxHeight = 9;
		
		int roomAttempts = 1000;
		
		for(int i = 0; i < roomAttempts; i++) {
			
			int width = random.nextInt(maxWidth - 3) + 3;
			int height = random.nextInt(maxHeight - 3) + 3;
			
			int posX = random.nextInt(World.TILE_MAP_SIZE - maxWidth - 2) + 3;			
			int posY = random.nextInt(World.TILE_MAP_SIZE - maxWidth - 2) + 3;
			
			width += width%2==0?1:0;
			height += height%2==0?1:0;
			posX += posX%2==0?1:0;
			posY += posY%2==0?1:0;
			
			AABB tmp = new AABB(new Vector2f(posX - 1 + (width/2), posY - 1 + (height/2)), new Vector2f(width + 1, height + 1), false);
			
			boolean collides = false;
			
			for(AABB other : rooms) {
				if(AABBCollider.collide(tmp, other)) {
					collides = true;
				}
			}
			
			if(!collides) {
				rooms.add(tmp);
			}
			
			if(!collides) {
				for(int x = posX; x < width+posX; x++) {
					for(int y = posY; y < height+posY; y++) {
						tile[x][y] = 0;
					}
				}
				
				List<Vector2i> connections = new ArrayList<Vector2i>();
				
				for(int x = posX-1; x < width+posX+1; x++) {
					for(int y = posY-1; y < height+posY+1; y++) {
						checkCoreCell(x, y);
						if((x == posX-1 || x == width+posX) ||
							(y == posY-1 || y == height+posY)) {
								if(!(x == posX-1 && y == posY-1) && !(x == width+posX && y == posY-1) && !(x == posX-1 && y == height+posY) && !(x == width+posX && y == height+posY)) {
									if(x != World.TILE_MAP_SIZE - 2 && y != World.TILE_MAP_SIZE - 2) {
										connections.add(new Vector2i(x, y));
									}
								}
						}
					}
				}
				
				room_connections.add(connections);
			}
		}
	}
	
	private void generateMaze() {
		
		int x = core_cells.get(0).x;
		int y = core_cells.get(0).y;
		dead_ends.add(new Vector2i(x, y));
		
		List<Integer> border;
		boolean alive = true;
		int backtracking = 0;
		
		int load = 0;
		
		while(alive) {
			System.out.println("L:"+load);
			load++;
			border = checkBorder(x, y, 1);
			
			if(backtracking == 0) {
				cell_stack.add(new Vector2i(x, y));
				tile[x][y] = 0;
				checkCoreCell(x, y);
			}
			
			if(border.size() > 0) {
				int dir = border.get(random.nextInt(border.size()));
				
				switch(dir){
					case 0: //Left
						tile[x-1][y] = 0;
						x-=2;
						break;
					case 1: //Right
						tile[x+1][y] = 0;
						x+=2;
						break;
					case 2: //Up
						tile[x][y-1] = 0;
						y-=2;
						break;
					case 3: //Down
						tile[x][y+1] = 0;
						y+=2;
						break;
					default:
						break;
				}
				
				backtracking = 0;
			}else {
				if(cell_stack.size() > 1) {
					backtracking++;
					if(backtracking == 1) {
						dead_ends.add(new Vector2i(x, y));
					}
					cell_stack.remove(cell_stack.size()-1);
					x = cell_stack.get(cell_stack.size()-1).x;
					y = cell_stack.get(cell_stack.size()-1).y;
					
				}else {
					if(core_cells.size() > 0) {
						x = core_cells.get(0).x;
						y = core_cells.get(0).y;
						dead_ends.add(new Vector2i(x, y));
						System.out.println("New Maze");
					}else {
						alive = false;
						System.out.println("Dead");
					}
				}
			}
		}
		
	}
	
	private void connectDoors() {
		for(List<Vector2i> connections : room_connections) {
			
			for(int i = 0; i < connections.size(); i++) {
				Vector2i d = connections.get(i);
				if(surround(d.x, d.y) == 1) {
					connections.remove(d);
				}
			}
			
			int max = 1;
			if(connections.size() > 1) {
				max = random.nextInt(4)+1;
			}
			for(int i = 0; i < max; i++) {
				Vector2i door = connections.get(random.nextInt(connections.size()));
				if(connections.contains(new Vector2i(door.x-1, door.y))) {
					connections.remove(new Vector2i(door.x-1, door.y));
				}
				if(connections.contains(new Vector2i(door.x+1, door.y))) {
					connections.remove(new Vector2i(door.x+1, door.y));
				}
				if(connections.contains(new Vector2i(door.x, door.y-1))) {
					connections.remove(new Vector2i(door.x, door.y-1));
				}
				if(connections.contains(new Vector2i(door.x, door.y+1))) {
					connections.remove(new Vector2i(door.x, door.y+1));
				}
				tile[door.x][door.y] = 0;	
			}
		}
	}
	
	private void decayBranches() {
		for(Vector2i pos: dead_ends) {
			
			int x = pos.x;
			int y = pos.y;
			
			boolean alive = true;
			
			while(alive) {
				tile[x][y] = 1;	
				int result = bite(x, y);
				switch(result) {
					case 0: //Left
						x--;
						break;
					case 1: //Right
						x++;
						break;
					case 2: //Up
						y--;
						break;
					case 3: //Down
						y++;
						break;
					case 4: //Junction
						alive = false;
						tile[x][y] = 0;
						break;
					default:
						break;
				}
				
			}
		}
	}
	
	private int bite(int x, int y) {
		int path = 0;
		int dir = 0;
		
		if(tile[x-1][y] == 0) {
			path ++;
			dir = 0;
		}
		if(tile[x+1][y] == 0) {
			path ++;
			dir = 1;
		}
		if(tile[x][y-1] == 0) {
			path ++;
			dir = 2;
		}
		if(tile[x][y+1] == 0) {
			path ++;
			dir = 3;
		}
		
		if(path > 1) {
			dir = 4;
		}
		
		return dir;
	}
	
	private int surround(int x, int y) {
		int path = 0;
		
		if(tile[x-1][y] == 0) {
			path ++;
		}
		if(tile[x+1][y] == 0) {
			path ++;
		}
		if(tile[x][y-1] == 0) {
			path ++;
		}
		if(tile[x][y+1] == 0) {
			path ++;
		}
		
		return path;
	}
	
	private List<Integer> checkBorder(int x, int y, int type) {
		List<Integer> border = new ArrayList<Integer>();
		
		if(x > 2) {
			if(tile[x - 2][y] == type && tile[x - 3][y - 1] == type && tile[x - 3][y + 1] == type) {
				border.add(0);
			}
		}
		
		if(x < World.TILE_MAP_SIZE-3) {
			if(tile[x + 2][y] == type && tile[x + 3][y - 1] == type && tile[x + 3][y + 1] == type) {
				border.add(1);
			}
		}
		
		if(y > 2) {
			if(tile[x][y - 2] == type && tile[x - 1][y - 3] == type && tile[x + 1][y - 3] == type) {
				border.add(2);
			}
		}
		
		if(y < World.TILE_MAP_SIZE-4) {
			if(tile[x][y + 2] == type && tile[x - 1][y + 3] == type && tile[x + 1][y + 3] == type) {
				border.add(3);
			}
		}
	
		return border;
	}
	
	private void checkCoreCell(int x, int y) {
		Vector2i test = new Vector2i(x, y);
		if(core_cells.contains(test)) {
			core_cells.remove(test);
		}
	}
	
	public int getTile(int x, int y) {
		return tile[y][x];
	}

}
