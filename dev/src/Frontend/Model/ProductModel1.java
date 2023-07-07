package Frontend.Model;

import Backend.ServiceLayer.ServiceFactory;

public class ProductModel1 {
    private int id;
    private String name;
    private int minAmount;
    private boolean inSale;
    private double sellingPrice;
    private double discountPrice;
    private double manufacturePrice;
    public double getManufacturePrice() {
        return manufacturePrice;
    }

    public void setManufacturePrice(double manufacturePrice) {
        this.manufacturePrice = manufacturePrice;
    }

    private int shelfNumber;
    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    private String manufacturer;
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    private String category;

   

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductModel1(int id, String name, int minAmount, boolean inSale, double sellingPrice, double discountPrice, double manufacturePrice, int shelfNumber, String manufacturer, String category) {
        this.id = id;
        this.name = name;
        this.minAmount = minAmount;
        this.inSale = inSale;
        this.sellingPrice = sellingPrice;
        this.discountPrice = discountPrice;
        this.manufacturePrice = manufacturePrice;
        this.shelfNumber = shelfNumber;
        this.manufacturer = manufacturer;
        this.category = category;
    }


    public String addProductToStore(int barcode, String name, int minAmount, double sellingPrice, double manufacturingPrice, int shelfNumber, String manufacturer, String category) {
        return ServiceFactory.getInstance().addProductToStore(barcode, name, minAmount, sellingPrice, manufacturingPrice, shelfNumber, manufacturer, category);
    }

    public String changeProductPrice(int productId, double newPrice) {
        return ServiceFactory.getInstance().changeProductPrice(productId, newPrice);
    }

    public String changeProductName(int productId, String newName) {
        return ServiceFactory.getInstance().changeProductName(productId, newName);
    }

    public String changeProductMinAmount(int productId, int min) {
        return ServiceFactory.getInstance().changeProductMinAmount(productId, min);
    }

    public String returnInformationProducts() {
        return ServiceFactory.getInstance().returnInformationProducts();
    }

    public String returnInformationProduct(int productId) {
        return ServiceFactory.getInstance().returnInformationProduct(productId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public boolean isInSale() {
        return inSale;
    }

    public void setInSale(boolean inSale) {
        this.inSale = inSale;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
