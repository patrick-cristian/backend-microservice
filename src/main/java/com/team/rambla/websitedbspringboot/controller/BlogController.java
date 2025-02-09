package com.team.rambla.websitedbspringboot.controller;

import com.team.rambla.websitedbspringboot.entity.Blog;
import com.team.rambla.websitedbspringboot.payload.request.BlogPayload;
import com.team.rambla.websitedbspringboot.service.impl.BlogServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@ControllerAdvice
public class BlogController {

    @Autowired
    private BlogServiceImplementation blogServiceImplementation;

    @PostMapping()
    Blog addBlog(@RequestPart BlogPayload blog) {
        return blogServiceImplementation.addBlog(blog);
    }

    @GetMapping()
    ResponseEntity<?> getAllBlogs(@RequestParam(value = "category", defaultValue = "ALL") String category) {
        return ResponseEntity.ok(blogServiceImplementation.getAllBlogs(category));
    }

    Blog getBlogByTitle(String title) {
        return blogServiceImplementation.getBlogByTitle(title);
    }

    List<Blog> getBlogsByAuthor(String author) {
        List<Blog> blogs = blogServiceImplementation.getBlogsByAuthor(author);
        return blogs;
    }

    @GetMapping("/details")
    Blog getBlogById(@RequestParam(name = "id") Long id) {
        return blogServiceImplementation.getBlogById(id);
    }

    void deleteBlogById(Long id) {
        blogServiceImplementation.deleteBlogById(id);
    }

    void updateBlogById(Long id, Blog blog) {
        blogServiceImplementation.updateBlogById(id, blog);
    }
}
