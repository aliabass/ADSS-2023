package Frontend.SupplierView.SuppInnerViews;
import javax.swing.*;

import Backend.BusinessLayer.objects.Suppliers.Contact;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.SupplierView.Msupp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AddSupplierView extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField companyNameField;
    private JTextField phoneNumberField;
    private JTextField companyIDField;
    private JTextField bankAccountField;
    private JTextField numOfContactsField;
    private JButton addSupplierButton;
    private JButton backButton;
    private List<Fcontact> contacts;
    private int used;

    public AddSupplierView() {
        used = -1;
        setTitle("Add Supplier");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createComponents();
        designUI();

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    private void createComponents() {
        titleLabel = new JLabel("Add Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contacts = new CopyOnWriteArrayList<>();

        companyNameField = new JTextField();
        phoneNumberField = new JTextField();
        companyIDField = new JTextField();
        bankAccountField = new JTextField();
        numOfContactsField = new JTextField();

        addSupplierButton = new JButton("Add Supplier");
        addSupplierButton.addActionListener(new ActionListener() {
            private int used;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String numOfContacts = numOfContactsField.getText();
                String company = companyNameField.getText();
                String phone = phoneNumberField.getText();
                String compID = companyIDField.getText();
                String bankAcc = bankAccountField.getText();

                if (numOfContacts.isEmpty() || company.isEmpty() || phone.isEmpty() || compID.isEmpty() || bankAcc.isEmpty()) {
                    JOptionPane.showMessageDialog(AddSupplierView.this,
                            "Please enter the number of contacts", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int numContacts = Integer.parseInt(numOfContacts);
                    this.used = numContacts;
                    if (used < 0) {
                        JOptionPane.showMessageDialog(AddSupplierView.this,
                                "Please enter a valid number of contacts", "Error", JOptionPane.ERROR_MESSAGE);
                    }else if (used == 0){performAction(company, phone, compID, bankAcc);}
                     else {
                         // Create a thread-safe list for contacts
                        ContactFormGUI contactForm = new ContactFormGUI(used);
                        contactForm.setContactSubmittedListener(new ContactSubmittedListener() {
                            @Override
                            public void onContactSubmitted(Fcontact contact) {
                                contacts.add(contact); // Add the submitted contact to the list
                                used--; // Decrease the number of contacts
                                if (used == 0) {
                                    performAction(company, phone,compID,bankAcc); // All contacts have been submitted, perform the action
                                    contactForm.dispose(); // Close the contact form
                                }
                            }
                        });
                    }
                }
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the AddSupplierView
                new Msupp();
            }
        });
    }

    private void clearFields(){
        companyNameField.setText("");
        phoneNumberField.setText("");
        companyIDField.setText("");
        bankAccountField.setText("");
        numOfContactsField.setText("");
    }

    private void designUI() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 5, 10));

        inputPanel.add(new JLabel("Company Name:"));
        inputPanel.add(companyNameField);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(phoneNumberField);
        inputPanel.add(new JLabel("Company ID:"));
        inputPanel.add(companyIDField);
        inputPanel.add(new JLabel("Bank Account:"));
        inputPanel.add(bankAccountField);
        inputPanel.add(new JLabel("Number of Contacts:"));
        inputPanel.add(numOfContactsField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(addSupplierButton);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void performAction(String compName, String phone, String compID, String bankAcc) {
        //here call SL.addsupplier(..,..,,..,..,..,..,);
        LinkedList<Contact> cs = new LinkedList<>();
        for (Fcontact contact : contacts) {
            Contact curr = new Contact(contact.Name(), contact.phoneNumber(),contact.Email(),Integer.parseInt(compID), contact.fax());
            cs.add(curr);
        }
        ServiceFactory.getInstance().addSupplier(compName, phone, Integer.parseInt(compID), bankAcc, cs);
        JOptionPane.showMessageDialog(AddSupplierView.this,
                            "Supplier Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            clearFields();
        
    }
}

class ContactFormGUI extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField faxField;
    private JButton submitButton;
    private int numOfContacts;
    private ContactSubmittedListener contactSubmittedListener;

    public ContactFormGUI(int numOfContacts) {
        this.numOfContacts = numOfContacts;

        setTitle("Contact Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createComponents();
        designUI();

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    public void setContactSubmittedListener(ContactSubmittedListener listener) {
        this.contactSubmittedListener = listener;
    }

    private void createComponents() {
        titleLabel = new JLabel("Contact Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        addressField = new JTextField();
        faxField = new JTextField();

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String fax = faxField.getText();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || fax.isEmpty()) {
                    JOptionPane.showMessageDialog(ContactFormGUI.this,
                            "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Fcontact contact = new Fcontact(name, email, phone, address, fax);
                    if (contactSubmittedListener != null) {
                        contactSubmittedListener.onContactSubmitted(contact);
                    }
                }
            }
        });
    }

    private void designUI() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 10));

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Fax:"));
        inputPanel.add(faxField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(submitButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}


interface ContactSubmittedListener {
    void onContactSubmitted(Fcontact contact);
}

class Fcontact {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String fax;

    public Fcontact(String name, String email, String phone,String add, String fax) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
        this.address = add;
        this.fax = fax;
    }

    public String Name() {
        return name;
    }

    public String Email() {
        return email;
    }
    public String Address() {
        return address;
    }
    public String phoneNumber() {
        return phoneNumber;
    }
    public String fax() {
        return fax;
    }



    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email;
    }
}
