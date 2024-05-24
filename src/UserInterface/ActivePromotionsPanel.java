package UserInterface;

import Controller.ProductController;
import Model.Product;
import Exception.*;
import javax.swing.*;
import java.awt.*;

public class ActivePromotionsPanel extends JPanel {
    private ActivePromotionsModel promotionsModel;
    private JTable promotionsTable;
    private ProductController productController;
    private JTable clientTable;

    public ActivePromotionsPanel(JTable clientTable) {
        setLayout(new BorderLayout());
        promotionsModel = new ActivePromotionsModel();
        promotionsTable = new JTable(promotionsModel);
        this.clientTable = clientTable;

        JScrollPane scrollPane = new JScrollPane(promotionsTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton deletePromotion = new JButton("Supprimer la promotion");
        deletePromotion.addActionListener(e -> {
            try {
                deleteSelectedPromotion();
            } catch (ProductException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(deletePromotion);

        add(buttonPanel, BorderLayout.SOUTH);

        productController = new ProductController(); // Initialize the productController here
    }

    private void deleteSelectedPromotion() throws ProductException {
        int selectedRow = promotionsTable.getSelectedRow();

        if (selectedRow != -1) {
            int productId = (int) promotionsModel.getValueAt(selectedRow, 0);
            productController.removePromotion(productId);
            promotionsModel.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Promotion supprimée avec succès.");
            refreshData();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une promotion à supprimer.");
        }
    }

    public void refreshData() {
        try {
            clientTable.setModel(new ListingProductsModel());
        } catch (ProductException ex) {
            throw new RuntimeException(ex);
        }
    }
}
