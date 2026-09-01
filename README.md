# Library Management System

## 🎓 Final Year Student - Information Technology

**Developer:** Mavangwa Andzani Singh
**Institution:** [Vaal University Of Technology]  
**Academic Year:** 2024-2026

---

## 📋 Project Overview

A comprehensive Library Management System built to automate and streamline library operations including book management, user administration, and transaction tracking. This project demonstrates practical application of software engineering principles and database integration concepts.

### Problem Statement

Traditional library systems rely on manual record-keeping, which is time-consuming, error-prone, and inefficient. This project addresses these challenges by providing a digital solution that:

- **Eliminates manual paperwork** for book records and transactions
- **Reduces human error** in book tracking and user management
- **Improves efficiency** in book issuance and return processes
- **Provides real-time tracking** of book availability and user borrowing history
- **Enables quick search** and retrieval of book information

---

## 🛠️ Technology Stack

### Core Technologies
- **Java 21** - Programming language
- **Swing** - GUI framework for desktop application
- **FlatLaf 3.0** - Modern look and feel for Java Swing
- **NetBeans IDE** - Development environment

### Database & Data Management
- **In-memory storage** - ConcurrentHashMap for current implementation
- **Planned migration to MySQL** - For production deployment
- **Hibernate-ready architecture** - Model classes designed for easy database integration

### Additional Libraries
- **jBCrypt 0.4** - Password hashing (for future security enhancement)
- **LGoodDatePicker 11.2.1** - Date selection components (available for future use)

---

## ✨ Key Features Implemented

### 🔐 Authentication & User Management
- **User Login System** - Secure authentication with username/password
- **User Registration** - New user signup with validation
- **Role-based Access** - Admin and User roles
- **User Profile Management** - Full name, email, phone number

### 📚 Book Management
- **Add Books** - Register new books with title, author, ISBN, and copy count
- **Edit Books** - Update existing book information
- **Delete Books** - Remove books from the system
- **Search Books** - Find books by title or author
- **Book Availability Tracking** - Real-time availability status

### 📋 Transaction Management
- **Book Borrowing** - Issue books to users with date tracking
- **Book Returns** - Process book returns and update availability
- **Transaction History** - View complete borrowing/return records
- **Status Tracking** - Monitor BORROWED, RETURNED, and OVERDUE status

### 🎨 User Interface
- **Modern GUI** - Clean, professional interface using FlatLaf
- **Tabbed Navigation** - Organized sections for Books, Users, and Transactions
- **Menu System** - Easy access to all features
- **Responsive Design** - Adaptable to different screen sizes
- **User-friendly Forms** - Intuitive input dialogs with validation

---

## 🏗️ Architecture & Design

### Project Structure
```
LMS/
├── src/main/java/com/mycompany/lms/
│   ├── config/          # Configuration classes
│   ├── dao/             # Data Access Objects
│   ├── gui/             # Graphical User Interface
│   ├── model/           # Domain models
│   └── service/         # Business logic layer
├── lib/                 # External libraries
├── pom.xml             # Maven configuration
└── README.md           # Project documentation
```

### Design Patterns Used
- **MVC Pattern** - Separation of concerns (Model-View-Controller)
- **Service Layer Pattern** - Business logic separation
- **DAO Pattern** - Data access abstraction
- **Singleton Pattern** - Configuration management

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 21 or higher
- NetBeans IDE (recommended)
- Maven (optional, for dependency management)

### Installation Steps

1. **Clone or Download** the project
   ```bash
   git clone [repository-url]
   cd LMS
   ```

2. **Compile the Project**
   ```bash
   cd "C:\Users\andza\OneDrive\Documents\NetBeansProjects\LMS"
   javac -d "target\classes" -cp "lib\*;target\classes" src\main\java\com\mycompany\lms\gui\LMS.java
   ```

3. **Run the Application**
   ```bash
   java -cp "lib\*;target\classes" com.mycompany.lms.gui.LMS
   ```

### Default Login Credentials
- **Username:** admin
- **Password:** admin123

---

## 📊 Learning Outcomes

### Technical Skills Gained
- **Java Programming** - Advanced Java concepts and Swing GUI development
- **Software Architecture** - Understanding of layered architecture and design patterns
- **Database Design** - Data modeling and relationship management
- **API Development** - Service layer design and implementation
- **Version Control** - Project management and collaboration

### Problem-Solving Skills
- **Requirement Analysis** - Translating user needs into technical solutions
- **System Design** - Planning scalable and maintainable software
- **Debugging** - Troubleshooting and error resolution
- **Documentation** - Technical writing and project documentation

---

## 🔮 Future Enhancements

### Planned Features
- **MySQL Database Integration** - Replace in-memory storage with MySQL
- **Hibernate ORM** - Implement object-relational mapping
- **Spring Boot Migration** - Transition to Spring Boot framework
- **Web Interface** - Develop web-based frontend using Thymeleaf
- **Spring Security** - Implement advanced authentication and authorization
- **Fine Calculation** - Automated fine calculation for overdue books
- **Email Notifications** - Send due date reminders to users
- **Reporting System** - Generate reports on library usage and statistics

### Scalability Improvements
- **Multi-user Support** - Concurrent user handling
- **Data Backup** - Automated backup and recovery system
- **Audit Logging** - Track all system changes
- **Performance Optimization** - Improve response times for large datasets

---

## 📝 Project Documentation

### Key Files Reference
- **LMS.java** - Main application entry point
- **MainFrame.java** - Primary GUI window
- **LoginDialog.java** - User authentication interface
- **RegisterDialog.java** - New user registration
- **BookService.java** - Book business logic
- **UserService.java** - User management logic
- **TransactionService.java** - Transaction handling

### Configuration Files
- **pom.xml** - Maven dependencies and build configuration
- **DatabaseConfig.java** - Database configuration (for future MySQL integration)

---


## 📄 License

This project is developed for educational/skill purposes as part of academic requirements.

---

## 📞 Contact

For questions or feedback about this project, please contact:
- **Email:** [andzanimavangwa88@gmail.com]
- **GitHub:** [Andzani-Singh]

---

**Project Status:** ✅ Complete and Functional  
**Last Updated:** September 2025  
**Version:** 1.0
