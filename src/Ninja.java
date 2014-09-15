
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;



public class Ninja {
	
	private Image image;
	
	public static float x;
	public static float y;
	public static float v;
	private static final int CHR_WIDTH 	= 30;
	private static final int CHR_HEIGHT = 30;
	
	private boolean left;	private boolean right;
	private boolean up;		private boolean down;
	 
	public Ninja(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.v = 3;
	    image = new Image("res/ninja-dot.png");
	}

	public void render() {
	    image.draw(x, y, CHR_WIDTH, CHR_HEIGHT);
	    adjustHeadDirection();
	}

	private void adjustHeadDirection () {
		float mouseX = (float)BlinkTheGame.mouseX;
		float mouseY = (float)BlinkTheGame.mouseY;
		float xDistance = mouseX - x;
		float yDistance = mouseY - y;
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		image.setRotation((float)angleToTurn);
	}
	public void update(GameContainer container) {
	    Input input = container.getInput();
		left=false; right=false; up=false; down=false;
		image.setCenterOfRotation(CHR_WIDTH/2, CHR_HEIGHT/2);
	    if (input.isKeyDown(Input.KEY_A)) { 
	        left = true;
	    }
	    if (input.isKeyDown(Input.KEY_D)) {
	        right = true; 
	    }
	    if (input.isKeyDown(Input.KEY_W)) { 
	        up = true;
	    }
	    if (input.isKeyDown(Input.KEY_S)) {
	        down = true;      
	    }
	    changePosition();
	    adjustPosition();
	}
	private void changePosition () {
		if (left) {
			if (up) {
				x-=v;
				y-=v;
			}
			else if (down) {
				x-=v;
				y+=v;
			}
			else x-=v*Math.sqrt(2);
		}
		else if (right) {
			if (up) {
				x+=v;
				y-=v;
			}
			else if (down) {
				x+=v;
				y+=v;
			}
			else x+=v*Math.sqrt(2);
		}
		else if (up) y-=v*Math.sqrt(2);
		else if (down) y+=v*Math.sqrt(2);
	}
	private void adjustPosition () {
		int limitX = BlinkTheGame.GAME_WIDTH - CHR_WIDTH;
		int limitY = BlinkTheGame.GAME_HEIGHT - CHR_HEIGHT;
		if (x<0) x=0;
		if (x>limitX) x=limitX;
		if (y<0) y=0;
		if (y>limitY) y=limitY;
	}
}
