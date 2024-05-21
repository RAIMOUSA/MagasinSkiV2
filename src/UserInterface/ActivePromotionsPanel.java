package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ActivePromotionsPanel extends JPanel {
    private ActivePromotionsModel promotionsModel;
    private JTable promotionsTable;
    private JLabel lastUpdatedLabel;

    public ActivePromotionsPanel() {
        setLayout(new BorderLayout());

        promotionsModel = new ActivePromotionsModel();
        promotionsTable = new JTable(promotionsModel);

        JScrollPane scrollPane = new JScrollPane(promotionsTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refreshButton = new JButton("Mettre à jour");
        refreshButton.addActionListener(e -> updatePromotions());
        buttonPanel.add(refreshButton);

        lastUpdatedLabel = new JLabel("Dernière mise à jour : " + getInitialDate());
        buttonPanel.add(lastUpdatedLabel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updatePromotions() {
        // Ici vous pouvez mettre à jour les données des promotions actives
        List<Object[]> promotionsData = new ArrayList<>();

        promotionsModel.setData(promotionsData);

        updateLastUpdatedLabel(); // Met à jour la date après la mise à jour des données
    }

    private void updateLastUpdatedLabel() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = currentTime.format(formatter);
        lastUpdatedLabel.setText("Dernière mise à jour : " + formattedDateTime);
    }

    private String getInitialDate() {
        // Retourne une date antérieure à afficher initialement
        LocalDateTime initialTime = LocalDateTime.of(2022, 1, 1, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return initialTime.format(formatter);
    }
}
