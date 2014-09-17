import org.newdawn.slick.SlickException;


public class StupidOne extends Enemy {

	private static final double DEG_LIMIT = 3.0;
	
	public StupidOne(float x, float y) throws SlickException {
		super(x, y, 2.5f, "res/stupid-dot.png");
		// TODO Auto-generated constructor stub
	}

	public void update () {
		double degreeChange = Math.random() * DEG_LIMIT * 2 - DEG_LIMIT;
		this.degree += degreeChange;
	}
	
	
}
