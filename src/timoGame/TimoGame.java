package timoGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

/*
 * �̳���棬������˫��������Ϸ����˸����
 */
public class TimoGame extends JPanel {
    protected int a = 0;// Ϊ�����ӵ��ṩ�ӳٵĲ���
    protected int s = 0;// Ϊ�˻�ȡ��ƹ����ṩ�����Ĳ���
    protected int ic1x = 0, ic1y = 0;// ����1�ĳ�ʼλ��
    protected int ic2x = 0, ic2y = 800;// ����2�ĳ�ʼλ��
    protected int value;// ��Ϣ�Ի���İ���
    protected int period;// ��Ϸ����ʱ��
    protected int score = 0;// Ϊ�˻�ȡ��ƹ����ṩ�����Ĳ���
    protected boolean left, up, right, down;
    protected boolean live = true, fire, start = true;
    Date startTime = new Date();// ������Ϸ�Ŀ�ʼʱ��
    Date endTime;// ������Ϸ�Ľ���ʱ��
    Image im1 = (new ImageIcon("src/images/��Ī��Ϸͼ��.jpg")).getImage();// ������Ϸ����ͼ��
    Image ic1 = (new ImageIcon("src/images/���񱳾�b1.jpg")).getImage();// ��Ϸ����
    Image ic2 = (new ImageIcon("src/images/���񱳾�b2.jpg")).getImage();// ��Ϸ����
    Image timo = (new ImageIcon("src/images/timo.png")).getImage();// ������Ī
    JFrame jfrm = new JFrame();// ��������
    PaintThread thread = new PaintThread();// �����ػ��߳�
    Explode explode;// ������ը��Ķ���
    Shell[] shells = new Shell[8];// �½��ڵ�������
    Character Timo = new Character();// ����Character����һ������Timo
    ArrayList<Arrow> Arrows = new ArrayList<Arrow>();// �½���������
    static ArrayList<GuaiWu> GuaiWus = new ArrayList<GuaiWu>();// �½���������

    public TimoGame() {
        jfrm.setLayout(null);
        jfrm.setTitle("��Ī����ս");
        jfrm.setSize(800, 800);
        jfrm.setLocationRelativeTo(null);//���ô��������ʾ
        jfrm.setIconImage(im1);
        jfrm.setResizable(false);// ʹ��Ϸ���ڲ��ܸı��С
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �������ڹرհ�ť����������
        jfrm.setVisible(true);// ʹ������ʾ
        jfrm.add(this);// �������뵽����
        jfrm.addKeyListener(new MyKey());// �ڲ������Ϊ�¼������򴰿�ע;
        this.setLayout(null);// ������棬��ֹ��˸
        this.setBounds(0, 0, 800, 800);
        thread.start();
        repaintGuaiWus();// ����ѭ����佫������ӵ������еķ���
        // ���������ʼ���ڵ�
        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }

    /*
     * ����forѭ����ѭ������������Ĺ�����ӵ�������
     */
    public void repaintGuaiWus() {
        int m = (int) (Math.random() * 4 + 4);// ���4-7������
        GuaiWus.clear();// ���֮ǰ���֮ǰ�Ĺ���
        for (int i = 0; i < m; i++) {
            GuaiWus.add(new GuaiWu(this));
        }
    }

    /*
     * ��Ī���������Ի���
     */
    public void Timoend() {
        int value = JOptionPane.showOptionDialog(jfrm, "�Ƿ�����ս��", "��Ī����ս", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, new Object[] {"���¿�ʼ��Ϸ", "����"}, null);
        jfrm.dispose();
        if (value == 0) {
            new TimoGame();// ���ؿ�ʼ��Ϸ����
        } else {
            new Main();// ���ص�¼��Ϸ����

        }
    }

