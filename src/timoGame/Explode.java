package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

class Explode {
	protected int x, y;
	protected int count;
	Image[] imgs = new Image[4];
	Character Timo = new Character();// ����Character����һ������Timo

	Explode(int x, int y) {
		for (int i = 0; i < 4; i++) {
			imgs[i] = (new ImageIcon("src/images/Explode/e" + (i + 1) + ".png")).getImage();
			imgs[i].getWidth(null);// �ɻ��ͼƬ����ʵ�߶�
		}
	}

	public void draw(Graphics g) {
		if (count <= 3) {
			g.drawImage(imgs[count], Timo.x, Timo.y, null);// ���������ֲ���ը
			count++;
		}
	}
}
