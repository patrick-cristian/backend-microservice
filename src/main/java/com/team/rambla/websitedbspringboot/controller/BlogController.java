package com.team.rambla.websitedbspringboot.controller;

import com.team.rambla.websitedbspringboot.entity.Blog;
import com.team.rambla.websitedbspringboot.entity.Users;
import com.team.rambla.websitedbspringboot.service.BlogServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@ControllerAdvice
public class BlogController {

    @Autowired
    private BlogServiceImplementation blogServiceImplementation;

    @PostMapping()
    Blog addBlog(@RequestBody Blog blog) {
        return blogServiceImplementation.addBlog(blog);
    }

    @GetMapping()
    List<Blog> getAllBlogs() {
        return blogServiceImplementation.getAllBlogs();
    }

    Blog getBlogByTitle(String title) {
        return blogServiceImplementation.getBlogByTitle(title);
    }

    List<Blog> getBlogsByAuthor(String author) {
        List<Blog> blogs = blogServiceImplementation.getBlogsByAuthor(author);
        return blogs;
    }

    Blog getBlogById(Long id) {
        return blogServiceImplementation.getBlogById(id);
    }

    void deleteBlogById(Long id) {
        blogServiceImplementation.deleteBlogById(id);
    }

    void updateBlogById(Long id, Blog blog) {
        blogServiceImplementation.updateBlogById(id, blog);
    }
}
