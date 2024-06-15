package com.team.rambla.websitedbspringboot.repository;

import com.team.rambla.websitedbspringboot.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findByTitle(String title);
    Boolean existsByTitle(String title);

    List<Blog> getBlogsByAuthor(String author);
}
