import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EnemySight {

	private Image image;
	private Enemy enemy;
	
	private float x, y;
	
	private double range;
	private double detectDegree;
	private float degree;
	
	public EnemySight(Enemy enemy, String enemyType, double range, double degree) throws SlickException {
		this.enemy = enemy;
		this.image = new Image("res/"+enemyType+"-sight.png");
		this.range = range;
		this.detectDegree = degree;
	}
	
	public void update () {
		this.x = enemy.x+Enemy.CHR_WIDTH/2;
		this.y = enemy.y+Enemy.CHR_HEIGHT/2;
		this.degree = enemy.degree;
	}
	
	public void render () {
	    image.setCenterOfRotation((int)range, (int)range);
		image.draw((int)(x-range), (int)(y-range), (int)range*2, (int)range*2);
		image.setRotation(degree);
	}
}
