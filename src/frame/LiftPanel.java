package frame;

import elavator.anElevator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LiftPanel extends JPanel {
    anElevator lift;
    public Image image;
    public Image backImage;


    public LiftPanel(anElevator lift) throws IOException {
        this.lift = lift;
        this.setOpaque(false);
        this.image = ImageIO.read(new File("src/image/lift2.jpg"));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.fillRect(0,640-80*this.lift.getFloor(),100,80);
        //g.drawImage(this.backImage,0,0,110,640,this);
        g.drawImage(this.image,0,650-80*this.lift.getFloor(),110,70,this);
        g.setColor(Color.black);
        g.fillRect(0,640-80*this.lift.getFloor(),110,80);
        g.setColor(Color.black);
        g.fillRect(0,640-80*this.lift.getFloor(),110,10);
        g.setColor(Color.darkGray);
        g.fillRect(0,650-80*this.lift.getFloor(),53,70);
        g.fillRect(55,650-80*this.lift.getFloor(),53,70);
        g.setColor(Color.RED);
        g.setFont(new Font("Microsoft YaHei UI",Font.BOLD, 15));
        g.drawString(Integer.valueOf(lift.getNum()).toString(),50,650-80*this.lift.getFloor());


    }
}
