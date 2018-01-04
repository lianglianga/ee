package com.liangliang.bookmanager.service.impl;

import com.liangliang.bookmanager.bean.*;
import com.liangliang.bookmanager.mapper.*;
import com.liangliang.bookmanager.repository.BookRepository;
import com.liangliang.bookmanager.repository.StateRepository;
import com.liangliang.bookmanager.repository.TypeRepository;
import com.liangliang.bookmanager.repository.UserRepository;
import com.liangliang.bookmanager.service.BookService;
import com.liangliang.bookmanager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private StateRepository stateRepository;

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

//        int state = 0;
        try {
//            state = bookMapper.addBook(book);
            bookRepository.save(book);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer updateBook(Book book){

//        int state = 0;
        try {
            bookRepository.saveAndFlush(book);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

//        return state;
    }

    @Override
    public Integer deleteBook(int bookId) throws Exception {

//        int state = 0;
        try {
            bookRepository.delete(bookId);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

    @Override
    public Book getBookInfoById(int bookId) {

        Book book = new Book();

        try {
            book = bookRepository.findOne(bookId);
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
            bookList = bookRepository.getBookAndUserList(pageable);

            if(tableMessage.getSearch()!=null){
                if(tableMessage.getSearch().equals("")){
                    bookList = bookRepository.getBookAndUserList(pageable);
                    for (Book book : bookList) {
                        Type type = typeRepository.findOne(book.getTypeId());
                        book.setType(type);
                        State state = stateRepository.findOne(book.getState());
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
                    tableMessage.setTotal(bookRepository.bookCount());
                }else {
                    tableMessage.setSearch("%"+tableMessage.getSearch()+"%");
                    Page<Book> searchBookList = bookRepository.searchBook(tableMessage.getSearchName(),tableMessage.getSearch(),pageable);
                    tableMessage.setRows(searchBookList);
                    for (Book book : searchBookList) {
                        int typeId = book.getTypeId();
                        Type type = typeRepository.findOne(typeId);
                        book.setType(type);
                        State state = stateRepository.findOne(book.getState());
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

                    tableMessage.setTotal(bookRepository.searchBookCount(tableMessage.getSearchName(),tableMessage.getSearch()));
                }

            }else {
                tableMessage.setRows(bookList);
                tableMessage.setTotal(bookRepository.bookCount());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return tableMessage;
    }



}
