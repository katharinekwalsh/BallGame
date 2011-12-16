package balls;

import java.awt.event.*;
import javax.swing.event.MouseInputListener;
import static java.lang.Thread.*;


/**
 * BallCreator class listens to the activity
 * in a World and creates balls based on the 
 * activity.
 * @author Katharine Walsh
 *
 */
public class BallCreator implements MouseInputListener{
	
	private World world;
	private GrowBall lastCreated;
	
	public BallCreator(World w){
		world = w;
	}

	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		lastCreated.follow(arg0.getX(),arg0.getY());
	}



	@Override
	public void mouseMoved(MouseEvent arg0) {
		//
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
	
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		lastCreated = world.addGrowBall(e.getX(), e.getY());
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		lastCreated.setInMotion();
	}
	
	
}
