package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

class Explode {
	protected int x, y;
	protected int count;
	Image[] imgs = new Image[4];
	Character Timo = new Character();// 对类Character声明一个对象Timo

	Explode(int x, int y) {
		for (int i = 0; i < 4; i++) {
			imgs[i] = (new ImageIcon("src/images/Explode/e" + (i + 1) + ".png")).getImage();
			imgs[i].getWidth(null);// 可获得图片的真实高度
		}
	}

	public void draw(Graphics g) {
		if (count <= 3) {
			g.drawImage(imgs[count], Timo.x, Timo.y, null);// 利用数组轮播爆炸
			count++;
		}
	}
}
