package com.mycompany.lms.gui;

import com.mycompany.lms.model.Book;
import com.mycompany.lms.model.Transaction;
import com.mycompany.lms.model.User;
import com.mycompany.lms.service.BookService;
import com.mycompany.lms.service.TransactionService;
import com.mycompany.lms.service.UserService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class MainFrame extends JFrame {
    private User currentUser;
    private JTabbedPane tabbedPane;
    private BookService bookService = new BookService();
    private UserService userService = new UserService();
    private TransactionService transactionService = new TransactionService();
    
    // Book management components
    private JTable booksTable;
    private DefaultTableModel booksTableModel;
    private JTextField bookTitleField;
    private JTextField bookAuthorField;
    private JTextField bookIsbnField;
    private JTextField bookTotalCopiesField;
    private JTextField bookSearchField;
    
    // User management components
    private JTable usersTable;
    private DefaultTableModel usersTableModel;
    private JTextField userSearchField;
    
    // Transaction components
    private JTable transactionsTable;
    private DefaultTableModel transactionsTableModel;
    
    public MainFrame(User user) {
        this.currentUser = user;
        setTitle("Library Management System - " + user.getFullName());
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
        loadBooks();
        loadUsers();
        loadTransactions();
    }
    
    private void initComponents() {
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        JMenu booksMenu = new JMenu("Books");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        JMenuItem refreshBooksItem = new JMenuItem("Refresh Books");
        booksMenu.add(addBookItem);
        booksMenu.add(refreshBooksItem);
        
        JMenu usersMenu = new JMenu("Users");
        JMenuItem refreshUsersItem = new JMenuItem("Refresh Users");
        usersMenu.add(refreshUsersItem);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(booksMenu);
        menuBar.add(usersMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        
        // Menu actions
        logoutItem.addActionListener(e -> logout());
        exitItem.addActionListener(e -> System.exit(0));
        addBookItem.addActionListener(e -> showAddBookDialog());
        refreshBooksItem.addActionListener(e -> loadBooks());
        refreshUsersItem.addActionListener(e -> loadUsers());
        aboutItem.addActionListener(e -> showAboutDialog());
        
        // Tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Books tab
        JPanel booksPanel = createBooksPanel();
        tabbedPane.addTab("Books", booksPanel);
        
        // Users tab
        JPanel usersPanel = createUsersPanel();
        tabbedPane.addTab("Users", usersPanel);
        
        // Transactions tab
        JPanel transactionsPanel = createTransactionsPanel();
        tabbedPane.addTab("Transactions", transactionsPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Status bar
        JLabel statusLabel = new JLabel("Logged in as: " + currentUser.getUsername() + " (" + 
            (currentUser.getRole() != null ? currentUser.getRole().getName() : "USER") + ")");
        add(statusLabel, BorderLayout.SOUTH);
    }
    
    private JPanel createBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        bookSearchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");
        searchPanel.add(bookSearchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);
        
        searchButton.addActionListener(e -> searchBooks());
        showAllButton.addActionListener(e -> loadBooks());
        
        // Books table
        String[] columnNames = {"ID", "Title", "Author", "ISBN", "Total Copies", "Available"};
        booksTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        booksTable = new JTable(booksTableModel);
        JScrollPane scrollPane = new JScrollPane(booksTable);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add Book");
        JButton editButton = new JButton("Edit Book");
        JButton deleteButton = new JButton("Delete Book");
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        
        addButton.addActionListener(e -> showAddBookDialog());
        editButton.addActionListener(e -> showEditBookDialog());
        deleteButton.addActionListener(e -> deleteBook());
        borrowButton.addActionListener(e -> borrowBook());
        returnButton.addActionListener(e -> returnBook());
        
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        userSearchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");
        searchPanel.add(userSearchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);
        
        searchButton.addActionListener(e -> searchUsers());
        showAllButton.addActionListener(e -> loadUsers());
        
        // Users table
        String[] columnNames = {"ID", "Username", "Full Name", "Email", "Phone", "Role"};
        usersTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        usersTable = new JTable(usersTableModel);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createTransactionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Transactions table
        String[] columnNames = {"ID", "User", "Book", "Borrow Date", "Return Date", "Status"};
        transactionsTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        transactionsTable = new JTable(transactionsTableModel);
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadBooks() {
        booksTableModel.setRowCount(0);
        for (Book book : bookService.getAllBooks()) {
            Object[] row = {
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getTotalCopies(),
                book.getAvailableCopies()
            };
            booksTableModel.addRow(row);
        }
    }
    
    private void loadUsers() {
        usersTableModel.setRowCount(0);
        for (User user : userService.getAllUsers()) {
            Object[] row = {
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole() != null ? user.getRole().getName() : "USER"
            };
            usersTableModel.addRow(row);
        }
    }
    
    private void loadTransactions() {
        transactionsTableModel.setRowCount(0);
        for (Transaction transaction : transactionService.getAllTransactions()) {
            Object[] row = {
                transaction.getId(),
                transaction.getUser().getFullName(),
                transaction.getBook().getTitle(),
                transaction.getBorrowDate(),
                transaction.getReturnDate(),
                transaction.getStatus()
            };
            transactionsTableModel.addRow(row);
        }
    }
    
    private void searchBooks() {
        String keyword = bookSearchField.getText().trim();
        if (keyword.isEmpty()) {
            loadBooks();
            return;
        }
        
        booksTableModel.setRowCount(0);
        for (Book book : bookService.searchBooks(keyword)) {
            Object[] row = {
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getTotalCopies(),
                book.getAvailableCopies()
            };
            booksTableModel.addRow(row);
        }
    }
    
    private void searchUsers() {
        String keyword = userSearchField.getText().trim();
        if (keyword.isEmpty()) {
            loadUsers();
            return;
        }
        
        usersTableModel.setRowCount(0);
        for (User user : userService.getAllUsers()) {
            if (user.getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                user.getUsername().toLowerCase().contains(keyword.toLowerCase()) ||
                user.getEmail().toLowerCase().contains(keyword.toLowerCase())) {
                Object[] row = {
                    user.getId(),
                    user.getUsername(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getRole() != null ? user.getRole().getName() : "USER"
                };
                usersTableModel.addRow(row);
            }
        }
    }
    
    private void showAddBookDialog() {
        JDialog dialog = new JDialog(this, "Add New Book", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Title:"), gbc);
        
        gbc.gridx = 1;
        bookTitleField = new JTextField(20);
        panel.add(bookTitleField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Author:"), gbc);
        
        gbc.gridx = 1;
        bookAuthorField = new JTextField(20);
        panel.add(bookAuthorField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("ISBN:"), gbc);
        
        gbc.gridx = 1;
        bookIsbnField = new JTextField(20);
        panel.add(bookIsbnField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Total Copies:"), gbc);
        
        gbc.gridx = 1;
        bookTotalCopiesField = new JTextField(20);
        panel.add(bookTotalCopiesField, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        saveButton.addActionListener(e -> {
            String title = bookTitleField.getText().trim();
            String author = bookAuthorField.getText().trim();
            String isbn = bookIsbnField.getText().trim();
            String totalCopiesStr = bookTotalCopiesField.getText().trim();
            
            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || totalCopiesStr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                int totalCopies = Integer.parseInt(totalCopiesStr);
                Book book = new Book(title, author, isbn, totalCopies);
                bookService.saveBook(book);
                loadBooks();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Book added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Total copies must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void showEditBookDialog() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to edit", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Long bookId = (Long) booksTableModel.getValueAt(selectedRow, 0);
        Book book = bookService.getBookById(bookId);
        
        if (book == null) {
            JOptionPane.showMessageDialog(this, "Book not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JDialog dialog = new JDialog(this, "Edit Book", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Title:"), gbc);
        
        gbc.gridx = 1;
        JTextField titleField = new JTextField(book.getTitle(), 20);
        panel.add(titleField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Author:"), gbc);
        
        gbc.gridx = 1;
        JTextField authorField = new JTextField(book.getAuthor(), 20);
        panel.add(authorField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("ISBN:"), gbc);
        
        gbc.gridx = 1;
        JTextField isbnField = new JTextField(book.getIsbn(), 20);
        panel.add(isbnField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Total Copies:"), gbc);
        
        gbc.gridx = 1;
        JTextField totalCopiesField = new JTextField(String.valueOf(book.getTotalCopies()), 20);
        panel.add(totalCopiesField, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        saveButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String isbn = isbnField.getText().trim();
            String totalCopiesStr = totalCopiesField.getText().trim();
            
            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || totalCopiesStr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                int totalCopies = Integer.parseInt(totalCopiesStr);
                book.setTitle(title);
                book.setAuthor(author);
                book.setIsbn(isbn);
                book.setTotalCopies(totalCopies);
                book.setAvailableCopies(totalCopies); // Reset available copies
                bookService.saveBook(book);
                loadBooks();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Book updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Total copies must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void deleteBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this book?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            Long bookId = (Long) booksTableModel.getValueAt(selectedRow, 0);
            bookService.deleteBook(bookId);
            loadBooks();
            JOptionPane.showMessageDialog(this, "Book deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void borrowBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to borrow", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Long bookId = (Long) booksTableModel.getValueAt(selectedRow, 0);
        Book book = bookService.getBookById(bookId);
        
        if (book == null || book.getAvailableCopies() <= 0) {
            JOptionPane.showMessageDialog(this, "Book is not available for borrowing", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create transaction
        Transaction transaction = new Transaction(currentUser, book, LocalDate.now(), "BORROWED");
        transactionService.saveTransaction(transaction);
        
        // Update book availability
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookService.saveBook(book);
        
        loadBooks();
        loadTransactions();
        JOptionPane.showMessageDialog(this, "Book borrowed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void returnBook() {
        int selectedRow = transactionsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a transaction to return", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Long transactionId = (Long) transactionsTableModel.getValueAt(selectedRow, 0);
        Transaction transaction = transactionService.getTransactionById(transactionId);
        
        if (transaction == null || !"BORROWED".equals(transaction.getStatus())) {
            JOptionPane.showMessageDialog(this, "Invalid transaction or book already returned", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Update transaction
        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus("RETURNED");
        transactionService.saveTransaction(transaction);
        
        // Update book availability
        Book book = transaction.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookService.saveBook(book);
        
        loadBooks();
        loadTransactions();
        JOptionPane.showMessageDialog(this, "Book returned successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void logout() {
        dispose();
        SwingUtilities.invokeLater(() -> {
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.setVisible(true);
            
            if (loginDialog.isAuthenticated()) {
                User user = loginDialog.getAuthenticatedUser();
                MainFrame mainFrame = new MainFrame(user);
                mainFrame.setVisible(true);
            }
        });
    }
    
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this, 
            "Library Management System v1.0\n\n" +
            "A simple library management system with:\n" +
            "- Book management\n" +
            "- User management\n" +
            "- Book borrowing and returning\n\n" +
            "Developed by: Mavangwa Andzani Singh", 
            "About LMS", JOptionPane.INFORMATION_MESSAGE);
    }
}
