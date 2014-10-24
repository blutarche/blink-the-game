package blink;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Ninja {

	private Image image;
	private NinjaSight sight;

	public float x;
	public float y;
	public float v;
	public float degree;
	private static final int CHR_WIDTH = 30;
	private static final int CHR_HEIGHT = 30;
	private final static double RANGE_OF_SIGHT = 50;
	private final static double DEGREE_OF_SIGHT = 90;

	public int hp;
	public boolean isBeingSeen;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	public Ninja(float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		this.v = 2;
		this.hp = 10;
		this.isBeingSeen = false;
		sight = new NinjaSight(this, RANGE_OF_SIGHT, DEGREE_OF_SIGHT);
		image = new Image("res/ninja-dot.png");
	}

	public void render() {
		sight.render();
		image.draw(x, y, CHR_WIDTH, CHR_HEIGHT);
		adjustHeadDirection();
	}

	private void adjustHeadDirection() {
		float mouseX = (float) BlinkTheGame.mouseX;
		float mouseY = (float) BlinkTheGame.mouseY;
		float xDistance = mouseX - x;
		float yDistance = mouseY - y;
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		this.degree = (float)angleToTurn;
		image.setRotation(degree);
	}

	public void update(GameContainer container) {
		keyController(container);
		changePosition();
		adjustPosition();
		sight.update();
	}
	
	private void keyController (GameContainer container) {
		Input input = container.getInput();
		left = false;
		right = false;
		up = false;
		down = false;
		image.setCenterOfRotation(CHR_WIDTH / 2, CHR_HEIGHT / 2);
		
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

	private void changePosition() {
		if (left) {
			if (up) {
				x -= v;
				y -= v;
			} else if (down) {
				x -= v;
				y += v;
			} else
				x -= v * Math.sqrt(2);
		} else if (right) {
			if (up) {
				x += v;
				y -= v;
			} else if (down) {
				x += v;
				y += v;
			} else
				x += v * Math.sqrt(2);
		} else if (up)
			y -= v * Math.sqrt(2);
		else if (down)
			y += v * Math.sqrt(2);
	}

	private void adjustPosition() {
		int limitX = BlinkTheGame.GAME_WIDTH - CHR_WIDTH;
		int limitY = BlinkTheGame.GAME_HEIGHT - CHR_HEIGHT;
		if (x < 0)
			x = 0;
		if (x > limitX)
			x = limitX;
		if (y < 0)
			y = 0;
		if (y > limitY)
			y = limitY;
	}

	public void attack() {

	}

	public void decreaseHP(int damage) {
		this.hp -= damage;
	}

	public void blink(int mouseX, int mouseY) {
		this.x = mouseX - CHR_WIDTH / 2;
		this.y = mouseY - CHR_HEIGHT / 2;
		removeDetectStatus();
	}

	public void removeDetectStatus() {
		this.isBeingSeen = false;
	}

	public void applyDetectStatus() {
		this.isBeingSeen = true;
	}

	public void detected() {
		decreaseHP(1);
		applyDetectStatus();
	}
}
