package com.highasacat.dinnerwithdanger.graphics;

import com.highasacat.dinnerwithdanger.graphics.SpriteSheet;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite wall = new Sprite(32, 1, 0, SpriteSheet.tiles);
	public static Sprite floor = new Sprite(32, 2, 0, SpriteSheet.tiles);
	
	public static Sprite abyss = new Sprite(32, 0, 0, SpriteSheet.tiles);
	
	public static Sprite player_idle = new Sprite(32, 0, 1, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 1, 1, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32, 2, 1, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x * size; // applies size factor, treat sprites as whole units
		this.y = y * size;
		this.sheet = sheet;
		
		load();	
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++){
			for (int x = 0; x < SIZE; x++){
				pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y)* sheet.SIZE ];
			}
		}
	}	
	
	
	
	
	/* Allows definition of sprites based on solid colors. Previously used for
	 * the abyss tile, which was redefined in the SpriteSheet. 
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i > SIZE*SIZE; i++) {
			pixels[i] = color;
		}
	}
	
	*/
	
	
	
}
