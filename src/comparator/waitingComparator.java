package comparator;

import button.button;

import java.util.Comparator;

public class waitingComparator implements Comparator<button> {


    @Override
    public int compare(button o1, button o2) {

        return o1.getPriority() - o2.getPriority();
    }
}

