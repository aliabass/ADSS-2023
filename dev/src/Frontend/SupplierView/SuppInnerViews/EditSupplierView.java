// package Frontend.SupplierView.InnerViews;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class EditSupplierView extends JFrame {

//     private JPanel mainPanel;
//     private JLabel titleLabel;
//     private JTextField supplierIDField;
//     private JTextField nameField;
//     private JTextField phoneNumberField;
//     private JTextField bankAccountField;
//     private JButton updateNameButton;
//     private JButton updatePhoneNumberButton;
//     private JButton updateBankAccountButton;

//     public EditSupplierView() {
//         setTitle("Edit Supplier");
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         setSize(400, 300);
//         setLocationRelativeTo(null);

//         mainPanel = new JPanel();
//         mainPanel.setLayout(new BorderLayout());

//         createComponents();
//         designUI();

//         getContentPane().add(mainPanel);

//         setVisible(true);
//     }

//     private void createComponents() {
//         titleLabel = new JLabel("Edit Supplier");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//         titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

//         supplierIDField = new JTextField();
//         nameField = new JTextField();
//         phoneNumberField = new JTextField();
//         bankAccountField = new JTextField();

//         updateNameButton = new JButton("Update");
//         updatePhoneNumberButton = new JButton("Update");
//         updateBankAccountButton = new JButton("Update");

//         updateNameButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String supplierID = supplierIDField.getText();
//                 String name = nameField.getText();

//                 if (supplierID.isEmpty() || name.isEmpty()) {
//                     JOptionPane.showMessageDialog(EditSupplierView.this,
//                             "Please fill in the Supplier ID and Name fields", "Error", JOptionPane.ERROR_MESSAGE);
//                 } else {
//                     // Perform backend function to update supplier name using the supplied ID
//                     // Example: updateSupplierName(supplierID, name);

//                     JOptionPane.showMessageDialog(EditSupplierView.this,
//                             "Supplier name updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
//                     //clearFields();
//                 }
//             }
//         });

//         updatePhoneNumberButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String supplierID = supplierIDField.getText();
//                 String phoneNumber = phoneNumberField.getText();

//                 if (supplierID.isEmpty() || phoneNumber.isEmpty()) {
//                     JOptionPane.showMessageDialog(EditSupplierView.this,
//                             "Please fill in the Supplier ID and Phone Number fields", "Error", JOptionPane.ERROR_MESSAGE);
//                 } else {
//                     // Perform backend function to update supplier phone number using the supplied ID
//                     // Example: updateSupplierPhoneNumber(supplierID, phoneNumber);

//                     JOptionPane.showMessageDialog(EditSupplierView.this,
//                             "Supplier phone number updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
//                     //clearFields();
//                 }
//             }
//         });

//         updateBankAccountButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String supplierID = supplierIDField.getText();
//                 String bankAccount = bankAccountField.getText();

//                 if (supplierID.isEmpty() || bankAccount.isEmpty()) {
//                     JOptionPane.showMessageDialog(EditSupplierView.this,
//                             "Please fill in the Supplier ID and Bank Account fields", "Error", JOptionPane.ERROR_MESSAGE);
//                 } else {
//                     // Perform backend function to update supplier bank account using the supplied ID
//                     // Example: updateSupplierBankAccount(supplierID, bankAccount);

//                     JOptionPane.showMessageDialog(EditSupplierView.this,
//                             "Supplier bank account updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
//                     //clearFields();
//                 }
//             }
//         });
//     }

//     private void designUI() {
//         mainPanel.setBackground(Color.WHITE);
//         mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//         JPanel formPanel = new JPanel();
//         formPanel.setLayout(new GridLayout (4, 2, 10, 10));
//         formPanel.setBackground(Color.WHITE);
//         formPanel.add(new JLabel("Supplier ID:"));
//         formPanel.add(supplierIDField);
    
//         formPanel.add(new JLabel("Name:"));
//         formPanel.add(nameField);
//         formPanel.add(new JLabel("")); // Empty label for spacing
    
//         formPanel.add(updateNameButton);
    
//         formPanel.add(new JLabel("Phone Number:"));
//         formPanel.add(phoneNumberField);
//         formPanel.add(new JLabel("")); // Empty label for spacing
    
//         formPanel.add(updatePhoneNumberButton);
    
//         formPanel.add(new JLabel("Bank Account:"));
//         formPanel.add(bankAccountField);
//         formPanel.add(new JLabel("")); // Empty label for spacing
    
//         formPanel.add(updateBankAccountButton);
    
//         mainPanel.add(titleLabel, BorderLayout.NORTH);
//         mainPanel.add(formPanel, BorderLayout.CENTER);
//     }
    
