package ua.rubezhanskii.javabookshop.model;

import java.io.Serializable;

public class Author implements Serializable {

    private static final long serial_UID=1L;

    private Integer authorId;
    private String author1;
    private String author2;
    private String author3;
    private String author4;
    private  String aboutAuthor;


    public Author() {

    }

    public Author(Integer authorId, String author1, String author2, String author3, String author4,String aboutAuthor) {
        this.authorId = authorId;
        this.author1 = author1;
        this.author2 = author2;
        this.author3 = author3;
        this.author4 = author4;
        this.aboutAuthor=aboutAuthor;
    }


    public Integer getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }


    public String getAuthor1() {
        return author1;
    }
    public void setAuthor1(String author1) {
        this.author1 = author1;
    }


    public String getAuthor2() {
        return author2;
    }
    public void setAuthor2(String author2) {
        this.author2 = author2;
    }


    public String getAuthor3() {
        return author3;
    }
    public void setAuthor3(String author3) {
        this.author3 = author3;
    }


    public String getAuthor4() {
        return author4;
    }
    public void setAuthor4(String author4) {
        this.author4 = author4;
    }

    public String getAboutAuthor() {
        return aboutAuthor;
    }
    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }
}
