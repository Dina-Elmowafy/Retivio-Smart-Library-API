package com.example.demo.retivio.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.retivio.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JsonUserRepository {
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = new ClassPathResource("data/users.json").getInputStream();
            users = mapper.readValue(is, new TypeReference<List<User>>() {});
            System.out.println(" Users loaded successfully!");
        } catch (Exception e) {
            System.out.println(" Could not load users.json");
        }
    }

    public List<User> findAll() { return users; }

    public Optional<User> findById(String id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public User save(User user) {
        users.add(user);
        return user;
    }
}