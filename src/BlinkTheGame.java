import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class BlinkTheGame extends BasicGame {

	public static final int GAME_WIDTH 	= 1366;
	public static final int GAME_HEIGHT = 768;
	
	private Ninja ninja;
	
	public static void main (String [] args) {
	    try {
		      BlinkTheGame game = new BlinkTheGame("Blink!");
		      AppGameContainer appgc = new AppGameContainer(game);
		      appgc.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, true);
		      appgc.setMinimumLogicUpdateInterval(10);
		      appgc.setMaximumLogicUpdateInterval(10);
		      appgc.start();
		    } catch (SlickException e) {
		      e.printStackTrace();
		    }
	}
	
	public BlinkTheGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		ninja.render();
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
	    Color background = new Color(41, 51, 61);
	    container.getGraphics().setBackground(background);        
	    ninja = new Ninja(GAME_WIDTH/2,GAME_HEIGHT/2);
		
	}

	@Override
	public void update(GameContainer container, int val) throws SlickException {
		// TODO Auto-generated method stub
		ninja.update(container);
	}

}
