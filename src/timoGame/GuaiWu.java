package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

class GuaiWu {
	protected int width, heighth;// ����Ŀ��
	protected int y=-100;// ��������y�����ƶ�
	TimoGame add;// ����
	boolean live = true;
	Image im1 = (new ImageIcon("src/images/����1.png")).getImage();
	Image im2 = (new ImageIcon("src/images/����2.png")).getImage();
	Image im3 = (new ImageIcon("src/images/����3.png")).getImage();
	Image im4 = (new ImageIcon("src/images/����4.png")).getImage();
	Image im5 = (new ImageIcon("src/images/����5.png")).getImage();
	int n = (int) (Math.random() * (800 - 100 + 1));// ���������ˢ�¹���
	int GW = (int) (Math.random() * 5 + 1);// ���������һ�ֹ���

	public GuaiWu(TimoGame GuaiWu) {
		this.add = GuaiWu;
	}

	/*
	 * ����5�ֹ���
	 */
	public void drawGuaiWu(Graphics g) {
		switch (GW) {
		case 1:
			width = 80;
			heighth = 80;
			y += 6;
			g.drawImage(im1, n++, y, width, heighth, null);
			break;
		case 2:
			width = 80;
			heighth = 80;
			y += 6;
			g.drawImage(im2, n, y, width, heighth, null);
			break;
		case 3:
			width = 80;
			heighth = 80;
			y += 7;
			g.drawImage(im3, n, y, width, heighth, null);
			break;
		case 4:
			width = 80;
			heighth = 80;
			y += 5;
			g.drawImage(im4, n, y, width, heighth, null);
			break;
		case 5:
			width = 100;
			heighth = 100;
			y += 4;
			g.drawImage(im5, n -= 1, y, width, heighth, null);
			break;
		}
		/*
		 * ����������С��0�����߳����߽�������ˢ��
		 */
		if (TimoGame.GuaiWus.size() <= 0 || y > 1200) {
			add.repaintGuaiWus();// ����StartGame�ķ���
		}
	}

	/*
	 * �õ�����ľ���
	 */
	public Rectangle getRect() {
		return new Rectangle(n, y, width / 2, heighth / 2);
	}
}
