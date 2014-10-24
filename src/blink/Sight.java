package blink;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sight {

	protected Object owner;

	protected float x, y;

	protected double range;
	protected double detectDegree;
	protected float degree;

	public Sight(Object owner, double range, double degree)
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
	}

	protected boolean isObjectWithinSight(Character character) {
		if (isObjectWithinDegree(character)
				&& isObjectWithinDistance(character)) {
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
		double diffX = character.x - this.x;
		double diffY = character.y - this.y;
		double angle = Math.toDegrees(Math.atan2(diffY, diffX));
		if (angle < 0) {
			angle += 360;
		}
		if (Math.abs(angle - tempSightDegree) <= detectDegree / 2) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isObjectWithinDistance(Character character) {
		double diffX = character.x - this.x;
		double diffY = character.y - this.y;
		double distance = Math.sqrt(diffX * diffX + diffY * diffY);
		if (distance <= range)
			return true;
		else
			return false;
	}
}
