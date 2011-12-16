package balls;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class Ball {
	
	public double PI = 3.14;
	public boolean inMotion;
	public int centerPointX;
	public int centerPointY;
	public int velocityX;
	public int velocityY;
	public double radius;
	public abstract void draw(Graphics g);
	public abstract void move(int boundaryX, int boundaryY);
	public abstract void collide(Ball other);
	public abstract void grow();
	
	public boolean collision(Ball other){
		double dist = Point2D.distance(this.centerPointX, this.centerPointY, 
				other.centerPointX, other.centerPointY);
		double combinedRad = this.radius + other.radius;
		return (dist < combinedRad);
	}
	
	public int area(){
		return (int)(this.radius*this.radius*PI);
	}
	
	
}
