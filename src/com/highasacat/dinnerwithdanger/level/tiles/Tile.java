package com.highasacat.dinnerwithdanger.level.tiles;

import com.highasacat.dinnerwithdanger.graphics.Screen;
import com.highasacat.dinnerwithdanger.graphics.Sprite;



public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile wall = new WallTile(Sprite.wall); 
	public static Tile floor = new FloorTile(Sprite.floor);
	public static Tile abyss = new AbyssTile(Sprite.abyss);
	
	
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid () {
		return false;
	}
	
	
}
