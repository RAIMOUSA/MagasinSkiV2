package src.UserInterface;

import javax.swing.*;
import java.awt.*;

public class BackgroundImagePanel extends JPanel {
    private Image backgroundImage;

    public BackgroundImagePanel(Image image) {
        // Chargez l'image depuis les ressources
       backgroundImage = new ImageIcon(getClass().getResource("moutain.jpg")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}