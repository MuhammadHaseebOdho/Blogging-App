package com.blog.payload;

import com.blog.entity.Post;
import lombok.Getter;
import lombok.Lombok;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDto> posts;
    private Integer pageSize;
    private Long totalPosts;
    private Integer pageNumber;
    private Integer totalPages;
    private Boolean lastPage;


}
