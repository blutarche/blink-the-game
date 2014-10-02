import org.newdawn.slick.SlickException;


public class StupidOne extends Enemy {

	private final static float RUN_SPEED = 1f;
	private final static double RANGE_OF_SIGHT = 120;
	private final static double DEGREE_OF_SIGHT = 90; 
	
	public StupidOne(float x, float y) throws SlickException {
		super(x, y, RUN_SPEED, RANGE_OF_SIGHT, DEGREE_OF_SIGHT, "Stupid-One");
	}

	public void update () {
		super.update();
	}
	
	
}
