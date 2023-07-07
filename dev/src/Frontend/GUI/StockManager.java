package Frontend.GUI;

import javax.swing.*;

import Backend.ServiceLayer.ServiceFactory;
import Frontend.View.CategoryView;
import Frontend.View.DiscountView;
import Frontend.View.MainView;
import Frontend.View.ManageOrdersView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider.Service;
import java.sql.SQLException;

public class StockManager extends JFrame {
    private JButton loadDataButton;
    private JButton deleteDataButton;
    public StockManager() {
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Stock Manager");

        JPanel panel = new JPanel();
        add(panel);

        JButton stockButton = new JButton("Stock");
        stockButton.setBounds(50, 30, 200, 30);
        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new CategoryView().setVisible(true);
              dispose();
            }
        });
        panel.add(stockButton);

        JButton discountButton = new JButton("Discount");
        discountButton.setBounds(50, 70, 200, 30);
        discountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DiscountView();
            }
        });
        panel.add(discountButton);

        loadDataButton = new JButton("Load Data");
        loadDataButton.setBounds(50, 110, 200, 30);
        loadDataButton.addActionListener(e -> {
            try {
                ServiceFactory.getInstance().LoadData();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panel.add(loadDataButton);

        deleteDataButton = new JButton("Delete Data");
        deleteDataButton.setBounds(50, 150, 200, 30);
        deleteDataButton.addActionListener(e -> {
            try {
                ServiceFactory.getInstance().DeleteData();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        panel.add(deleteDataButton);
        JButton orderButton = new JButton("Order");
        orderButton.setBounds(50, 190, 200, 30);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
                new ManageOrdersView();
            }
        });
        panel.add(orderButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(50, 230, 200, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainView();
            }
        });
        panel.add(exitButton);

        panel.setLayout(null);
        setVisible(true);
    }
}
