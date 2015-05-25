package com.highasacat.dinnerwithdanger.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.highasacat.dinnerwithdanger.entities.Player;
import com.highasacat.dinnerwithdanger.graphics.Screen;
import com.highasacat.dinnerwithdanger.level.tiles.Tile;

public class Level {
	
	public static int width;
	protected int height;
	protected int[] tilesInt;
	public static int[] tiles;

	public Level(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();

			tiles = new int[w*h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file.");
		}
		generateLevel();
	}
	
	protected void loadLevel(String path) {
		
	}
	
	protected void generateLevel() {
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		// Ep 34: Setting Offsets
		screen.setOffset(xScroll, yScroll);
		// Ep 31: Corner Pins
		int x0 = xScroll >> 5; // conversion to tiles
		int x1 = (xScroll + screen.width + 32) >> 5;
		int y0 = yScroll >> 5;
		int y1 = (yScroll + screen.height + 32) >> 5; 
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);

			}
		}
	}
	
	public Tile getTile(int x, int y) {
		
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.abyss;
		if (tiles[x + y * width] == 0xff000000) return Tile.abyss;
		if (tiles[x + y * width] == 0xff8B89FF) return Tile.wall;
		if (tiles[x + y * width] == 0xff515151) return Tile.floor;
		/*if (tiles[x + y * width] == 0xffFFD800) {
			setPlayerSpawn(x, y);
			return null;
		}*/
		else return Tile.wall;
	}
	
	public void setPlayerSpawn(int x, int y) {
		
	}
	
}
