package Frontend.SupplierView.SuppInnerViews;

import javax.swing.*;

import com.google.gson.Gson;

import Backend.ServiceLayer.Response;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.SupplierView.Msupp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveSupplierView extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField supplierIDField;
    private JButton removeButton;
    private JButton backButton;

    public RemoveSupplierView() {
        setTitle("Remove Supplier");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createComponents();
        designUI();

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    private void createComponents() {
        titleLabel = new JLabel("Remove Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        supplierIDField = new JTextField();
        removeButton = new JButton("Remove");
        backButton = new JButton("Back");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierID = supplierIDField.getText();

                if (supplierID.isEmpty()) {
                    JOptionPane.showMessageDialog(RemoveSupplierView.this,
                            "Please enter the Supplier ID", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Perform backend function to remove supplier using the supplied ID
                    boolean removed = removeSupplier(supplierID);

                    if (removed) {
                        JOptionPane.showMessageDialog(RemoveSupplierView.this,
                                "Supplier removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(RemoveSupplierView.this,
                                "Supplier not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    clearFields();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the Remove Supplier GUI
                new Msupp(); // Open the Supplier Manager GUI
            }
        });
    }

    private void designUI() {
        mainPanel.setBackground(Color.WHITE);

        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);

        formPanel.add(new JLabel("Supplier ID:"));
        formPanel.add(supplierIDField);

        formPanel.add(new JLabel(""));
        formPanel.add(removeButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
    }

    private void clearFields() {
        supplierIDField.setText("");
    }

    private boolean removeSupplier(String supplierID) {
            String json = ServiceFactory.getInstance().removeSupplier(Integer.parseInt(supplierID));
            Gson gson = new Gson();
            Response res = gson.fromJson(json, Response.class);
            return (res.isError()) ? false : true;
    }
}
