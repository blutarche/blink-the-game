import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {

	private Image image;
	private EnemySight sight;

	public float x;
	public float y;
	public float v;
	public float degree;
	public double rangeOfSight;
	public double degreeOfSight;

	public static final int CHR_WIDTH = 30;
	public static final int CHR_HEIGHT = 30;
	public static final double PADDING = 30;

	private double targetDegree;
	private double distanceGoing;

	private double RUN_LIMIT = 100.0;
	private final double RUN_INTO_LIMIT = 150.0;
	private final double RUN_RANDOM_RANGE = 30.0;
	private final double DEG_LIMIT = 50.0;
	private final double TURN_SPEED = 0.5;

	private final int MODE_OFFSCREEN = 0;
	private final int MODE_RUNNING = 1;
	private final int MODE_TURNSTART = 2;
	private final int MODE_TURNING = 3;
	private final int MODE_CHASING = 4;

	private int movementMode;

	public Enemy(float x, float y, float v, double sightRange,
			double sightDegree, String enemyType) throws SlickException {
		this.x = x;
		this.y = y;
		this.v = v;

		image = new Image("res/" + enemyType + ".png");
		movementMode = MODE_OFFSCREEN;
		distanceGoing = 0;
		degree = 0;

		rangeOfSight = sightRange;
		degreeOfSight = sightDegree;
		sight = new EnemySight(this, enemyType, sightRange, sightDegree);
	}

	public void render() {
		sight.render();

		image.setCenterOfRotation(CHR_WIDTH / 2, CHR_HEIGHT / 2);
		image.draw(x, y, CHR_WIDTH, CHR_HEIGHT);
		adjustHeadDirection();
	}

	public void update() {
		this.v = BlinkTheGame.difficulty;
		this.move();
		sight.update();
	}

	private void move() {
		if (BlinkTheGame.ninja.isBeingSeen) {
			movementMode = 4;
			distanceGoing = RUN_LIMIT;
			chasingMovement();
		} else {
			normalMovement();
		}
	}

	private void chasingMovement() {
		if (movementMode == MODE_CHASING) {
			adjustDegreeToNinja();
			runWithOutChecking();
		}
	}

	private void normalMovement() {
		checkRunEnd();
		if (movementMode == MODE_OFFSCREEN) {
			offscreenMovement();
		} else if (movementMode == MODE_RUNNING) {
			runNormal();
		} else if (movementMode == MODE_TURNSTART) {
			turnStart();
		} else if (movementMode == MODE_TURNING) {
			turning();
		}
	}

	private void runNormal() {
		run();
		if (isOffScreen()) {
			movementMode = MODE_OFFSCREEN;
		}
	}

	private void offscreenMovement() {
		checkStartingPosition();
		if (!checkTurnEnd()) {
			turning();
		} else if (!isOffScreen()) {
			run();
		} else {
			movementMode = MODE_RUNNING;
		}
	}

	private void adjustDegreeToNinja() {
		float ninjaX = (float) BlinkTheGame.ninja.x;
		float ninjaY = (float) BlinkTheGame.ninja.y;
		float xDistance = ninjaX - this.x;
		float yDistance = ninjaY - this.y;
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		int tempDegree = (int) (angleToTurn / TURN_SPEED);
		double desiredTurnDegree = tempDegree * TURN_SPEED;
		this.degree = (float) desiredTurnDegree;
	}

	private void adjustHeadDirection() {
		image.setRotation(degree);
	}

	private boolean isOffScreen() {
		if (Enemy.PADDING <= x
				&& x <= BlinkTheGame.GAME_WIDTH - CHR_WIDTH - PADDING
				&& Enemy.PADDING <= y
				&& y <= BlinkTheGame.GAME_HEIGHT - CHR_HEIGHT - PADDING) {
			return false;
		} else {
			return true;
		}
	}

	private void checkStartingPosition() {
		if (this.x < Enemy.PADDING) {
			targetDegree = 0;
		} else if (this.x > BlinkTheGame.GAME_WIDTH - CHR_WIDTH - PADDING) {
			targetDegree = 180;
		} else if (this.y < PADDING) {
			targetDegree = 90;
		} else if (this.y > BlinkTheGame.GAME_HEIGHT - CHR_HEIGHT - PADDING) {
			targetDegree = -90;
		}
	}

	private void run() {
		runWithOutChecking();
		checkRunEnd();
	}

	private void runWithOutChecking() {
		double radians = Math.toRadians(this.degree);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		this.x += this.v * cos;
		this.y += this.v * sin;
		distanceGoing += this.v;
	}

	private boolean checkRunEnd() {
		if (distanceGoing >= RUN_LIMIT) {
			movementMode = MODE_TURNSTART;
			distanceGoing = 0;
			RUN_LIMIT = randomRunDistance();
			return true;
		}
		return false;
	}

	private void turnStart() {
		double degreeChange = randomTurnDegree();
		int tempDegree = (int) (degreeChange / TURN_SPEED);
		double desiredTurnDegree = tempDegree * TURN_SPEED;

		this.targetDegree = this.degree + desiredTurnDegree;
		if (this.targetDegree < -180)
			this.targetDegree += 360.0;
		else if (this.targetDegree > 180)
			this.targetDegree -= 360.0;

		movementMode = MODE_TURNING;
	}

	private double randomTurnDegree() {
		double temp = Math.random() * DEG_LIMIT * 2 - DEG_LIMIT;
		if (temp < 0)
			temp -= DEG_LIMIT;
		else
			temp += DEG_LIMIT;
		return temp;
	}

	private double randomRunDistance() {
		double temp = Math.random() * RUN_RANDOM_RANGE * 2 - RUN_RANDOM_RANGE;
		temp += RUN_INTO_LIMIT;
		return temp;
	}

	private boolean checkTurnEnd() {
		if (degree == targetDegree) {
			return true;
		} else {
			return false;
		}
	}

	private void turning() {
		if (degree > targetDegree) {
			this.degree -= TURN_SPEED;
		} else {
			this.degree += TURN_SPEED;
		}

		if (degree < -180) {
			degree += 360;
		} else if (degree > 180) {
			degree -= 360;
		}

		if (checkTurnEnd()) {
			movementMode = MODE_RUNNING;
		}
	}
}
