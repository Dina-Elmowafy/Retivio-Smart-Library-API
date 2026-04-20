package com.example.demo.retivio.model;


public class SearchLog {
    private String id;
    private String userId;
    private String searchTerm;
    private String query;
    private String timestamp;

    public SearchLog() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}