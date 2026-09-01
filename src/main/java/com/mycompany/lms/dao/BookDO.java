package com.mycompany.lms.dao;

import com.mycompany.lms.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BookDO {
    private static final ConcurrentHashMap<Long, Book> books = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);
    
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.getAndIncrement());
        }
        books.put(book.getId(), book);
        return book;
    }
    
    public Book findById(Long id) {
        return books.get(id);
    }
    
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
    
    public void delete(Long id) {
        books.remove(id);
    }
    
    public List<Book> searchByKeyword(String keyword) {
        List<Book> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(lowerKeyword) || 
                book.getAuthor().toLowerCase().contains(lowerKeyword)) {
                results.add(book);
            }
        }
        return results;
    }
    
    public List<Book> findAvailableBooks() {
        List<Book> results = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAvailableCopies() > 0) {
                results.add(book);
            }
        }
        return results;
    }
}
