package com.highasacat.dinnerwithdanger;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.highasacat.dinnerwithdanger.entities.Player;
import com.highasacat.dinnerwithdanger.graphics.Screen;
import com.highasacat.dinnerwithdanger.input.Keyboard;
import com.highasacat.dinnerwithdanger.level.Level;


public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 4;
	public static String title = "Dinner With Danger v0.01";
	
	private Thread thread; // declare a thread object
	private JFrame frame;  // instantiate an instance of JFrame, we'll call it frame
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;  // flag for "is the game currently running?", starts false
	
	
	private Screen screen;
	
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension (width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		level = new Level("/textures/levels/level.png");
		key = new Keyboard();
		player = new Player(30, 30, key);
		addKeyListener(key);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		long lastTime = System.nanoTime(); // establishes comparison point in time, initial
		final double ns = 1000000000.0 / 100.0; // 1/100th of one second
		double delta = 0;
		
		long timer = System.currentTimeMillis();
		int frames = 0; // FPS counter
		int updates = 0; // Logic counter, locked at 60 loops/sec
		
		requestFocus();
		
		while (running) {
			long now = System.nanoTime(); // point to be compared against initial time
			delta += (now - lastTime) / ns; // 1/100th of the change in time, compounded onto delta
			lastTime = now; // update to new comparison point for next loop, new initial
			
			while (delta >= 1){  //limits execution to 100 times per second
				update();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			// this conditional watches the system msec time, and every full second tick, prints our ups/fps
			if (System.currentTimeMillis() - timer > 1000) {
				timer +=1000; // loop triggered, this sets the loop ready to watch for the next trigger
				System.out.println(updates + " ups, " + frames + " fps"); // print relevant information to console
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps"); // prints info to titlebar
				updates = 0; // reset for next output
				frames = 0; // reset
			}
		}
		
		stop();
	}

	//Main method
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
	public void update() {
		key.update();		
		player.update();

		
	}
	
	public void render() {
		// Triple buffering, basic graphic frames handling policy
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		int xScroll = player.x - screen.width /2;
		int yScroll = player.y - screen.height/2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("verdana", 0, 50));
		g.drawString("X: " + player.x + ", Y: " + player.y, 800, 600);
		g.dispose(); //releases resources after render complete, for each frame
		bs.show();
		
	}
	
}
