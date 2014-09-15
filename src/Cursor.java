import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Cursor {

	private Image image;
	
	public static float x;
	public static float y;
	
	private static final int CHR_WIDTH 	= 30;
	private static final int CHR_HEIGHT = 30;

	public Cursor(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    image = new Image("res/cursor-1.png");
		image.setCenterOfRotation(CHR_WIDTH/2, CHR_HEIGHT/2);
	  }
	
	public void update (GameContainer container, int mouseX, int mouseY) {
		x = mouseX;
		y = mouseY;
	}
	
	public void render () {
	    image.draw(x - (CHR_WIDTH/2), y - (CHR_HEIGHT/2), CHR_WIDTH, CHR_HEIGHT);
	}
}
