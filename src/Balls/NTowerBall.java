package Balls;

import Helpers.Config;
import swingFrontEnd.GameInfo;

public class NTowerBall extends TowerBall {
	public NTowerBall(int id, int x, int y, int size) {
		super(id, x, y, size, Configs.NTowerImagepath, Configs.NTowerScope, Configs.NTowerCost,
				Configs.NTowerMapID, Configs.NTowerBulletName);
	}
}