import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Enemy {

	private Image image;
	
	public static float x;
	public static float y;
	public static float v;
	public static float degree;
	private static final int CHR_WIDTH 	= 40;
	private static final int CHR_HEIGHT = 40;
	
	public Enemy(float x, float y, float v, String imageLocation) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.v = v;
	    
	    image = new Image(imageLocation);
	}
	
	public void render() {
	    image.draw(x, y, CHR_WIDTH, CHR_HEIGHT);
	}
}
