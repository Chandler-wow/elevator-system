package frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class mainJPanel extends JPanel {
    public Image image;

    public mainJPanel() throws IOException {
        image = ImageIO.read(new File("src/image/background.png"));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.image,0,0,950,700,this);


    }
}
