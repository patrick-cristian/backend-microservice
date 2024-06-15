package com.team.rambla.websitedbspringboot.service;

import com.team.rambla.websitedbspringboot.entity.Blog;
import com.team.rambla.websitedbspringboot.repository.BlogRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImplementation {

    private final BlogRepository blogRepository;

    public void createBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void updateBlog(Blog blog) {
        if (blog != null) {
            blogRepository.save(blog);
        }
    }

    public void deleteBlog(Blog blog) {
        try {
            blogRepository.delete(blog);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsByAuthor(String author) {
        if (author != null) {
            return blogRepository.getBlogsByAuthor(author);
        } else {
            return blogRepository.findAll();
        }
    }

    public Blog getBlogByTitle(String title) {
        if (blogRepository.findByTitle(title) != null) {
            return blogRepository.findByTitle(title);
        } else {
            return null;
        }
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).get();
    }

    public void deleteBlogById(Long id) {
        blogRepository.deleteById(id);
    }

    public void updateBlogById(Long id, Blog blog) {
        try {
            blogRepository.save(blogRepository.findById(id).get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
