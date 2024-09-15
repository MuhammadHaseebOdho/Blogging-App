package com.blog.service;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto getPost(Integer postId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    PostResponse getPosts(int pageNumber, int pageSize);

    List<PostDto> getPostByUser(Integer userId );

    List<PostDto> getPostByCategory(Integer categoryId);

}
