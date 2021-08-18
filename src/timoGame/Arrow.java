package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/*
 * ���칭��
 * ��Ҫ���� ��Ī������    
 */
class Arrow {
	protected int xArrow, yArrow;
	final int speed = 10;// �ӵ��ٶ�
	Image imgb = (new ImageIcon("src/images/����.png")).getImage();
	boolean live = true;// �жϹ����Ĵ���״̬
	/*
	 * �õ���Ī�����꣬ʹ������Ī��λ�÷���
	 */

	Arrow(int TimoX, int TimoY) {
		xArrow = TimoX;
		yArrow = TimoY;
 	}

	/*
	 * �õ������ľ���
	 */
	public Rectangle getRect() {
		return new Rectangle(xArrow, yArrow, 100, 100);
	}

	/*
	 * ������
	 */
	public void drawArrow(Graphics g) {
		if (live) {
			yArrow -= speed;
			g.drawImage(imgb, xArrow, yArrow, 100, 100, null);
		}
		if (yArrow < -100)
			this.live = false;// �������������Ƴ�
	}
}
