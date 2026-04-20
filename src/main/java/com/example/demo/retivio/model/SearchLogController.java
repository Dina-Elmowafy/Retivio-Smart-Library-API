package com.example.demo.retivio.model;

import com.example.demo.retivio.model.SearchLog;
import com.example.demo.retivio.service.SearchLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search_logs")
public class SearchLogController {
    private final SearchLogService logService;

    public SearchLogController(SearchLogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity<SearchLog> logSearch(@RequestBody SearchLog log) {
        if ((log.getSearchTerm() == null || log.getSearchTerm().isEmpty()) &&
                (log.getQuery() == null || log.getQuery().isEmpty())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(logService.logSearch(log));
    }
}