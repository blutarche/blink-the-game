
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;



public class Ninja {
	private Image image;
	
	public static float x;
	public static float y;
	private static final int CHR_WIDTH 	= 60;
	private static final int CHR_HEIGHT = 60;
	
	  private boolean left;	private boolean right;
	  private boolean up;	private boolean down;
	
	public Ninja(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    image = new Image("res/ninja-dot.png");
	  }

	  public void render() {
	    image.draw(x - (CHR_WIDTH/2), y - (CHR_HEIGHT/2), CHR_WIDTH, CHR_HEIGHT);
	    adjustHeadDirection();
	  }

		private void adjustHeadDirection () {
			if (left) {
				if (up) image.setRotation(315);
				else if (down) image.setRotation(225);
				else image.setRotation(270);
			}
			else if (right) {
				if (up) image.setRotation(45);
				else if (down) image.setRotation(135);
				else image.setRotation(90);
			}
			else if (up) image.setRotation(0);
			else if (down) image.setRotation(180);
			
			
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
	  }
		private void changePosition () {
			if (left) {
				if (up) {
					x-=1;
					y-=1;
				}
				else if (down) {
					x-=1;
					y+=1;
				}
				else x-=Math.sqrt(2);
			}
			else if (right) {
				if (up) {
					x+=1;
					y-=1;
				}
				else if (down) {
					x+=1;
					y+=1;
				}
				else x+=Math.sqrt(2);
			}
			else if (up) y-=Math.sqrt(2);
			else if (down) y+=Math.sqrt(2);
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
