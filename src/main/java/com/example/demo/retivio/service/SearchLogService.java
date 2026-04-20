package com.example.demo.retivio.service;


import com.example.demo.retivio.model.SearchLog;
import com.example.demo.retivio.repository.JsonSearchLogRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class SearchLogService {
    private final JsonSearchLogRepository logRepository;

    public SearchLogService(JsonSearchLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public SearchLog logSearch(SearchLog log) {
        log.setId("log-" + System.currentTimeMillis());
        log.setTimestamp(Instant.now().toString());

        String effectiveQuery = log.getQuery() != null ? log.getQuery() : log.getSearchTerm();
        log.setSearchTerm(effectiveQuery);
        log.setQuery(effectiveQuery);

        if (log.getUserId() == null || log.getUserId().isEmpty()) {
            log.setUserId("anonymous");
        }

        return logRepository.save(log);
    }
}
