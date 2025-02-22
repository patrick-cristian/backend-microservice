package com.team.rambla.websitedbspringboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.rambla.websitedbspringboot.entity.Blog;
import com.team.rambla.websitedbspringboot.payload.request.BlogPayload;
import com.team.rambla.websitedbspringboot.payload.response.BlogResponse;
import com.team.rambla.websitedbspringboot.service.impl.BlogServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@ControllerAdvice
public class BlogController {

    @Autowired
    private BlogServiceImplementation blogServiceImplementation;

    @PostMapping()
    ResponseEntity<?> addBlog(@RequestPart(value = "blog") String blog, @RequestPart(value = "image") MultipartFile fileImage) {

        System.out.println("BLOG FROM CONTROLLE:" + blog);
        ObjectMapper mapper = new ObjectMapper();
        BlogPayload blogPayload ;

        try {
            blogPayload = mapper.readValue(blog, BlogPayload.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("There was a problem processing your request:" + e.getMessage());
        }
        return ResponseEntity.ok(blogServiceImplementation.addBlog(blogPayload, fileImage));
    }

    @GetMapping()
    ResponseEntity<?> getAllBlogs(@RequestParam(value = "category", defaultValue = "ALL") String category) {
        List<Blog> blogs = blogServiceImplementation.getAllBlogs(category);
        List<BlogResponse> blogResponses = new ArrayList<>();

        for (Blog blog: blogs) {
            blogResponses.add(BlogResponse.builder()
                            .id(blog.getId())
                            .title(blog.getTitle())
                            .content(blog.getContent())
                            .excerpt(blog.getExcerpt())
                            .categories(blog.getCategories())
                            .author(blog.getAuthor())
                            .image(blog.getImage() != null ? "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(blog.getImage()):null)
                    .build());
        }
        return ResponseEntity.ok(blogResponses);
    }

    Blog getBlogByTitle(String title) {
        return blogServiceImplementation.getBlogByTitle(title);
    }

    List<Blog> getBlogsByAuthor(String author) {
        List<Blog> blogs = blogServiceImplementation.getBlogsByAuthor(author);
        return blogs;
    }

    @GetMapping("/details")
    ResponseEntity<?> getBlogById(@RequestParam(name = "id") Long id) {
        Blog blog = blogServiceImplementation.getBlogById(id);
        BlogResponse blogResponse = (BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .excerpt(blog.getExcerpt())
                .categories(blog.getCategories())
                .author(blog.getAuthor())
                .image(blog.getImage() != null ? "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(blog.getImage()):null)
                .build());
        return ResponseEntity.ok(blogResponse);
    }

    void deleteBlogById(Long id) {
        blogServiceImplementation.deleteBlogById(id);
    }

    void updateBlogById(Long id, Blog blog) {
        blogServiceImplementation.updateBlogById(id, blog);
    }
}
