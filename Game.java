package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{
	private static final long serialVersionUID = 1L;

	public static final int width = 640;
	public static final int height = 480;

	public static String title = "Game";
	
	public static boolean running = false;
	public final int fps = 60;
	private Thread thread;
	private JFrame frame;

	public Pong pong;
	public Ball ball;
	public Brick bricks[] = new Brick[100];
	int n = 0;
	public Game() {
		pong = new Pong();
		ball = new Ball();
		for(int i = 1;i <= 5;i++) {
			for(int j = 1;j <= 5;j++) {
				bricks[n] = new Brick(i*120-80,j*40);
				n++;
			}
		}
		frame = new JFrame();
		Dimension size = new Dimension(width,height);
		setPreferredSize(size);
		setFocusable(true);
		requestFocus();
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		addKeyListener(this);
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
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0/fps;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				update();
				delta--;
			}

			render();
		}
	}
	
	public void update() {
		pong.update();
		ball.update();
		ball.edges();
		for(int i = 0;i < n;i++) {
			bricks[i].update();
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(51,51,51));
		g.fillRect(0, 0, getWidth(), getHeight());
		pong.render(g);
		ball.render(g);
		for(int i = 0;i < n;i++) {
			bricks[i].show(g);
		}
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}

	public void keyPressed(KeyEvent key) {
		int code = key.getKeyCode();
		if(code == KeyEvent.VK_LEFT) {
			pong.setLeft(true);
		} 
		if(code == KeyEvent.VK_RIGHT) {
			pong.setRight(true);
		} 
	}

	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();
		if(code == KeyEvent.VK_LEFT) {
			pong.setLeft(false);
		} 
		if(code == KeyEvent.VK_RIGHT) {
			pong.setRight(false);
		} 
	}

	public void keyTyped(KeyEvent arg0) {}

}
