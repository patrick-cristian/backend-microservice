package com.team.rambla.websitedbspringboot.service.impl;

import com.team.rambla.websitedbspringboot.entity.Blog;
import com.team.rambla.websitedbspringboot.entity.Category;
import com.team.rambla.websitedbspringboot.payload.request.BlogPayload;
import com.team.rambla.websitedbspringboot.repository.BlogRepository;

import com.team.rambla.websitedbspringboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImplementation {

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

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

    public List<Blog> getAllBlogs(String category) {
        if (!category.equalsIgnoreCase("ALL")) {
            return blogRepository.getBlogsByCategories_Name(category);
        }
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

    public Blog addBlog(BlogPayload blog) {
        try {
            Blog blogToSave = new Blog();
            blogToSave.setAuthor(blog.getAuthor());
            blogToSave.setDate(blog.getDate());
            blogToSave.setExcerpt(blog.getExcerpt());
            blogToSave.setContent(blog.getContent());
            blogToSave.setImage(blog.getImage());
            blogToSave.setCategories(new HashSet<>(categoryRepository.findAllById(blog.getCategories())));

            blogRepository.save(blogToSave);
            return blogToSave;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
