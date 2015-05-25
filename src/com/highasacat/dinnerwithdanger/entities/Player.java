package com.highasacat.dinnerwithdanger.entities;

import com.highasacat.dinnerwithdanger.graphics.Screen;
import com.highasacat.dinnerwithdanger.graphics.Sprite;
import com.highasacat.dinnerwithdanger.input.Keyboard;
import com.highasacat.dinnerwithdanger.level.Level;

public class Player extends Mob {
	
	private Keyboard input;
	private boolean walking = false;
	private boolean airborne = false;
	
	private double vel = 0.0;
	private double accel = 0.0;
	private double accel_jump = 0.0;
	private static final double gravity = 9.8;
	private static final double delta_t = 0.1;
	
		
	// Constructor - default "spawner" for player
	public Player(Keyboard input) {
		this.input = input;
	}
	
	// Constructor - alternative, specific spawn locations
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	// Player update method, overrides Mob update, which overrides Entity
	public void update() {
		
		int xMove = 0, yMove = 0;

		this.accel = accel_jump + gravity;
		this.vel = this.vel + (this.accel * delta_t);
		
		this.y = (int) (this.y + (this.vel * delta_t) + (0.5 * (this.accel * (delta_t * delta_t))));
		
		//if (input.up) yMove--;
		//if (input.down) yMove++;
		if (input.right) xMove++;
		if (input.left) xMove--; 
		if (input.jump) {
			this.accel_jump -= 0.1;
		}
		
		
		if (this.y > 224) {
			this.y = 224;
			this.accel = 0;
			this.vel = 0;
		}
	
		
		if (xMove != 0 || yMove != 0) {
			move(xMove, yMove);
			walking = true;
		} else {
			walking = false;
			
		}
		
		
		
	}
	
	public void setPlayerSpawn(int x, int y) {
		
	}
	

	// Player render method, overrides Mob render, which overrides Entity
	public void render(Screen screen) {
		
		
		//if (this.dir == 0) screen.renderPlayer(x, y, Sprite.player0);
		if (this.dir == 1) screen.renderPlayer(x, y, Sprite.player_right);
		//if (this.dir == 2) screen.renderPlayer(x, y, Sprite.player2);
		if (this.dir == 3) screen.renderPlayer(x, y, Sprite.player_left);
		
	}
}
