import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Ninja {
	private Image image;
	
	public static float x;
	public static float y;
	private static final int CHR_WIDTH 	= 80;
	private static final int CHR_HEIGHT = 80;
	
	public Ninja(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    image = new Image("res/ninja-dot.png");
	  }

	  public void render() {
	    image.draw(x - (CHR_WIDTH/2), y - (CHR_HEIGHT/2), CHR_WIDTH, CHR_HEIGHT);
	  }
}
