package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

	
	public static int x;
	public static int y;
	public static int r = 12;
	
	static float xspeed = 4;
	static float yspeed = 5;
	Pong pong;
	
	Ball(){
		reset();
	}
	
	public void update() {
		x += xspeed;
		y += yspeed;
	    Rectangle ball = new Rectangle(x, y, r, r);
	    Rectangle pong = new Rectangle(Pong.x, Pong.y, Pong.w, 5);
	    
	    if(ball.intersects(pong)) {
	    	yspeed *= -1;
	    }
	}
	
	public void edges() {
		if(x < 0 || x > Game.width-r)
			xspeed *= -1;
		if(y < 0)
			yspeed *= -1;
		if(y > Game.height)reset();
	}
	
	private void reset() {
		x = Game.width/2;
		y = Game.height/2;
		float angle = (float) Lib.random(-Math.PI/4,Math.PI/4);
		xspeed = (float) (5*Math.cos(angle));
		yspeed = (float) (5*Math.sin(angle));
		if(Math.random() < 0.5) {
			yspeed *= -1;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, r*2, r*2);
	}
	
	
}
