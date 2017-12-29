package com.liangliang.bookmanager.service.impl;

import com.liangliang.bookmanager.bean.*;
import com.liangliang.bookmanager.mapper.*;
import com.liangliang.bookmanager.service.BookService;
import com.liangliang.bookmanager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private StateMapper stateMapper;

    @Autowired
    private OrderService orderService;

    @Override
    public List<Book> getBookList() {

        List<Book> bookList = new ArrayList<>();
        try {
            bookList = bookMapper.getBookList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return bookList;
    }

    @Override
    public Integer addBook(Book book){

        int state = 0;
        try {
            state = bookMapper.addBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return state;
    }

    @Override
    public Integer updateBook(Book book){

        int state = 0;
        try {
            state = bookMapper.updateBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return state;
    }

    @Override
    public Integer deleteBook(int bookId) throws Exception {

        int state = 0;
        try {
            state = bookMapper.deleteBook(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return state;
    }

    @Override
    public Book getBookInfoById(int bookId) {

        Book book = new Book();

        try {
            book = bookMapper.getBookInfoById(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return book;
    }

    @Override
    @Transactional
    public TableMessage searchBook(TableMessage tableMessage){

        List<Book> bookList = new ArrayList<>();
        int orderStatusId = 0;
        try {
            bookList = bookMapper.getBookAndUserList(tableMessage);

            if(tableMessage.getSearch()!=null){
                if(tableMessage.getSearch().equals("")){
                    bookList = bookMapper.getBookAndUserList(tableMessage);
                    for (Book book : bookList) {
                        Type type = typeMapper.getTypeById(book.getTypeId());
                        book.setType(type);
                        State state = stateMapper.getStateInfoById(book.getState());
                        book.setStateInfo(state);
                        if(book.getState()==5){
                            orderStatusId = -1;
                        }else if(book.getState()==3){
                            orderStatusId = 0;
                        }else if(book.getState()==6){
                            orderStatusId = 6;
                        }else if(book.getState()==1){
                            orderStatusId = 1;
                        }else if(book.getState()==4){
                            orderStatusId = 2;
                        }else if(book.getState()==0){
                            orderStatusId = 3;
                        }else if(book.getState()==2){
                            orderStatusId = 4;
                        }
                        List<Order> order = orderService.getOrderByMore(book.getBookId(), orderStatusId);
                        book.setOrder(order);
                    }
                    tableMessage.setRows(bookList);
                    tableMessage.setTotal(bookMapper.bookCount(tableMessage));
                }else {
                    tableMessage.setSearch("%"+tableMessage.getSearch()+"%");
                    List<Book> searchBookList = bookMapper.searchBook(tableMessage);
                    tableMessage.setRows(searchBookList);
                    for (Book book : searchBookList) {
                        int typeId = book.getTypeId();
                        Type type = typeMapper.getTypeById(typeId);
                        book.setType(type);
                        State state = stateMapper.getStateInfoById(book.getState());
                        book.setStateInfo(state);
                        if(book.getState()==5){
                            orderStatusId = -1;
                        }else if(book.getState()==3){
                            orderStatusId = 0;
                        }else if(book.getState()==6){
                            orderStatusId = 6;
                        }else if(book.getState()==1){
                            orderStatusId = 1;
                        }else if(book.getState()==4){
                            orderStatusId = 2;
                        }else if(book.getState()==0){
                            orderStatusId = 3;
                        }else if(book.getState()==2){
                            orderStatusId = 4;
                        }
                        List<Order> order = orderService.getOrderByMore(book.getBookId(), orderStatusId );
                        book.setOrder(order);
                    }

                    tableMessage.setTotal(bookMapper.searchBookCount(tableMessage));
                }

            }else {
                tableMessage.setRows(bookList);
                tableMessage.setTotal(bookMapper.bookCount(tableMessage));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return tableMessage;
    }



}
