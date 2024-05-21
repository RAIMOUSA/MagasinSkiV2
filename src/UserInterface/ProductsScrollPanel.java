package UserInterface;

import javax.swing.*;
import java.awt.*;

public class ProductsScrollPanel extends JPanel {
    private JPanel scrollPanel;
    private BackgroundImagePanel backgroundImagePanel;
    private boolean isScrolling;
    private Thread scrollThread;
    private Thread backgroundThread;

    public ProductsScrollPanel() {
        setLayout(new BorderLayout());

        backgroundImagePanel = new BackgroundImagePanel();
        backgroundImagePanel.setLayout(null); // Utiliser un layout null pour placer scrollPanel manuellement

        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
        scrollPanel.setOpaque(false); // Rendre scrollPanel transparent pour voir l'image de fond

        // Ajouter scrollPanel au BackgroundImagePanel
        backgroundImagePanel.add(scrollPanel);

        // Positionner scrollPanel
        scrollPanel.setBounds(0, 0, scrollPanel.getPreferredSize().width, 200);

        add(backgroundImagePanel, BorderLayout.CENTER);

        startScrolling();
        startBackgroundImageChange();
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

    private void startBackgroundImageChange() {
        backgroundThread = new Thread(() -> {
            String[] images = {
                    "product1.png","product2.png","product3.png","product4.png","product5.png","product6.png","product7.png","product8.png","product9.png","product10.png"
            };
            int index = 0;

            while (true) {
                final int currentIndex = index;
                SwingUtilities.invokeLater(() -> {
                    backgroundImagePanel.setBackgroundImage(new ImageIcon(getClass().getResource(images[currentIndex])).getImage());
                    backgroundImagePanel.repaint();
                });

                index = (index + 1) % images.length;

                try {
                    Thread.sleep(2000); // Changer d'image toutes les 2 secondes
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        backgroundThread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Promotion Scrolling Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new ProductsScrollPanel());
        frame.setVisible(true);
    }
}
