package blink;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class NinjaSight extends Sight {
	private Image image;
	private Ninja ninja;


	public NinjaSight(Ninja ninja, double range, double degree)
			throws SlickException {
		super(ninja, range, degree);
		this.ninja = ninja;
		this.image = new Image("res/Stupid-One-sight.png");
		this.range = range;
		this.detectDegree = degree;
	}

	public void update() {
		update(ninja.degree,  ninja.x + Enemy.CHR_WIDTH / 2,  ninja.y + Enemy.CHR_HEIGHT / 2);
	}

	public void render() {
		render(image);
	}
}
