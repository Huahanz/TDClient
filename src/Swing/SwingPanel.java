package Swing;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Balls.*;
import Helpers.Config;
import Helpers.ImageHelper;
import Helpers.MapData;
import Helpers.TestHelper;

public class SwingPanel extends JPanel {

	public SwingPanel() {
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.paintMap(g);
		this.paintDiedBalls(g);
		this.paintBullets(g);
		for (int i = 0; i < GameInfo.balls.size(); i++) {
			Ball ball = GameInfo.balls.get(i);
			if (ball != null) {
				BufferedImage image = ball.getImage();
				if (image != null) {
					int paintX = ball.getX() - Configs.DragonImageSize;
					int paintY = ball.getY() - Configs.DragonImageSize;
					paintX = Math.max(0,
							Math.min(Configs.defaultOneSlotWidth, paintX));
					paintY = Math.max(0,
							Math.min(Configs.defaultOneSlotHeight, paintY));
					g.drawImage(image, paintX, paintY, null);
				}
				BufferedImage healthImage = null;
				if (ball instanceof DragonBall) {
					healthImage = ((DragonBall) ball).getHealthImage();
					if (healthImage != null) {
						int paintX = ball.getX() - Configs.DragonImageSize;
						int paintY = ball.getY() - Configs.DragonImageSize - 5;
						paintX = Math.max(0,
								Math.min(Configs.defaultOneSlotWidth, paintX));
						paintY = Math.max(0,
								Math.min(Configs.defaultOneSlotHeight, paintY));
						g.drawImage(healthImage, paintX, paintY, null);
					}
				}
				if (ball instanceof TowerBall) {
					if (((TowerBall) ball).createFlag == 0) {
						int paintX = ball.getX() + Configs.DragonImageSize;
						int paintY = ball.getY() + Configs.DragonImageSize - 5;
						paintX = Math.max(0,
								Math.min(Configs.defaultOneSlotWidth, paintX));
						paintY = Math.max(0,
								Math.min(Configs.defaultOneSlotHeight, paintY));
						((Graphics2D) g).setComposite(AlphaComposite
								.getInstance(AlphaComposite.SRC_OVER, 0.2f));
						g.drawOval(paintX - ((TowerBall) ball).scope,
								paintY - ((TowerBall) ball).scope,
								2 * ((TowerBall) ball).scope,
								2 * ((TowerBall) ball).scope);
						g.fillOval(paintX - ((TowerBall) ball).scope,
								paintY - ((TowerBall) ball).scope,
								2 * ((TowerBall) ball).scope,
								2 * ((TowerBall) ball).scope);
						((TowerBall) ball).createFlag = 1;
					}
				}
			}
		}

		SwingFrame.goldLabel.setText("Gold: " + Configs.gold);
		SwingFrame.lostLabel.setText("LostDragons: " + Configs.lostDragon);
		SwingFrame.killDragonLabel.setText("KillDragons: " + Configs.killDragons);
	}

