package com.team.rambla.websitedbspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header {
    private String headerName;
    private String headerMapping;
    private Boolean allowModify;
}
