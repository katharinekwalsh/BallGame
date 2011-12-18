package balls;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class Ball {
	
	public boolean inMotion;
	public int centerPointX;
	public int centerPointY;
	public int velocityX;
	public int velocityY;
	public double radius;
	public static double PI = 3.14;
	public static int WORLD_HEIGHT;
	public static int WORLD_WIDTH;
	public abstract void draw(Graphics g);
	public abstract void move();
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
	
	public static void setWorldHeight(int height){
		WORLD_HEIGHT = height;
	}
	
	public static void setWorldWidth(int width){
		WORLD_WIDTH = width;
	}
	
	
}
