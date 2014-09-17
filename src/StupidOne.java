import org.newdawn.slick.SlickException;


public class StupidOne extends Enemy {

	private static final double DEG_LIMIT = 10.0;
	
	public StupidOne(float x, float y) throws SlickException {
		super(x, y, 2.0f, "res/stupid-dot.png");
		// TODO Auto-generated constructor stub
	}

	public void update () {
		double degreeChange = Math.random() * DEG_LIMIT * 2 - DEG_LIMIT;
		this.degree += degreeChange;
		double radians = Math.toRadians(this.degree);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		this.x += this.v*cos;
		this.y += this.v*sin;
	}
	
	
}
