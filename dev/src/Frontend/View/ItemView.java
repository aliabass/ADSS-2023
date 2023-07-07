package Frontend.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Frontend.Model.ItemModel1;
import Frontend.ViewModel.ItemViewModel;

public class ItemView extends JFrame {
    private ItemViewModel viewModel;
    private JPanel itemPanel;
    final int productID;
    private String cat;

    public ItemView(int productID, String catID) {
        this.cat = catID;
        this.productID = productID;
        viewModel = new ItemViewModel();
        initView();
    }

    private void initView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Product View");

        JPanel mainPanel = new JPanel(new BorderLayout());

        itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        addItemsToPanel();

        JScrollPane scrollPane = new JScrollPane(itemPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton addButtonStore = new JButton("Add Item To The Store");
        addButtonStore.addActionListener(e -> {
            try {
                LocalDate expirationDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the expiration date (YYYY-MM-DD):"));
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity:"));

                String result = viewModel.addStoreItem(expirationDate, productID, quantity);
                JOptionPane.showMessageDialog(ItemView.this, result);
                addItemsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ItemView.this, "Error: " + ex.getMessage());
            }
        });

        JButton addButtonStorage = new JButton("Add Item To The Storage");
        addButtonStorage.addActionListener(e -> {
            try {
                LocalDate expirationDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the expiration date (YYYY-MM-DD):"));
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity:"));

                String result = viewModel.addStorageItem(expirationDate, productID, quantity);
                JOptionPane.showMessageDialog(ItemView.this, result);
                addItemsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ItemView.this, "Error: " + ex.getMessage());
            }
        });

        JButton addDmgStoreButton = new JButton("Add Damage from Store");
        addDmgStoreButton.addActionListener(e -> {
            try {
                int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount:"));
                LocalDate expirationDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the expiration date (YYYY-MM-DD):"));

                String result = viewModel.addDamfromstore(productID, expirationDate, amount);
                JOptionPane.showMessageDialog(ItemView.this, result);
                addItemsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ItemView.this, "Error: " + ex.getMessage());
            }
        });

        JButton addDmgStorageButton = new JButton("Add Damage from Storage");
        addDmgStorageButton.addActionListener(e -> {
            try {
                int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount:"));
                LocalDate expirationDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the expiration date (YYYY-MM-DD):"));

                String result = viewModel.addDamfromstorage(productID, expirationDate, amount);
                JOptionPane.showMessageDialog(ItemView.this, result);
                addItemsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ItemView.this, "Error: " + ex.getMessage());
            }
        });

        JButton removeStoreButton = new JButton("Remove from Store");
        removeStoreButton.addActionListener(e -> {
            try {
                int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount:"));
                LocalDate expirationDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the expiration date (YYYY-MM-DD):"));

                String result = viewModel.removefromstore(productID, expirationDate, amount);
                JOptionPane.showMessageDialog(ItemView.this, result);
                addItemsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ItemView.this, "Error: " + ex.getMessage());
            }
        });

        JButton removeStorageButton = new JButton("Remove from Storage");
        removeStorageButton.addActionListener(e -> {
            try {
                int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount:"));
                LocalDate expirationDate = LocalDate.parse(JOptionPane.showInputDialog("Enter the expiration date (YYYY-MM-DD):"));

                String result = viewModel.removefromstorage(productID, expirationDate, amount);
                JOptionPane.showMessageDialog(ItemView.this, result);
                addItemsToPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ItemView.this, "Error: " + ex.getMessage());
            }
        });
         JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> {
        dispose();
        new ProductView(this.cat).setVisible(true);
    });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(removeStorageButton);
        buttonPanel.add(removeStoreButton);
        buttonPanel.add(addDmgStorageButton);
        buttonPanel.add(addDmgStoreButton);
        buttonPanel.add(addButtonStorage);
        buttonPanel.add(addButtonStore);
         buttonPanel.add(exitButton);


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void addItemsToPanel() {
        itemPanel.removeAll();

        if (viewModel.getItems(productID).isEmpty()) {
            JLabel emptyLabel = new JLabel("No products available.");
            itemPanel.add(emptyLabel);
        } else {
            for (ItemModel1 item : viewModel.getItems(productID)) {
                JPanel singleProductPanel = new JPanel();
                singleProductPanel.setLayout(new BorderLayout());

                JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JButton productNameButton = new JButton("Item date: " + item.getExpDate().toString());
                namePanel.add(productNameButton);
                singleProductPanel.add(namePanel, BorderLayout.NORTH);

                JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                detailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

                JLabel detailsLabel = new JLabel(
                    " Damaged: " + item.getDamaged() +
                    " Expired: " + item.getExpired() +
                    " Store Amount: " + item.getStoreamount() +
                    " Storage Amount: " + item.getStorageamount()
                );

                detailsPanel.add(detailsLabel);

                singleProductPanel.add(detailsPanel, BorderLayout.CENTER);

                itemPanel.add(singleProductPanel);
            }
        }

        pack();
    }

}
