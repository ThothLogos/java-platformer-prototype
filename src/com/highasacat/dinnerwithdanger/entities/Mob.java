package com.highasacat.dinnerwithdanger.entities;

import com.highasacat.dinnerwithdanger.graphics.Sprite;



public class Mob extends Entity {
	
	protected Sprite sprite; // assigns a sprite object for mobs
	protected int dir = 1; // the direction the mob is facing, right (1) by default
	protected boolean moving = false; // flag to manage movement animations
	
	public void move(int xMove, int yMove) {
		
		// based on movement input, change the direction of our Mob
		if (xMove > 0) dir = 1;
		if (xMove < 0) dir = 3;
		//if (yMove < 0) dir = 0;
		//if (yMove > 0) dir = 2;
		
		
		// Go ahead and update position, ie move, as long as we aren't up against something solid
		if (!collision()) {
			x += xMove;
			y += yMove;
		}
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	private boolean collision() {
		return false;
	}
}
