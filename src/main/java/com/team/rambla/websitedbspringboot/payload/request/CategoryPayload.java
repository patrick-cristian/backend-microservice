package com.team.rambla.websitedbspringboot.payload.request;

import com.team.rambla.websitedbspringboot.entity.Category;
import com.team.rambla.websitedbspringboot.pojo.Header;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryPayload {
    List<Header> headers;
    List<Category> categories;
}
