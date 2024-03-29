package balls;

import java.awt.*;
import java.awt.geom.Point2D;

public class GrowBall extends Ball{
	
	public static double BALL_BOUNCE_DEC = 0.5;
	public static double BALL_X_DEC = 0.3;
	public boolean bounced;
	
	public GrowBall(int x, int y, int r){
		centerPointX = x;
		centerPointY = y;
		velocityX = 0;
		velocityY = 0;
		radius = r;
		inMotion = false;
		bounced = false;
	}

	public void draw(Graphics g){
		g.setColor(Color.blue);
		g.fillOval((int)(centerPointX-radius), 
				(int)(centerPointY-radius), 
				(int)(2*radius), 
				(int)(2*radius));
		g.setColor(Color.black);
		g.drawOval((int)(centerPointX-radius), 
				(int)(centerPointY-radius), 
				(int)(2*radius), 
				(int)(2*radius));
	}
	
	public void follow(int x, int y){
		centerPointX = x;
		centerPointY = y;
	}
	
	public void move(){
		
		//always scale back X range of movement
		centerPointX += velocityX;
		centerPointY += velocityY;
		
		System.out.println("move vX: "+velocityX+" vY: "+velocityY);
		
		
		//right boundary
		if(centerPointX + radius > WORLD_WIDTH){
			centerPointX = (int)(WORLD_WIDTH - radius);
			velocityX = -velocityX;
		}
		//left boundary
		if(centerPointX - radius < 0){
			centerPointX = (int)radius;
			velocityX = -velocityX;
		}
		
		//Hit the ground
		if(centerPointY + radius> WORLD_HEIGHT){
			centerPointY = (int)(WORLD_HEIGHT - radius);
			velocityY = -velocityY;
			bounced = true;
		}
		
		//rising
		if(bounced){
			velocityY *= BALL_BOUNCE_DEC;
			
		//falling	
		}else if(!bounced && (centerPointY + radius) < WORLD_HEIGHT) {
			velocityY+= 1;
		}
		
		//rising
		if( velocityY < 0 ){
			bounced  = false;
		}
		
	}
	
	public void setInMotion(){
		inMotion = true;
	}
	
	public void grow(){
		radius+=1;
	}
	
	public void collide(Ball other){
		
		//if other is a GrowBall call its collide
		
		int thisVX = collide(velocityX,other.velocityX,radius,other.radius);
		int thisVY = collide(velocityY,other.velocityY,radius,other.radius);
		
		if(other instanceof GrowBall){
			int otherVX = collide(other.velocityX,velocityX,other.radius,radius);
			int otherVY = collide(other.velocityY,velocityY,other.radius,radius);
			
			other.velocityX = otherVX;
			other.velocityY = otherVY;
			System.out.println("other vX: "+other.velocityX+" vY: "+other.velocityY);
		}
		
		velocityX = thisVX;
		velocityY = thisVY;
		System.out.println("this vX: "+velocityX+" vY: "+velocityY);
	}
		
		
	
	
	public int collide(int initVelocityA, int initVelocityB, 
						double radiusA, double radiusB){
		return (int)((radiusB*(initVelocityB-initVelocityA)+
				radiusA*initVelocityA+radiusB*initVelocityB)
				/(radiusA+radiusB));	
	}

}

