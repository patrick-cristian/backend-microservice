package com.team.rambla.websitedbspringboot.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPayload {

    private String title;
    private String content;
    private String author;
    private String excerpt;
    private String image;
    private List<Long> categories;
}
