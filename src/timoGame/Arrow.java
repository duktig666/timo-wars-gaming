package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/*
 * 构造弓箭
 * 需要参数 提莫的坐标    
 */
class Arrow {
	protected int xArrow, yArrow;
	final int speed = 10;// 子弹速度
	Image imgb = (new ImageIcon("src/images/弓箭.png")).getImage();
	boolean live = true;// 判断弓箭的存在状态
	/*
	 * 得到提莫的坐标，使弓箭提莫的位置发射
	 */

	Arrow(int TimoX, int TimoY) {
		xArrow = TimoX;
		yArrow = TimoY;
 	}

	/*
	 * 得到弓箭的矩形
	 */
	public Rectangle getRect() {
		return new Rectangle(xArrow, yArrow, 100, 100);
	}

	/*
	 * 画弓箭
	 */
	public void drawArrow(Graphics g) {
		if (live) {
			yArrow -= speed;
			g.drawImage(imgb, xArrow, yArrow, 100, 100, null);
		}
		if (yArrow < -100)
			this.live = false;// 若弓箭出窗口移除
	}
}
