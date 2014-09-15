import net.java.games.input.Mouse;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class BlinkTheGame extends BasicGame {

	public static final int GAME_WIDTH 	= 1200;
	public static final int GAME_HEIGHT = 700;
	
	private Ninja ninja;
	
	public static int mouseX, mouseY;
	
	public static void main (String [] args) {
	    try {
		      BlinkTheGame game = new BlinkTheGame("Blink!");
		      AppGameContainer appgc = new AppGameContainer(game);
		      appgc.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
		      appgc.setMinimumLogicUpdateInterval(5);
		      appgc.setMaximumLogicUpdateInterval(5);
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
		ninja.render();
		//cursor.render();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
	    Color background = new Color(0, 0, 0);
	    container.getGraphics().setBackground(background);        
	    ninja = new Ninja(GAME_WIDTH/2,GAME_HEIGHT/2);
		Image cursorImage = new Image("res/cursor-2.png"); 
		container.setMouseCursor(cursorImage, 25, 25);
	}

	@Override
	public void update(GameContainer container, int val) throws SlickException {
		ninja.update(container);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		this.mouseX = newx;
		this.mouseY = newy;
	}

}
