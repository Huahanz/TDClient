package Balls;

import Helpers.Config;

public class SilverBulletBall extends BulletBall {

	public SilverBulletBall(int id, int x, int y, Ball ball) {
		super(id, x, y, Configs.silverBulletXSize, Configs.silverBulletYSize,
				Configs.silverBulletStepLength, ball,
				Configs.silverBulletBallImagePath, Configs.silverBulletDamage);
	}
}