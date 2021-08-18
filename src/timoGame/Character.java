package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

class Character {
	protected int x = 350, y = 680;// ��Ϸ�տ�ʼ��Ī����λ�ã����·������м䣩
	protected boolean left, up, right, down;
	protected boolean live = true;
	final int speed = 10;// ��Ī�ƶ����ٶȴ�С

	Image timo = (new ImageIcon( "src/images/timo.png")).getImage();
	Image imgb = (new ImageIcon("src/images/����.png")).getImage();

	/*
	 * ����Ī
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
			g.drawImage(timo, x, y, 100, 100, null);// ������ɫ��
		}
	}

	/*
	 * ���������õ���Ī����
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, 80, 80);
	}

	/*
	 * ʹ�ð�����������Ī������ͨ�����������ҡ��͡�WASD"ͬʱ������Ī,WASDֻ���ڼ����ǣ�Ӣ�Ĵ�д����������ƶ�
	 */
	public void Key_move(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:// ���������ƶ�
			left = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_UP:// ���������ƶ�
			up = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:// ���������ƶ�
			right = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_DOWN:// ���������ƶ�
			down = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		}
	}

	public void Key_dismove(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:// ȡ�������ƶ�
			left = false;
			break;
		case KeyEvent.VK_A:// WASDֻ���ڼ����ǣ�Ӣ�Ĵ�д����������ƶ�
			left = false;
			break;
		case KeyEvent.VK_UP:// ȡ�������ƶ�
			up = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:// ȡ�������ƶ�
			right = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_DOWN:// ȡ�������ƶ�
			down = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		}
	}
}
