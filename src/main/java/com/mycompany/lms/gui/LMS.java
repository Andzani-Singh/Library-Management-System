/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lms.gui;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.lms.model.Role;
import com.mycompany.lms.model.User;
import com.mycompany.lms.service.UserService;
import javax.swing.*;

/**
 *
 * @author andzani
 */
public class LMS {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            // Initialize with admin user if not exists
            initializeAdminUser();
            
            // Show login dialog
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.setVisible(true);
            
            if (loginDialog.isAuthenticated()) {
                User user = loginDialog.getAuthenticatedUser();
                MainFrame mainFrame = new MainFrame(user);
                mainFrame.setVisible(true);
            } else {
                System.exit(0);
            }
        });
    }
    
    private static void initializeAdminUser() {
        UserService userService = new UserService();
        if (userService.getUserByUsername("admin") == null) {
            User admin = new User("admin", "admin123", "System Administrator", "admin@lms.com", "1234567890");
            Role adminRole = new Role("ADMIN", "Administrator with full access");
            admin.setRole(adminRole);
            userService.saveUser(admin);
        }
    }
}
