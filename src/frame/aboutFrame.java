package frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class aboutFrame extends JFrame {
    private aboutJPanel jp;


    public aboutFrame() throws IOException {
        setTitle("关于");
        this.setBounds(300,200,410,290);
        //setSize(410, 290);
        setLayout(null);
        setResizable(false);

        jp = new aboutJPanel();

        jp.setBounds(0,0,420,290);
        jp.setBackground(Color.white);
        add(jp);

        setVisible(true);


    }
}
