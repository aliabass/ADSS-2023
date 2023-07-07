package Frontend.GUI;

import javax.swing.*;

import Backend.ServiceLayer.ServiceFactory;
import Frontend.View.MainView;
import Frontend.View.ReportView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StoreManager extends JFrame {
    private JButton loadDataButton;
    private JButton deleteDataButton;
    public StoreManager() {
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store Manager");

        JPanel panel = new JPanel();
        add(panel);


        panel.setLayout(new GridLayout(2, 1, 10, 10));


        JButton reportButton = new JButton("Report");
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
               new ReportView();
            }
        });
        panel.add(reportButton);

        loadDataButton = new JButton("Load Data");
        deleteDataButton = new JButton("Delete Data");


         deleteDataButton.addActionListener(e -> {
            try {
                ServiceFactory.getInstance().DeleteData();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panel.add(deleteDataButton);
        loadDataButton.addActionListener(e -> {
            try {
                ServiceFactory.getInstance().LoadData();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panel.add(loadDataButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the StoreManager window
                new MainView();
            }
        });
        panel.add(exitButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StoreManager();
            }
        });
    }
}
