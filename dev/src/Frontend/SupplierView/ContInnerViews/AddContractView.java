package Frontend.SupplierView.ContInnerViews;
import javax.swing.*;

import com.google.gson.Gson;

import Backend.BusinessLayer.Controllers.Stock.productscontroller;
import Backend.BusinessLayer.objects.Stock.Product;
import Backend.BusinessLayer.objects.Suppliers.Pair;
import Backend.BusinessLayer.objects.Suppliers.Payment;
import Backend.BusinessLayer.objects.Suppliers.Payment.NetTerm;
import Backend.BusinessLayer.objects.Suppliers.Payment.PaymentMethod;
import Backend.ServiceLayer.Response;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.SupplierView.Mcont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddContractView extends JFrame {

    private JPanel mainPanel;
    private JLabel supplierIdLabel;
    private JTextField supplierIdTextField;
    private JLabel paymentMethodLabel;
    private JComboBox<String> paymentMethodComboBox;
    private JLabel netTermLabel;
    private JComboBox<String> netTermComboBox;
    private JLabel itemsCountLabel;
    private JTextField itemsCountTextField;
    private JButton addItemsButton;
    private JCheckBox fixedDaysCheckBox;
    private JCheckBox responsibleDeliveryCheckBox;
    private JLabel orderPreparationDaysLabel;
    private JTextField orderPreparationDaysTextField;
    private List<Item> itemsList;
    private int count;

    public AddContractView() {
        setTitle("Add Contract");
        setSize(600 , 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        supplierIdLabel = new JLabel("Supplier ID:");
        supplierIdTextField = new JTextField(20);
        addLabelAndComponent(supplierIdLabel, supplierIdTextField, constraints);

        String[] paymentMethods = {"CASH", "BIT", "CHECK", "CRYPTO", "CREDITCARD", "BankTransfer"};
        paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodComboBox = new JComboBox<>(paymentMethods);
        addLabelAndComponent(paymentMethodLabel, paymentMethodComboBox, constraints);

        String[] netTerms = {"Net30", "Net45", "Net60", "Net90"};
        netTermLabel = new JLabel("Net Term:");
        netTermComboBox = new JComboBox<>(netTerms);
        addLabelAndComponent(netTermLabel, netTermComboBox, constraints);

        itemsCountLabel = new JLabel("Number of Items:");
        itemsCountTextField = new JTextField(20);
        addLabelAndComponent(itemsCountLabel, itemsCountTextField, constraints);

        addItemsButton = new JButton("Add Items");
        constraints.gridx = 2;
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(addItemsButton, constraints);

        JLabel fixedDaysLabel = new JLabel("Fixed Days:");
        fixedDaysCheckBox = new JCheckBox();
        addLabelAndComponent(fixedDaysLabel, fixedDaysCheckBox, constraints);

        JLabel responsibleDeliveryLabel = new JLabel("Responsible for Delivery:");
        responsibleDeliveryCheckBox = new JCheckBox();
        addLabelAndComponent(responsibleDeliveryLabel, responsibleDeliveryCheckBox, constraints);

        orderPreparationDaysLabel = new JLabel("Order Preparation days:");
        orderPreparationDaysTextField = new JTextField(20);
        addLabelAndComponent(orderPreparationDaysLabel, orderPreparationDaysTextField, constraints);


        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Mcont();
            }
        });
        GridBagConstraints backConstraints = new GridBagConstraints();
        backConstraints.gridx = 0;
        backConstraints.gridy = GridBagConstraints.RELATIVE;
        backConstraints.anchor = GridBagConstraints.SOUTHWEST;
        backConstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(backButton, backConstraints);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction(itemsList);
            }
        });
        GridBagConstraints submitConstraints = new GridBagConstraints();
        submitConstraints.gridx = 2;
        submitConstraints.gridy = 8;
        submitConstraints.anchor = GridBagConstraints.SOUTHEAST;
        submitConstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(submitButton, submitConstraints);

        getContentPane().add(mainPanel);
        setVisible(true);
        itemsList = new ArrayList<>();
        
        addItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierId = supplierIdTextField.getText();
                int itemCount = Integer.parseInt(itemsCountTextField.getText());

                if (supplierId.isEmpty()) {
                    JOptionPane.showMessageDialog(AddContractView.this,
                            "Fill the Supplier ID field!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (itemCount > 0) {
                    openItemInputGUI(itemCount);
                } else {
                    JOptionPane.showMessageDialog(AddContractView.this,
                            "Please Enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void performAction(List<Item> itemsList) {
        Gson gson = new Gson();
            String supplierId = supplierIdTextField.getText();
            String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
            String netTerm = (String) netTermComboBox.getSelectedItem();
            boolean hasFixedDays = fixedDaysCheckBox.isSelected();
            boolean isResponsibleDelivery = responsibleDeliveryCheckBox.isSelected();
            int orderPreparationDays = Integer.parseInt(orderPreparationDaysTextField.getText());
                
        Pair<Boolean, Boolean> supplier_config = new Pair<>(hasFixedDays, isResponsibleDelivery);
        Map<Integer, Double> items_prices = new HashMap<>();
        Map<Product, Integer> map = new HashMap<>();
        for (Item item : itemsList) {
            items_prices.put(item.itemID(), item.itemPrice());
            map.put(productscontroller.getInstance().getProduct(item.itemID()), item.itemQuantity());
        }
        Payment payment = new Payment(PaymentMethod.valueOf(paymentMethod.toUpperCase()),
                NetTerm.valueOf(netTerm.toUpperCase()));
        String json = ServiceFactory.getInstance().addContract(Integer.parseInt(supplierId), payment, map, supplier_config,
                orderPreparationDays, items_prices);
                Response res = gson.fromJson(json, Response.class);
                if(res.isError()){
                    JOptionPane.showMessageDialog(AddContractView.this,
                    "Error while trying to add a Contract!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(AddContractView.this,
                            "Contract added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
    }
    private void addLabelAndComponent(JLabel label, JComponent component, GridBagConstraints constraints) {
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(label, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(component, constraints);
    }


    private void openItemInputGUI(int itemCount) {
        this.count = itemCount;
        JFrame itemInputFrame = new JFrame("Item Input");
        itemInputFrame.setSize(400, 200);
        itemInputFrame.setLocationRelativeTo(null);
        itemInputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel itemInputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        itemInputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel itemQuantLabel = new JLabel("Item Supplied Quantity:");
        itemInputPanel.add(itemQuantLabel);

        JTextField itemQuantTextField = new JTextField();
        itemInputPanel.add(itemQuantTextField);

        JLabel itemIdLabel = new JLabel("Item ID:");
        itemInputPanel.add(itemIdLabel);

        JTextField itemIdTextField = new JTextField();
        itemInputPanel.add(itemIdTextField);

        JLabel itemPriceLabel = new JLabel("Item Price:");
        itemInputPanel.add(itemPriceLabel);

        JTextField itemPriceTextField = new JTextField();
        itemInputPanel.add(itemPriceTextField);

        JButton addItemButton = new JButton("Add");
        itemInputPanel.add(addItemButton);

        itemInputFrame.getContentPane().add(itemInputPanel);

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currCount = count;
                String itemName = itemQuantTextField.getText();
                String itemId = itemIdTextField.getText();
                String itemPrice = itemPriceTextField.getText();
                if(itemName.isEmpty() || itemId.isEmpty() || itemPrice.isEmpty()){
                    JOptionPane.showMessageDialog(AddContractView.this,
                            "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);

                }else{
                Item item = new Item(itemName, itemId, Double.parseDouble(itemPrice));
                itemsList.add(item);
                currCount--;
                reduce();
                if (currCount > 0) {
                    itemQuantTextField.setText("");
                    itemIdTextField.setText("");
                    itemPriceTextField.setText("");
                    itemQuantTextField.requestFocus();
                    JOptionPane.showMessageDialog(AddContractView.this,
                            "Item Registered Successfully!, Remaining: " + currCount+".", "Registered", JOptionPane.INFORMATION_MESSAGE);
                            openItemInputGUI(currCount);
                    
                } else {
                    JOptionPane.showMessageDialog(AddContractView.this,
                            "Item Registered Successfully!, Remaining: " + currCount+".", "Registered", JOptionPane.INFORMATION_MESSAGE);
                    itemInputFrame.dispose();
                }
            }
            }
        });

        itemInputFrame.setVisible(true);
    }

    private void reduce(){
        this.count =-1;
    }
}



class Item {
    private String itemQuantity;
    private String itemId;
    private double itemPrice;

    public Item(String itemQuantity, String itemId, double itemPrice) {
        this.itemQuantity = itemQuantity;
        this.itemId = itemId;
        this.itemPrice = itemPrice;
    }

    public Integer itemQuantity(){
        return Integer.parseInt(itemQuantity);
    }
    public Integer itemID(){
        return Integer.parseInt(itemId);
    }
    public double itemPrice(){
        return itemPrice;
    }

    // Add getters and setters here
}


