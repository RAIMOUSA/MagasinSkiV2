package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.ProductController;
import Exception.ProductException;
import Model.Product;

public class PromotionPanel extends JPanel {
    private JTextField searchField;
    private JTable productTable;
    private ListingProductsModel productModel;
    private JButton applyPromotionButton;
    private JButton viewActivePromotionsButton;


    public PromotionPanel() throws ProductException {
        setLayout(new BorderLayout());

        productModel = new ListingProductsModel();

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Rechercher par nom:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Rechercher");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                filterProductsByName(searchTerm);
            }
        });
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        viewActivePromotionsButton = new JButton("Voir les promos actives");
        viewActivePromotionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showActivePromotions();
            }
        });

        productTable = new JTable(productModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        applyPromotionButton = new JButton("Appliquer une promotion");
        applyPromotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    Product selectedProduct = productModel.getRow(selectedRow);
                    showPromotionDialog(selectedProduct);
                } else {
                    JOptionPane.showMessageDialog(PromotionPanel.this, "Veuillez sélectionner un produit.");
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(applyPromotionButton);
        buttonPanel.add(viewActivePromotionsButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void filterProductsByName(String name) {
        productModel.filterProductsByName(name);
    }

    private void showPromotionDialog(Product product) {
        PromotionDialog dialog = new PromotionDialog((Frame) SwingUtilities.getWindowAncestor(this), product, productTable);
        dialog.setVisible(true);
    }

    private void showActivePromotions() {
        JFrame frame = new JFrame("Promotions Actives");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ActivePromotionsPanel(productTable));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
