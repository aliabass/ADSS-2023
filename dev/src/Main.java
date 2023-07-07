import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.module.ModuleDescriptor.Exports;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import Backend.BusinessLayer.Controllers.DataBaseController;
import Backend.BusinessLayer.Controllers.Stock.productscontroller;
import Backend.BusinessLayer.objects.Stock.Category;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.SupplierView.SupplierManager;
import Frontend.SupplierView.SuppInnerViews.RemoveSupplierView;
import Frontend.View.MainView;
import Frontend.GUI.StockManager;
import Frontend.GUI.StoreManager;
import Frontend.SupplierView.*;

public class Main {
    static  ServiceFactory  store= ServiceFactory.getInstance();

         public static void main(String[] args) throws SQLException, IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int menu;


            if(args.length == 0){
                SwingUtilities.invokeLater(MainView::new);
            }
            else{
             String ui = args[0];
             String panel = args[1];
             int direct_head = (ui.equals("CLI")) ? 0 : 1;
             int direct_tail = panel.equals("StockManager") ? 0 : ((panel.equals("SupplierManager")) ? 1 : 2);
             STOCKUI a = null;
             CLUI c = null;

             switch(direct_head) {
                 case 0:
                    switch(direct_tail){
                        case 0:
                        int secret = Integer.parseInt(reader.readLine());
                        if(a == null){
                            a = new STOCKUI();
                            if(c == null){
                                c = new CLUI(store, secret);
                            }
                        }
                            a.start();
                            break;

                        case 1:
                        int secret2 = Integer.parseInt(reader.readLine());
                        if(c == null){
                            c=new CLUI(store,secret2);
                        }
                        c.start();
                        break;
                        case 2:
                        break;

                    }
                break;
                case 1:
                    switch(direct_tail){
                        case 0:
                        new StockManager();
                        //will be gui class for store,
                        //init here
                        break;
                        case 1:
                        new SupplierManager();
                        break;
                        //will be gui class for supps,
                        //init here
                        case 2:
                        new MainView();
                        break;
                        //store manager window
                    }
            }
             
        }
    }
    }

