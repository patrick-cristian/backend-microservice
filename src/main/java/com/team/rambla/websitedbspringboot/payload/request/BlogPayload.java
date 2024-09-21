package com.team.rambla.websitedbspringboot.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPayload {

    private String title;
    private String content;
    private String author;
    private String date;
    private String excerpt;
    private String image;
    private List<Long> categories;

}
