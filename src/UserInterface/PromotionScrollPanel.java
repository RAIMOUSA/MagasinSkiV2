package UserInterface;

import javax.swing.*;
import java.awt.*;

public class PromotionScrollPanel extends JPanel {
    private JPanel scrollPanel;
    private BackgroundImagePanel backgroundImagePanel;
    private JButton startButton;
    private JButton stopButton;
    private boolean isScrolling;
    private Thread scrollThread;

    public PromotionScrollPanel() {
        setLayout(new BorderLayout());

        // Charger l'image de fond
        ImageIcon icon = new ImageIcon("moutain.jpg");
        backgroundImagePanel = new BackgroundImagePanel(icon.getImage());
        backgroundImagePanel.setLayout(null); // Utiliser un layout null pour placer scrollPanel manuellement

        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
        scrollPanel.setOpaque(false); // Rendre scrollPanel transparent pour voir l'image de fond

        // Ajouter des cartes de promotions
        for (int i = 0; i < 10; i++) {
            JPanel promoCard = createPromoCard("Produit " + (i + 1), (i + 1) * 10 + "% de réduction");
            scrollPanel.add(promoCard);
        }

        // Ajouter scrollPanel au BackgroundImagePanel
        backgroundImagePanel.add(scrollPanel);

        // Positionner scrollPanel
        scrollPanel.setBounds(0, 0, scrollPanel.getPreferredSize().width, 100);

        add(backgroundImagePanel, BorderLayout.CENTER);

        startScrolling();
    }

    private JPanel createPromoCard(String productName, String discount) {
        JPanel promoCard = new JPanel();
        promoCard.setPreferredSize(new Dimension(200, 100));
        promoCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        promoCard.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent pour l'effet visuel
        promoCard.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(productName, SwingConstants.CENTER);
        JLabel discountLabel = new JLabel(discount, SwingConstants.CENTER);
        discountLabel.setFont(new Font("Serif", Font.BOLD, 20));

        promoCard.add(nameLabel, BorderLayout.NORTH);
        promoCard.add(discountLabel, BorderLayout.CENTER);

        return promoCard;
    }

    private void startScrolling() {
        if (!isScrolling) {
            isScrolling = true;
            scrollThread = new Thread(() -> {
                while (isScrolling) {
                    SwingUtilities.invokeLater(() -> {
                        Point location = scrollPanel.getLocation();
                        location.x -= 1;
                        if (location.x + scrollPanel.getWidth() < 0) {
                            location.x = getWidth();
                        }
                        scrollPanel.setLocation(location);
                    });

                    try {
                        Thread.sleep(10); // Vitesse du défilement
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            scrollThread.start();
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Promotion Scrolling Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new PromotionScrollPanel());
        frame.setVisible(true);
    }
}
