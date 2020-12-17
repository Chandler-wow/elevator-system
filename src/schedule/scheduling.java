package schedule;

import elavator.anElevator;

import javax.swing.*;
import java.awt.*;


public class scheduling implements Runnable {
    private anElevator lift;
    private scheduleQueue scheQ;
    private Component component1;
    private JLabel floorLabel;

    public scheduling(anElevator lift, Component component1, JLabel floorLabel) {
        this.component1 = component1;
        this.floorLabel = floorLabel;
        this.lift = lift;
        scheQ = lift.getSchQ();
        scheQ.execute(lift.getFloor());

    }

    @Override
    public void run() {
        while (true) {
            //System.out.println(this.queue.getWait_q().isEmpty());
            if (scheQ.isExecQEmpty()) {
                scheQ.execute(lift.getFloor());
            } else {
                try {
                    lift.move(component1,floorLabel,scheQ);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



