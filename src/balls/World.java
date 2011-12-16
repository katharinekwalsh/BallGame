package balls;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import static java.lang.Thread.*;

/**
 * World class is the world in which the balls 
 * live in as well as the main game loop.
 * @author Katharine Walsh
 *
 */
public class World extends JPanel{

	private static ArrayList<Ball> ballArray;
	static int INIT_WORLD_SIZE_X = 425;
	static int INIT_WORLD_SIZE_Y = 450;
	static int BALL_WORLD_BOUND_X = 400;
	static int BALL_WORLD_BOUND_Y = 350;
	static int INIT_GROWBALL_SIZE = 2;
	static int areaUsed;
	
	public World(){
		super();
		ballArray = new ArrayList<Ball>();
	}
	
	/**
	 * paintComponent paints the balls in the world's ballArray. 
	 * Called automatically.
	 * @param Graphics g
	 */
	public void paintComponent(Graphics g){
	    for(Ball b : ballArray)
	    	b.draw(g);
	    		
	    g.setColor(Color.black);
	    g.drawString(" Level 1 You can't die yet.", 0, 370);
	    g.drawString(" "+areaUsed+"%", 0, 390);
	    this.getBorder().paintBorder(this, g,
	    		0,0,BALL_WORLD_BOUND_X,BALL_WORLD_BOUND_Y);
	}
	
	/**
	 * addGrowBall adds a ball to the world. Used only by BallCreator 
	 * that creates new balls on mouse clicks.
	 * @param x - x coordinate for the ball.
	 * @param y - y coordinate for the ball.
	 */
	public GrowBall addGrowBall(int x, int y){
		GrowBall gb = new GrowBall(x, y, INIT_GROWBALL_SIZE);
		ballArray.add(gb);
		return gb;
	}
	
	public int worldArea(){
		return BALL_WORLD_BOUND_X*BALL_WORLD_BOUND_Y;
	}
	
	public static void main(String args[]){
		
		JFrame universe = new JFrame("Fill it with Balls..");
		universe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		universe.setSize(new Dimension(INIT_WORLD_SIZE_X,INIT_WORLD_SIZE_Y));
		
		World world = new World();
		world.setBounds(0, 0, INIT_WORLD_SIZE_X,INIT_WORLD_SIZE_Y);
        world.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
		universe.setContentPane(world);
		universe.setVisible(true);
		universe.setBackground(Color.white);
		
		BallCreator listener = new BallCreator(world);
		world.addMouseListener(listener);
		world.addMouseMotionListener(listener);
		
		//add first balls
		ballArray.add(new BouncyBall(BALL_WORLD_BOUND_X,BALL_WORLD_BOUND_Y));

		int ballArea;
		while(true){
		
			try {
				//Replace with Timer
				sleep(1000/40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ballArea = 0;
			
			//move balls..
			for(Ball ball : ballArray){
				//Make the balls do something...
				if(ball.inMotion){
					ball.move(BALL_WORLD_BOUND_X,BALL_WORLD_BOUND_Y);
				}else{
					ball.grow();
				}
				//calc total area covered by balls
				ballArea += ball.area();
			}
			
			areaUsed = (ballArea*100)/world.worldArea();
			
			//check and handle collisions..
			for(int i = 0; i<ballArray.size(); i++){
				for(int j = i+1; j<ballArray.size(); j++){
					if(ballArray.get(i).collision(ballArray.get(j))){
						ballArray.get(i).collide(ballArray.get(j));
						//ballArray.get(j).collide(ballArray.get(i));
					}
				}
			}
			universe.repaint();
		}
		
	}
	
}