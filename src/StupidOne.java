import org.newdawn.slick.SlickException;


public class StupidOne extends Enemy {

	private double desiredTurnDegree;
	private double turningDegree;
	private double distanceGoing;
	private boolean isAtBorder;

	private double RUN_LIMIT = 100.0;
	private final double RUN_INTO_LIMIT = 150.0;
	private final double RUN_RANDOM_RANGE = 75.0;
	private static final double DEG_LIMIT = 40.0;
	private static final double TURN_SPEED = 0.5;
	private static final float RUN_SPEED = 0.5f;
	
	private int movementMode; //0=start 1=runintoscreen 2=running 3=turnstart 4=turning
	
	public StupidOne(float x, float y) throws SlickException {
		super(x, y, RUN_SPEED, "res/stupid-dot.png");
		movementMode = 0;
		distanceGoing = 0;
		// TODO Auto-generated constructor stub
	}


	public void update () {
		if (distanceGoing >= RUN_LIMIT) {
			movementMode = 3;
			distanceGoing = 0;
			RUN_LIMIT = randomRunDistance();
		}
		if (movementMode == 0) {
			checkIfInsideScreen();
			run();
		}
		else if (movementMode == 1) {
			RUN_LIMIT=240;
			movementMode = 2;
		}
		else if (movementMode == 3) {
			turnStart();
		}
		else if (movementMode == 4) {
			turning();
		}
		else if (movementMode == 5) {
			run();
		}
		else if (movementMode == 2) {
			if (isOffScreen()){
				turnAround();
			}
			else {
				run();
			} 
		}
		//run();
	}
	
	private void turnAround () {
		desiredTurnDegree = 175;
		turningDegree = 0;
		distanceGoing = 0;
		movementMode = 4;
		isAtBorder = true;
		RUN_LIMIT = randomRunDistance();
	}
	
	private boolean isOffScreen () {
		if (0<=x&&x<=BlinkTheGame.GAME_WIDTH-CHR_WIDTH && 0<=y&&y<=BlinkTheGame.GAME_HEIGHT-CHR_HEIGHT) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private void checkIfInsideScreen () {
		if (this.x<0) { //Start at Left side
			degree = 0;
		}
		else if (this.x>BlinkTheGame.GAME_WIDTH-CHR_WIDTH) { // Start at Right side
			degree = 180;
		}
		else if (this.y<0) { //Start at Up side
			degree = 90;
		}
		else if (this.y>BlinkTheGame.GAME_HEIGHT-CHR_HEIGHT) { // Start at Down side
			degree = 270;
		}
		else {
			movementMode = 2;
		}
	}
	
	private void run () {	
//		double degreeChange = Math.random() * DEG_LIMIT * 2 - DEG_LIMIT;
//		this.degree += degreeChange;
		double radians = Math.toRadians(this.degree);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		this.x += this.v*cos;
		this.y += this.v*sin;	
		distanceGoing+=this.v;
	}
	
	private void turnStart () {
		double degreeChange = randomTurnDegree();
		int tempDegree = (int)(degreeChange/TURN_SPEED);
		this.desiredTurnDegree = tempDegree * TURN_SPEED;
		this.turningDegree = 0.0;
		movementMode = 4;
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
	
	private void turning () {
		System.out.println("t:"+turningDegree+" d:"+desiredTurnDegree);
		if (turningDegree==desiredTurnDegree) {
			this.turningDegree = 0.0;
			distanceGoing = 0;
			if (!isAtBorder) movementMode = 2;
			else {
				isAtBorder = false;
				movementMode = 5;
			}
		}
		else if (desiredTurnDegree<0) {
			this.degree -= TURN_SPEED;
			this.turningDegree -= TURN_SPEED;
		}
		else {
			this.degree += TURN_SPEED;
			this.turningDegree += TURN_SPEED;
		}
	}
}
