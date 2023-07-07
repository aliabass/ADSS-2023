// package Frontend.SupplierView;
// import javax.swing.*;
// import java.awt.*;

// public class Mord extends JFrame {

//     public Mord() {
//         setTitle("Orders Manager");
//         setSize(400, 400);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         JPanel mainPanel = new JPanel(new BorderLayout());

//         JLabel titleLabel = new JLabel("Orders Manager", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//         mainPanel.add(titleLabel, BorderLayout.NORTH);

//         JPanel buttonPanel = new JPanel(new GridLayout(9, 1, 10, 10));

//         JButton placePeriodicOrderButton = new JButton("Place Periodic Order");
//         JButton listShortageOrdersButton = new JButton("List Shortage Orders");
//         JButton listPeriodicOrdersButton = new JButton("List Periodic Orders");
//         JButton advanceDayButton = new JButton("Advance Day");
//         JButton cancelOrderButton = new JButton("Cancel Order");
//         JButton updatePeriodicDateButton = new JButton("Update Periodic Date");
//         JButton changeProductButton = new JButton("Change Product in a Periodic Order");
//         JButton addItemButton = new JButton("Add Item to a Periodic Order");
//         JButton backButton = new JButton("Back");
//         backButton.addActionListener(e -> {new SupplierManager(); dispose();});

//         buttonPanel.add(placePeriodicOrderButton);
//         buttonPanel.add(listShortageOrdersButton);
//         buttonPanel.add(listPeriodicOrdersButton);
//         buttonPanel.add(advanceDayButton);
//         buttonPanel.add(cancelOrderButton);
//         buttonPanel.add(updatePeriodicDateButton);
//         buttonPanel.add(changeProductButton);
//         buttonPanel.add(addItemButton);
//         buttonPanel.add(backButton);

//         mainPanel.add(buttonPanel, BorderLayout.CENTER);
//         getContentPane().add(mainPanel);

//         setVisible(true);
//     }
// }
