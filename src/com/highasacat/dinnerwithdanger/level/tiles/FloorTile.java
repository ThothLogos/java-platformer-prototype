package com.highasacat.dinnerwithdanger.level.tiles;

import com.highasacat.dinnerwithdanger.graphics.Screen;
import com.highasacat.dinnerwithdanger.graphics.Sprite;

public class FloorTile extends Tile {

	public FloorTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << screen.TILE_RES_2N, y << screen.TILE_RES_2N, this);
	}
	
	public boolean solid () {
		return true;
	}
	
}
