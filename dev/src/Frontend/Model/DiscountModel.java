package Frontend.Model;

import java.time.LocalDate;

import Backend.ServiceLayer.ServiceFactory;

public class DiscountModel {
    public DiscountModel() {
        
    }

    

    private int discountID;
    private int product;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private double percent;



    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    
    public String buildProductDiscount(int product, double percent, LocalDate start,
    LocalDate end) {
        return ServiceFactory.getInstance().buildProductDiscount(product, percent, start, end);
}

//
public String buildCategoryDiscount(String category, double percent, LocalDate start,
    LocalDate end) {
        return ServiceFactory.getInstance().buildCategoryDiscount(category, percent, start, end);
}

// //
// public String endDiscounts() {
//     return ServiceFactory.getInstance().endDiscounts();
// }

// // public String addPDiscount(Product product, double discount) {
// // }

// public String endDiscount(int product) {
// return ServiceFactory.getInstance().endDiscount(product);
// }




}
