package com.highasacat.dinnerwithdanger.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	// Number of unique keystrokes that can be handled by the input engine
	private boolean[] keys = new boolean[256]; // plenty
	
	// declare our control concepts
	public boolean up, down, left, right, jump;
	
	//public boolean test_function;
	

	public void update(){
		up = keys[KeyEvent.VK_UP] || keys [KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys [KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys [KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys [KeyEvent.VK_D];
		
		jump = keys[KeyEvent.VK_SPACE];
		
		//test_function = keys[KeyEvent.VK_T];
		
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	public void keyTyped(KeyEvent e) {

	}

}
