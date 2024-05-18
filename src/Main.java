package src;

import UserInterface.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(900, 700);
        mainWindow.setVisible(true);
    }
}