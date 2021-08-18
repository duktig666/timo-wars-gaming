package timoGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

/*
 * 继承面版，用面板的双缓冲解决游戏的闪烁问题
 */
public class TimoGame extends JPanel {
    protected int a = 0;// 为发射子弹提供延迟的参数
    protected int s = 0;// 为了获取设计怪物提供分数的参数
    protected int ic1x = 0, ic1y = 0;// 背景1的初始位置
    protected int ic2x = 0, ic2y = 800;// 背景2的初始位置
    protected int value;// 消息对话框的按键
    protected int period;// 游戏持续时间
    protected int score = 0;// 为了获取设计怪物提供分数的参数
    protected boolean left, up, right, down;
    protected boolean live = true, fire, start = true;
    Date startTime = new Date();// 设置游戏的开始时间
    Date endTime;// 设置游戏的结束时间
    Image im1 = (new ImageIcon("src/images/提莫游戏图标.jpg")).getImage();// 创建游戏窗口图标
    Image ic1 = (new ImageIcon("src/images/网格背景b1.jpg")).getImage();// 游戏背景
    Image ic2 = (new ImageIcon("src/images/网格背景b2.jpg")).getImage();// 游戏背景
    Image timo = (new ImageIcon("src/images/timo.png")).getImage();// 人物提莫
    JFrame jfrm = new JFrame();// 创建窗口
    PaintThread thread = new PaintThread();// 创建重画线程
    Explode explode;// 声明爆炸类的对象
    Shell[] shells = new Shell[8];// 新建炮弹的数组
    Character Timo = new Character();// 对类Character声明一个对象Timo
    ArrayList<Arrow> Arrows = new ArrayList<Arrow>();// 新建容器弓箭
    static ArrayList<GuaiWu> GuaiWus = new ArrayList<GuaiWu>();// 新建容器怪物

    public TimoGame() {
        jfrm.setLayout(null);
        jfrm.setTitle("提莫大作战");
        jfrm.setSize(800, 800);
        jfrm.setLocationRelativeTo(null);//设置窗体居中显示
        jfrm.setIconImage(im1);
        jfrm.setResizable(false);// 使游戏窗口不能改变大小
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 单击窗口关闭按钮，结束程序
        jfrm.setVisible(true);// 使窗口显示
        jfrm.add(this);// 将面版加入到窗口
        jfrm.addKeyListener(new MyKey());// 内部类对象为事件监听向窗口注;
        this.setLayout(null);// 设置面版，防止闪烁
        this.setBounds(0, 0, 800, 800);
        thread.start();
        repaintGuaiWus();// 调用循环语句将怪物添加到容器中的方法
        // 利用数组初始化炮弹
        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }

    /*
     * 利用for循环，循环将随机数量的怪物添加到容器中
     */
    public void repaintGuaiWus() {
        int m = (int) (Math.random() * 4 + 4);// 随机4-7个怪物
        GuaiWus.clear();// 添加之前清楚之前的怪物
        for (int i = 0; i < m; i++) {
            GuaiWus.add(new GuaiWu(this));
        }
    }

    /*
     * 提莫死，弹出对话框
     */
    public void Timoend() {
        int value = JOptionPane.showOptionDialog(jfrm, "是否重新战斗", "提莫大作战", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, new Object[] {"重新开始游戏", "返回"}, null);
        jfrm.dispose();
        if (value == 0) {
            new TimoGame();// 加载开始游戏窗口
        } else {
            new Main();// 加载登录游戏窗口

        }
    }

