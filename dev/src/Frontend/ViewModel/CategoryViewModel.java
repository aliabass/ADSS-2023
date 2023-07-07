package Frontend.ViewModel;

import Backend.BusinessLayer.Controllers.DataBaseController;
import Backend.BusinessLayer.objects.Stock.Category;
import Backend.BusinessLayer.objects.Stock.Product;
import Backend.ServiceLayer.ServiceFactory;
import Frontend.Model.CategoryModel1;
import Frontend.Model.ProductModel1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CategoryViewModel implements ActionListener {
    private LinkedList<CategoryModel1> categories = new LinkedList<>();

    public CategoryViewModel() {
      
    }

    public LinkedList<CategoryModel1> getCategories() {
        categories.clear();
        for (Category category : ServiceFactory.getInstance().getCategoryService().categoryController.returnCategories()) {
            CategoryModel1 cc = null;
            if (category.getFatherCategory() != null) {

                cc = new CategoryModel1(category.getName(), category.getFatherCategory().getName());
            } else {
                cc = new CategoryModel1(category.getName(), null);
            }
            categories.addLast(cc);
        }
        return categories;
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

    public String addCategory(String categoryName, String father) {
        return ServiceFactory.getInstance().addCategory(categoryName, father);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle the action event here
        if (e.getActionCommand().equals("SomeActionCommand")) {
            // Perform the necessary actions
        }
    }

    public String deleteCategory(String categoryName) {
        return ServiceFactory.getInstance().deleteCategory(categoryName);
    }

    public String addSubcategory(String categoryName, String subcategoryName) {
        return ServiceFactory.getInstance().addSubCategory(categoryName,subcategoryName);
    }

    public String addFather(String categoryName, String fatherCategoryName) {
        return ServiceFactory.getInstance().addFatherCategory(categoryName,fatherCategoryName);
    }

    public String changeCategoryName(String categoryName, String newName) {
        return ServiceFactory.getInstance().changeCategoryName(categoryName,newName);
    }
}
