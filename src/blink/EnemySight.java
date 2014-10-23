package blink;
import java.time.temporal.TemporalQueries;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemySight {

	private Image image;
	private Image imageDetected;
	private Enemy enemy;

	private float x, y;

	private float attackDelay;

	private static final float ATTACK_COOLDOWN = 120.0f;
	private double range;
	private double detectDegree;
	private float degree;

	private boolean ninjaDetected;

	public EnemySight(Enemy enemy, String enemyType, double range, double degree)
			throws SlickException {
		this.enemy = enemy;
		this.image = new Image("res/" + enemyType + "-sight.png");
		this.imageDetected = new Image("res/" + enemyType
				+ "-sight-detected.png");
		this.range = range;
		this.detectDegree = degree;
		this.ninjaDetected = false;
		this.attackDelay = ATTACK_COOLDOWN;
	}

	public void update() {
		this.x = enemy.x + Enemy.CHR_WIDTH / 2;
		this.y = enemy.y + Enemy.CHR_HEIGHT / 2;
		this.degree = enemy.degree;
		if (attackDelay < ATTACK_COOLDOWN)
			this.attackDelay++;

		isSawNinja();
	}

	private void isSawNinja() {
		if (isNinjaWithinDegree() && isNinjaWithinDistance()) {
			if (attackDelay == ATTACK_COOLDOWN) {
				attackDelay = 0;
				BlinkTheGame.ninja.detected();
			}
			ninjaDetected = true;
		} else {
			ninjaDetected = false;
		}
	}

	private boolean isNinjaWithinDegree() {
		double tempSightDegree = degree;
		if (tempSightDegree < 0)
			tempSightDegree += 360;
		double diffX = BlinkTheGame.ninja.x - this.x;
		double diffY = BlinkTheGame.ninja.y - this.y;
		double angle = Math.toDegrees(Math.atan2(diffY, diffX));
		if (angle < 0)
			angle += 360;
		if (Math.abs(angle - tempSightDegree) <= detectDegree / 2)
			return true;
		else
			return false;
	}

	private boolean isNinjaWithinDistance() {
		double diffX = BlinkTheGame.ninja.x - this.x;
		double diffY = BlinkTheGame.ninja.y - this.y;
		double distance = Math.sqrt(diffX * diffX + diffY * diffY);
		if (distance <= range)
			return true;
		else
			return false;
	}

	public void render() {
		if (!ninjaDetected) {
			renderNormalSight();
		} else {
			renderDetectedSight();
		}
	}
	
	private void renderNormalSight () {
		image.setCenterOfRotation((int) range, (int) range);
		image.setRotation(degree);
		image.draw((int) (x - range), (int) (y - range), (int) range * 2, (int) range * 2);
	}
	private void renderDetectedSight () {
		imageDetected.setCenterOfRotation((int) range, (int) range);
		imageDetected.setRotation(degree);
		imageDetected.draw((int) (x - range), (int) (y - range), (int) range * 2, (int) range * 2);
	}
}