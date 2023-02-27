/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import Entity.Admin;
import Entity.User;
import Manager.AdminManager;
import Manager.UserManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author phanh
 */
public class UserManagerTest {

    UserManager userManager = new UserManager();
    AdminManager adminManager = new AdminManager();
    @Test
    void test() {
        User user = userManager.getCustomer("suong", "admin");
        assertFalse(user == null);
    }
    @Test
    void test2() {
        Admin admin = adminManager.login("hau", "admin");
        assertFalse(admin == null);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
