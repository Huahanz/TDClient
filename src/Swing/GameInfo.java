package Swing;

import java.awt.BorderLayout;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import Balls.*;
import Configs.Config;
import Map.MapTestData;

/**
 * TODO concurrency issues. 
 * TODO better global index. 
 * TODO reconstruct and rewrite this class. 
 */
public class GameInfo {
	public static SwingPanel swingPanel;
	public static Rectangle2D Bounds;
	
	//TODO change to concurrent Lists -- Vector.  
	public static Vector<Ball> balls = new Vector<Ball>();
	public static Vector<Ball> dieBalls = new Vector<Ball>();
	public static Vector<BulletBall> bullets = new Vector<BulletBall>();


	//TODO rewrite this, remove currentmap. 
	//This need to change for every pvp. 
	public static int[][] currentMap; // the map
	private static boolean[][][][] marks; // temp marks

	public static void clearBalls(){
		balls = new Vector<Ball>();
		dieBalls = new Vector<Ball>();
		bullets = new Vector<BulletBall>();
	}
	
	public static void clearMap(int mapID){
		currentMap = MapTestData.loadMap(Config.defaultTestMapNum);
	}
	
	public static boolean load(SwingFrame swingFrame) {
		swingFrame.setSize(Config.mapWidth, Config.mapHeight);
		GameInfo.swingPanel = new SwingPanel();
		GameInfo.Bounds = GameInfo.swingPanel.getBounds();
		swingFrame.add(GameInfo.swingPanel, BorderLayout.CENTER);
		Thread painterThread = new Thread(new PainterRunnable());
		painterThread.start();
		swingFrame.addComponents();
		return true;
	}

	public static void loadMap(int mapID) throws IOException, ClassNotFoundException {
		// if not in db
		currentMap = MapTestData.loadMap(Config.defaultTestMapNum);
	}

	public static boolean isXSlotValide(int slotNum) {
		int xSlotNum = Config.mapWidth / Config.slotWidth;
		return slotNum >= 0 && slotNum < xSlotNum;
	}

	public static boolean isYSlotValide(int slotNum) {
		int ySlotNum = Config.mapHeight / Config.slotHeight;
		return slotNum >= 0 && slotNum < ySlotNum;
	}

	public static boolean isValide(int x, int y) {
		int xSlot = x / Config.slotWidth;
		int ySlot = y / Config.slotHeight;
		return isXSlotValide(xSlot) && isYSlotValide(ySlot);
	}
}