package blink;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class NinjaSight extends Sight {
	private Image image;
	private Image imageDetected;
	private Ninja ninja;

	public NinjaSight(Ninja ninja, double range, double degree)
			throws SlickException {
		super(ninja, range, degree);
		this.ninja = ninja;
		this.image = new Image("res/Stupid-One-sight.png");
		this.imageDetected = new Image("res/Stupid-One-sight-detected.png");
		this.range = range;
		this.detectDegree = degree;
	}

	public void update() {
		super.update(ninja.degree, ninja.x + Enemy.CHR_WIDTH / 2, ninja.y
				+ Enemy.CHR_HEIGHT / 2);
	}

	public void render() {
		if (searchEnemy() == -1) {
			super.render(image);
		} else {
			super.render(imageDetected);
		}
	}

	public void attack() {
		int indexOfEnemyFound = searchEnemy();
		if (indexOfEnemyFound != -1) {
			BlinkTheGame.stupids.get(indexOfEnemyFound).killed();
		}
	}

	private int searchEnemy() {
		int i = 0;
		for (StupidOne stupid : BlinkTheGame.stupids) {
			if (isObjectWithinSight(stupid)) {
				return i;
			}
			i++;
		}
		return -1;
	}

}
