package blink;

import java.util.LinkedList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class BlinkTheGame extends BasicGame {

	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final float DIFFICULTY_INTERVAL = 0.00005f;
	public static final float DIFFICULTY_LIMIT = 2.5f;
	public static final float SPAWN_MARGIN = 150;

	public static float difficulty = 0.5f;
	public static int score = 0;

	public static Ninja ninja;
	public static LinkedList<StupidOne> stupids = new LinkedList<StupidOne>();

	public static int STUPID_COUNT = 5;
	public static int mouseX, mouseY;

	public static boolean left;
	public static boolean right;
	public static boolean up;
	public static boolean down;

	public static boolean isGameStart;
	public static boolean isGameEnd;

	public static void main(String[] args) {
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
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (isGameStart) {
			ninja.update(container);
			updateStupids();
			Input input = container.getInput();
			keyController(input);
			difficultyIncrease();
			checkGameEnds();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if (isGameStart) {
			renderEnemies();
			ninja.render();
			renderInformation(g);
		} else {
			if (isGameEnd) {
				renderGameOver(g);
			} else {
				renderLaunchPage(g);
			}
		}
		// cursor.render();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		initBackground(container);
		initCursor(container);
		initNinja();
		initEnemies();
		isGameStart = false;
		isGameEnd = false;
	}

	private void initBackground(GameContainer container) {
		Color background = new Color(0, 0, 0);
		container.getGraphics().setBackground(background);
	}

	private void initCursor(GameContainer container) throws SlickException {
		Image cursorImage = new Image("res/cursor-2.png");
		container.setMouseCursor(cursorImage, 25, 25);
	}

	private void initNinja() throws SlickException {
		ninja = new Ninja(GAME_WIDTH / 2, GAME_HEIGHT / 2);
	}

	private void initEnemies() throws SlickException {
		for (int i = 0; i < STUPID_COUNT; i++) {
			spawnEnemy();
		}
	}

	private void renderInformation(Graphics g) {
		g.drawString("Ninja HP: " + ninja.hp, 10, 30);
		g.drawString("Difficulty: " + difficulty, 10, 50);
		g.drawString("Score: " + score, 10, 70);
	}

	private void renderEnemies() {
		for (StupidOne stupid : stupids) {
			stupid.render();
		}
	}

	private void renderLaunchPage(Graphics g) {
		g.drawString(".........................", GAME_WIDTH / 2 - 110,
				GAME_HEIGHT / 2 - 18);
		g.drawString("Click mouse to start game", GAME_WIDTH / 2 - 110,
				GAME_HEIGHT / 2);
		g.drawString(".........................", GAME_WIDTH / 2 - 110,
				GAME_HEIGHT / 2 + 12);
	}

	private void renderGameOver(Graphics g) {
		g.drawString("GAME OVER", GAME_WIDTH / 2 - 37,
				GAME_HEIGHT / 2 - 30);
		g.drawString("Your score is", GAME_WIDTH / 2 - 52,
				GAME_HEIGHT / 2);
		g.drawString(""+score, GAME_WIDTH / 2 - 5,
				GAME_HEIGHT / 2 + 20);
	}

	public Point randomPosition(double randomSide) {
		int stupidWidth = Enemy.CHR_WIDTH;
		int stupidHeight = Enemy.CHR_HEIGHT;
		double randomX;
		double randomY;
		if (randomSide < 1) { // Left
			randomX = -stupidWidth - SPAWN_MARGIN;
			randomY = Math.random()
					* (GAME_HEIGHT - stupidHeight - Enemy.PADDING * 2)
					+ Enemy.PADDING;
		} else if (randomSide < 2) { // Right
			randomX = GAME_WIDTH + SPAWN_MARGIN;
			randomY = Math.random()
					* (GAME_HEIGHT - stupidHeight - Enemy.PADDING * 2)
					+ Enemy.PADDING;
		} else if (randomSide < 3) { // Up
			randomX = Math.random()
					* (GAME_WIDTH - stupidWidth - Enemy.PADDING * 2)
					+ Enemy.PADDING;
			randomY = -stupidHeight - SPAWN_MARGIN;
		} else { // Down
			randomX = Math.random()
					* (GAME_WIDTH - stupidWidth - Enemy.PADDING * 2)
					+ Enemy.PADDING;
			randomY = GAME_HEIGHT + SPAWN_MARGIN;
		}
		Point position = new Point((float) randomX, (float) randomY);
		return position;
	}

	private void checkGameEnds() {
		if (ninja.hp <= 0) {
			isGameStart = false;
			isGameEnd = true;
		}
	}

	private void updateStupids() throws SlickException {
		for (StupidOne stupid : stupids) {
			stupid.update();
		}
		checkForDeadStupid();
	}

	private void checkForDeadStupid() throws SlickException {
		int i = 0;
		for (StupidOne stupid : stupids) {
			if (stupid.isDeletable()) {
				System.out.println("Death at index: " + i);
				killEnemy(i);
				break;
			}
			i++;
		}

	}

	private void keyController(Input input) {
		left = input.isKeyDown(Input.KEY_A);
		right = input.isKeyDown(Input.KEY_D);
		up = input.isKeyDown(Input.KEY_W);
		down = input.isKeyDown(Input.KEY_S);
	}

	private void difficultyIncrease() {
		if (difficulty < DIFFICULTY_LIMIT) {
			difficulty += DIFFICULTY_INTERVAL;
		}
	}

	public void killEnemy(int index) throws SlickException {
		stupids.remove(index);
		score++;
		spawnEnemy();
	}

	private void spawnEnemy() throws SlickException {
		double randomSide = Math.random() * 4;
		Point randomPos = randomPosition(randomSide);
		StupidOne newEnemy = new StupidOne((float) randomPos.getX(),
				(float) randomPos.getY());
		stupids.add(newEnemy);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		mouseX = newx;
		mouseY = newy;
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (!isGameStart) {
			isGameStart = true;
		} else {
			if (button == 0) {
				ninja.attack();
			} else if (button == 1) {
				ninja.blink(x, y);
			}
		}
	}

}
