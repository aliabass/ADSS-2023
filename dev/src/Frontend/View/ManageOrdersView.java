package Frontend.View;

import javax.swing.*;

import Backend.BusinessLayer.Controllers.Stock.Categorycontroller;
import Backend.BusinessLayer.Controllers.Stock.productscontroller;
import Backend.BusinessLayer.Controllers.Suppliers.OrdersController;
import Backend.BusinessLayer.Controllers.Suppliers.SupplierController;
import Backend.BusinessLayer.objects.Stock.Product;
import Backend.BusinessLayer.objects.Suppliers.Contract;
import Backend.BusinessLayer.objects.Suppliers.Pair;
import Backend.BusinessLayer.objects.Suppliers.Payment;
import Backend.BusinessLayer.objects.Suppliers.Record;

import Backend.ServiceLayer.ServiceFactory;
import Frontend.GUI.StockManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import java.util.List;

public class ManageOrdersView extends JFrame {
    public Map<Integer, Product> items_map;
    ServiceFactory factory = ServiceFactory.getInstance();

    public ManageOrdersView() {
        items_map = new HashMap<>();
        Product product1 = new Product(1, "milk", -1, 5, 2, 1, "man");
        Product product2 = new Product(2, "Bamba", -1, 5, 4, 1, "man");
        Product product3 = new Product(3, "Fanta", -1, 5, 4, 1, "man");
        Product product4 = new Product(4, "Maltesers", -1, 5, 4, 1, "man");
        Product product5 = new Product(5, "Tuna", -1, 5, 4, 1, "man");
        items_map.put(1, product1);
        items_map.put(2, product2);
        items_map.put(3, product3);
        items_map.put(4, product4);
        items_map.put(5, product5);
        try {
            // store.addCategory("milk", "0");

            // //Category c=new Category("milk", null);
            List<Product> lc=new ArrayList<>();
            lc.add(product1);
            lc.add(product2);
            lc.add(product3);
            lc.add(product4);
            lc.add(product5);
            Categorycontroller.getInstance().buildCategory("milk", lc, null, "0");
            productscontroller.getInstance().buildProduct(1, "milk", -1, 5, 4, 1, "milk", Categorycontroller.getInstance().getCategories().get("milk"));
            productscontroller.getInstance().buildProduct(2, "Bamba", -1, 5, 4, 1, "milk", Categorycontroller.getInstance().getCategories().get("milk"));
            productscontroller.getInstance().buildProduct(3, "Fanta", -1, 5, 4, 1, "milk", Categorycontroller.getInstance().getCategories().get("milk"));
            productscontroller.getInstance().buildProduct(4, "Maltesers", -1, 5, 4, 1, "milk", Categorycontroller.getInstance().getCategories().get("milk"));
            productscontroller.getInstance().buildProduct(5, "Tuna", -1, 5, 4, 1, "milk", Categorycontroller.getInstance().getCategories().get("milk"));

            Payment payment = new Payment(Backend.BusinessLayer.objects.Suppliers.Payment.PaymentMethod.BIT,
            Backend.BusinessLayer.objects.Suppliers.Payment.NetTerm.NET30);

            Map<Product, Integer> map = new HashMap<>();
            map.put(productscontroller.getInstance().getProduct(1), 10);
            map.put(productscontroller.getInstance().getProduct(2), 10);
            map.put(productscontroller.getInstance().getProduct(3), 10);
            map.put(productscontroller.getInstance().getProduct(4), 10);
            map.put(productscontroller.getInstance().getProduct(5), 10);

    
            Pair<Boolean, Boolean> supplier_config = new Pair<>(true, true);
    
            Map<Integer,Double> items_prices = new HashMap<>(); //prod_id ==> price
            Map<Integer,Map<Integer, Pair<Character,Integer>>> discounts = new HashMap<>(); //
            Map<Integer,Pair<Character,Integer>> ovl_discount = new HashMap<>();//
            items_prices.put(1, new Double(7));
            items_prices.put(2, new Double(4));
            items_prices.put(3, new Double(6));
            items_prices.put(4, new Double(8));
            items_prices.put(5, new Double(11));
            Map<Integer,Double> items_prices2 = new HashMap<>();
            items_prices2.put(1, new Double(8));
            items_prices2.put(2, new Double(3));
            items_prices2.put(3, new Double(8));
            items_prices2.put(4, new Double(10));
            items_prices2.put(5, new Double(9));

           ServiceFactory.getInstance().addSupplier("comp", "123123", 0, "bank account", new LinkedList<>());
            ServiceFactory.getInstance().addContract(0, payment, map, supplier_config,1,items_prices);
            ServiceFactory.getInstance().addSupplier("compppppppppp", "123123123", 1, "vbbadg", new LinkedList<>());
            ServiceFactory.getInstance().addContract(1, payment, map, supplier_config, 3, items_prices2);
        } catch (SQLException e) {
        }

        setTitle("Manage Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 1));

        JLabel titleLabel = new JLabel("Please choose an option:");
        mainPanel.add(titleLabel);

        JButton placePeriodicOrderButton = new JButton("Place Periodic Order");
        mainPanel.add(placePeriodicOrderButton);

        JButton advanceDayButton = new JButton("Advance Day");
        mainPanel.add(advanceDayButton);

        JButton cancelOrderButton = new JButton("Cancel Order");
        mainPanel.add(cancelOrderButton);

        JButton updatePeriodicDateButton = new JButton("Update Periodic date");
        mainPanel.add(updatePeriodicDateButton);

        JButton changeProductButton = new JButton("Change product in a Periodic order");
        mainPanel.add(changeProductButton);

        JButton addItemButton = new JButton("Add an item to a Periodic order");
        mainPanel.add(addItemButton);

        JButton backButton = new JButton("Exit");
        mainPanel.add(backButton);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        placePeriodicOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //items_map = new HashMap<Integer, Product>();
                List<Record> items = new LinkedList<>();
                try {
                    int supplier_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Supplier ID:"));
                    int order_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Order ID:"));
                    String address = JOptionPane.showInputDialog("Enter address:");
                    String contact_number = JOptionPane.showInputDialog("Enter Contact number:");
                    int days = Integer.parseInt(JOptionPane.showInputDialog("Enter the day of arrival:"));
                    Contract cont = OrdersController.getInstance().suppliers_contracts.get(supplier_id);
                    int quant = Integer.parseInt(JOptionPane.showInputDialog("Enter how many items are included in the order (Max 5):"));

                    for (int i = 1; i <= quant; i++) {
                        String[] options = {"Milk", "Bamba", "Fanta", "Maltesers", "Tuna"};
                        int choice = JOptionPane.showOptionDialog(null, "Choose the items included in the Order:", "Item Selection",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        Product curr = items_map.get(choice + 1);
                        int q = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity to order:"));
                        int quantity_can = cont.quant(curr.getId(), q);
                        double discount = cont.get_margin_price(curr.getId(), quantity_can);
                        double final_price = cont.getItemPrice(curr.getId(), quantity_can);
                        Record toAdd = new Record(curr, supplier_id, order_id, quantity_can, discount, final_price);
                        items.add(toAdd);
                    }

                    String supp_name = SupplierController.getInstance().suppliers.get(supplier_id).getSupplier_name();
                    JOptionPane.showMessageDialog(ManageOrdersView.this, factory.add_PeriodicOrder(supp_name, supplier_id, address, contact_number, items, days));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ManageOrdersView.this, "Error: " + ex.getMessage());
                }
            }
        });

        advanceDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    factory.incrementDay();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ManageOrdersView.this, "Error: " + ex.getMessage());
                }
            }
        });

        cancelOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int order = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the Order You would like to cancel"));
                    String result = factory.cancel_order(order);
                    JOptionPane.showMessageDialog(ManageOrdersView.this, result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ManageOrdersView.this, "Error: " + ex.getMessage());
                }
            }
        });

        updatePeriodicDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int order = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the Order You would like to update"));
                    int date = Integer.parseInt(JOptionPane.showInputDialog("Enter the new date"));
                    String result = factory.updatePeriodic_date(order, date);
                    JOptionPane.showMessageDialog(ManageOrdersView.this, result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ManageOrdersView.this, "Error: " + ex.getMessage());
                }
            }
        });

        changeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int order = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the Order You would like to add to"));
                    int product = Integer.parseInt(JOptionPane.showInputDialog("What is the product ID?"));
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog("How much?"));
                    String result = factory.add_toPeriodic_product(product, order, quantity);
                    JOptionPane.showMessageDialog(ManageOrdersView.this, result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ManageOrdersView.this, "Error: " + ex.getMessage());
                }
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Perform action for "Add an item to a Periodic order"
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ManageOrdersView.this, "Error: " + ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StockManager();
            }
        });
    }

    public static void main(String[] args) {
        new ManageOrdersView();
    }
}
