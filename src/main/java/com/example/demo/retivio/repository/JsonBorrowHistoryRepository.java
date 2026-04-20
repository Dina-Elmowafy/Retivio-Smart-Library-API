package com.example.demo.retivio.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.retivio.model.BorrowRecord;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JsonBorrowHistoryRepository {
    private List<BorrowRecord> history = new ArrayList<>();

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = new ClassPathResource("data/borrow_history.json").getInputStream();
            history = mapper.readValue(is, new TypeReference<List<BorrowRecord>>() {});
        } catch (Exception e) {}
    }

    public List<BorrowRecord> findAll() { return history; }
    public BorrowRecord save(BorrowRecord record) {
        history.add(record);
        return record;
    }
}