import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Enemy {

	private Image image;
	
	public float x;
	public float y;
	public float v;
	public float degree;
	private static final int CHR_WIDTH  = 30;
	private static final int CHR_HEIGHT = 30;
	
	public Enemy(float x, float y, float v, String imageLocation) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.v = v;
	    this.degree = 0;

		System.out.println("x:"+x+" y:"+y);
	    image = new Image(imageLocation);
	}
	
	public void render() {
	    image.draw(x, y, CHR_WIDTH, CHR_HEIGHT);
	    adjustHeadDirection();
	}
	
	private void adjustHeadDirection () {
		image.setRotation(degree);;
	}
}
