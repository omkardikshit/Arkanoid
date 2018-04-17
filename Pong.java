package game;

import java.awt.Color;
import java.awt.Graphics;

public class Pong {

	static int x = Game.width/2;
	static int y = 450;
	static int w = 125;
	static int h = 25;
	static boolean left;
	static boolean right;
	static int xspeed;
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	
	public void update() {
		if(left) {
			xspeed = -10;
		}else if(right) {
			xspeed = 10;
		}else {
			xspeed = 0;
		}
		x += xspeed;
		if(x<0)x=0;
		if(x>Game.width-w)x=Game.width-w;
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 125, 25);
	}

}
