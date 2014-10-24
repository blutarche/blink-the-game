package blink;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sight {

	protected Character owner;

	protected float x, y;
	private float realDetectX, realDetectY;

	protected double range;
	protected double detectDegree;
	protected float degree;

	private static final float DEGREE_DEBUG = 15.0f;

	public Sight(Character owner, double range, double degree)
			throws SlickException {
		this.owner = owner;
		this.range = range;
		this.detectDegree = degree;
	}

	protected void render(Image image) {
		image.setCenterOfRotation((int) range, (int) range);
		image.setRotation(degree);
		image.draw((int) (x - range), (int) (y - range), (int) range * 2,
				(int) range * 2);
	}

	protected void update(float degree, float x, float y) {
		this.degree = degree;
		this.x = x;
		this.y = y;
		setRealDetectPosition();
	}

	private void setRealDetectPosition() {
		double radians = Math.toRadians(this.degree);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		this.realDetectX = (float) (this.x - (Character.CHR_WIDTH / 3) * cos);
		this.realDetectY = (float) (this.y - (Character.CHR_WIDTH / 3) * sin);
	}

	protected boolean isObjectWithinSight(Character character) {
		if ((isObjectWithinDegree(character) && isObjectWithinDistance(character))
				|| isObjectVeryNear(character)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isObjectWithinDegree(Character character) {
		double tempSightDegree = degree;
		if (tempSightDegree < 0) {
			tempSightDegree += 360;
		}
		double diffX = character.x - this.realDetectX;
		double diffY = character.y - this.realDetectY;
		double angle = Math.toDegrees(Math.atan2(diffY, diffX));
		if (angle < 0) {
			angle += 360;
		}
		if (Math.abs(angle - tempSightDegree) <= (detectDegree / 2)
				+ DEGREE_DEBUG) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isObjectWithinDistance(Character character) {
		double diffX = character.x - owner.x;
		double diffY = character.y - owner.y;
		double distance = Math.sqrt(diffX * diffX + diffY * diffY)
				- (Character.CHR_HEIGHT / 2);

		if (distance <= range) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isObjectVeryNear(Character character) {
		double diffX = character.x - owner.x;
		double diffY = character.y - owner.y;
		double distance = Math.sqrt(diffX * diffX + diffY * diffY)
				- (Character.CHR_HEIGHT / 2);

		if (distance <= Character.CHR_WIDTH / 2) {
			return true;
		} else {
			return false;
		}

	}
}
