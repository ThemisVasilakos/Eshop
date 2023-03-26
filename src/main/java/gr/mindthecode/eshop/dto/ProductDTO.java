package gr.mindthecode.eshop.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {
    private String productDescription;
    private Double productPrice;
    private String categoryDescription;


    //Getters and Setters
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
