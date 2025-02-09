package com.team.rambla.websitedbspringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_blog")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String author;
    private String date;
    private String excerpt;

    @Lob
    private byte[] image;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tags> tags;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne(cascade = CascadeType.ALL)
    private Users user;

    @ManyToMany
    @JoinTable(
            name = "blog_category",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> categories;

    @Override
    public String toString() {
        return "title: " + title + ", content: " + content + ", author: " + author + ", date: " + date + ", comments: " + comments;
    }
}
