package frame;

import elavator.anElevator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class aboutJPanel extends JPanel {

    public Image image;

    public aboutJPanel() throws IOException {
        image = ImageIO.read(new File("src/image/timg.jfif"));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.image,0,0,240,250,this);
        Color color = new Color(169, 148, 167);
        g.setColor(color);
        g.setFont(new Font("楷体",Font.BOLD, 25));
        g.drawString("版权所有：",245,30);
        g.drawString("制作人：",245,100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体",Font.BOLD,18));
        g.drawString("软件工程第16组",245,60);

        g.drawString("wjf",280,130);
        g.drawString("yhn",280,150);
        g.drawString("hys",280,170);
        g.drawString("ltf",280,190);
        g.drawString("zhn",280,210);


    }
}
