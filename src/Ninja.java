
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;



public class Ninja {
	
	private Image image;
	
	public static float x;
	public static float y;
	public static float v;
	private static final int CHR_WIDTH 	= 40;
	private static final int CHR_HEIGHT = 40;
	
	  private boolean left;	private boolean right;
	  private boolean up;	private boolean down;
	
	public Ninja(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.v = 3;
	    image = new Image("res/ninja-dot.png");
		image.setCenterOfRotation(CHR_WIDTH/2, CHR_HEIGHT/2);
	  }

	  public void render() {
	    image.draw(x - (CHR_WIDTH/2), y - (CHR_HEIGHT/2), CHR_WIDTH, CHR_HEIGHT);
	    adjustHeadDirection();
	  }

		private void adjustHeadDirection () {
			
		}
	  public void update(GameContainer container) {
	    Input input = container.getInput();
		left=false; right=false; up=false; down=false;
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
			if (x<CHR_WIDTH) x=CHR_WIDTH;
			if (x>limitX) x=limitX;
			if (y<CHR_HEIGHT) y=CHR_HEIGHT;
			if (y>limitY) y=limitY;
		}
}
