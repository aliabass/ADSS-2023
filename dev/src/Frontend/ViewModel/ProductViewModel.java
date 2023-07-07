package Frontend.ViewModel;



import java.util.LinkedList;

import Backend.BusinessLayer.Controllers.DataBaseController;
import Backend.BusinessLayer.objects.Stock.Category;
import Backend.BusinessLayer.objects.Stock.Product;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.Model.ProductModel1;

public class ProductViewModel {
   
    
    private LinkedList<ProductModel1> allProducts;
   

    

    public LinkedList<ProductModel1> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(LinkedList<ProductModel1> allProducts) {
        this.allProducts = allProducts;
      //  RaisePropertyChanged("Products_Content");
    }

    public ProductViewModel() {
     
    }

    public String changeProductName(int name, String newName) {
    return    ServiceFactory.getInstance().changeProductName(name, newName);
    }
   public String addProduct(int barcode, String name, int minAmount, double sellingPrice,
            double manufacturingPrice,
            int shelfNumber, String manufacturer, String category) {
                             return   ServiceFactory.getInstance().addProductToStore(barcode, name, minAmount, sellingPrice, manufacturingPrice, shelfNumber, manufacturer, category);

            }

    public String changeSellingPrice(int product, double newSellingPrice) {
             return   ServiceFactory.getInstance().changeProductPrice(product, newSellingPrice);

    }

    public String changeMinAmount(int product, int newMinAmount) {
                     return   ServiceFactory.getInstance().changeProductPrice(product, newMinAmount);

    }
     public LinkedList<ProductModel1> getProducts(String categoryId) {
        LinkedList<ProductModel1> products = new LinkedList<>();
        Category category = ServiceFactory.getInstance().getCategoryService().categoryController.returnCategory(categoryId);
        if (category != null) {
            for (Product product : category.getProducts()) {
                ProductModel1 productModel = new ProductModel1(
                        product.getId(),
                        product.getName(),
                        product.getMinAmount(),
                        product.isInSale(),
                        product.getSellingPrice(),
                        product.getDiscountPrice(),
                        product.getManufacturePrice(),
                        product.getShelfNumber(),
                        product.getManufacturer(),
                        category.getName()
                );
                products.add(productModel);
            }
        }
        return products;
    }
}
