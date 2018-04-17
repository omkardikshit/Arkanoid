package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick {
	static int w = 100;
	static int h = 20;
	
	int x;
	int y;
	
	public Brick(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
	    Rectangle ball = new Rectangle(Ball.x, Ball.y, Ball.r, Ball.r);
	    Rectangle brick = new Rectangle(x,y,w,h);
	    
	    if(ball.intersects(brick)) {
	    	this.x = -100;
	    	this.y = -100;
	    	Ball.yspeed *= -1;
	    }
	}
	
	public void show(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(this.x,this.y,w,h);
	}

}
