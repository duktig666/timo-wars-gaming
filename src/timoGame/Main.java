package timoGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JFrame implements ActionListener {
    Image im1 = (new ImageIcon("src/images/提莫游戏图标.jpg")).getImage();// 游戏缩小图标
    ImageIcon icon = new ImageIcon("src/images/提莫背景3.jpg");
    JButton btn1 = new JButton("开始游戏");// 创建按钮
    JButton btn2 = new JButton("关于游戏");
    JButton btn3 = new JButton("退出游戏");
    JPanel jpan = new JPanel();// 创建面板
    JLabel jlbBackImg = new JLabel();// 创建标签
    /*
     * 设置游戏窗口界面
     */

    Main() {
        this.setLayout(null);// 取消窗口布局管理器
        this.setTitle("提莫大作战");
        this.setIconImage(im1);// 设置与游戏窗口图标
        //this.setBounds(500, 120, 800, 800);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);//设置窗体居中显示
        jpan.setBounds(0, 0, 800, 800);
        jpan.setLayout(null);// 取消面板的布局管理器
        btn1.setBounds(300, 360, 120, 40);// 设置按钮的位置与大小
        btn2.setBounds(300, 410, 120, 40);
        btn3.setBounds(300, 460, 120, 40);
        btn1.addActionListener(this);// 让包含“事件源”的容器去担任监听，窗口向按钮注册
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        jlbBackImg.setBounds(0, 0, 800, 800);
        jlbBackImg.setIcon(icon);
        jpan.add(btn1);
        jpan.add(btn2);
        jpan.add(btn3);
        jpan.add(jlbBackImg);
        this.add(jpan);
        this.setResizable(false);// 使游戏窗口不能改变大小
        this.setVisible(true);// 使窗口显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 单击窗口关闭按钮，结束程序
    }

    /*
     * 按钮的动作事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) { // e.getSource 为获得事件源
            this.dispose();// 点击按钮“开始游戏”关闭当前窗口
            new TimoGame();// 加载开始游戏窗口
        }
        if (e.getSource() == btn2) {
            int value = JOptionPane.showOptionDialog(this, "关于游戏：“上下左右”或者“WASD”控制方向，“空格建”"
                            + "发射弓箭，但是并不能引爆炸弹，炸弹需要躲避。\n                        “F9”重新开始游戏，"
                            + "“F10”返回游戏界面，“F11”退出游戏。祝您游戏愉快！", "提莫大作战", JOptionPane.YES_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, null, null);
        }
        if (e.getSource() == btn3) {
            System.exit(0);// 退出程序
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
