package Frontend.SupplierView;
import javax.swing.*;

import Backend.ServiceLayer.ServiceFactory;
import Frontend.SupplierView.ContInnerViews.AddContractView;
import Frontend.SupplierView.ContInnerViews.RemoveContractView;
import java.awt.*;

public class Mcont extends JFrame {
    
    public Mcont() {
        setTitle("Contracts Manager");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Contracts Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton addContractButton = new JButton("Add Contract");
        addContractButton.addActionListener(e -> {new AddContractView(); dispose();});
        //JButton editContractButton = new JButton("Edit Contract");
        JButton removeContractButton = new JButton("Remove Contract");
        removeContractButton.addActionListener(e -> {new RemoveContractView(); dispose();});
        JButton showContractsButton = new JButton("Show Contracts");
        showContractsButton.addActionListener(e -> {
            String j = ServiceFactory.getInstance().listContracts();
            if(j.isEmpty()){
                JOptionPane.showMessageDialog(Mcont.this,
                        "Error Fetching Contracts!", "Error", JOptionPane.ERROR_MESSAGE);

            }else{
            JOptionPane.showMessageDialog(Mcont.this,
                        j, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {new SupplierManager(); dispose();});

        buttonPanel.add(addContractButton);
        //buttonPanel.add(editContractButton);
        buttonPanel.add(removeContractButton);
        buttonPanel.add(showContractsButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Bottom label
        //JLabel bottomLabel = new JLabel("© 2023 Suppliers Manager. All rights reserved.", SwingConstants.RIGHT);
        //mainPanel.add(bottomLabel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    
}