    /*
     * ������Ϸ�������ʣ�������������л�����
     */
    @Override
    public void paint(Graphics g) {
        // paintϵͳ�Զ����ã�g�൱�ڻ���
        /*
         * ����ѭ����仭��Ϸ�����������ű����������
         */
        if (ic1y >= 800) {
            ic1y = - 800;
        }
        if (ic2y >= 800) {
            ic2y = - 800;
        }
        ic1y += 10;
        ic2y += 10;
        g.drawImage(ic1, ic1x, ic1y, 800, 800, null);// ������Ϸ����
        g.drawImage(ic2, ic2x, ic2y, 800, 800, null);
        /*
         * ��Character���е���Ī
         */
        Timo.move(g);// ����ɫ��Ī
        /*
         * ����forѭ���������еĹ���
         */
        for (int i = 0; i < Arrows.size(); i++) {
            Arrows.get(i).drawArrow(g);// ����ѭ����仭������
        }
        /*
         * ����if������䣬���û�й��ｫ��������������� ���û�д��жϣ��򽫹�����ȫɱ���򲻻���ֹ���
         */
        if (GuaiWus.size() == 0) {
            repaintGuaiWus();// ���ø������ӹ���ķ��������û��ֻ��ˢ��һֻ����
            GuaiWus.add(new GuaiWu(this));
        }
        /*
         * �������еĹ���
         */
        for (int k = 0; k < GuaiWus.size(); k++) {
            GuaiWus.get(k).drawGuaiWu(g);// ����ѭ����仭����
        }
        /*
         * �����еĹ����������еĹ��������ײ��� ����˫��ѭ��ʹ�����е�ÿһ֧������ÿһ��������м��
         */
        for (int i = 0; i < Arrows.size(); i++) {
            for (int k = 0; k < GuaiWus.size(); k++) {
                if (Arrows.get(i).getRect().intersects(GuaiWus.get(k).getRect())) {
                    s++;// s��ʼֵΪ0����ײһ�μ�1������Ʒ�
                    Arrows.remove(Arrows.get(i));// ����ײ�Ƴ�����
                    GuaiWus.remove(GuaiWus.get(k));// ����ײ�Ƴ���
                    break;// ����ѭ��
                }
            }
        }
        /*
         * ÿ���������Ī������ײ���
         */
        for (int k = 0; k < GuaiWus.size(); k++) {
            if (GuaiWus.get(k).getRect().intersects(Timo.getRect())) {
                Timo.live = false;// ����ײ��Ī��ʧ
                /*
                 * ���������ֲ���ʾ��ըЧ�� ����ը�������յģ�null����ֹһֱ��ײ��һֱ��ͼ���±�ըЧ����������
                 */
                break;
            }
        }
        /*
         * ��ÿ���ڵ�����Ī������ײ���
         */
        for (int i = 0; i < shells.length; i++) {
            shells[i].drawShell(g);// �����ڵ�
            if (shells[i].getRect().intersects(Timo.getRect())) {
                Timo.live = false;
                break;
            }
        }
        endTime = new Date();
        if (Timo.live) {
            g.setColor(Color.RED);
            Font f = new Font("����", Font.BOLD, 30);
            g.setFont(f);
            score = 10 * s;// ����ÿ�������10��
            period = (int) ((endTime.getTime() - startTime.getTime()) / 1000);
            g.drawString("��Ϸʱ�䣺" + period + "��" + "  ������" + score + "��", 220, 40);
        }
        if (! Timo.live) {
            if (explode == null) {
                explode = new Explode(Timo.x, Timo.y);
            }
            explode.draw(g);
            start = false;// ��Ī���������߳�
            Timoend();
        }
    }

    /*
     * �����ػ����̣߳���ʹ�����ƶ�
     *
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (start) {
                // while������ѭ����䣬�����ж���䣬start��ʼֵΪtrue��Ĭ���߳�����
                if (a == 8)// ���ӹ����������ӳ�ʱ�䣬��ֹ��ס�ո�һ������
                {
                    fire = true;
                }
                a++;// ѭ��a�η��乭�����൱��a*40���뷢��һ���ӵ�
                try {
                    sleep(40);// 40���룬sleep()����д��try-catch֮��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (! start) {
                    break;// �����߳�
                }
                repaint();// �ػ��������߳��ڿ��Ե����ػ���ʱ��
            }
        }
    }

    class MyKey extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // ���¼���ʼ�����ƶ�
            Timo.Key_move(e);// ���÷���
            while (fire) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Arrows.add(new Arrow(Timo.x, Timo.y));// ÿ�ΰ��¿ո�����ᷢ�乭�����õ���Ī������
                }
                a = 0;// ÿ�ΰ��¿ո񶼽�a����Ϊ0
                fire = false;// ���÷���״̬Ϊfalse
            }
            if (e.getKeyCode() == KeyEvent.VK_F9) {
                jfrm.dispose();
                new TimoGame();// ���ؿ�ʼ��Ϸ����
            }
            if (e.getKeyCode() == KeyEvent.VK_F10) {
                jfrm.dispose();
                new Main();// ���ؿ�ʼ��Ϸ����
            }
            if (e.getKeyCode() == KeyEvent.VK_F11) {
                System.exit(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // �ɿ�����ȡ������
            Timo.Key_dismove(e);
        }
    }
}
