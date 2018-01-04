package com.liangliang.bookmanager.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.liangliang.bookmanager.config.CustomJsonDateDeserializer;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue
    private Integer bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "location")
    private String location;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "book_date")
    private Date bookDate;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "state")
    private Integer state;

    @Transient
    private Type type;

    @Transient
    private State stateInfo;

    @Transient
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