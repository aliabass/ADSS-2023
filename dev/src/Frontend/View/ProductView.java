package Frontend.View;

import Frontend.Model.ProductModel1;
import Frontend.ViewModel.ProductViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductView extends JFrame {
    private ProductViewModel viewModel;
    private JPanel productPanel;
    private String categoryName;

    public ProductView( String categoryName) {
        this.viewModel = new ProductViewModel();
        this.categoryName = categoryName;
        initView();
    }

    private void initView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Product View");

        JPanel mainPanel = new JPanel(new BorderLayout());

        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        addProductsToPanel();

        JScrollPane scrollPane = new JScrollPane(productPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String productName = JOptionPane.showInputDialog("Enter product name:");
                    int barcode = Integer.parseInt(JOptionPane.showInputDialog("Enter barcode:"));
                    int minAmount = Integer.parseInt(JOptionPane.showInputDialog("Enter minimum amount:"));
                    double sellingPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter selling price:"));
                    double manufacturingPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter manufacturing price:"));
                    int shelfNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter shelf number:"));
                    String manufacturer = JOptionPane.showInputDialog("Enter manufacturer:");

                    String result = viewModel.addProduct(barcode, productName, minAmount, sellingPrice, manufacturingPrice, shelfNumber, manufacturer, categoryName);
                    JOptionPane.showMessageDialog(ProductView.this, result);
                    addProductsToPanel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ProductView.this, "Error: " + ex.getMessage());
                }
            }
        });

        JButton changeSellingPriceButton = new JButton("Change Selling Price");
        changeSellingPriceButton.addActionListener(e -> {
            try {
                String productIDStr = JOptionPane.showInputDialog("Enter product ID:");
                int productID = Integer.parseInt(productIDStr);
                String newSellingPriceStr = JOptionPane.showInputDialog("Enter new selling price:");
                double newSellingPrice = Double.parseDouble(newSellingPriceStr);

                String result = viewModel.changeSellingPrice(productID, newSellingPrice);
                JOptionPane.showMessageDialog(ProductView.this, result);
                addProductsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ProductView.this, "Error: " + ex.getMessage());
            }
        });

        JButton changeMinAmountButton = new JButton("Change Min Amount");
        changeMinAmountButton.addActionListener(e -> {
            try {
                String productIDStr = JOptionPane.showInputDialog("Enter product ID:");
                int productID = Integer.parseInt(productIDStr);
                String newMinAmountStr = JOptionPane.showInputDialog("Enter new minimum amount:");
                int newMinAmount = Integer.parseInt(newMinAmountStr);

                String result = viewModel.changeMinAmount(productID, newMinAmount);
                JOptionPane.showMessageDialog(ProductView.this, result);
                addProductsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ProductView.this, "Error: " + ex.getMessage());
            }
        });

        JButton changeNameButton = new JButton("Change Name");
        changeNameButton.addActionListener(e -> {
            try {
                String productIDStr = JOptionPane.showInputDialog("Enter product ID:");
                int productID = Integer.parseInt(productIDStr);
                String newName = JOptionPane.showInputDialog("Enter new product name:");

                String result = viewModel.changeProductName(productID, newName);
                JOptionPane.showMessageDialog(ProductView.this, result);
                addProductsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ProductView.this, "Error: " + ex.getMessage());
            }
        });
             JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> {
        dispose();
        new CategoryView();
    });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(changeSellingPriceButton);
        buttonPanel.add(changeMinAmountButton);
        buttonPanel.add(changeNameButton);
                 buttonPanel.add(exitButton);


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void addProductsToPanel() {
        productPanel.removeAll();

        if (viewModel.getProducts(categoryName).isEmpty()) {
            JLabel emptyLabel = new JLabel("No products available.");
            productPanel.add(emptyLabel);
        } else {
            for (ProductModel1 product : viewModel.getProducts(categoryName)) {
    JPanel singleProductPanel = new JPanel();
    singleProductPanel.setLayout(new BorderLayout());

    JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton productNameButton = new JButton("Product Name: " + product.getName() + " | Product ID: " + product.getId());
    namePanel.add(productNameButton);
     productNameButton.addActionListener(e -> {
         dispose();
                ItemView productView = new ItemView(product.getId(), categoryName);
                productView.setVisible(true);
            });
    singleProductPanel.add(namePanel, BorderLayout.NORTH);

    JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    detailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

    JLabel detailsLabel = new JLabel(
        
        "Minimum Amount: " + product.getMinAmount() + 
        "In Sale: " + product.isInSale()  +
        "Selling Price: " + product.getSellingPrice() + 
        "Discount Price: " + product.getDiscountPrice() + 
        "Manufacture Price: " + product.getManufacturePrice()  +
        "Shelf Number: " + product.getShelfNumber() +
        "Manufacturer: " + product.getManufacturer() 
    );

    detailsPanel.add(detailsLabel);

    singleProductPanel.add(detailsPanel, BorderLayout.CENTER);

    productPanel.add(singleProductPanel);
} }

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<ProductModel1> products = List.of();
            try {
                new ProductView( "1").setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        });
    }
}
