package blink;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ninja extends Character {

	private NinjaSight sight;

	private final static double RANGE_OF_SIGHT = 50;
	private final static double DEGREE_OF_SIGHT = 90;

	public int hp;
	public boolean isBeingSeen;


	public Ninja(float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		this.v = 2;
		this.hp = 10;
		this.isBeingSeen = false;
		this.rangeOfSight = RANGE_OF_SIGHT;
		this.degreeOfSight = DEGREE_OF_SIGHT;
		sight = new NinjaSight(this, rangeOfSight, degreeOfSight);
		image = new Image("res/ninja-dot.png");
	}

	public void update(GameContainer container) {
		keyController(container);
		changePosition();
		adjustPosition();
		sight.update();
		setDegreeToCursor();
	}

	public void render() {
		sight.render();
		super.render();
	}

	private void setDegreeToCursor() {
		float mouseX = (float) BlinkTheGame.mouseX;
		float mouseY = (float) BlinkTheGame.mouseY;
		float xDistance = mouseX - x;
		float yDistance = mouseY - y;
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		this.degree = (float) angleToTurn;
	}

	private void keyController(GameContainer container) {
		image.setCenterOfRotation(CHR_WIDTH / 2, CHR_HEIGHT / 2);

	}

	private void changePosition() {
		float vSqrt = (float) (v * Math.sqrt(2));
		if (BlinkTheGame.left) {
			if (BlinkTheGame.up) {
				setXY(x - v, y - v);
			} else if (BlinkTheGame.down) {
				setXY(x - v, y + v);
			} else {
				setXY(x - vSqrt, y);
			}
		} else if (BlinkTheGame.right) {
			if (BlinkTheGame.up) {
				setXY(x + v, y - v);
			} else if (BlinkTheGame.down) {
				setXY(x + v, y + v);
			} else {
				setXY(x + vSqrt, y);
			}
		} else if (BlinkTheGame.up) {
			setXY(x, y - vSqrt);
		} else if (BlinkTheGame.down) {
			setXY(x, y + vSqrt);
		}
	}

	private void adjustPosition() {
		int limitX = BlinkTheGame.GAME_WIDTH - CHR_WIDTH;
		int limitY = BlinkTheGame.GAME_HEIGHT - CHR_HEIGHT;
		if (x < 0) {
			x = 0;
		}
		if (x > limitX) {
			x = limitX;
		}
		if (y < 0) {
			y = 0;
		}
		if (y > limitY) {
			y = limitY;
		}
	}

	public void attack() {
		sight.attack();
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
