package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

class Shell {
	Image ima = (new ImageIcon("src/images/炮弹.png")).getImage();
	protected int speed = 7;
	protected double degree = Math.random() * Math.PI * 2;// 炮弹随机角度
	protected int Shellx = 300, Shelly = 300;
	/*
	 * 获得炮弹的矩形
	 */
	public Rectangle getRect() {
		return new Rectangle(Shellx, Shelly, 35, 35);
	}

	/*
	 * 画出炮弹使其碰撞窗口反弹
	 */
	public void drawShell(Graphics g) {
		Shellx += speed * Math.cos(degree);
		Shelly += speed * Math.sin(degree);
		if (Shellx < 0 || Shellx > 760) {
			degree = Math.PI - degree;
		}
		if (Shelly < 0 || Shelly > 760) {
			degree = -degree;
		}
		g.drawImage(ima, Shellx, Shelly, 40, 40, null);// 画出炮弹
	}
}