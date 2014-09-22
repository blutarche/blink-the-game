import org.newdawn.slick.SlickException;


public class StupidOne extends Enemy {

	private double targetDegree;
	private double distanceGoing;
	
	private double RUN_LIMIT = 100.0;
	private final double RUN_INTO_LIMIT = 150.0;
	private final double RUN_RANDOM_RANGE = 30.0;
	private final double DEG_LIMIT = 50.0;
	private final double TURN_SPEED = 0.5;
	private static final float RUN_SPEED = 1f;
	
	private final int MODE_OFFSCREEN = 0;
	private final int MODE_RUNNING = 1;
	private final int MODE_TURNSTART = 2;
	private final int MODE_TURNING = 3; 
	
	private int movementMode;
	
	public StupidOne(float x, float y) throws SlickException {
		super(x, y, RUN_SPEED, "res/stupid-dot.png");
		movementMode = MODE_OFFSCREEN;
		distanceGoing = 0;
		degree = 0;
	}

	public void update () {
		if (distanceGoing >= RUN_LIMIT) {
			movementMode = MODE_TURNSTART;
			distanceGoing = 0;
			RUN_LIMIT = RUN_INTO_LIMIT;
		}
		if (movementMode == MODE_OFFSCREEN) {
			checkStartingPosition();
			if (!checkTurnEnd()) {
				turning();
			}
			else if (!isOffScreen()){
				run();
			}
			else {
				movementMode = MODE_RUNNING;
			}
		}
		else if (movementMode == MODE_RUNNING) {
			run();
			if (isOffScreen()) {
				movementMode = MODE_OFFSCREEN;
			}
		}
		else if (movementMode == MODE_TURNSTART) {
			turnStart();
		}
		else if (movementMode == MODE_TURNING){
			turning();
			if (checkTurnEnd()) {
				movementMode = MODE_RUNNING;
			}
		}
	}
	
	private boolean isOffScreen () {
		if (Enemy.PADDING<=x&&x<=BlinkTheGame.GAME_WIDTH-CHR_WIDTH-PADDING &&
			Enemy.PADDING<=y&&y<=BlinkTheGame.GAME_HEIGHT-CHR_HEIGHT-PADDING) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private void checkStartingPosition () {
		if (this.x<Enemy.PADDING) { //Start at Left side
			targetDegree = 0;
		}
		else if (this.x>BlinkTheGame.GAME_WIDTH-CHR_WIDTH-PADDING) { // Start at Right side
			targetDegree = 180;
		}
		else if (this.y<PADDING) { //Start at Up side
			targetDegree = 90;
		}
		else if (this.y>BlinkTheGame.GAME_HEIGHT-CHR_HEIGHT-PADDING) { // Start at Down side
			targetDegree = -90;
		}
		else {
			
		}
	}
	private void run () {
		double radians = Math.toRadians(this.degree);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		this.x += this.v*cos;
		this.y += this.v*sin;	
		distanceGoing+=this.v;
		checkRunEnd();
	}
	
	private boolean checkRunEnd () {
		if (distanceGoing >= RUN_LIMIT) {
			movementMode = MODE_TURNSTART;
			distanceGoing = 0;
			RUN_LIMIT = randomRunDistance();
			return true;
		}
		return false;
	}
	
	private void turnStart () {
		double degreeChange = randomTurnDegree();
		int tempDegree = (int)(degreeChange/TURN_SPEED);
		double desiredTurnDegree = tempDegree * TURN_SPEED;
		this.targetDegree = this.degree + desiredTurnDegree;
		System.out.println("targetDegree: "+targetDegree);
		if (this.targetDegree<-180) this.targetDegree += 360.0;
		else if (this.targetDegree>180) this.targetDegree -= 360.0;
		movementMode = MODE_TURNING;
	}
	
	private double randomTurnDegree () {
		double temp = Math.random() * DEG_LIMIT * 2 - DEG_LIMIT;
		if (temp<0) temp -= DEG_LIMIT;
		else temp += DEG_LIMIT;
		return temp;
	}
	private double randomRunDistance () {
		double temp = Math.random() * RUN_RANDOM_RANGE * 2 - RUN_RANDOM_RANGE;
		temp += RUN_INTO_LIMIT;
		return temp;
	}
	
	private boolean checkTurnEnd () {
		if (degree==targetDegree) {
			return true;
		}
		else {
			return false;
		}
	}
	private void turning () {
		System.out.println("target:"+targetDegree+" current:"+degree);
		if (degree>targetDegree) {
			this.degree -= TURN_SPEED;
		}
		else {
			this.degree += TURN_SPEED;
		}
		if (degree<-180) {
			degree += 360;
		}
		else if (degree>180) {
			degree -= 360;
		}
	}
	
}
