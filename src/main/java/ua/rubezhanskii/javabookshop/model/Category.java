package ua.rubezhanskii.javabookshop.model;

import java.io.Serializable;

public class Category implements Serializable {

    private static final long serial_UID=4L;
    private Integer categoryId;
    private String category;


    public Category() {

    }

    public Category(Integer categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }


    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }



    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }


}

