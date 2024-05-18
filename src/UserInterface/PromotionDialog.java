package UserInterface;

import javax.swing.*;
import java.awt.*;
import Model.Product;

public class PromotionDialog extends JDialog {
    private Product product;

    public PromotionDialog(Frame owner, Product product) {
        super(owner, "Appliquer une promotion", true);
        this.product = product;
        initComponents();
    }

    private void initComponents() {
        // Implémenter l'interface utilisateur pour appliquer une promotion
        // Utilisez les informations du produit pour configurer les champs de la boîte de dialogue
        setLayout(new BorderLayout());

        // Exemple d'utilisation du produit pour remplir un label
        JLabel productLabel = new JLabel("Appliquer une promotion pour: " + product.getName());
        add(productLabel, BorderLayout.NORTH);

        // Ajouter des champs pour la promotion (ex. pourcentage de réduction)
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.add(new JLabel("Pourcentage de réduction:"));
        JTextField discountField = new JTextField();
        formPanel.add(discountField);

        add(formPanel, BorderLayout.CENTER);

        // Ajouter des boutons OK et Cancel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            // Implémenter la logique pour appliquer la promotion
            // product.applyDiscount(Integer.parseInt(discountField.getText()));
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
