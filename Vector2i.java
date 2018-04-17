package game;

public class Vector2i {

	double x = 0;
	double y = 0;
	
	public Vector2i(double x,double y) {
		set(x,y);
	}
	
	public Vector2i() {
		set(0,0);
	}
	
	public Vector2i(Vector2i vector) {
		set(vector.x,vector.y);
	}
	
	public void set(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(double value) {
		this.x += value;
		this.y += value;
	}
	
	public void add(Vector2i vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
	
	public void mult(double value) {
		this.x *= value;
		this.y *= value;
	}
	
	public void mult(Vector2i vector) {
		this.x *= vector.x;
		this.y *= vector.y;
	}
	
}
