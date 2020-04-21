package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.models.UserProfile;
import org.upgrad.repositories.UserProfileRepository;
import org.upgrad.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserServiceImp(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void addUser(String username, String password, String email, String firstName, String lastName,
                        String aboutMe, String dob, String contactNumber, String country) {
        userRepository.addUser(username, password, email, "user");
        User user = userRepository.findUserByUsername(username);
        userProfileRepository.addUserProfile(user.getId(), firstName, lastName, aboutMe, dob, contactNumber,
                country);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public String getPasswordByUsername(String username) {
        return userRepository.getPasswordByUsername(username);
    }

    @Override
    public String getRoleByUsername(String username) {
        return userRepository.getRoleByUsername(username);
    }

    @Override
    public UserProfile getUserProfile(long userId) {
        return userProfileRepository.findUserProfile(userId);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public int findUserId(String username) {
        return (int) findUserByUsername(username).getId();
    }
}
