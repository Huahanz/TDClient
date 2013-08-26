package Configs;
public class Config{
	/**
	 * income queue
	 */
	public static int[] mapList = {0};

	/**
	 * New global vars
	 */	
	public static final int defaultTestMapNum = 7;
	public static final String[] activeballButtons = {"Fast", "Slow", "Hero", "Solider"};
	public static final String[] towerButtons = {"STower", "DTower"};
	public static final String[] otherButtons = {"Start", "Wall", "Cancel", "Random", "Hard"};
	public static final String startButtonName = "Start";
	public static final String simulatorButtonName = "Simulator";
	public static final int DragonImageSize = 20;
	public static int lostDragon = 0;
	public static int killDragons = 0;
	public static final String SoliderBallImagePath = "Asset/SoliderBall.jpg";
	public static final int ImageWidth = 30;
	public static final int ImageHeight = 30;
	
	/**
	 * Map
	 */
	public static final int mapWidth = 1200;
	public static final int mapHeight = 600;
	public static int slotWidth = 100;
	public static int slotHeight = 60;

	/**
	 * Static data. Load from db. 
	 */
	public static int undieHealth = Integer.MAX_VALUE/2;
	public static int bulletBallHealth = undieHealth / 1000;

	/**
	 * BulletBall
	 */
	public static int bulletBallScope = 0;

	/**
	 * SilverBullet
	 */
	public static int silverBulletXSize = 5;
	public static int silverBulletYSize = 5;
	public static int silverBulletStepLength = 300;
	public static int silverBulletDamage = 2;
	public static String silverBulletBallImagePath = null;

	/**
	 * StalkBullet
	 */
	public static int stalkBulletXSize = 5;
	public static int stalkBulletYSize = 5;
	public static int stalkBulletStepLength = 50;
	public static int stalkBulletAttack = 5;
	public static String stalkBulletBallImagePath = null;

	/**
	 * Tower
	 */
	public static int towerBallAttack = 0;

	/**
	 * DTower
	 */
	public static int DTowerAttack = 10;
	public static int DTowerScope = 300;
	public static int DTowerCost = 200;
	public static int DTowerMapID = 20;
	public static String DTowerBulletName = "SilverBulletBall";
	public static String DTowerImagepath = "";
	
	/**
	 * NTower
	 */
	public static int NTowerAttack = 4;
	public static int NTowerScope = 26;
	public static int NTowerCost = 2000;
	public static int NTowerMapID = 40;
	public static String NTowerBulletName = "FastBulletBall";
	public static String NTowerImagepath = "";
	
	/**
	 * FastBall
	 */
	public static int fastBallMaxHealth = 100;
	public static int fastBallStepLength = 20;
	public static int fastBallXSize = 12;
	public static int fastBallYSize = 15;
	public static String fastBallImagePath = "Asset/FastBall.gif";
	public static int fastBallAttack = 20;
	public static int fastBallScope = 30;
	
	/**
	 * SlowBall
	 */
	public static int slowBallMaxHealth = 300;
	public static int slowBallStepLength = 10;
	public static int slowBallXSize = 10;
	public static int slowBallYSize = 10;
	public static String slowBallImagePath = "Asset/SlowBall.jpg";
	public static int slowBallScope = 20;
	public static int slowBallAttack = 20;
	
	/**
	 * Hero Ball
	 */
	public static int heroBallAttack = 90;
	public static int heroBallScope = 30;
	public static int heroBallHealth = 5000;
	public static int heroBallXSize= 10;
	public static int heroBallYSize= 10;
	public static int heroBallStepLength= 13;	
}