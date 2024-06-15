package com.team.rambla.websitedbspringboot.controller;

import com.team.rambla.websitedbspringboot.entity.Blog;
import com.team.rambla.websitedbspringboot.entity.Comment;
import com.team.rambla.websitedbspringboot.repository.BlogRepository;
import com.team.rambla.websitedbspringboot.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@ControllerAdvice
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/api/{blog_id}/comments")
    public Comment addComment(@PathVariable Long blog_id, @RequestBody Comment comment) {
        try {
            System.out.println(comment);
            comment.setTimeCreated(new Date());
            Blog blog = blogRepository.findById(blog_id).orElseThrow();
            comment.setBlog(blog);
            List<Comment> comments = blog.getComments();
            comments.add(comment);
            blog.setComments(comments);
            System.out.println("Before saving");
            commentRepository.save(comment);
            blogRepository.save(blog);
            return comment;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @GetMapping("/api/{blog_id}/comments")
    public List<Comment> getCommentByBlogId(@PathVariable Long blog_id) {
        if (blogRepository.existsById(blog_id)) {
            return commentRepository.findCommentByBlogId(blog_id);
        } else {
            return null;
        }
    }

    @DeleteMapping("/api/{blog_id}/comments")
    public void deleteCommentsByBlogId(@PathVariable Long blog_id) {
        try {
            Blog blog = blogRepository.findById(blog_id).orElseThrow();
            System.out.println("Before deleting" + blog);
            for (Comment comment : blog.getComments()) {
                System.out.println(comment);
                commentRepository.delete(comment);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @DeleteMapping("/api/{comment_id}")
    public void deleteCommentById(@PathVariable Long comment_id) {
        commentRepository.deleteById(comment_id);
    }
}
