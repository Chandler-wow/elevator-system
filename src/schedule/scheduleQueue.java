package schedule;

import button.button;
import comparator.executedComparator;
import comparator.waitingComparator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class scheduleQueue {
    private Queue<button> waitQ;
    private Queue<button> execQ;
    private executedComparator comp;//每次都要修改floor

    public scheduleQueue(int floor) {
        comp = new executedComparator(floor);
        waitQ = new PriorityQueue<>(new waitingComparator());
        execQ = new PriorityQueue<>(comp);
    }

    public Queue<button> getWaitQ() {
        return waitQ;
    }

    public void setWaitQ(Queue<button> waitQ) {
        this.waitQ = waitQ;
    }

    public Queue<button> getExecQ() {
        return execQ;
    }

    public void setExecQ(Queue<button> execQ) {
        this.execQ = execQ;
    }

    public executedComparator getComp() {
        return comp;
    }

    public void setComp(int floor) {
        this.comp.setFloor(floor);
    }

    public boolean isExecQEmpty() {
        return execQ.isEmpty();
    }

    public boolean isWaitQEmpty() {
        return waitQ.isEmpty();
    }

    public button execQPeek() {
        return execQ.peek();
    }

    public button execQRemove() {
        return execQ.remove();
    }

    public void waitQADD(button b) {
        waitQ.add(b);
    }

    public int getExecHeadFloor() {
        try {
            return ((button) execQ.toArray()[execQ.size() - 1]).getFloor();
        } catch (Exception e) {
            return 0;
        }
    }

    public Queue<String> getWaitNameCollection() {
        Queue<String> waitQName = new LinkedList<>();
        for (button i : waitQ) {
            waitQName.add(i.getName());
        }
        return waitQName;
    }

    public Queue<String> getExecNameCollection() {
        Queue<String> execQName = new LinkedList<>();
        for (button i : execQ) {
            execQName.add(i.getName());
        }
        return execQName;
    }

    public synchronized void execute(int currentFloor) {
        if (!waitQ.isEmpty()) {
            button head = waitQ.remove();
            int headFloor = head.getFloor();
            var sourceArray = waitQ.toArray();

            this.execQ.add(head);
            while (!waitQ.isEmpty()) waitQ.remove();
            waitQ2execQ(headFloor, currentFloor, sourceArray);
            System.out.println("EXECUTE");
        }
    }

    public synchronized void executeFlush(int currentFloor) {
        if ((!waitQ.isEmpty()) && (!execQ.isEmpty())) {
            int headFloor = ((button) execQ.toArray()[execQ.size() - 1]).getFloor();
            var sourceArray = waitQ.toArray();
            while (!waitQ.isEmpty()) waitQ.remove();
            waitQ2execQ(headFloor, currentFloor, sourceArray);
            System.out.println("EXECUTE_FLUSH");
        }
    }

    public synchronized void waitQ2execQ(int headFloor, int currentFloor, Object[] sourceArray) {
        for (Object i : sourceArray) {
            button temp = (button) i;
            int elevatorLabel_ = temp.getElevatorLabel();
            int floor_ = temp.getFloor();
            int isSameDireOut = (headFloor - currentFloor) * floor_;
            int isSameDireIn = (headFloor - floor_) * (floor_ - currentFloor);
            // 考虑方向
            if (((elevatorLabel_ == -1) && (isSameDireOut > 0)) || ((elevatorLabel_ != -1) && (isSameDireIn >= 0))) {
                execQ.add(temp);
//                System.out.println("HeadFloor:" + headFloor + "currentFloor:" + currentFloor + " isMid:" + isMid + " isSamDire:" + isSameDire + " 电梯：" + elevatorLabel_ + " 按钮：" + temp.getName() + " join exec");
            } else {
                waitQ.add(temp);
//                System.out.println("HeadFloor:" + headFloor + "currentFloor:" + currentFloor + " isMid:" + isMid + " isSamDire:" + isSameDire + "电梯：" + elevatorLabel_ + " 按钮：" + temp.getName() + " stay wait");
            }
        }
    }

    public synchronized boolean addexecQ(int currentFloor, button temp) {
        if (execQ.isEmpty()) return false;
        int headFloor = execQ.peek().getFloor();
        int elevatorLabel_ = temp.getElevatorLabel();
        int floor_ = temp.getFloor();
        int isMid = (headFloor - Math.abs(floor_)) * (Math.abs(floor_) - currentFloor);
        int isSameDire = (headFloor - currentFloor) * floor_;
        // 考虑方向
        if (((elevatorLabel_ == -1) && (isSameDire > 0 && isMid >= 0)) || ((elevatorLabel_ != -1) && (isMid >= 0))) {
            execQ.add(temp);
            System.out.println("HeadFloor:" + headFloor + "currentFloor:" + currentFloor + " isMid:" + isMid + " isSamDire:" + isSameDire + " 电梯：" + elevatorLabel_ + " 按钮：" + temp.getName() + " join exec");
            return true;
        }
        return false;

    }

}
