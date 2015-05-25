package com.highasacat.dinnerwithdanger.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpriteSheet {
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	// Declares and instantiates a SpriteSheet object, and passes the constructor
	// two pieces of information: the location of an image and the size of area to scan
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 512);
	
	
	// SpriteSheet Constructor -- this is called as part of our object creation, above.
	// The incoming path and size are mapped locally, and size is used to create a 1D
	// integer array, having the same number of elements as the total number of pixels to
	// be scanned from the incoming image referenced by path.
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		// prepare an array of appropriate size
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	
	
	// Translates image file to pixel data in the pixels array
	private void load() {
		try {
			//creates an image object composed of the source file passed in path
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth(); // measures and matches the incoming image
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w); // scans the image, places it in the array
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/*
	 * Attempt to custom read sprite sheets by skipping borders for sprite cells
	 * Hasn't worked out yet, getRGB isn't playing nice with my requests for scans
	 * of specific sections of coordinates, rather than the whole image.
	 * 
	 * // Translates image file to pixel data in the pixels array
	private void load() {
		try {
			//creates an image object composed of the source file passed in path
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth(); // measures and matches the incoming image
			int h = image.getHeight();
			int interval = 0;
			int counter = 0;
			for (int i = SIZE; i < SIZE * SIZE; i++) {
				interval++;
				if (interval == 32) {
					i++;
					interval = 0;
				} else {
					int x = 0 - (i - SIZE);
					int y = i % SIZE;
					image.getRGB(x, y, x+32, y, pixels, 0, x+32); // scans the image, places it in the array
				}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	 */
}
