package Frontend.ViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedList;

import Backend.BusinessLayer.Controllers.DataBaseController;
import Backend.BusinessLayer.objects.Stock.Item;
import Backend.BusinessLayer.objects.Stock.Product;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.Model.ItemModel1;

public class ItemViewModel implements ActionListener {
    private LinkedList<ItemModel1> items = new LinkedList<>();

    public ItemViewModel() {
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public String addStoreItem(LocalDate expirationDate, int productId, int quantity) {
        return ServiceFactory.getInstance().addStoreItem(expirationDate, productId, quantity);
    }

    public String addStorageItem(LocalDate expirationDate, int productID, int quantity) {
        return ServiceFactory.getInstance().addStorageItem(productID, expirationDate, quantity);
    }

    public String addDamfromstore(int productID, LocalDate expirationDate, int amount) {
        return ServiceFactory.getInstance().addDamfromstore(expirationDate, productID, amount);
    }

    public String removefromstorage(int productID, LocalDate expirationDate, int amount) {
        return ServiceFactory.getInstance().removeStorageItem(productID, expirationDate, amount);
    }

    public String removefromstore(int productID, LocalDate expirationDate, int amount) {
        return ServiceFactory.getInstance().removeStoreItem(productID, expirationDate, amount);
    }

    public LinkedList<ItemModel1> getItems(int productID) {
        LinkedList<ItemModel1> itemsList = new LinkedList<>();
        Product product = ServiceFactory.getInstance().getProductService().productsController.returnProduct(productID);

        for (Item item : product.getItems().values()) {
            ItemModel1 itemModel = new ItemModel1();
            itemModel.setId(item.getId());
            itemModel.setExpDate(item.getExpDate());
            itemModel.setDamaged(item.getDamaged());
            itemModel.setExpired(item.getExpired());
            itemModel.setStoreamount(item.getStoreamount());
            itemModel.setStorageamount(item.getStorageamount());
            itemsList.add(itemModel);
        }

        return itemsList;
    }

    public String addDamfromstorage(int productID, LocalDate expirationDate, int amount) {
        return ServiceFactory.getInstance().addDamfromstorage(expirationDate, productID, amount);
    }
}
