package src.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Exception.*;

public class MainWindow extends JFrame implements ActionListener {

    private JPanel currentPanel;
    private JButton saleResumeButton, customerSaleDetails, productSaleDetails;

    public MainWindow() {
        super("Gestionnaire de magasins de ski");
        this.currentPanel = new WelcomePanel();
        this.add(currentPanel);

        JMenu crud = new JMenu("CRUD");
        JMenuItem newClient = new JMenuItem("Ajout d'un client");
        JMenuItem clientModification = new JMenuItem("Modification d'un client");
        JMenuItem clientListening = new JMenuItem("Listing des Clients");
        JMenuItem clientElimination = new JMenuItem("Suppression d'un client");

        newClient.addActionListener(this);
        clientModification.addActionListener(this);
        clientElimination.addActionListener(this);
        clientListening.addActionListener(this);

        crud.add(newClient);
        crud.add(clientModification);
        crud.add(clientElimination);
        crud.add(clientListening);

        JMenu search = new JMenu("Recherches");
        JMenuItem customerSaleDetails = new JMenuItem("Ventes du client");
        JMenuItem diet = new JMenuItem("Statistiques mensuelles du type de produit");
        JMenuItem recipe = new JMenuItem("Ventes journalière");

        search.addActionListener(this);
        customerSaleDetails.addActionListener(this);
        diet.addActionListener(this);
        recipe.addActionListener(this);

        search.add(customerSaleDetails);
        search.add(diet);
        search.add(recipe);

        JMenu jobTask = new JMenu("Tache Metier");
        JMenuItem fidelity = new JMenuItem("Ajouter des promotions");

        jobTask.addActionListener(this);
        fidelity.addActionListener(this);

        jobTask.add(fidelity);

        JMenu vegetableThread = new JMenu("Thread");
        JMenuItem vegetableMoving = new JMenuItem("Animation promos");

        vegetableThread.addActionListener(this);
        vegetableMoving.addActionListener(this);

        vegetableThread.add(vegetableMoving);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(crud);
        menuBar.add(search);
        menuBar.add(jobTask);
        menuBar.add(vegetableThread);

        this.setJMenuBar(menuBar);
        setVisible(true);
    }

    private JPanel gePanel(String name) throws ProductException {
        switch (name) {
            case "Ajout d'un client" -> {
                 return new AddCustomerPanel();
            }
            case "Modification d'un client" -> {
                 return new ModifyCustomerListPanel();
            }
            case "Listing des Clients" -> {
                 return new ListingCustomerPanel();
            }
            case "Suppression d'un client" -> {
                return new DeleteCustomerPanel();
            }
            case "Ventes du client" -> {
                return new ClientPurchasePanel();
            }
            case "Statistiques mensuelles du type de produit" -> {
                return new MonthlySaleStatsPanel();
            }
            case "Ventes journalière" -> {
                return new ListingSalePanel();
            }
            case "Ajouter des promotions" -> {
                return new PromotionPanel(null);
            }
            case "Animation promos" -> {
                return new PromotionScrollPanel();
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        try {
            currentPanel = this.gePanel(action);
        } catch (ProductException e) {
            throw new RuntimeException(e);
        }
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
    }
}
