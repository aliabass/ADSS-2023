package Frontend.View;

import javax.swing.*;

import Frontend.GUI.StockManager;
import Frontend.GUI.StoreManager;
import Frontend.SupplierView.SupplierManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    int role;
    public LoginView(int role) {
        this.role=role;
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        JLabel labelUser = new JLabel("ID");
        labelUser.setBounds(10, 20, 80, 25);
        panel.add(labelUser);
        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(10, 50, 80, 25);
        panel.add(labelPassword);
        JPasswordField passText = new JPasswordField();
        passText.setBounds(100, 50, 165, 25);
        panel.add(passText);

        JLabel label = new JLabel("");
        label.setBounds(10, 110, 300, 25);
        panel.add(label);

        JButton loginButton = new JButton("Login");
        frame.getRootPane().setDefaultButton(loginButton);
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userText.getText();
                String password = new String(passText.getPassword());

                if (id.equals("ginger") && password.equals("snow")&&role==1) {
                   
                    new StockManager();
                    frame.dispose();} 
                else if (password.equals("snow") && id.equals("ginger")&&role==2) {
                    new StoreManager();

 
                    frame.dispose();} 
                 else {
                    if((password.equals("snow") && id.equals("ginger")&&role==3)){
                        new SupplierManager();
                        frame.dispose();
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
