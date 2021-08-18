package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

class Character {
	protected int x = 350, y = 680;// 游戏刚开始提莫出现位置（最下方，最中间）
	protected boolean left, up, right, down;
	protected boolean live = true;
	final int speed = 10;// 提莫移动的速度大小

	Image timo = (new ImageIcon( "src/images/timo.png")).getImage();
	Image imgb = (new ImageIcon("src/images/弓箭.png")).getImage();

	/*
	 * 画提莫
	 */
	public void move(Graphics g) {
		if (live) {
			if (left && x > 0) {
				x -= speed;
			}
			if (up && y > 100) {
				y -= speed;
			}
			if (right && x < 700) {
				x += speed;
			}
			if (down && y < 680) {
				y += speed;
			}
			g.drawImage(timo, x, y, 100, 100, null);// 画出角色提
		}
	}

	/*
	 * 创建方法得到提莫矩形
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, 80, 80);
	}

	/*
	 * 使用按键来控制提莫，可以通过“上下左右”和“WASD"同时操作提莫,WASD只能在键盘是（英文大写）的情况下移动
	 */
	public void Key_move(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:// 按键向左移动
			left = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_UP:// 按键向上移动
			up = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:// 按键向右移动
			right = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_DOWN:// 按键向下移动
			down = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		}
	}

	public void Key_dismove(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:// 取消向左移动
			left = false;
			break;
		case KeyEvent.VK_A:// WASD只能在键盘是（英文大写）的情况下移动
			left = false;
			break;
		case KeyEvent.VK_UP:// 取消向上移动
			up = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:// 取消向右移动
			right = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_DOWN:// 取消向下移动
			down = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		}
	}
}
