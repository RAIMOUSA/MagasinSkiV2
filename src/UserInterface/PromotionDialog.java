package UserInterface;

import Controller.ProductController;
import Model.Product;

import javax.swing.*;
import java.awt.*;

public class PromotionDialog extends JDialog {
    private Product product;
    private ProductController productController;

    public PromotionDialog(Frame owner, Product product) {
        super(owner, "Appliquer une promotion", true);
        this.product = product;
        this.productController = new ProductController();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());


        JLabel productLabel = new JLabel("Appliquer une promotion pour: " + product.getName());
        add(productLabel, BorderLayout.NORTH);


        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.add(new JLabel("Pourcentage de réduction:"));
        JTextField discountField = new JTextField();
        formPanel.add(discountField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {

            int discount = Integer.parseInt(discountField.getText());
            try {
                productController.applyDiscount(product, discount);
                JOptionPane.showMessageDialog(this, "Promotion appliquée avec succès");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de l'application de la promotion: " + ex.getMessage());
            }
            dispose();
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }
}
