package com.example.liquibase.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "published")
    private Integer published;

    @Column(name = "image")
    private String image;

    @Lob
    @Column(name = "description", length = 100000)
    private String description;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "books")
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Authors> authors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", published=" + published +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", authors=" + authors +
                '}';
    }
}
