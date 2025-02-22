package com.team.rambla.websitedbspringboot.payload.response;

import com.team.rambla.websitedbspringboot.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String excerpt;
    private String image;
    private Set<Category> categories;
}
