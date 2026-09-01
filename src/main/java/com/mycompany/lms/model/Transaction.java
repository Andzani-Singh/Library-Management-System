package com.mycompany.lms.model;

import java.time.LocalDate;

public class Transaction {
    
    private Long id;
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status; // BORROWED, RETURNED, OVERDUE
    
    public Transaction() {}
    
    public Transaction(User user, Book book, LocalDate borrowDate, String status) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.status = status;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
