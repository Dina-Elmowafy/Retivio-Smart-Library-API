package com.example.demo.retivio.repository;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.retivio.model.SearchLog;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JsonSearchLogRepository {
    private List<SearchLog> logs = new ArrayList<>();

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = new ClassPathResource("data/search_logs.json").getInputStream();
            logs = mapper.readValue(is, new TypeReference<List<SearchLog>>() {});
        } catch (Exception e) {}
    }

    public SearchLog save(SearchLog log) {
        logs.add(log);
        return log;
    }
}
