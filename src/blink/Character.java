package blink;

import org.newdawn.slick.Image;

public class Character {

	public float x;
	public float y;
	public float v;
	public float degree;
	
	protected Image image;
	protected static final int CHR_WIDTH = 30;
	protected static final int CHR_HEIGHT = 30;
	
	public double rangeOfSight;
	public double degreeOfSight;
	
	protected void render () {
		image.setCenterOfRotation(CHR_WIDTH / 2, CHR_HEIGHT / 2);
		image.draw(x, y, CHR_WIDTH, CHR_HEIGHT);
		adjustHeadDirection();
	}
	
	private void adjustHeadDirection() {
		image.setRotation(degree);
	}
	
	protected void setXY(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
