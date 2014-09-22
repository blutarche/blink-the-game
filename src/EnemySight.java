import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EnemySight {

	private Image image;
	private Enemy enemy;
	
	public EnemySight(Enemy enemy, String enemyType) throws SlickException {
		this.enemy = enemy;
		this.image = new Image ("res/"+enemyType+"-sight.png");	
	}
}
