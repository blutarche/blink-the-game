package blink;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemySight extends Sight {

	private Image image;
	private Image imageDetected;
	private Enemy enemy;

	private float attackDelay;
	private static final float ATTACK_COOLDOWN = 120.0f;

	private boolean ninjaDetected;

	public EnemySight(Enemy enemy, String enemyType, double range, double degree)
			throws SlickException {
		super(enemy, range, degree);
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
		update(enemy.degree,  enemy.x + Enemy.CHR_WIDTH / 2,  enemy.y + Enemy.CHR_HEIGHT / 2);
		if (attackDelay < ATTACK_COOLDOWN) {
			this.attackDelay++;
		}
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
			render(image);
		} else {
			render(imageDetected);
		}
	}
}