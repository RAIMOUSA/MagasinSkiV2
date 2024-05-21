package UserInterface;

import javax.swing.*;
import java.awt.*;

class BackgroundImagePanel extends JPanel {
    private Image backgroundImage;

    public BackgroundImagePanel() {
        this.backgroundImage = new ImageIcon(getClass().getResource("product1.png")).getImage();
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
