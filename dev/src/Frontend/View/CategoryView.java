package Frontend.View;

import Frontend.GUI.StockManager;
import Frontend.Model.CategoryModel1;
import Frontend.ViewModel.CategoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryView extends JFrame {
    private CategoryViewModel viewModel;
    private JPanel categoryPanel;

    public CategoryView() {
        this.viewModel = new CategoryViewModel();
        initView();
    }

    private void initView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Category View");

        JPanel mainPanel = new JPanel(new BorderLayout());

        categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
        addCategoriesToPanel();

        JScrollPane scrollPane = new JScrollPane(categoryPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Category");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String categoryName = JOptionPane.showInputDialog("Enter category name:");
                    String fatherCategoryName = JOptionPane.showInputDialog("Enter father category name (type 0 if no father):");

                    String result = viewModel.addCategory(categoryName, fatherCategoryName);
                    JOptionPane.showMessageDialog(CategoryView.this, result);

                    updateCategoryList();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CategoryView.this, "Error: " + ex.getMessage());
                }
            }
        });

        JButton deleteButton = new JButton("Delete Category");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String categoryName = JOptionPane.showInputDialog("Enter category name:");
                    String result = viewModel.deleteCategory(categoryName);
                    JOptionPane.showMessageDialog(CategoryView.this, result);

                    updateCategoryList();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CategoryView.this, "Error: " + ex.getMessage());
                }
            }
        });

        JButton addSubcategoryButton = new JButton("Add Subcategory");
        addSubcategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String categoryName = JOptionPane.showInputDialog("Enter category name:");
                    String subcategoryName = JOptionPane.showInputDialog("Enter subcategory name:");
                    String result = viewModel.addSubcategory(categoryName, subcategoryName);
                    JOptionPane.showMessageDialog(CategoryView.this, result);

                    updateCategoryList();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CategoryView.this, "Error: " + ex.getMessage());
                }
            }
        });

        JButton addFatherButton = new JButton("Add Father");
        addFatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String categoryName = JOptionPane.showInputDialog("Enter category name:");
                    String fatherCategoryName = JOptionPane.showInputDialog("Enter father category name:");
                    String result = viewModel.addFather(categoryName, fatherCategoryName);
                    JOptionPane.showMessageDialog(CategoryView.this, result);

                    updateCategoryList();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CategoryView.this, "Error: " + ex.getMessage());
                }
            }
        });

        JButton changeNameButton = new JButton("Change Name");
        changeNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String categoryName = JOptionPane.showInputDialog("Enter category name:");
                    String newName = JOptionPane.showInputDialog("Enter new name:");
                    String result = viewModel.changeCategoryName(categoryName, newName);
                    JOptionPane.showMessageDialog(CategoryView.this, result);

                    updateCategoryList();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CategoryView.this, "Error: " + ex.getMessage());
                }
            }
        });
             JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> {
        dispose();
        new StockManager();
    });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(addSubcategoryButton);
        buttonPanel.add(addFatherButton);
        buttonPanel.add(changeNameButton);
                         buttonPanel.add(exitButton);


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void addCategoriesToPanel() {
        List<CategoryModel1> categories = viewModel.getCategories();
        for (CategoryModel1 category : categories) {
            JButton categoryButton = new JButton("Category Name: " + category.getName());
            categoryButton.addActionListener(e -> {
                dispose();
                ProductView productView = new ProductView(category.getName());
                productView.setVisible(true);
            });

            JLabel fatherLabel = new JLabel("Father: " + category.getFatherName());
            JLabel subcategoriesLabel = new JLabel("Subcategories: " + category.getSubcategories(category.getName()));

            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
            categoryPanel.add(categoryButton);
            categoryPanel.add(fatherLabel);
            categoryPanel.add(subcategoriesLabel);

            this.categoryPanel.add(categoryPanel);
        }
        pack();
    }

    private void updateCategoryList() {
        categoryPanel.removeAll();
        addCategoriesToPanel();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CategoryView().setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        });
    }
}
