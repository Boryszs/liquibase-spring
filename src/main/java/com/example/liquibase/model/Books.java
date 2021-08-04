package com.example.liquibase.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Authors> authors;

    public Books(long id, String title, Integer published, String image, String description, Boolean available) {
        this.id = id;
        this.title = title;
        this.published = published;
        this.image = image;
        this.description = description;
        this.available = available;
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Books books = (Books) o;

        if (id != books.id) return false;
        if (title != null ? !title.equals(books.title) : books.title != null) return false;
        if (published != null ? !published.equals(books.published) : books.published != null) return false;
        if (image != null ? !image.equals(books.image) : books.image != null) return false;
        if (description != null ? !description.equals(books.description) : books.description != null) return false;
        if (available != null ? !available.equals(books.available) : books.available != null) return false;
        return authors != null ? authors.equals(books.authors) : books.authors == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }

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
