import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Enemy {

	private Image image;
	
	public float x; public float y;
	public float v; public float degree;
	
	public static final int CHR_WIDTH  = 20;
	public static final int CHR_HEIGHT = 20;
	public static final double PADDING = 60;
	
	public Enemy(float x, float y, float v, String imageLocation) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.v = v;
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
}
