package com.team.rambla.websitedbspringboot.controller;

import com.team.rambla.websitedbspringboot.entity.Comment;
import com.team.rambla.websitedbspringboot.repository.BlogRepository;
import com.team.rambla.websitedbspringboot.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ControllerAdvice
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    public void addComment(Long blogId, Comment comment) {
        try {
            commentRepository.save(comment);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Comment> getCommentByBlogId(Long blogId) {
        if (blogRepository.existsById(blogId)) {
            return commentRepository.findCommentByBlogId(blogId);
        } else {
            return null;
        }
    }

    public void deleteCommentsByBlogId(Long blogId) {
        try {
            commentRepository.deleteAll();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
