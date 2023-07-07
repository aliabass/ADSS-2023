package Frontend.View;

import javax.swing.*;
import Backend.BusinessLayer.Controllers.DataBaseController;
import Backend.BusinessLayer.objects.Stock.*; 
import Backend.ServiceLayer.ServiceFactory;
import Frontend.GUI.StoreManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportView extends JFrame {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JTextArea outputTextArea;
    private List<Category> categories;

    public ReportView() {

        setTitle("Report GUI");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        JButton defectiveItemsButton = new JButton("Defective Items Report");
        defectiveItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.getDefectiveItemsByStore();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
            }
        }
        });
        mainPanel.add(defectiveItemsButton);

        JButton stockButton = new JButton("Stock Report");
        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectCategoryIDs();
                    String result = serviceFactory.getProductsAndAmount(categories);
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(stockButton);

        JButton damagedItemsButton = new JButton("Damaged Items Report");
        damagedItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.getDamagedItemReportsByStore();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(damagedItemsButton);

        JButton expiredItemsButton = new JButton("Expired Items Report");
        expiredItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.getExpiredItemReportsByStore();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(expiredItemsButton);

        JButton minReportButton = new JButton("Min Report");
        minReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.getMinReport();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(minReportButton);

        JButton allProductsButton = new JButton("All Products Information");
        allProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.returnInformationProducts();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(allProductsButton);

        JButton oneProductButton = new JButton("One Product Information");
        oneProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int productId = getProductId();
                    String result = serviceFactory.returnInformationProduct(productId);
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(oneProductButton);

        JButton allCategoryDiscountsButton = new JButton("All Category Discounts");
        allCategoryDiscountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.getactiveCategoriesDiscounts();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(allCategoryDiscountsButton);

        JButton allProductDiscountsButton = new JButton("All Product Discounts");
        allProductDiscountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.getactiveProductsDiscounts();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(allProductDiscountsButton);

        JButton allCategoriesButton = new JButton("All Categories");
        allCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.returnCategories();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(allCategoriesButton);

        JButton shortOrdersButton = new JButton("List Short Orders");
        shortOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.listOrders();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(shortOrdersButton);

        JButton periodicOrdersButton = new JButton("List Periodic Orders");
        periodicOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = serviceFactory.listPerOrder();
                    displayOutput(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportView.this, ex);
                }
            }
        });
        mainPanel.add(periodicOrdersButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StoreManager();
            }
        });
        mainPanel.add(exitButton);

        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        mainPanel.add(scrollPane);

        setVisible(true);
    }

    private void displayOutput(String output) {
        outputTextArea.setText(output);
    }

    private void selectCategoryIDs() {
        categories = new ArrayList<>();
        String categoryID;
        JOptionPane.showMessageDialog(null, "Type 'exit' if you wish to stop");

        while (true) {
            categoryID = JOptionPane.showInputDialog(this, "Enter a category ID:");
            if (categoryID == null || categoryID.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                String categoryId = categoryID;
                Category category = serviceFactory.getCategoryService().getCategory(categoryId);
                if (category != null) {
                    categories.add(category);
                } else {
                    JOptionPane.showMessageDialog(this, "No such category.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid category ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int getProductId() {
        String productId = JOptionPane.showInputDialog(this, "Enter a product ID:");
        try {
            return Integer.parseInt(productId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid product ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ReportView();
            }
        });
    }
}
