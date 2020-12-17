package button;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class button extends JButton{
    private boolean isClick;
    private int priority;
    private final int floor;
    private final int elevatorLabel;
    private String name;
    private final int loc;                         //1代表电梯外，-1代表电梯内

    public int getLoc() { return loc; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }



    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public button(int elevatorLabel, int floor, String s,String name,int loc) {
        super(s);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.floor = floor;
        this.elevatorLabel = elevatorLabel;
        this.priority = 1;
        this.name = name;
        this.loc = loc;
        if(elevatorLabel == -1){
            this.priority = 2;
        }
        this.isClick = false;
    }

    public button() {
        System.exit(-1);
        this.floor = -1;
        this.elevatorLabel = -1;
        this.priority = 1;
        if(elevatorLabel == -1){
            this.priority = 2;
        }

        loc = 0;
    }



    public boolean isClick() {
        return isClick;
    }

    public String click() {
        this.setOpaque(true);
        this.isClick = true;
        this.setBackground(new Color(236, 74, 74));      //3
        return String.valueOf(floor) + "#" + String.valueOf(elevatorLabel);
    }

    public void click_not_change() {
        this.isClick = true;
    }

    public void unclick() {
        this.isClick = false;
        this.setOpaque(false);
        this.setBackground(new ColorUIResource(238,238,238));//3
    }

    public int getFloor() {
        return floor;
    }

    public int getElevatorLabel() {
        return elevatorLabel;
    }
}
