package blink;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class NinjaSight extends Sight {
	private Image image;
	private Ninja ninja;

	private float x, y;

	private double range;
	private double detectDegree;
	private float degree;

	public NinjaSight(Ninja ninja, double range, double degree)
			throws SlickException {
		this.ninja = ninja;
		this.image = new Image("res/Stupid-One-sight.png");
		this.range = range;
		this.detectDegree = degree;
	}

	public void update() {
		this.x = ninja.x + Enemy.CHR_WIDTH / 2;
		this.y = ninja.y + Enemy.CHR_HEIGHT / 2;
		this.degree = ninja.degree;
	}

	public void render() {
		renderNormalSight();
	}

	private void renderNormalSight() {
		image.setCenterOfRotation((int) range, (int) range);
		image.setRotation(degree);
		image.draw((int) (x - range), (int) (y - range), (int) range * 2,
				(int) range * 2);
	}
}
