package Frontend.View;

import javax.swing.*;

import Backend.BusinessLayer.Controllers.DataBaseController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

import Frontend.GUI.StockManager;
import Frontend.Model.DiscountModel;

public class DiscountView extends JFrame {
    private DiscountModel discountModel = new DiscountModel();
    private JButton buildProductDiscountButton;
    private JButton buildCategoryDiscountButton;
    private JButton exitButton;
    private JTextArea resultTextArea;

    public DiscountView() {
    
        initializeComponents();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Discount Management");
        setSize(400, 400);
        setVisible(true);
    }

    private void initializeComponents() {
        buildProductDiscountButton = new JButton("Build Product Discount");
        buildCategoryDiscountButton = new JButton("Build Category Discount");
        exitButton = new JButton("Exit");
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 1;
        panel.add(buildProductDiscountButton, gbc);

        gbc.gridy = 2;
        panel.add(buildCategoryDiscountButton, gbc);

        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        buildProductDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int productID = Integer.parseInt(JOptionPane.showInputDialog("Enter the product ID:"));
                    double percent = Double.parseDouble(JOptionPane.showInputDialog("Enter the discount percentage:"));
                    LocalDate startDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the start date (YYYY-MM-DD):"));
                    LocalDate endDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the end date (YYYY-MM-DD):"));

                    String result = discountModel.buildProductDiscount(productID, percent, startDate, endDate);
                    resultTextArea.setText(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                 } }
        });

        buildCategoryDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String category = JOptionPane.showInputDialog("Enter the category name:");
                    double percent = Double.parseDouble(JOptionPane.showInputDialog("Enter the discount percentage:"));
                    LocalDate startDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the start date (YYYY-MM-DD):"));
                    LocalDate endDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the end date (YYYY-MM-DD):"));

                    String result = discountModel.buildCategoryDiscount(category, percent, startDate, endDate);
                    resultTextArea.setText(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StockManager();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DiscountView();
            }
        });
    }
}
