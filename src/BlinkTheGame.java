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
	public static final int STUPID_COUNT = 8;
	
	private Ninja ninja;
	private StupidOne[] stupids = new StupidOne[STUPID_COUNT];
	
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
		for (int i=0;i<STUPID_COUNT;i++) {
			stupids[i].render();
		}
		//cursor.render();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
	    Color background = new Color(0, 0, 0);
	    container.getGraphics().setBackground(background);
	    ninja = new Ninja(GAME_WIDTH/2,GAME_HEIGHT/2);
		Image cursorImage = new Image("res/cursor-2.png");
		container.setMouseCursor(cursorImage, 25, 25);
		
		int stupidWidth = Enemy.CHR_WIDTH;
		int stupidHeight = Enemy.CHR_HEIGHT;
		
		for (int i=0;i<STUPID_COUNT;i++) {
			double randomX = Math.random() * (GAME_WIDTH-stupidWidth)  + 1;
			double randomY = Math.random() * (GAME_HEIGHT-stupidHeight) + 1;
			stupids[i] = new StupidOne((float)randomX, (float)randomY);
		}
		
	}

	@Override
	public void update(GameContainer container, int val) throws SlickException {
		ninja.update(container);
		for (int i=0;i<STUPID_COUNT;i++) {
			stupids[i].update();
		}
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		this.mouseX = newx;
		this.mouseY = newy;
	}

}
