package org.upgrad.services;

import org.upgrad.models.User;
import org.upgrad.models.UserProfile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface UserService {

    void addUser(String username, String password, String email, String firstName, String lastName,
                 String aboutMe, String dob, String contactNumber, String country);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    String getPasswordByUsername(String username);
    String getRoleByUsername(String username);
    UserProfile getUserProfile(long userId);
    void deleteUser(long userId);
    List<User> getAllUsers();
    int findUserId(String username);
}
