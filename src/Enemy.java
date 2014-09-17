import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Enemy {

	private Image image;
	
	public float x;
	public float y;
	public float v;
	public float degree;
	
	private double DEG_LIMIT;
	private static final int TURN_INTERVAL = 50;
	public static final int CHR_WIDTH  = 30;
	public static final int CHR_HEIGHT = 30;
	
	public Enemy(float x, float y, float v, float degLimit, String imageLocation) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.v = v;
	    this.degree = 0;
	    this.DEG_LIMIT = degLimit;
		System.out.println("x:"+x+" y:"+y);
	    image = new Image(imageLocation);
	}
	
	public void render() {
	    image.setCenterOfRotation(CHR_WIDTH/2, CHR_HEIGHT/2);
	    image.draw(x, y, CHR_WIDTH, CHR_HEIGHT);
	    adjustHeadDirection();
	}
	
	private void adjustHeadDirection () {
		image.setRotation(degree);
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
