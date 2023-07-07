package Frontend.View;

import javax.swing.*;

import Backend.BusinessLayer.Controllers.DataBaseController;
import Backend.BusinessLayer.Controllers.Stock.discountcontroller;
import Backend.ServiceLayer.ServiceFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainView extends JFrame {
    public MainView(){
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main View");

        JPanel panel = new JPanel();
        add(panel);

        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton StockButton = new JButton("Stock Manager");
        StockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                ServiceFactory.getInstance().getDiscountService().discountController.endDiscounts();
                }catch(Exception ex){
                    
                }
                promptLogin(1);
                dispose();
            }
        });
        panel.add(StockButton);

        JButton StoreButton = new JButton("Reports Manager");
        StoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promptLogin(2);
                dispose();
            }
        });
        panel.add(StoreButton);
                JButton maged = new JButton("Supplier Manager");
        maged.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promptLogin(3);
                dispose();
            }
        });
        panel.add(maged);

        setVisible(true);
    //     try{
    //    // promptDataAction();
    //     }catch(Exception EX){
    //         System.out.println("ABO ASSI");
    //     }
    }

    private void promptLogin(int role) {
        LoginView loginView = new LoginView(role);
    }


//     private void promptDataAction() throws SQLException {
//         int option = JOptionPane.showOptionDialog(this, "Please choose an action:", "Data Action", JOptionPane.DEFAULT_OPTION,
//                 JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Load Data", "Delete Data"}, "Load Data");
        
//         if (option == JOptionPane.YES_OPTION) {
//             try{
//       DataBaseController.getInstance().LoadData();
//             }
//             catch(Exception ex){
//                 String result = ex.getMessage();
//                                 JOptionPane.showMessageDialog(MainView.this, result);

//             }
//         } else if (option == JOptionPane.NO_OPTION) {
//            try{
//     DataBaseController.getInstance().deleteData();}
//  catch(Exception ex){   String result = ex.getMessage();
//                                 JOptionPane.showMessageDialog(MainView.this, result);}
//         } else {
         
//             System.exit(0);
//         }
//     }

  
}
