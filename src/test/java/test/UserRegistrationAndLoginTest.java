package test;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationAndLoginTest {

    @Test
    void testRegisterUser_ValidInput() {
        String input = "test@example.com\npassword\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        UserRegistrationAndLogin.registerUser(new Scanner(System.in));

        assertTrue(out.toString().contains("Registration successful."));
    }

    @Test
    void testRegisterUser_InvalidEmail() {
        String input = "invalid_email\npassword\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        UserRegistrationAndLogin.registerUser(new Scanner(System.in));

        assertTrue(out.toString().contains("Invalid email address format. Registration failed."));
    }

    @Test
    void testLoginUser_ValidCredentials() {
        UserRegistrationAndLogin.registeredUsers.put("test@example.com", "password");
        String input = "test@example.com\npassword\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        UserRegistrationAndLogin.loginUser(new Scanner(System.in));

        assertTrue(out.toString().contains("Login successful."));
    }

    @Test
    void testLoginUser_InvalidCredentials() {
        UserRegistrationAndLogin.registeredUsers.put("test@example.com", "password");
        String input = "test@example.com\nwrong_password\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        UserRegistrationAndLogin.loginUser(new Scanner(System.in));

        assertTrue(out.toString().contains("Login unsuccessful. Invalid email or password."));
    }

    @Test
    void testForgotPassword_EmailExists() {
        UserRegistrationAndLogin.registeredUsers.put("test@example.com", "password");
        String input = "test@example.com\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        UserRegistrationAndLogin.forgotPassword(new Scanner(System.in));

        assertTrue(out.toString().contains("An email with instructions for resetting your password has been sent to test@example.com"));
    }

    @Test
    void testForgotPassword_EmailNotExists() {
        String input = "nonexistent@example.com\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        UserRegistrationAndLogin.forgotPassword(new Scanner(System.in));

        assertTrue(out.toString().contains("Email not registered. Please register first."));
    }
}
