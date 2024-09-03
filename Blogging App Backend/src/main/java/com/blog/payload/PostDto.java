package com.blog.payload;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {

    private Integer id;
    private String title;
    private String content;
    private String imageName;
    private Date createdDate;
    private UserDto user;
    private CategoryDto category;
}
