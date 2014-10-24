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
	
	protected void render (Image image) {
		image.setCenterOfRotation((int) range, (int) range);
		image.setRotation(degree);
		image.draw((int) (x - range), (int) (y - range), (int) range * 2,
				(int) range * 2);
	}
	
	protected void update (float degree, float x, float y) {
		
	}
}
