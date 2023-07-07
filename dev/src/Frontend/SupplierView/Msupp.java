package Frontend.SupplierView;
import javax.swing.*;

import Backend.BusinessLayer.objects.Suppliers.SupplierCard;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.SupplierView.SuppInnerViews.AddSupplierView;
import Frontend.SupplierView.SuppInnerViews.EditSupplierView;
import Frontend.SupplierView.SuppInnerViews.RemoveSupplierView;

import java.awt.*;
import java.util.Collection;

public class Msupp extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton addSupplierButton;
    private JButton editSupplierButton;
    private JButton removeSupplierButton;
    private JButton showSuppliersButton;
    private JButton backButton;
    private JPanel buttonPanel;
    
    public Msupp() {
        setTitle("Suppliers Manager");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         mainPanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel("Suppliers Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

         buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        addSupplierButton = new JButton("Add Supplier");
        addSupplierButton.addActionListener(e -> {new AddSupplierView(); dispose();});
        editSupplierButton = new JButton("Edit Supplier");
        editSupplierButton.addActionListener(e -> {new EditSupplierView(); dispose();});
        removeSupplierButton = new JButton("Remove Supplier");
        removeSupplierButton.addActionListener(e -> {new RemoveSupplierView(); dispose();});
        showSuppliersButton = new JButton("Show Suppliers");
        showSuppliersButton.addActionListener(e -> {
            Collection<SupplierCard> suppliers = ServiceFactory.getInstance().listSuppsGUI();
        
            StringBuilder message = new StringBuilder();
            for (SupplierCard supplier : suppliers) {
                message.append("ID: ").append(supplier.getSupplier_id()).append("\n");
                message.append("Supplier Name: ").append(supplier.getSupplier_name()).append("\n");
                message.append("Company ID: ").append(supplier.getCompany_id()).append("\n");
                message.append("Phone Number: ").append(supplier.getPhone_number()).append("\n");
                message.append("Bank Account: ").append(supplier.getBank_account()).append("\n");
                message.append("-----------------------------------------------------------------\n");
            }
        
            JOptionPane.showMessageDialog(Msupp.this, message.toString(), "Suppliers List", JOptionPane.INFORMATION_MESSAGE);
        });
        
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {new SupplierManager(); dispose();});

        buttonPanel.add(addSupplierButton);
        buttonPanel.add(editSupplierButton);
        buttonPanel.add(removeSupplierButton);
        buttonPanel.add(showSuppliersButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setVisible(true);
    }

}
