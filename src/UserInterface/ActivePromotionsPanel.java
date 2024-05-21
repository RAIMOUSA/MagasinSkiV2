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

    public ActivePromotionsPanel() {
        setLayout(new BorderLayout());
        promotionsModel = new ActivePromotionsModel();
        promotionsTable = new JTable(promotionsModel);

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
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une promotion à supprimer.");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Active Promotions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new ActivePromotionsPanel());
        frame.setVisible(true);
    }
}
