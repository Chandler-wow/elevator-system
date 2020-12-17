package frame;

import button.button;
import button.floorButtons;
import comparator.executedComparator;
import comparator.waitingComparator;
import elavator.anElevator;
import schedule.scheduleQueue;
import schedule.scheduling;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


public class setFrame extends JFrame {
    //动态列表

    private JMenuBar jmb = new JMenuBar();
    private JMenu jm = new JMenu("Menu");
    private JMenuItem jmi = new JMenuItem("About");

    private JLabel jl_1F;//各楼层数字-1
    private JLabel jl_2F;//各楼层数字-2
    private JLabel jl_3F;//各楼层数字-3
    private JLabel jl_4F;//各楼层数字-4
    private JLabel jl_5F;//各楼层数字-5
    private JLabel jl_6F;//各楼层数字-6
    private JLabel jl_7F;//各楼层数字-6
    private JLabel jl_8F;//各楼层数字-6

    private JLabel elevatorLabel1;//显示牌
    private JLabel elevatorLabel2;//显示牌
    private JLabel elevatorLabel3;//显示牌

    private JLabel jl_wait_1;
    private JLabel jl_exe_1;
    private JLabel jl_wait_2;
    private JLabel jl_exe_2;
    private JLabel jl_wait_3;
    private JLabel jl_exe_3;


    private JLabel floorLabel1;//显示牌
    private JLabel floorLabel2;//显示牌
    private JLabel floorLabel3;//显示牌

    //创建一个电梯
    private anElevator lift1;
    private anElevator lift2;
    private anElevator lift3;
    //队列
    private Map<Integer, button> floorUpButtons;
    private Map<Integer, button> floorDownButtons;


