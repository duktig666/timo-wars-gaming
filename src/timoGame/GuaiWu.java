package timoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

class GuaiWu {
	protected int width, heighth;// 怪物的宽高
	protected int y=-100;// 怪物随着y坐标移动
	TimoGame add;// 传参
	boolean live = true;
	Image im1 = (new ImageIcon("src/images/怪物1.png")).getImage();
	Image im2 = (new ImageIcon("src/images/怪物2.png")).getImage();
	Image im3 = (new ImageIcon("src/images/怪物3.png")).getImage();
	Image im4 = (new ImageIcon("src/images/怪物4.png")).getImage();
	Image im5 = (new ImageIcon("src/images/怪物5.png")).getImage();
	int n = (int) (Math.random() * (800 - 100 + 1));// 随机横坐标刷新怪物
	int GW = (int) (Math.random() * 5 + 1);// 随机生成哪一种怪物

	public GuaiWu(TimoGame GuaiWu) {
		this.add = GuaiWu;
	}

	/*
	 * 画出5种怪物
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
		 * 若怪物数量小于0，或者超出边界则重新刷新
		 */
		if (TimoGame.GuaiWus.size() <= 0 || y > 1200) {
			add.repaintGuaiWus();// 调用StartGame的方法
		}
	}

	/*
	 * 得到怪物的矩形
	 */
	public Rectangle getRect() {
		return new Rectangle(n, y, width / 2, heighth / 2);
	}
}