    /*
     * 整个游戏的主画笔，调用其他类进行画对象
     */
    public void paint(Graphics g) {
        // paint系统自动调用，g相当于画笔
        /*
         * 利用循环语句画游戏背景，是两张背景交替出现
         */
        if (ic1y >= 800) {
            ic1y = - 800;
        }
        if (ic2y >= 800) {
            ic2y = - 800;
        }
        ic1y += 10;
        ic2y += 10;
        g.drawImage(ic1, ic1x, ic1y, 800, 800, null);// 画的游戏背景
        g.drawImage(ic2, ic2x, ic2y, 800, 800, null);
        /*
         * 画Character类中的提莫
         */
        Timo.move(g);// 画角色提莫
        /*
         * 利用for循环画容器中的弓箭
         */
        for (int i = 0; i < Arrows.size(); i++) {
            Arrows.get(i).drawArrow(g);// 利用循环语句画弓箭类
        }
        /*
         * 利用if条件语句，如果没有怪物将继续向容器中添加 如果没有此判断，则将怪物完全杀死则不会出现怪物
         */
        if (GuaiWus.size() == 0) {
            repaintGuaiWus();// 调用给容器加怪物的方法，如果没有只会刷新一只怪物
            GuaiWus.add(new GuaiWu(this));
        }
        /*
         * 画容器中的怪物
         */
        for (int k = 0; k < GuaiWus.size(); k++) {
            GuaiWus.get(k).drawGuaiWu(g);// 利用循环语句画怪物
        }
        /*
         * 容器中的弓箭和容器中的怪物进行碰撞检测 利用双重循环使容器中的每一支弓箭和每一个怪物进行检测
         */
        for (int i = 0; i < Arrows.size(); i++) {
            for (int k = 0; k < GuaiWus.size(); k++) {
                if (Arrows.get(i).getRect().intersects(GuaiWus.get(k).getRect())) {
                    s++;// s初始值为0，碰撞一次加1，方便计分
                    Arrows.remove(Arrows.get(i));// 若碰撞移除弓箭
                    GuaiWus.remove(GuaiWus.get(k));// 若碰撞移除怪
                    break;// 跳出循环
                }
            }
        }
        /*
         * 每个怪物和提莫进行碰撞检测
         */
        for (int k = 0; k < GuaiWus.size(); k++) {
            if (GuaiWus.get(k).getRect().intersects(Timo.getRect())) {
                Timo.live = false;// 若碰撞提莫消失
                /*
                 * 利用数组轮播显示爆炸效果 将爆炸加条件空的（null）防止一直碰撞，一直画图导致爆炸效果画不出来
                 */
                break;
            }
        }
        /*
         * 对每个炮弹和提莫进行碰撞检测
         */
        for (int i = 0; i < shells.length; i++) {
            shells[i].drawShell(g);// 画出炮弹
            if (shells[i].getRect().intersects(Timo.getRect())) {
                Timo.live = false;
                break;
            }
        }
        endTime = new Date();
        if (Timo.live) {
            g.setColor(Color.RED);
            Font f = new Font("宋体", Font.BOLD, 30);
            g.setFont(f);
            score = 10 * s;// 射死每个怪物加10分
            period = (int) ((endTime.getTime() - startTime.getTime()) / 1000);
            g.drawString("游戏时间：" + period + "秒" + "  分数：" + score + "分", 220, 40);
        }
        if (Timo.live == false) {
            if (explode == null) {
                explode = new Explode(Timo.x, Timo.y);
            }
            explode.draw(g);
            start = false;// 提莫死，结束线程
            Timoend();
        }
    }

    /*
     * 创建重画的线程，来使物体移动
     *
     */
    class PaintThread extends Thread {
        public void run() {
            while (start) {
                // while语句既是循环语句，又是判断语句，start初始值为true，默认线程启动
                if (a == 8)// 增加弓箭画出的延迟时间，防止按住空格一发弓箭
                    fire = true;
                a++;// 循环a次发射弓箭，相当于a*40毫秒发射一次子弹
                try {
                    sleep(40);// 40毫秒，sleep()必须写在try-catch之间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (! start)
                    break;// 结束线程
                repaint();// 重画，放置线程内可以调节重画的时间
            }
        }
    }

    class MyKey extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // 按下键开始方向移动
            Timo.Key_move(e);// 调用方法
            while (fire) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Arrows.add(new Arrow(Timo.x, Timo.y));// 每次按下空格键都会发射弓箭并得到提莫的坐标
                }
                a = 0;// 每次按下空格都将a重置为0
                fire = false;// 重置发射状态为false
            }
            if (e.getKeyCode() == KeyEvent.VK_F9) {
                jfrm.dispose();
                new TimoGame();// 加载开始游戏窗口
            }
            if (e.getKeyCode() == KeyEvent.VK_F10) {
                jfrm.dispose();
                new Main();// 加载开始游戏窗口
            }
            if (e.getKeyCode() == KeyEvent.VK_F11) {
                System.exit(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // 松开按键取消操作
            Timo.Key_dismove(e);
        }
    }
}
