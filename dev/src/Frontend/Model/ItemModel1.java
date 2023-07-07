package Frontend.Model;

import java.time.LocalDate;

import Backend.ServiceLayer.ServiceFactory;

public class ItemModel1 {
     public ItemModel1() {
   
    }
    private LocalDate expDate;
    private int damaged;
    private int expired;
    private int id;
    private int storeamount;
    private int storageamount;
    public LocalDate getExpDate() {
        return expDate;
    }
    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
    public int getDamaged() {
        return damaged;
    }
    public void setDamaged(int damaged) {
        this.damaged = damaged;
    }
    public int getExpired() {
        return expired;
    }
    public void setExpired(int expired) {
        this.expired = expired;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getStoreamount() {
        return storeamount;
    }
    public void setStoreamount(int storeamount) {
        this.storeamount = storeamount;
    }
    public int getStorageamount() {
        return storageamount;
    }
    public void setStorageamount(int storageamount) {
        this.storageamount = storageamount;
    }
      public String addStorageItem(int productId, LocalDate expirationDate, int quantity) {
      return ServiceFactory.getInstance().addStorageItem(productId, expirationDate, quantity);
    }

    public String addStoreItem(LocalDate expirationDate, int productId, int quantity) {
     return ServiceFactory.getInstance().addStoreItem(expirationDate, productId, quantity);
    }

    public String removeStoreItem(int productID, LocalDate eexDate, int amount) {
    return ServiceFactory.getInstance().removeStoreItem(productID, eexDate, amount);
    }

    public String removeStorageItem(int productID, LocalDate eexDate, int amount) {
    return ServiceFactory.getInstance().removeStorageItem(productID, eexDate, amount);
    }
 
    public String addDamfromstore(int id, LocalDate eexDate, int amount) {
      return ServiceFactory.getInstance().addDamfromstore(eexDate, id, amount);
    }

    public String addDamfromstorage(int id, LocalDate eexDate, int amount) {
       return ServiceFactory.getInstance().addDamfromstorage(eexDate,id,amount);
    }
}