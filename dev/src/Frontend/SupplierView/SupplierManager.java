// package Frontend.SupplierView;
// import javax.swing.*;
// import java.awt.*;

// public class SupplierManager {

//     private JFrame frame;
//     private JPanel mainPanel;
//     private JLabel titleLabel;
//     private JButton manageSuppliersButton;
//     private JButton manageContractsButton;
//     private JButton manageOrdersButton;
//     //SupplierManagerModel init here

//     public SupplierManager() {
//         frame = new JFrame("Suppliers Managing Panel");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         mainPanel = new JPanel();
//         mainPanel.setLayout(new BorderLayout());

//         createComponents();
//         designUI();

//         frame.add(mainPanel);
//         frame.pack();
//         frame.setLocationRelativeTo(null);
//         frame.setVisible(true);
//     }

//     private void createComponents() {
//         titleLabel = new JLabel("Suppliers Managing Panel");
//         manageSuppliersButton = new JButton("Manage Suppliers");
//         manageContractsButton = new JButton("Manage Contracts");
//         manageOrdersButton = new JButton("Manage Orders");


//         manageSuppliersButton.addActionListener(e -> {new Msupp(); frame.dispose();});
//         manageContractsButton.addActionListener(e -> {new Mcont(); frame.dispose();});
//         manageOrdersButton.addActionListener(e -> {new Mord(); frame.dispose();});
//     }

//     private void designUI() {
//         mainPanel.setBackground(Color.WHITE);

//         Font titleFont = new Font("Arial", Font.BOLD, 24);
//         Font buttonFont = new Font("Arial", Font.PLAIN, 16);

//         titleLabel.setFont(titleFont);
//         titleLabel.setHorizontalAlignment(JLabel.CENTER);
//         titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

//         JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
//         buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
//         buttonsPanel.setBackground(Color.WHITE);
//         buttonsPanel.add(manageSuppliersButton);
//         buttonsPanel.add(manageContractsButton);
//         buttonsPanel.add(manageOrdersButton);

//         manageSuppliersButton.setFont(buttonFont);
//         manageContractsButton.setFont(buttonFont);
//         manageOrdersButton.setFont(buttonFont);

//         mainPanel.add(titleLabel, BorderLayout.NORTH);
//         mainPanel.add(buttonsPanel, BorderLayout.CENTER);
//     }
// }
package Frontend.SupplierView;

import javax.swing.*;

import Backend.ServiceLayer.ServiceFactory;
import Frontend.View.MainView;

import java.awt.*;
import java.sql.SQLException;

public class SupplierManager {

    private JFrame frame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton manageSuppliersButton;
    private JButton manageContractsButton;
    private JButton backButton;
    // private boolean wrap;
    //private JButton manageOrdersButton;
    private JButton loadDataButton;
    private JButton deleteDataButton;

    public SupplierManager() {
        // this.wrap = wrap;
        frame = new JFrame("Suppliers Managing Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createComponents();
        designUI();

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createComponents() {
        titleLabel = new JLabel("Suppliers Managing Panel");
        manageSuppliersButton = new JButton("Manage Suppliers");
        manageContractsButton = new JButton("Manage Contracts");
        loadDataButton = new JButton("Load Data");
        deleteDataButton = new JButton("Delete Data");
        backButton = new JButton("Back");

        manageSuppliersButton.addActionListener(e -> {
            new Msupp();
            frame.dispose();
        });
        manageContractsButton.addActionListener(e -> {
            new Mcont();
            frame.dispose();
        });

        deleteDataButton.addActionListener(e -> {
            try {
                ServiceFactory.getInstance().DeleteData();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            //frame.dispose();
        });
        titleLabel.add(deleteDataButton);
        loadDataButton.addActionListener(e -> {
            try {
                ServiceFactory.getInstance().LoadData();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            //frame.dispose();
        });
        titleLabel.add(loadDataButton);
        backButton.addActionListener(e -> {
            new MainView();
            frame.dispose();
         });
    }

    private void designUI() {
        mainPanel.setBackground(Color.WHITE);

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.add(manageSuppliersButton);
        buttonsPanel.add(manageContractsButton);
        buttonsPanel.add(backButton);
        //buttonsPanel.add(manageOrdersButton);
        buttonsPanel.add(loadDataButton);
        buttonsPanel.add(deleteDataButton);

        manageSuppliersButton.setFont(buttonFont);
        manageContractsButton.setFont(buttonFont);
         backButton.setFont(buttonFont);
        //manageOrdersButton.setFont(buttonFont);
        loadDataButton.setFont(buttonFont);
        deleteDataButton.setFont(buttonFont);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
    }
}
