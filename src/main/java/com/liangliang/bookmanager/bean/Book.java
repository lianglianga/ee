package com.liangliang.bookmanager.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.liangliang.bookmanager.config.CustomJsonDateDeserializer;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
public class Book {
    @Id
    private Integer bookId;

    private String bookName;

    private String author;

    private String imageUrl;

    private String location;

    private Integer typeId;

    private Date bookDate;

    private String isbn;

    private Integer state;
    @OneToOne
    private Type type;
    @OneToOne
    private State stateInfo;
    @OneToMany
    private List<Order> order;

    public Book(Integer bookId, String bookName, String author, String imageUrl, String location, Integer typeId, String isbn, Integer state, Date bookDate ) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.imageUrl = imageUrl;
        this.location = location;
        this.typeId = typeId;
        this.isbn = isbn;
        this.state = state;
        this.bookDate = bookDate;
    }

    public Book() {
        super();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public State getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(State stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Date getBookDate() {
        return bookDate;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}