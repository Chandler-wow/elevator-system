package comparator;

import button.button;

import java.util.Comparator;

public class executedComparator implements Comparator<button> {
    private int floor;

    public executedComparator(int floor) {
        this.floor = floor;
    }

    @Override
    public int compare(button o1, button o2) {
        int abso1Floor = Math.abs(o1.getFloor());
        int abso2Floor = Math.abs(o2.getFloor());
        int minAbsFloor = Math.min(abso1Floor, abso2Floor);
        if (o1.getElevatorLabel() == -1 && o2.getElevatorLabel() == -1 && (minAbsFloor - this.floor) * o1.getFloor() < 0) {
            return Math.abs(floor - Math.abs(o2.getFloor())) - Math.abs(floor - Math.abs(o1.getFloor()));
        }
        return Math.abs(floor - abso1Floor) - Math.abs(floor - abso2Floor);
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

}