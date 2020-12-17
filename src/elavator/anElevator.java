package elavator;


import button.button;
import button.floorButtons;
import frame.LiftPanel;
import schedule.scheduleQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static button.floorButtons.PEAK_FLOOR;
import static java.lang.Thread.sleep;

public class anElevator {
    private final int elevatorLabel;
    private int floor;
    private Map<Integer, button> elevatorButtons;
    private int num;
    private static int maxNum = 9;
    private scheduleQueue schQ;
    public DefaultListModel<String> dlWait;  //3
    public JList<String> listWait;  //3
    public DefaultListModel<String> dlexecute;  //3
    public JList<String> listExecute;  //3
    boolean isFirstStay = false;
    private LiftPanel lp;

    public LiftPanel getLp() {
        return lp;
    }

    public scheduleQueue getSchQ() {
        return schQ;
    }

    public int getNum() { return this.num; }

    public anElevator(int elevatorLabel, int floor) throws IOException {
        this.num = 0;
        this.elevatorLabel = elevatorLabel;
        elevatorButtons = getButtons();
        lp = new LiftPanel(this);
        if ((floor < 0) || (floor > PEAK_FLOOR)) {
            Random r = new Random();
            this.floor = r.nextInt(PEAK_FLOOR + 1);
        } else {
            this.floor = floor;
        }
        schQ = new scheduleQueue(this.floor);
        dlWait = new DefaultListModel<String>();  //3
        listWait = new JList<String>(dlWait);  //3
        dlexecute = new DefaultListModel<String>();  //3
        listExecute = new JList<String>(dlexecute);  //3
        listWait.setOpaque(false);
        listExecute.setOpaque(false);
        listWait.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        listExecute.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        ActionListener act = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button actionButton = (button)e.getSource();
                if(num==0){}
                else if(!actionButton.isClick()){
                    actionButton.click();
                    schQ.waitQADD((button) e.getSource());
                    /*
                    for(button each:queue.getWait_q()){
                        System.out.println("Wait_q:"+each.getFloor());
                    }
                    for(button each:queue.getSource()){
                        System.out.println("s:"+each.getFloor());
                    }
                     */
                }
            }
        };

        for(int i = 1; i <= floorButtons.PEAK_FLOOR; i++){
            elevatorButtons.get(i).addActionListener(act);
        }
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Map<Integer, button> getElevatorButtons() {
        return this.elevatorButtons;
    }

    private Map<Integer, button> getButtons() {
        var al = new HashMap<Integer, button>();
        for (int i = 1; i <= PEAK_FLOOR; i++) {
            al.put(i, new button(this.elevatorLabel, i, Integer.toString(i),i+"L",-1));
            al.get(i).setFont(new Font("楷体",Font.BOLD,35));
            al.get(i).setForeground(Color.LIGHT_GRAY);
        }
        return al;
    }

    public void move(Component component, JLabel floorLabel, scheduleQueue scheQ) throws InterruptedException {
        button temp;
        dlWait.addAll(scheQ.getWaitNameCollection());
        dlexecute.addAll(scheQ.getExecNameCollection());
        listWait.repaint();
        listExecute.repaint();
        while (!scheQ.isExecQEmpty()) {

            temp = scheQ.execQPeek();
            isFirstStay = true;
            int label = Math.abs(temp.getFloor()) - this.floor;
            if (label == 0) {
                //到达remove()
                button b = scheQ.execQRemove();
                b.unclick();
                if(isFirstStay) {
                    openDoor(b);
                    isFirstStay = false;
                    this.moving(moveElevator.STAY,b.getLoc());

                }


            } else if (label > 0) {
                isFirstStay = true;
                this.moving(moveElevator.UP,0);
            } else {
                isFirstStay = true;
                this.moving(moveElevator.DOWN,0);
            }
            scheQ.executeFlush(floor);
            scheQ.setComp(floor);
            dlWait.removeAllElements();
            dlexecute.removeAllElements();
            dlWait.addAll(scheQ.getWaitNameCollection());
            dlexecute.addAll(scheQ.getExecNameCollection());
            if(dlWait.isEmpty()){
                dlWait.add(0,"NULL");
            }
            if(dlexecute.isEmpty()){
                dlexecute.add(0,"NULL");
            }
            component.repaint();
            listWait.repaint();
            listExecute.repaint();

            floorLabel.setText("FLOOR：" + this.floor);
//            System.out.println("HeadFloor:"+scheQ.getExecHeadFloor()+"currentFloor:"+floor+" 电梯："+elevatorLabel+" 按钮："+temp.getName());
            sleep(1000);
        }
    }

    public synchronized void moving(moveElevator m,int loc) {
        switch (m) {
            case UP:
                floor++;
                break;

            case DOWN:
                floor--;
                break;
            case STAY: {
                Random r = new Random();
                if(loc==0){
                    break;
                }else if(loc ==1){
                    int num_up = r.nextInt(maxNum-num+1);
                    this.num += num_up;
                }else if(loc==-1){
                    int num_down = r.nextInt(this.num+1);
                    this.num -= num_down;
                }
            }
        }
    }

    public void openDoor(button b) throws InterruptedException {
        if(b.getLoc() ==1){
            String direction = b.getText();
            if(direction.equals("上")){
                for(int i = b.getFloor();i>0;i--){
                    elevatorButtons.get(i).click_not_change();
                }

            }else{
                for(int i = Math.abs(b.getFloor());i<= PEAK_FLOOR;i++){
                    elevatorButtons.get(i).click_not_change();
                }
            }
        }
        Graphics g = this.lp.getGraphics();
        for(int i = 1 ; i < 9 ; i++){
//            g.setColor(Color.WHITE);
//            g.fillRect(1,640-80*this.floor,170,80);
            g.drawImage(lp.image,0,650-80*this.floor,109,70,lp);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(1,650-80*this.floor,49-i*5,70);
            g.fillRect(60+5*i,650-80*this.floor,49-i*5,70);
            g.setColor(Color.black);
            g.fillRect(1,640-80*this.floor,108,10);
            g.setColor(Color.RED);
            g.setFont(new Font("Microsoft YaHei UI",Font.BOLD, 15));
            g.drawString(Integer.valueOf(this.num).toString(),53,650-80*this.floor);
            sleep(200);
        }
       Thread.sleep(1000);
        for(int i = 1 ; i < 9 ; i++){
//            g.setColor(Color.WHITE);
//            g.fillRect(1,640-80*this.floor,170,80);
            g.drawImage(lp.image,0,650-80*this.floor,109,70,lp);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(1,650-80*this.floor,9+i*5,70);
            g.fillRect(100-5*i,650-80*this.floor,9+i*5,70);
            g.setColor(Color.black);
            g.fillRect(1,640-80*this.floor,108,10);
            g.setColor(Color.RED);
            g.setFont(new Font("Microsoft YaHei UI",Font.BOLD, 15));
            g.drawString(Integer.valueOf(this.num).toString(),53,650-80*this.floor);
            sleep(200);
        }
        if(b.getLoc() ==1){
            String direction = b.getText();
            if(direction.equals("上")){
                for (int i = b.getFloor(); i > 0; i--) {
                    elevatorButtons.get(i).unclick();
                }

            }else {
                for(int i = Math.abs(b.getFloor());i<= PEAK_FLOOR;i++){
                    elevatorButtons.get(i).unclick();
                }
            }
        }

    }

    public int getDis(int buttonFloor){
        if(!this.schQ.getExecQ().isEmpty()){
            return Math.abs(buttonFloor-Math.abs(schQ.getExecQ().peek().getFloor()));
        }else{
            return Math.abs(buttonFloor-this.floor);
        }
    }

}