    private void init() throws IOException {
        Random r = new Random();
//        lift1 = new anElevator(1, r.nextInt(8)+1);
//        lift2 = new anElevator(1, r.nextInt(8)+1);
//        lift3 = new anElevator(1, r.nextInt(8)+1);
        lift1 = new anElevator(1, 1);
        lift2 = new anElevator(1, 1);
        lift3 = new anElevator(1, 1);
        //窗口设置
        JPanel jl = new mainJPanel();
        jl.setLayout(null);
        jl.setOpaque(true);
        jl.setBounds(0,0,950,700);
        Color color0 = new Color(241, 231,214);
        jl.setBackground(color0);
//        ImageIcon icon = new ImageIcon("src/image/background7.jpg");
//        jl.setIcon(icon);



        setTitle("Elevator");
        setSize(950, 700);
        setLayout(null);
        setResizable(false);

        jm.add(jmi);
        jmb.add(jm);
        jmb.setBounds(0,0,950,20);
        jl.add(jmb);

        jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null,"版权所有：\n第十六组：\n汪嘉富 ， 颜浩楠 ， 胡言数 ，李桐凤 ， 张鹤宁  " );
                try {
                    new aboutFrame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        lift1.listWait.setBounds(490,150,60,130);
        lift1.listExecute.setBounds(560,150,60,130);
        jl.add(lift1.listWait);
        jl.add(lift1.listExecute);
        lift2.listWait.setBounds(640,150,60,130);
        lift2.listExecute.setBounds(710,150,60,130);
        jl.add(lift2.listWait);
        jl.add(lift2.listExecute);
        lift3.listWait.setBounds(790,150,60,130);
        lift3.listExecute.setBounds(860,150,60,130);
        jl.add(lift3.listWait);
        jl.add(lift3.listExecute);
        //  按钮
        floorUpButtons = floorButtons.floorUpButtons;
        floorDownButtons = floorButtons.floorDownButtons;

        //各楼层数字
        jl_1F = new JLabel(" 1");
        jl_1F.setBounds(70, 580, 20, 80);
        jl_1F.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_1F.setBorder(BorderFactory.createLineBorder(Color.gray));
        jl.add(jl_1F);
        jl_2F = new JLabel(" 2");
        jl_2F.setBounds(70, 500, 20, 80);
        jl_2F.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_2F.setBorder(BorderFactory.createLineBorder(Color.gray));
        jl.add(jl_2F);
        jl_3F = new JLabel(" 3");
        jl_3F.setBounds(70, 420, 20, 80);
        jl_3F.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_3F.setBorder(BorderFactory.createLineBorder(Color.gray));
        jl.add(jl_3F);
        jl_4F = new JLabel(" 4");
        jl_4F.setBounds(70, 340, 20, 80);
        jl_4F.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_4F.setBorder(BorderFactory.createLineBorder(Color.gray));
        jl.add(jl_4F);
        jl_5F = new JLabel(" 5");
        jl_5F.setBounds(70, 260, 20, 80);
        jl_5F.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_5F.setBorder(BorderFactory.createLineBorder(Color.gray));
        jl.add(jl_5F);
        jl_6F = new JLabel(" 6");
        jl_6F.setBounds(70, 180, 20, 80);
        jl_6F.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_6F.setBorder(BorderFactory.createLineBorder(Color.gray));
        jl.add(jl_6F);
        jl_7F = new JLabel(" 7");
        jl_7F.setBounds(70, 100, 20, 80);
        jl_7F.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_7F.setBorder(BorderFactory.createLineBorder(Color.gray));
        jl.add(jl_7F);
        jl_8F = new JLabel(" 8");
        jl_8F.setBounds(70, 20, 20, 80);
        jl_8F.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_8F.setBorder(BorderFactory.createLineBorder(Color.gray));
        jl.add(jl_8F);

        //各层楼梯按钮设置
        for(int i = 1; i <=floorButtons.PEAK_FLOOR;i++){
            button b_down = floorDownButtons.get(i);
            button b_up = floorUpButtons.get(i);
            b_down.setBounds(20,700-80*i,50,40);
            b_up.setBounds(20,660-80*i,50,40);
            button e1 = lift1.getElevatorButtons().get(i);
            e1.setBounds(500+60*((i-1)%2),350+60*((i-1)/2),60,60);
            button e2 = lift2.getElevatorButtons().get(i);
            e2.setBounds(650+60*((i-1)%2),350+60*((i-1)/2),60,60);
            button e3 = lift3.getElevatorButtons().get(i);
            e3.setBounds(800+60*((i-1)%2),350+60*((i-1)/2),60,60);
            jl.add(b_up);
            jl.add(b_down);
            jl.add(e1);
            jl.add(e2);
            jl.add(e3);
        }
        floorDownButtons.get(1).setEnabled(false);
        floorUpButtons.get(floorButtons.PEAK_FLOOR).setEnabled(false);

        //电梯编号
        elevatorLabel1 = new JLabel("ElevatorID：1",JLabel.CENTER);
        elevatorLabel1.setBounds(490,30,130,60);
        elevatorLabel1.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 18));
        elevatorLabel1.setForeground(Color.WHITE);
        elevatorLabel1.setOpaque(true);
        Color color1 = new Color(116,64,76);
        elevatorLabel1.setBackground(color1);
        jl.add(elevatorLabel1);

        elevatorLabel2 = new JLabel("ElevatorID：2",JLabel.CENTER);
        elevatorLabel2.setBounds(640,30,130,60);
        elevatorLabel2.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 18));
        elevatorLabel2.setForeground(Color.WHITE);
        elevatorLabel2.setOpaque(true);
        Color color2 = new Color(203,99,71);
        elevatorLabel2.setBackground(color2);
        jl.add(elevatorLabel2);

        elevatorLabel3 = new JLabel("ElevatorID：3",JLabel.CENTER);
        elevatorLabel3.setBounds(790,30,130,60);
        elevatorLabel3.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 18));
        elevatorLabel3.setForeground(Color.WHITE);
        elevatorLabel3.setOpaque(true);
        Color color3 = new Color(9,93,106);
        elevatorLabel3.setBackground(color3);
        jl.add(elevatorLabel3);

        //等待执行标签
        jl_wait_1 = new JLabel("wait",JLabel.CENTER);
        jl_wait_1.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_exe_1 = new JLabel("execute",JLabel.CENTER);
        jl_exe_1.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_wait_1.setBorder(BorderFactory.createLineBorder(color1));
        jl_wait_1.setBounds(490,100,60,40);
        jl_exe_1.setBorder(BorderFactory.createLineBorder(color1));
        jl_exe_1.setBounds(560,100,60,40);
        jl.add(jl_wait_1);
        jl.add(jl_exe_1);

        jl_wait_2 = new JLabel("wait",JLabel.CENTER);
        jl_wait_2.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_exe_2 = new JLabel("execute",JLabel.CENTER);
        jl_exe_2.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_wait_2.setBorder(BorderFactory.createLineBorder(color2));
        jl_wait_2.setBounds(640,100,60,40);
        jl_exe_2.setBorder(BorderFactory.createLineBorder(color2));
        jl_exe_2.setBounds(710,100,60,40);
        jl.add(jl_wait_2);
        jl.add(jl_exe_2);

        jl_wait_3 = new JLabel("wait",JLabel.CENTER);
        jl_wait_3.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_exe_3 = new JLabel("execute",JLabel.CENTER);
        jl_exe_3.setFont(new Font("Microsoft YaHei UI\n\n",Font.BOLD, 12));
        jl_wait_3.setBorder(BorderFactory.createLineBorder(color3));
        jl_wait_3.setBounds(790,100,60,40);
        jl_exe_3.setBorder(BorderFactory.createLineBorder(color3));
        jl_exe_3.setBounds(860,100,60,40);
        jl.add(jl_wait_3);
        jl.add(jl_exe_3);

        //目前所在层数
        floorLabel1 = new JLabel("FLOOR：" + lift1.getFloor(), JLabel.CENTER);
        floorLabel1.setForeground(color1);
        floorLabel1.setFont(new  Font("Microsoft YaHei UI",Font.BOLD,18));
        floorLabel1.setBounds(500, 290, 120, 60);
        floorLabel1.setBorder(BorderFactory.createLineBorder(color0));
        jl.add(floorLabel1);
        floorLabel2 = new JLabel("FLOOR：" + lift2.getFloor(), JLabel.CENTER);
        floorLabel2.setForeground(color2);
        floorLabel2.setFont(new  Font("Microsoft YaHei UI",Font.BOLD,18));
        floorLabel2.setBounds(650, 290, 120, 60);
        floorLabel2.setBorder(BorderFactory.createLineBorder(color0));
        jl.add(floorLabel2);
        floorLabel3 = new JLabel("FLOOR：" + lift1.getFloor(), JLabel.CENTER);
        floorLabel3.setForeground(color3);
        floorLabel3.setFont(new  Font("Microsoft YaHei UI",Font.BOLD,18));
        floorLabel3.setBounds(800, 290, 120, 60);
        floorLabel3.setBorder(BorderFactory.createLineBorder(color0));
        jl.add(floorLabel3);
        //创建电梯
        JPanel liftPanel1 = lift1.getLp();
        liftPanel1.setBounds(90, 20, 110, 640);
        liftPanel1.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        jl.add(liftPanel1);
        JPanel liftPanel2 = lift2.getLp();
        liftPanel2.setBounds(210, 20, 110, 640);
        liftPanel2.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        jl.add(liftPanel2);
        JPanel liftPanel3 = lift3.getLp();
        liftPanel3.setBounds(330, 20, 110, 640);
        liftPanel3.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        jl.add(liftPanel3);

        setVisible(true);
        add(jl);

        Runnable r1 = new scheduling(lift1,liftPanel1,floorLabel1);
        Thread t1 = new Thread(r1);
        t1.start();
        Runnable r2 = new scheduling(lift2,liftPanel2,floorLabel2);
        Thread t2 = new Thread(r2);
        t2.start();
        Runnable r3 = new scheduling(lift3,liftPanel3,floorLabel3);
        Thread t3 = new Thread(r3);
        t3.start();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public setFrame() throws IOException {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ActionListener act = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button actionButton = (button) e.getSource();
                if(!actionButton.isClick()){
                    actionButton.click();
                   distribution(actionButton);
                }
            }

        };
        for(int i = 1; i <=floorButtons.PEAK_FLOOR;i++){
            floorUpButtons.get(i).addActionListener(act);
            floorDownButtons.get(i).addActionListener(act);

        }


    }

    public void distribution(button actionButton){
        boolean addJudge = lift1.getSchQ().addexecQ(lift1.getFloor(),actionButton)
                || lift2.getSchQ().addexecQ(lift2.getFloor(),actionButton)
                || lift3.getSchQ().addexecQ(lift3.getFloor(),actionButton);
        System.out.println(addJudge);
        if(!addJudge){
            int buttonFloor = Math.abs(actionButton.getFloor());
//            if(lift1.getSchQ().getExecQ().isEmpty()){
//                lift1.getSchQ().waitQADD(actionButton);
//            }else if(lift2.getSchQ().getExecQ().isEmpty()){
//                lift2.getSchQ().waitQADD(actionButton);
//            }else if(lift3.getSchQ().getExecQ().isEmpty()){
//                lift3.getSchQ().waitQADD(actionButton);
//            }else{
                if(compare(lift1,lift2,buttonFloor)){
                    if(compare(lift1,lift3,buttonFloor))
                        lift1.getSchQ().waitQADD(actionButton);
                    else
                        lift3.getSchQ().waitQADD(actionButton);
                }else{
                    if(compare(lift2,lift3,buttonFloor))
                        lift2.getSchQ().waitQADD(actionButton);
                    else
                        lift3.getSchQ().waitQADD(actionButton);
                }
            }

        //}


    }

    public boolean compare(anElevator l1,anElevator l2,int buttonFloor){
        int l1_dis = l1.getDis(buttonFloor);
        int l2_dis = l2.getDis(buttonFloor);

        if(l1_dis==l2_dis){
            int l1_dis_twice = Math.abs(l1.getFloor()-buttonFloor);
            int l2_dis_twice = Math.abs(l2.getFloor()-buttonFloor);
            if(l1_dis_twice<=l2_dis_twice)  return true;
            else return false;
        }else if(l1_dis<l2_dis)  return true;
        else  return false;
    }

}