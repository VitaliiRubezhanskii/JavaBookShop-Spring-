package ua.rubezhanskii.javabookshop.model;

public class BookSearchCriteria {

    private String criteria;


    public BookSearchCriteria(String criteria) {
        this.criteria = criteria;
    }

    public BookSearchCriteria() {
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
