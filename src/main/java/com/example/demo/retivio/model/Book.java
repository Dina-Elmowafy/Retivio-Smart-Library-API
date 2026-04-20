package com.example.demo.retivio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

// السطر ده سحري: بيخلي الجافا تتجاهل أي حقل زيادة في الـ JSON إحنا مش معرفينه هنا
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String category;
    private String description;
    private String coverUrl;
    private double avgRating;

    // ضفنا الـ tags عشان لو حبيتي تستخدميها بعدين في الواجهة أو في البحث
    private List<String> tags;

    public Book() {}

    // Getters and Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCoverUrl() { return coverUrl; }
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }

    public double getAvgRating() { return avgRating; }
    public void setAvgRating(double avgRating) { this.avgRating = avgRating; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}