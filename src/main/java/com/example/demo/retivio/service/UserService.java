package com.example.demo.retivio.service;



import com.example.demo.retivio.model.User;
import com.example.demo.retivio.repository.JsonUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final JsonUserRepository userRepository;

    public UserService(JsonUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user) {
        // توليد ID واسم صورة افتراضية لو مش موجودين
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId("user-" + System.currentTimeMillis());
        }
        if (user.getAvatarUrl() == null || user.getAvatarUrl().isEmpty()) {
            user.setAvatarUrl("https://ui-avatars.com/api/?name=" + user.getName() + "&background=random");
        }
        return userRepository.save(user);
    }
}