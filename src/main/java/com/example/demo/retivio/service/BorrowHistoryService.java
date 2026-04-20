package com.example.demo.retivio.service;



import com.example.demo.retivio.model.BorrowRecord;
import com.example.demo.retivio.repository.JsonBorrowHistoryRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class BorrowHistoryService {
    private final JsonBorrowHistoryRepository historyRepository;

    public BorrowHistoryService(JsonBorrowHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<BorrowRecord> getAllHistory() {
        return historyRepository.findAll();
    }

    public BorrowRecord addRecord(BorrowRecord record) {
        record.setId("borrow-" + System.currentTimeMillis());
        record.setBorrowDate(Instant.now().toString());

        if (record.getStatus() == null || record.getStatus().isEmpty()) {
            record.setStatus(record.getRating() != null ? "rated" : "active");
        }
        return historyRepository.save(record);
    }
}
