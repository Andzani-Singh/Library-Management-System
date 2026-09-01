package com.mycompany.lms.service;

import com.mycompany.lms.dao.BookDO;
import com.mycompany.lms.model.Book;
import java.util.List;

public class BookService {
    private BookDO bookDO = new BookDO();
    
    public Book saveBook(Book book) {
        return bookDO.save(book);
    }
    
    public Book getBookById(Long id) {
        return bookDO.findById(id);
    }
    
    public List<Book> getAllBooks() {
        return bookDO.findAll();
    }
    
    public void deleteBook(Long id) {
        bookDO.delete(id);
    }
    
    public List<Book> searchBooks(String keyword) {
        return bookDO.searchByKeyword(keyword);
    }
    
    public List<Book> getAvailableBooks() {
        return bookDO.findAvailableBooks();
    }
}
