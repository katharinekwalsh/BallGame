package balls;

import java.awt.*;
import java.util.Random;

public class BouncyBall extends Ball{
	
	private static double BOUNCY_RADIUS = 5.0;
	
	private static Random rand = new Random();
	
	public BouncyBall(){
		centerPointX = (int)(WORLD_WIDTH*rand.nextDouble());
		centerPointY = (int)(WORLD_HEIGHT*rand.nextDouble());
		radius = BOUNCY_RADIUS;		
		velocityX = genRandVelocity();
		velocityY = genRandVelocity();
		inMotion = true;
		
		//System.out.println("ball velX: "+velocityX+" velY: "+velocityY);
	
	}
	
	private int genRandVelocity() {
		int r1 = rand.nextInt(6);
		int r2 = rand.nextInt(6);
		if(r1 == 1){
			return r2 + 2;
		}else{
			return -r2 - 2;
		}
	}

	public void draw(Graphics g){
		g.setColor(Color.red);
		g.fillOval((int)(centerPointX-radius), 
				(int)(centerPointY-radius), 
				(int)(2*radius), 
				(int)(2*radius));
	}
	
	public void move(){
		
		
		centerPointX += velocityX;
		centerPointY += velocityY;
		
		if(centerPointX-radius < 0){
			centerPointX = (int)radius;
			velocityX = -velocityX;
		}
		if(centerPointX +radius > WORLD_WIDTH){
			centerPointX = (int)(WORLD_WIDTH - radius);
			velocityX = -velocityX;
		}
		if(centerPointY - radius < 0){
			centerPointY = (int)(radius);
			velocityY = -velocityY;
		}
		//should Stop when hits the ground
		if(centerPointY + radius> WORLD_HEIGHT){
			centerPointY = (int)(WORLD_HEIGHT - radius);
			velocityY = -velocityY;
		}
	}
	
	public void collide(Ball other){
		
		if(other instanceof GrowBall){
			other.collide(this);
		}else{
			//line through each balls center;
			//this is the line around which the collision reflects
			double tanX = other.centerPointX-centerPointX;
			double tanY = other.centerPointY-centerPointY;
			double tanLen = Math.sqrt(Math.pow(tanX, 2.0)+Math.pow(tanY, 2.0));
			//original velocity vector length
			double velLen = Math.sqrt(Math.pow(other.velocityX, 2.0)+
					Math.pow(other.velocityY, 2.0));
				
			//using unit vectors
			tanX = tanX/tanLen;
			tanY = tanY/tanLen;
			double d_u_velX = (other.velocityX*1.0)/velLen;
			double d_u_velY = (other.velocityY*1.0)/velLen;
			
			//vector addition with unit vectors for new direction
			double newX = d_u_velX + 2.0*tanX;
			double newY = d_u_velY + 2.0*tanY;
			
			double scaleFactor = 
					velLen/(Math.sqrt(Math.pow(newX, 2.0)+Math.pow(newY, 2.0)));
			
			//Shouldn't be zero..
			scaleFactor = Math.ceil(scaleFactor);
			
			other.velocityX = (int)(newX*scaleFactor);
			other.velocityY = (int)(newY*scaleFactor);
		}
		
		//line through each balls center;
		//this is the line around which the collision reflects
		double tanX = centerPointX-other.centerPointX;
		double tanY = centerPointY-other.centerPointY;
		double tanLen = Math.sqrt(Math.pow(tanX, 2.0)+Math.pow(tanY, 2.0));
		//original velocity vector length
		double velLen = Math.sqrt(Math.pow(velocityX, 2.0)+
				Math.pow(velocityY, 2.0));
			
		//using unit vectors
		tanX = tanX/tanLen;
		tanY = tanY/tanLen;
		double d_u_velX = (velocityX*1.0)/velLen;
		double d_u_velY = (velocityY*1.0)/velLen;
		
		//vector addition with unit vectors for new direction
		double newX = d_u_velX + 2.0*tanX;
		double newY = d_u_velY + 2.0*tanY;
		
		double scaleFactor = 
				velLen/(Math.sqrt(Math.pow(newX, 2.0)+Math.pow(newY, 2.0)));
		
		//Shouldn't be zero..
		scaleFactor = Math.ceil(scaleFactor);
		
		velocityX = (int)(newX*scaleFactor);
		velocityY = (int)(newY*scaleFactor);
		
			
	}
	
	public void grow(){}
	
	public static int gcd(int a, int b)
	{
	   if (b==0) return a;
	   return gcd(b,a%b);
	}

}
