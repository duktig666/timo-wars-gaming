package timoGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JFrame implements ActionListener {
    Image im1 = (new ImageIcon("src/images/��Ī��Ϸͼ��.jpg")).getImage();// ��Ϸ��Сͼ��
    ImageIcon icon = new ImageIcon("src/images/��Ī����3.jpg");
    JButton btn1 = new JButton("��ʼ��Ϸ");// ������ť
    JButton btn2 = new JButton("������Ϸ");
    JButton btn3 = new JButton("�˳���Ϸ");
    JPanel jpan = new JPanel();// �������
    JLabel jlbBackImg = new JLabel();// ������ǩ
    /*
     * ������Ϸ���ڽ���
     */

    Main() {
        this.setLayout(null);// ȡ�����ڲ��ֹ�����
        this.setTitle("��Ī����ս");
        this.setIconImage(im1);// ��������Ϸ����ͼ��
        //this.setBounds(500, 120, 800, 800);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);//���ô��������ʾ
        jpan.setBounds(0, 0, 800, 800);
        jpan.setLayout(null);// ȡ�����Ĳ��ֹ�����
        btn1.setBounds(300, 360, 120, 40);// ���ð�ť��λ�����С
        btn2.setBounds(300, 410, 120, 40);
        btn3.setBounds(300, 460, 120, 40);
        btn1.addActionListener(this);// �ð������¼�Դ��������ȥ���μ�����������ťע��
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        jlbBackImg.setBounds(0, 0, 800, 800);
        jlbBackImg.setIcon(icon);
        jpan.add(btn1);
        jpan.add(btn2);
        jpan.add(btn3);
        jpan.add(jlbBackImg);
        this.add(jpan);
        this.setResizable(false);// ʹ��Ϸ���ڲ��ܸı��С
        this.setVisible(true);// ʹ������ʾ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �������ڹرհ�ť����������
    }

    /*
     * ��ť�Ķ����¼�
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) { // e.getSource Ϊ����¼�Դ
            this.dispose();// �����ť����ʼ��Ϸ���رյ�ǰ����
            new TimoGame();// ���ؿ�ʼ��Ϸ����
        }
        if (e.getSource() == btn2) {
            int value = JOptionPane.showOptionDialog(this, "������Ϸ�����������ҡ����ߡ�WASD�����Ʒ��򣬡��ո񽨡�"
                            + "���乭�������ǲ���������ը����ը����Ҫ��ܡ�\n                        ��F9�����¿�ʼ��Ϸ��"
                            + "��F10��������Ϸ���棬��F11���˳���Ϸ��ף����Ϸ��죡", "��Ī����ս", JOptionPane.YES_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, null, null);
        }
        if (e.getSource() == btn3) {
            System.exit(0);// �˳�����
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