	private void paintDiedBalls(Graphics g) {
		for (int i = 0; i < GameInfo.dieBalls.size(); i++) {
			Ball ball = GameInfo.dieBalls.get(i);
			if (ball != null) {
				BufferedImage originalImage;
				try {
					originalImage = ImageIO.read(new File(Configs.DieImagePath));
					BufferedImage image = ImageHelper.resizeImage(Configs.ImageWidth, Configs.ImageHeight,
							originalImage, originalImage.getType());
					if (image != null) {
						int paintX = ball.getX() - Configs.DragonImageSize;
						int paintY = ball.getY() - Configs.DragonImageSize;
						paintX = Math.max(0,
								Math.min(Configs.defaultOneSlotWidth, paintX));
						paintY = Math.max(0,
								Math.min(Configs.defaultOneSlotHeight, paintY));
						g.drawImage(image, paintX, paintY, null);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		if (GameInfo.dieBalls.size() > 0 && Math.random() < 0.026 * GameInfo.dieBalls.size())
			GameInfo.dieBalls.remove(GameInfo.dieBalls.size() - 1);
	}

	private void paintBullets(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < GameInfo.bullets.size(); i++) {
			BulletBall ball = GameInfo.bullets.get(i);
			if (ball != null) {
				if(ball instanceof SilverBulletBall){
					g2.drawLine(ball.getX(), ball.getY() + Configs.DragonImageSize, ((SilverBulletBall)ball).getTargetX(), ((SilverBulletBall)ball).getTargetY());
				}else if(ball instanceof StalkBulletBall){
//					g2.drawLine(ball.getX(), ball.getY(), ball.getTarget().getX(), ball.getTarget().getY());
					g2.fill((Shape) ball.getShape());
				}
				if (ball instanceof StalkBulletBall) {
					g2.setColor(Color.blue);
				}
				BufferedImage image = ball.getImage();
				if (image != null) {
					int paintX = ball.getX() - Configs.DragonImageSize;
					int paintY = ball.getY() - Configs.DragonImageSize;
					paintX = Math.max(0,
							Math.min(Configs.defaultOneSlotWidth, paintX));
					paintY = Math.max(0,
							Math.min(Configs.defaultOneSlotHeight, paintY));
					g.drawImage(image, paintX, paintY, null);
				}
			}
		}
	}

	public void paintMap(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage originalBackgroundImage = null;
		BufferedImage backgroundImage = null;
		try {
			originalBackgroundImage = ImageIO.read(new File(
					Configs.backgroundImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (originalBackgroundImage != null) {
			backgroundImage = ImageHelper.resizeImage(Configs.slotWidth,
					Configs.slotHeight, originalBackgroundImage,
					originalBackgroundImage.getType());
		}
		for (int i = 0; i < Configs.defaultOneSlotWidth; i += Configs.slotWidth) {
			for (int j = 0; j < Configs.defaultOneSlotHeight; j += Configs.slotHeight) {
				if (backgroundImage != null) {
					g2d.drawImage(backgroundImage, i, j, null);
				}
			}
		}
		BufferedImage originalDestinationImage = null;
		BufferedImage destinationImage = null;
		try {
			originalDestinationImage = ImageIO.read(new File(
					Configs.destinationImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (originalBackgroundImage != null) {
			destinationImage = ImageHelper.resizeImage(Configs.slotWidth,
					Configs.slotHeight, originalDestinationImage,
					originalDestinationImage.getType());
		}
		g2d.drawImage(destinationImage, Configs.defaultOneSlotWidth
				- Configs.slotWidth, Configs.defaultOneSlotHeight
				- Configs.slotHeight, null);

		BufferedImage originalWallImage = null;
		BufferedImage wallImage = null;
		try {
			originalWallImage = ImageIO.read(new File(Configs.wallImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (originalBackgroundImage != null) {
			wallImage = ImageHelper.resizeImage(Configs.slotWidth,
					Configs.slotHeight, originalWallImage,
					originalWallImage.getType());
		}
		int[][] currentMap = GameInfo.currentMap;
		for (int i = 0; i < currentMap.length; i++) {
			for (int j = 0; j < currentMap[0].length; j++) {
				if (currentMap[i][j] == 0) {
				} else if (currentMap[i][j] == 1) {
					int height = Configs.slotHeight;
					int width = Configs.slotWidth;
					g2d.drawImage(wallImage, width * j, height * i, null);
				} else if (currentMap[i][j] < Configs.TowerNumber) {
					int height = Configs.slotHeight;
					int width = Configs.slotWidth;
					g.drawImage(this.getMapImage(currentMap[i][j]), width * j,
							height * i, null);
				}
			}
		}
	}

	public BufferedImage getMapImage(int x) {
		if (x < 0 || x / 10 > MapTestData.mapImagePath.length)
			return null;
		String imagePath = MapTestData.mapImagePath[x / 10];
		if (imagePath != null && imagePath.length() > 0) {
			try {
				BufferedImage originalImage = ImageIO.read(new File(imagePath));
				BufferedImage resizedImage = ImageHelper.resizeImage(
						Configs.slotWidth, Configs.slotHeight, originalImage,
						originalImage.getType());
				if (x % 10 != 0) {
					return this.rotateimage(resizedImage, (x % 10) * 45);
				}
				return resizedImage;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public BufferedImage rotateimage(BufferedImage image, int angle) {
		double rotationRequired = Math.toRadians(angle);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(
				rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		return op.filter(image, null);
	}
}