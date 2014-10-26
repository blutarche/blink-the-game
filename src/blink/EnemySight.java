package blink;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemySight extends Sight {

	private Image image;
	private Image imageDetected;
	private Enemy enemy;

	private float attackDelay;
	private static final float ATTACK_COOLDOWN = 150.0f;

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
		super.update(enemy.degree, enemy.x + Enemy.CHR_WIDTH / 2, enemy.y
				+ Enemy.CHR_HEIGHT / 2);
		if (attackDelay < ATTACK_COOLDOWN) {
			this.attackDelay++;
		}
		searchNinja();
	}

	private void searchNinja() {
		if (isObjectWithinSight(BlinkTheGame.ninja)) {
			attack();
			ninjaDetected = true;
		} else {
			ninjaDetected = false;
		}
	}

	private void attack() {
		if (attackDelay == ATTACK_COOLDOWN) {
			attackDelay = 0;
			BlinkTheGame.ninja.detected();
		}
	}

	public void render() {
		if (!ninjaDetected) {
			super.render(image);
		} else {
			super.render(imageDetected);
		}
	}
}