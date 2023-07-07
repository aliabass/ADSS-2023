package Frontend.SupplierView.ContInnerViews;
import javax.swing.*;

import com.google.gson.Gson;

import Backend.ServiceLayer.Response;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.SupplierView.Mcont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveContractView extends JFrame {

    private JPanel mainPanel;
    private JLabel supplierIdLabel;
    private JTextField supplierIdTextField;
    private JButton removeButton;

    public RemoveContractView() {
        setTitle("Remove Contract");
        setSize(400, 200);
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

        removeButton = new JButton("Remove");
        constraints.gridx = 1;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(removeButton, constraints);

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

        getContentPane().add(mainPanel);
        setVisible(true);

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String supplierId = supplierIdTextField.getText();
                if(supplierId.isEmpty()){
                    JOptionPane.showMessageDialog(RemoveContractView.this,
                            "Please provide a SupplierID", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Gson gson = new Gson();
                    int id = Integer.parseInt(supplierId);
                    String json = ServiceFactory.getInstance().removeContract(id);
                    Response res = gson.fromJson(json, Response.class);
                    if(res.isError()){
                        JOptionPane.showMessageDialog(RemoveContractView.this,
                            "An Error occurred while trying to remove the Contract!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(RemoveContractView.this,
                        res.getValue(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        supplierIdTextField.setText("");
                    }

                }
            }
        });
    }

    private void addLabelAndComponent(JLabel label, JComponent component, GridBagConstraints constraints) {
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(label, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        mainPanel.add(component, constraints);
    }
}