//     private void clearFields() {
//         supplierIDField.setText("");
//         nameField.setText("");
//         phoneNumberField.setText("");
//         bankAccountField.setText("");
//     }
// }
    
package Frontend.SupplierView.SuppInnerViews;

import javax.swing.*;

import com.google.gson.Gson;

import Backend.ServiceLayer.Response;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.SupplierView.Msupp;
import Frontend.SupplierView.SupplierManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditSupplierView extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField supplierIDField;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField bankAccountField;
    private JButton updateNameButton;
    private JButton updatePhoneNumberButton;
    private JButton updateBankAccountButton;
    private JButton backButton;

    public EditSupplierView() {
        setTitle("Edit Supplier");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createComponents();
        designUI();

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    private void createComponents() {
        titleLabel = new JLabel("Edit Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        supplierIDField = new JTextField();
        nameField = new JTextField();
        phoneNumberField = new JTextField();
        bankAccountField = new JTextField();

        updateNameButton = new JButton("Update");
        updatePhoneNumberButton = new JButton("Update");
        updateBankAccountButton = new JButton("Update");
        backButton = new JButton("Back");

        updateNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierID = supplierIDField.getText();
                String name = nameField.getText();

                if (supplierID.isEmpty() || name.isEmpty()) {
                    JOptionPane.showMessageDialog(EditSupplierView.this,
                            "Please fill in the Supplier ID and Name fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean updated_name = update_name(supplierID,name);
                    if(updated_name){
                    JOptionPane.showMessageDialog(EditSupplierView.this,
                            "Supplier name updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }else{JOptionPane.showMessageDialog(EditSupplierView.this,
                        "Error updating name!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    clearFields();
                }
            }
        });

        updatePhoneNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierID = supplierIDField.getText();
                String phoneNumber = phoneNumberField.getText();

                if (supplierID.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(EditSupplierView.this,
                            "Please fill in the Supplier ID and Phone Number fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean updated_phone = update_phone(supplierID,phoneNumber);
                    if(updated_phone){
                    JOptionPane.showMessageDialog(EditSupplierView.this,
                            "Supplier Phone Number updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }else{JOptionPane.showMessageDialog(EditSupplierView.this,
                        "Error updating Phone Number!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    clearFields();
                }
            }
        });

        updateBankAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierID = supplierIDField.getText();
                String bankAccount = bankAccountField.getText();

                if (supplierID.isEmpty() || bankAccount.isEmpty()) {
                    JOptionPane.showMessageDialog(EditSupplierView.this,
                            "Please fill in the Supplier ID and Bank Account fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean updated_bank = update_bank(supplierID,bankAccount);
                    if(updated_bank){
                    JOptionPane.showMessageDialog(EditSupplierView.this,
                            "Supplier Bank Account updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }else{JOptionPane.showMessageDialog(EditSupplierView.this,
                        "Error updating Bank Account!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    clearFields();
                    
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the Edit Supplier GUI
                new Msupp(); // Open the Supplier Manager GUI
            }
        });
    }

    private boolean update_name(String id, String new_name){
        Gson gson = new Gson();
        String json = ServiceFactory.getInstance().editSupplier_name(Integer.parseInt(id), new_name);
        Response res = gson.fromJson(json, Response.class);
        return (res.isError()) ? false : true;
        
    }
    private boolean update_phone(String id, String new_phone){
        Gson gson = new Gson();
        String json = ServiceFactory.getInstance().ChangePhoneNumber(Integer.parseInt(id), new_phone);
        Response res = gson.fromJson(json, Response.class);
        return (res.isError()) ? false : true;
        
    }
    private boolean update_bank(String id, String new_bank){
        Gson gson = new Gson();
        String json = ServiceFactory.getInstance().changeBankAccount(Integer.parseInt(id), new_bank);
        Response res = gson.fromJson(json, Response.class);
        return (res.isError()) ? false : true;
    }



    private void designUI() {
        mainPanel.setBackground(Color.WHITE);

        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel formPanel = new JPanel(new GridLayout(6, 3, 10, 10));
        formPanel.setBackground(Color.WHITE);

        formPanel.add(new JLabel("Supplier ID:"));
        formPanel.add(supplierIDField);
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(updateNameButton);

        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);
        formPanel.add(updatePhoneNumberButton);

        formPanel.add(new JLabel("Bank Account:"));
        formPanel.add(bankAccountField);
        formPanel.add(updateBankAccountButton);

        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));
        formPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    private void clearFields() {
        supplierIDField.setText("");
        nameField.setText("");
        phoneNumberField.setText("");
        bankAccountField.setText("");
    }
}
