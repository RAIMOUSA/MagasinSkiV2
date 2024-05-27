package UserInterface;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel() {
        this.setLayout(new BorderLayout());

        JLabel textLabel = new JLabel("<html><h1>Bienvenue dans notre gestionnaire de magasins de ski !</h1></html>");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.CENTER);

        this.add(textPanel, BorderLayout.NORTH);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("product11.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        this.add(imageLabel, BorderLayout.CENTER);
    }
}
