package button;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class floorButtons {
    public static final int PEAK_FLOOR = 8;
    public static Map<Integer, button> floorUpButtons = getButtons(true);
    public static Map<Integer, button> floorDownButtons = getButtons(false);

    private static Map<Integer, button> getButtons(boolean up) {
        var al = new HashMap<Integer, button>();
        for (int i = 1; i <= PEAK_FLOOR; i++) {
            if (up) {
                button button1 = new button(-1, i,"上",i+"F"+"_UP",1);
                button1.setFont(new Font("Microsoft YaHei UI",Font.BOLD,12));
                al.put(i,button1);
            } else {
                button button2 = new button(-1, -i,"下",i+"F"+"_DOWN",1);
                button2.setFont(new Font("Microsoft YaHei UI",Font.BOLD,12));
                al.put(i, button2);
            }
        }
        return al;
    }

}
