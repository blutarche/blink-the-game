import org.newdawn.slick.SlickException;


public class StupidOne extends Enemy {

	private double desiredTurnDegree;
	private double turningDegree;
	private double distanceGoing;

	private static final double RUN_LIMIT = 120.0;
	private static final double DEG_LIMIT = 120.0;
	private static final double TURN_SPEED = 3.0;
	
	private int movementMode; //0=start 1=running 2=turnstart 3=turning
	
	public StupidOne(float x, float y) throws SlickException {
		super(x, y, 1.0f, "res/stupid-dot.png");
		movementMode = 0;
		distanceGoing = 0;
		// TODO Auto-generated constructor stub
	}


	public void update () {
		if (distanceGoing >= RUN_LIMIT) {
			movementMode = 2;
			distanceGoing = 0;
		}
		if (movementMode == 0) {
			checkIfInsideScreen();
		}
		else if (movementMode == 1) {
		}
		else if (movementMode == 2) {
			turnStart();
		}
		else {
			turning();
		}
		run();
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
		
		double degreeChange = Math.random() * DEG_LIMIT * 2 - DEG_LIMIT;
		int tempDegree = (int)degreeChange/(int)TURN_SPEED;
		System.out.println("tempDegree:"+tempDegree);
		this.desiredTurnDegree = tempDegree * TURN_SPEED;
		this.turningDegree = 0.0;
		movementMode = 3;
	}
	
	private void turning () {
//		System.out.println("t:"+turningDegree+" d:"+desiredTurnDegree);
		if (turningDegree==desiredTurnDegree) {
			this.turningDegree = 0.0;
			distanceGoing = 0;
			movementMode = 1;
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
