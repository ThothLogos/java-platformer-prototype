package com.highasacat.dinnerwithdanger.entities;

import java.util.Random;

import com.highasacat.dinnerwithdanger.graphics.Screen;
import com.highasacat.dinnerwithdanger.level.Level;

public abstract class Entity {

	
	public int x, y; // All entities should have a position
	private boolean removed = false; // flagged as in-game by default
	protected Level level;
	protected final Random random = new Random();
		
	public void update() {
	
	}
		
	public void render(Screen screen) {
		
	}
		
	public void remove() {
		removed = true;
	}
		
	public boolean isRemoved() {
		return removed;
	}
}
